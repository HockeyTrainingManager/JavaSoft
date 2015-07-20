package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import classobject.ParserGame;

public class ParserNHL {

	Element table;
	ArrayList<String> lstLiens = new ArrayList<String>();
	public static String Endroit = System.getProperty("user.dir") + "/TMP/";
	
	public void init()
	{
		if (!netIsAvailable())
		{
			System.err.println("No internet connection");
			return;
		}
		File dir = new File(Endroit);
		if (!dir.exists()) {
		    System.out.println("creating directory: " + Endroit);
		    boolean result = false;

		    try{
		        dir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        se.printStackTrace();
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		    get("");
		} else {
			if (dir.listFiles().length > 0) {
				File lg = dir.listFiles()[dir.listFiles().length - 1];
				System.err.println("" + lg.getName().replace(".xml", ""));
				get("" + lg.getName().replace(".xml", ""));
			} else {
				get("");
			}
		}
		
		
	}
	
	public void get(String lastSynch)
	{
		String html;
		try {
			html = getUrlSource("http://blackhawks.nhl.com/club/gamelog.htm");
		} catch (IOException e) {
			html = "";
			e.printStackTrace();
		}
				Document doc = Jsoup.parse(html);
				Elements links = doc.select("table");
				for (Element element : links) {
					if (element.attr("class").equals("data"))
					{
						table = element;
						break;
					}
				}
				
				for (Element e : table.select("tr"))
				{
					if (e.attr("class").equals("rwEven") || e.attr("class").equals("rwOdd"))
					{
						for (Element e2 : e.select("td"))
						{
							if (e2.attr("class").equals("leftAlignedColumn"))
							{
								Element e3 = e2.select("a").first();
								lstLiens.add(e3.attr("href").replace("http://blackhawks.nhl.com/gamecenter/en/recap?id=", ""));
							}
						}
					}
				}
				
				//changer recap dans url par boxscore
				String test = "";
				
				for(int i = 0; i < lstLiens.size(); i++)
				{
					if (lstLiens.get(i).equals(lastSynch))
						break;
					
					try {
						ParserGame.updateGame("" + lstLiens.get(i), "");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				System.err.println("synch finish");
				
	}

	
	private static String getUrlSource(String url) throws IOException {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
	
	
	private static boolean netIsAvailable() {                                                                                                                                                                                                 
	    try {                                                                                                                                                                                                                                 
	        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } catch (MalformedURLException e) {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } catch (IOException e) {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}    
}

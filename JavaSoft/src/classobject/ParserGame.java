package classobject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ParserGame {
	public static void updateGame(String name, String date) throws IOException
	{
		Game g = new Game();
		g.date = date;
		String html = "http://blackhawks.nhl.com/gamecenter/en/boxscore?id=" + name;
		Document doc = Jsoup.connect(html).get();
		
	    Elements bodies = doc.select("div.contentPad");
	    Element stat = bodies.get(2);
	    int goodChild = 1;
	    isWin(doc.getElementById("periodByPeriod"), g);
	   
	    for(int i = 0; i < stat.children().size(); i++)
	    {
	    	if (stat.child(i).children().size() >= 1 && stat.child(i).tagName().equals("div"))
	    	{	
	    		System.out.println(i + "  " + stat.child(i).tagName()  + "  " + stat.child(i).child(1).attr("href"));
	    		if (stat.child(i).child(1).attr("href").equals("http://blackhawks.nhl.com"))
	    		{	
	    			goodChild = (i+1);
	    			break;
	    		}
	    	}
	        
	    }
	    for(int i = 0; i < stat.child(goodChild).getElementsByClass("statsValues").size(); i++)
	    {
	    	Player p = new Player();
	    	Element e = stat.child(goodChild).getElementsByClass("statsValues").get(i);
	    	p.id = e.child(1).child(0).attr("href").replace("http://blackhawks.nhl.com/club/player.htm?id=", "");
	    	p.name = e.child(1).child(0).text();
	    	p.position = e.child(2).text();
	    	p.but = e.child(3).text();
	    	p.assist = e.child(4).text();
	    	p.point = e.child(5).text();
	    	p.plusmoins = e.child(6).text();
	    	p.timepenality = e.child(7).text();
	    	p.shoot = e.child(8).text();
	    	p.hit = e.child(9).text();
	    	p.blockshoot = e.child(10).text();
	    	p.cadeau = e.child(11).text();
	    	p.takeaway = e.child(12).text();
	    	p.faceoff = e.child(13).text().replace("%", "").replace("-", "NA");
	    	p.gametime = e.child(16).text();
	    	g.players.add(p);
	    }
	    
	    try {
			createXML(g, name);
		} catch (TransformerConfigurationException
				| ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createXML(Game g, String nom) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, Exception
	{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = docBuilder.newDocument();
		org.w3c.dom.Element rootElement = doc.createElement("game");
		doc.appendChild(rootElement);
		
		org.w3c.dom.Element date = doc.createElement("date");
		date.appendChild(doc.createTextNode(""));
		rootElement.appendChild(date);

		
		org.w3c.dom.Element win = doc.createElement("win");
		win.appendChild(doc.createTextNode(g.win));
		rootElement.appendChild(win);
		
		org.w3c.dom.Element score = doc.createElement("score");
		score.appendChild(doc.createTextNode(g.score));
		rootElement.appendChild(score);
		
		org.w3c.dom.Element adv = doc.createElement("adv");
		adv.appendChild(doc.createTextNode(g.adv));
		rootElement.appendChild(adv);
		
		org.w3c.dom.Element players = doc.createElement("players");
		rootElement.appendChild(players);
		
		for (Player p : g.players) {
			org.w3c.dom.Element player = doc.createElement("player");
			players.appendChild(player);
			
			Field[] fields = Player.class.getFields();
			for (Field field : fields) {
				org.w3c.dom.Element variable = doc.createElement(field.getName());
				variable.appendChild(doc.createTextNode(ReflectUtils.getValueOf(p, field.getName()).toString()));
				player.appendChild(variable);
				System.out.println(field.getName());
			}
		}
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("/Users/robinpauquet/desktop/projet_annuel/" + nom + ".xml"));
		transformer.transform(source, result);
		 
		System.out.println("File saved!");
	}
	
	public static void isWin(Element div, Game g)
	{
		Elements home = div.getElementsByClass("homeTeam");
		Elements away = div.getElementsByClass("awayTeam");

		int score1 = Integer.parseInt(home.get(0).getElementsByClass("total").get(0).text());
		int score2 = Integer.parseInt(away.get(0).getElementsByClass("total").get(0).text());
		String name1 = home.get(0).getElementsByClass("left").get(0).text();
		String name2 = away.get(0).getElementsByClass("left").get(0).text();
		
		if (name1.contains("CHI"))
		{	
			if (score1 >= score2)
				score(true, g, score1 + "-" + score2, name2);
			else
				score(false, g, score1 + "-" + score2, name2);
		} else {
			if (score2 >= score1)
				score(true, g, score2 + "-" + score1, name1);
			else
				score(false, g, score2 + "-" + score1, name1);
		}
	}
	
	public static void score(boolean win, Game g, String score, String Adv)
	{
		if (win)
		{
			g.win = "Y";
		} else {
			g.win = "N";
		}
		
		g.score = score;
		g.adv = Adv;
	}
}

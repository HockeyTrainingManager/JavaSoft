package plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;

import classobject.plugTexte;



public class PluginLoader {

	private ArrayList classPlugins;
	
	public PluginLoader()
	{
		this.classPlugins = new ArrayList();
		System.err.println("" + System.getProperty("user.dir"));
		File dir = new File(System.getProperty("user.dir"));
		File[] files = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".jar");
		    }
		});
		
		for (int i = 0; i <  files.length; i++) {
			if (files[i].getName().contains("plugin"))
			{
				try {
					addFileToPlugin(files[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public PluginBase[] getPlugin() throws Exception
	{
		PluginBase[] tmpPlugins = new PluginBase[this.classPlugins.size()];
		
		for(int index = 0 ; index < tmpPlugins.length; index ++ ){
			
			//On cr�er une nouvelle instance de l'objet contenu dans la liste gr�ce � newInstance() 
			//et on le cast en StringPlugins. Vu que la classe impl�mente StringPlugins, le cast est toujours correct
			tmpPlugins[index] = (PluginBase)((Class)this.classPlugins.get(index)).newInstance() ;
			
		}
		
		return tmpPlugins;
	}
	
	public ArrayList getPluginArray() throws Exception
	{
		ArrayList tmp = new ArrayList();
		tmp.add((PluginBase)((Class)plugTexte.class).newInstance());
		for(int index = 0 ; index < classPlugins.size(); index ++ ){
			
			//On cr�er une nouvelle instance de l'objet contenu dans la liste gr�ce � newInstance() 
			//et on le cast en StringPlugins. Vu que la classe impl�mente StringPlugins, le cast est toujours correct
			tmp.add((PluginBase)((Class)this.classPlugins.get(index)).newInstance()) ;
			
		}
		
		return tmp;
	}
	
	public void addFileToPlugin(File f)  throws Exception
	{
		URLClassLoader loader;
		String tmp = "";
		Enumeration enumeration;
		Class tmpClass = null;
		URL u = f.toURL();
		//On cr�er un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
		loader = new URLClassLoader(new URL[] {u}); 
		
		//On charge le jar en m�moire
		JarFile jar = new JarFile(f.getAbsolutePath());
		
		//On r�cup�re le contenu du jar
		enumeration = jar.entries();
		
		while(enumeration.hasMoreElements()){
			
			tmp = enumeration.nextElement().toString();

			//On v�rifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
			if(tmp.length() > 6 && tmp.substring(tmp.length()-6).compareTo(".class") == 0) {
				
				tmp = tmp.substring(0,tmp.length()-6);
				tmp = tmp.replaceAll("/",".");
				
				tmpClass = Class.forName(tmp ,true,loader);
				
				for(int i = 0 ; i < tmpClass.getInterfaces().length; i ++ ){
					
					//Une classe ne doit pas appartenir � deux cat�gories de plugins diff�rents. 
					//Si tel est le cas on ne la place que dans la cat�gorie de la premi�re interface correct
					//trouv�e
					if(tmpClass.getInterfaces()[i].getName().toString().equals("plugin.PluginBase") ) {
						this.classPlugins.add(tmpClass);
					}
				}
				
			}
		}
	}
}

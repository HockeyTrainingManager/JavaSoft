import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import classobject.Game;
import classobject.Player;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Utils {

	
	public static void Last2GamesToClass(File folder, Game g1, Game g2)
	{
		int size = folder.listFiles().length;
		File game1 = folder.listFiles()[size - 1];
		File game2 = folder.listFiles()[size - 2];

		g1 = FileToGame(game1);
		g2 = FileToGame(game2);
	}
	
	public static Game LastGame(File folder)
	{
		int size = folder.listFiles().length;
		File game1 = folder.listFiles()[size - 1];
		return FileToGame(game1);
	}
	
	public static Game AnteLastGame(File folder)
	{
		int size = folder.listFiles().length;
		File game1 = folder.listFiles()[size - 2];
		return FileToGame(game1);
	}
	
	public static Game FileToGame(File game)
	{
		Game retour = new Game();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(game);
			doc.getDocumentElement().normalize();
			retour.win = doc.getElementsByTagName("win").item(0).getTextContent();
			retour.adv = doc.getElementsByTagName("adv").item(0).getTextContent();
			retour.date = doc.getElementsByTagName("date").item(0).getTextContent();
			retour.score = doc.getElementsByTagName("score").item(0).getTextContent();
			NodeList nList = doc.getElementsByTagName("player");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Player p = new Player();
					p.id = eElement.getElementsByTagName("id").item(0).getTextContent();
					p.name = eElement.getElementsByTagName("name").item(0).getTextContent();
					p.position = eElement.getElementsByTagName("position").item(0).getTextContent();
					p.but = eElement.getElementsByTagName("but").item(0).getTextContent();
					p.assist = eElement.getElementsByTagName("assist").item(0).getTextContent();
					p.point = eElement.getElementsByTagName("point").item(0).getTextContent();
					p.plusmoins = eElement.getElementsByTagName("plusmoins").item(0).getTextContent();
					p.timepenality = eElement.getElementsByTagName("timepenality").item(0).getTextContent();
					p.shoot = eElement.getElementsByTagName("shoot").item(0).getTextContent();
					p.hit = eElement.getElementsByTagName("hit").item(0).getTextContent();
					p.blockshoot = eElement.getElementsByTagName("blockshoot").item(0).getTextContent();
					p.cadeau = eElement.getElementsByTagName("cadeau").item(0).getTextContent();
					p.takeaway = eElement.getElementsByTagName("takeaway").item(0).getTextContent();
					p.faceoff = eElement.getElementsByTagName("faceoff").item(0).getTextContent();
					p.gametime = eElement.getElementsByTagName("gametime").item(0).getTextContent();
					retour.players.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retour;
	}
	
	public static void listFilesForFolder(File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	            System.out.println(fileEntry.getName());
	        }
	    }
	}
	

	
	public static int getForme(String id, Game g1, Game g2)
	{
		int retour = 0;
		Player p1 = g1.getPlayerById(id);
		Player p2 = g2.getPlayerById(id);

		//BUT
		retour += CompareInt(p1.but, p2.but);
		
		//ASSIST
		retour += CompareInt(p1.assist, p2.assist);
		
		//PLUS OU MOINS
		retour += CompareInt(p1.plusmoins, p2.plusmoins);
		
		//PENALITY
		retour += CompareInt(p1.timepenality, p2.timepenality);
		
		//shoot
		retour += CompareInt(p1.shoot, p2.shoot);
		
		//blockshoot
		retour += CompareInt(p1.blockshoot, p2.blockshoot);
		
		//faceoff
		retour += ComparePourcent(p1.faceoff, p2.faceoff);
		
		
		return retour;
	}
	
	public static int CompareInt(String s1, String s2)
	{
		int i1 = Integer.parseInt(s1);
		int i2 = Integer.parseInt(s2);
		
		if (i1 > i2)
			return 1;
		else if (i1 == i2)
			return 0;
		else
			return -1;
	}
	
	public static int ComparePourcent(String s1, String s2)
	{
		if (s1.equals("NA") || s2.equals("NA"))
			return 0;
		
		int i1 = Integer.parseInt(s1);
		int i2 = Integer.parseInt(s2);
		
		if (i1 > i2)
			return 1;
		else if (i1 == i2)
			return 0;
		else
			return -1;
	}
	
	public static boolean exerciceShoot(Game g1, Game g2)
	{

		double ratio1 = g1.getAllBut() / g1.getAllShoot();
		double ratio2 = g2.getAllBut() / g2.getAllShoot();
		
		if ((ratio2 * 0.80) >= ratio1)
			return true;
		
		return false;
	}

}

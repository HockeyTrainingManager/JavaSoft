package classobject;

import java.util.ArrayList;


public class TrainningCalc {
	
	
	
	public static ArrayList<String> getTrainningPlayer(Player p1, Player p2)
	{
		ArrayList<String> lstTrainning = new ArrayList<String>();
		if (getForme(p1, p2) <= 0)
		{
			if (exerciceShoot(p1, p2))
				lstTrainning.add("SHOOT");
			
			if (exerciceDeff(p1, p2))
				lstTrainning.add("DEFF");
			
			if (exerciceSoloCage(p1, p2))
				lstTrainning.add("PENO");
			
			if (exerciceFaceOff(p1, p2))
				lstTrainning.add("FACEOFF");
		}
		
		return lstTrainning;
		
	}
	
	public static boolean exerciceFaceOff(Player p1, Player p2) {
		if (p1.faceoff.equals("NA") || p2.faceoff.equals("NA"))
			return false;
		
		int i1 = Integer.parseInt(p1.faceoff);
		int i2 = Integer.parseInt(p2.faceoff);
		
		if (i1 < 50)
			return true;
		
		if (i1 < i2)
			return true;
		
		return false;
	}

	public static boolean exerciceShoot(Player p1, Player p2)
	{

		double ratio1 = ratio(p1.but, p1.shoot);
		double ratio2 = ratio(p2.but, p2.shoot);
		
		if ((ratio2 * 0.80) >= ratio1)
			return true;
		
		return false;
	}
	
	
	
	public static boolean exerciceDeff(Player p1, Player p2)
	{
		if (getInt(p1.plusmoins) > 0)
		{
			return false;
		} 
		
		if (CompareInt(p1.blockshoot, p2.blockshoot) > 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean exerciceSoloCage(Player p1, Player p2)
	{
		
		double ratio1 = ratio(p1.but, p1.takeaway);
		
		if (ratio1 < 0.5)
			return true;
		
		return false;
	}
	
	public static int getInt(String s)
	{
		return Integer.parseInt(s);
	}
	
	public static int getForme(Player p1, Player p2)
	{
		int retour = 0;

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
	
	public static double ratio(String s1, String s2)
	{
		int i1 = Integer.parseInt(s1);
		int i2 = Integer.parseInt(s2);
		if (i2 == 0)
			return 0;
		return i1/i2;
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
	
}

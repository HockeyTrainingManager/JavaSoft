package classobject;

import java.util.ArrayList;


public class Game {
	public String date = "";
	public String score = "";
	public String adv = "";
	public String win = "";
	
	public boolean isWin()
	{
		if (win.equals("Y"))
			return true;
		else
			return false;
	}
	
	public String getResult()
	{
		if (isWin())
			return "Victoire";
		else
			return "Défaite";
	}
	
	public ArrayList<Player> players = new ArrayList<Player>();
	
	public Player getPlayerById(String id)
	{
		Player p = new Player();
		
		for(Player player : players) {
			if (player.id.equals(id))
			{
				p = player;
				break;
			}
		}
		
		return p;
	}
	
	public int getAllBut()
	{
		int retour = 0;
		
		for (Player player : players) {
			int but = Integer.parseInt(player.but);
			retour += but;
		}
		
		return retour;
	}
	
	public int getAllShoot()
	{
		int retour = 0;
		
		for (Player player : players) {
			int shoot = Integer.parseInt(player.shoot);
			retour += shoot;
		}
		
		return retour;
	}

	
	
	
}

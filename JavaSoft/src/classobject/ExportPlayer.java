package classobject;

import java.util.ArrayList;


public class ExportPlayer
{
	private String playerName = "";
	private String playerNum = "";
	private String playerPoste = "";
	private ArrayList<ExportExercice> exercices = new ArrayList<ExportExercice>();
	
	public String getPlayerNum() {
		return playerNum;
	}
	public void setPlayerNum(String playerNum) {
		this.playerNum = playerNum;
	}
	public String getPlayerPoste() {
		return playerPoste;
	}
	public void setPlayerPoste(String playerPoste) {
		this.playerPoste = playerPoste;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public ArrayList<ExportExercice> getExercices() {
		return exercices;
	}
	public void setExercices(ArrayList<ExportExercice> exercices) {
		this.exercices = exercices;
	}
	
	
}
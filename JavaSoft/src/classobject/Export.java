package classobject;
import java.util.ArrayList;


public class Export {
	
	private String teamName = "";
	private String date = "";
	private ArrayList<ExportPlayer> lstExport = new ArrayList<ExportPlayer>();
	
	
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<ExportPlayer> getLstExport() {
		return lstExport;
	}

	public void setLstExport(ArrayList<ExportPlayer> lstExport) {
		this.lstExport = lstExport;
	}

	
	
	
}	



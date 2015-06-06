
package classobject;

public class Player {
	public String id = "0";
	public String name = "0";
	public String position = "0";
	public String but = "0";
	public String assist = "0";
	public String point = "0";
	public String plusmoins = "0";
	public String timepenality = "0";
	public String shoot = "0";
	public String hit = "0";
	public String blockshoot = "0";
	public String cadeau = "0";
	public String takeaway = "0";
	public String faceoff = "0";
	public String gametime = "0";
	
	@Override
	public String toString() {
		String s = "";
		s += " id:" + this.id;
		s += " name:" + this.name;
		s += " position:" +  this.position;
		return s;
	}
	
}

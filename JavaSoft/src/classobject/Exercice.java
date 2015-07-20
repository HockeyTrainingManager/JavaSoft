package classobject;

public class Exercice {
	
	
	
	
	public static ExportExercice getExerciceByType(String type)
	{
		ExportExercice ee = new ExportExercice();
		if (type.equals("SHOOT")){
			ee.setExerciceName("One timer");
			ee.setExerciceDescr("Tir sur cage lancé, sur passe d'un coéquipier sans contrôle");
		} else if (type.equals("DEFF")) {
			ee.setExerciceName("Seul contre tous");
			ee.setExerciceDescr("Sur 3 rounds, en 1vs1, 2vs1, et 3vs1, si l'attaquant a trop de mal, un joueur viens l'aider");
		} else if (type.equals("PENO")) {
			ee.setExerciceName("Penalty sur goal");
			ee.setExerciceDescr("Alterner shoot, dribble coté bouclier/mitaine sur un goal");
		} else if (type.equals("FACEOFF")) {
			ee.setExerciceName("Le perdant Pompe");
			ee.setExerciceDescr("Serie de 10 faces off, chaque raté engendre 10 pompes");
		}
		return ee;
	}
	
	
}

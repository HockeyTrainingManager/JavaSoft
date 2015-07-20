package classobject;

import plugin.PluginBase;

public class plugTexte implements PluginBase{

	String RETOUR_LIGNE = "\n";
	
	@Override
	public String getLibelle() {
		
		return "Export Texte";
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String actionOnString(Export e) {
		String export = "";

		export += "---------------------------------------------" + RETOUR_LIGNE;
		export += "Team: " + e.getTeamName() + RETOUR_LIGNE;
		export += "Date: " + e.getDate() + RETOUR_LIGNE;
		export += "---------------------------------------------" + RETOUR_LIGNE;
		
		export += "Liste des joueurs: " + RETOUR_LIGNE;
		for(int i = 0; i < e.getLstExport().size(); i++)
		{
			export += "---------------------------------------------" + RETOUR_LIGNE;
			export += "" + (i+1) + ". " + e.getLstExport().get(i).getPlayerName() + " (" + e.getLstExport().get(i).getPlayerPoste() + ")"  + RETOUR_LIGNE ;
			export += "----------------------" + RETOUR_LIGNE;
			for (int j = 0; j < e.getLstExport().get(i).getExercices().size(); j++)
			{
				ExportExercice ee = e.getLstExport().get(i).getExercices().get(j);
				export += (j+1) + ". " + e.getLstExport().get(i).getExercices().get(j).getExerciceName() + RETOUR_LIGNE;
				export += "" + e.getLstExport().get(i).getExercices().get(j).getExerciceName() + RETOUR_LIGNE;
				export += "----------------------" + RETOUR_LIGNE;
			}
		}
		export += "---------------------------------------------" + RETOUR_LIGNE;
		
		
		return export;
	}

}

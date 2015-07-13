import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;


public class Export {
	public final static int TEXT = 1;
	public final static int HTML = 2;
	private int TYPE = 1;
	private String RETOUR_LIGNE = "\n";
	private String path = "";
	private String user = "";
	
	public void export()
	{
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  saveIt(file);
		}
	}
	
	public void saveIt(File file)
	{
		ExportInfo ei = Utils.getExportInfo(path, user);
		try {
			FileWriter fw = new FileWriter(file, false);
			switch (TYPE) {
			case HTML:
				fw.write(getHtml(ei));
				break;
			case TEXT:
				fw.write(getTexte(ei));
				break;
			default:
				fw.write("coucou export");
				break;
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getHtml(ExportInfo ei)
	{
		String retour = "";

		retour += "<html>" + RETOUR_LIGNE;
		
		retour += "<h1>Nom Repertoire: " + ei.getNameDirectory() + "</h1><br />" + RETOUR_LIGNE;
		retour += "<i>Path: " + ei.getPathDirectory() + "</i><br />" + RETOUR_LIGNE;
		retour += "<b>Creer par: " + ei.getUser() + "</b><br /><br /><br /><br />" + RETOUR_LIGNE;
		

		retour += "<i>Nombre de fichiers</i>: " + ei.getNbFile() + "<br />" + RETOUR_LIGNE;
		retour += "<i>Nombre de repertoire</i>: " + ei.getNbDir() + "<br />" + RETOUR_LIGNE;
		retour += "<i>Taille Disque totale</i>: " + ei.getTailleDisc() + "<br />" + RETOUR_LIGNE;
		retour += "<i>Nombre de fichiers en lecture</i>: " + ei.getNbFichiersLecture() + "<br />" + RETOUR_LIGNE;
		retour += "<i>Nombre de fichiers en écriture</i>: " + ei.getNbFichiersEcriture() + "<br />" + RETOUR_LIGNE;
		retour += "<i>Nombre de fichiers en lecture & écriture</i>: " + ei.getNbFichiersBoth() + "<br />" + RETOUR_LIGNE;
		
		retour += "</html>" + RETOUR_LIGNE;
		
		return retour;
	}
	
	private String getTexte(ExportInfo ei)
	{
		String retour = "";

		retour += "<info>" + RETOUR_LIGNE;
		
		retour += "<name>" + ei.getNameDirectory() + "</name>" + RETOUR_LIGNE;
		retour += "<path>" + ei.getPathDirectory() + "</path>" + RETOUR_LIGNE;
		retour += "<create_by>" + ei.getUser() + "</create_by>" + RETOUR_LIGNE;
		retour += "<nb_file>" + ei.getNbFile() + "</nb_file>" + RETOUR_LIGNE;
		retour += "<nb_dir>" + ei.getNbDir() + "</nb_dir>" + RETOUR_LIGNE;
		retour += "<taille_disque>" + ei.getTailleDisc() + "</taille_disque>" + RETOUR_LIGNE;
		retour += "<nb_file_read>" + ei.getNbFichiersLecture() + "</nb_file_read>" + RETOUR_LIGNE;
		retour += "<nb_file_write>" + ei.getNbFichiersEcriture() + "</nb_file_write>" + RETOUR_LIGNE;
		retour += "<nb_file_read_and_write>" + ei.getNbFichiersBoth() + "</nb_file_read_and_write>" + RETOUR_LIGNE;
		
		retour += "</info>" + RETOUR_LIGNE;
		
		return retour;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int tYPE) {
		TYPE = tYPE;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}

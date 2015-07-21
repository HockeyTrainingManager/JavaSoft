package classobject;

public class ExportExercice
{
	private String exerciceName = "";
	private String exerciceDescr = "";
	
	@MyAnnotation(exportName="Name")
	public String getExerciceName() {
		return exerciceName;
	}
	public void setExerciceName(String exerciceName) {
		this.exerciceName = exerciceName;
	}
	@MyAnnotation(exportName="Description")
	public String getExerciceDescr() {
		return exerciceDescr;
	}
	public void setExerciceDescr(String exerciceDescr) {
		this.exerciceDescr = exerciceDescr;
	}
	
	
}

package plugin;

import classobject.Export;

public interface PluginBase {

	/**
	 * Obtient le libell� � afficher dans les menu ou autre pour le plugins
	 * @return Le libell� sous forme de String. Ce libell� doit �tre clair et compr�hensible facilement 
	 */
	public String getLibelle();
	
	/**
	 * Obtient la cat�gorie du plugins. Cette cat�gorie est celle dans laquelle le menu du plugins sera ajout� une fois charg�
	 * @return
	 */
	public int getCategorie();
	
	/**
	 * On obtient le resultat a sauvegarder (csv, html, pdf en fonction du plugin)
	 * @return
	 */
	public String actionOnString(Export e);
}

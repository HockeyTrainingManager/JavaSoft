import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import parser.ParserNHL;
import plugin.PluginBase;
import plugin.PluginLoader;
import classobject.Exercice;
import classobject.Export;
import classobject.ExportExercice;
import classobject.ExportPlayer;
import classobject.Game;
import classobject.Player;
import classobject.TrainningCalc;


public class MainActivity extends JFrame{
	
	BorderLayout border;
	JButton btn2;
	JPanel panelContent;
	JPanel panelMenu;
	JList<String> list;
	ArrayList plugin;
	Export export = new Export();
	static Game lastGame;
	static Game antelastGame;
	
	public MainActivity() {

		PluginLoader loader = new PluginLoader();
        try {
			plugin = loader.getPluginArray();
		} catch (Exception e1) {
			plugin = new ArrayList();
			e1.printStackTrace();
		}
        
       ParserNHL parser = new ParserNHL();
       parser.init();
        
		this.setTitle("Hockey Trainning Manager");
	    getContentPane().setLayout(new BorderLayout());
	    panelMenu = new JPanel();
	    panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.PAGE_AXIS));;
	    JButton btnShowLast = new JButton("Last");
	    btnShowLast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowLastMatch();
			}
		});
	    JButton btnPlayer = new JButton("Player");
	    btnPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowPlayer();
			}
		});
	    JButton btnTeam = new JButton("Team");
	    btnTeam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowTeam();
			}
		});
	    JButton btnSetting = new JButton("A propos");
	    btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSetting();
			}
		});

	    
	    
	    
	    panelMenu.add(btnShowLast);
	    panelMenu.add(btnPlayer);
	   // panelMenu.add(btnTeam);
	    panelMenu.add(btnSetting);

	    panelContent = new JPanel();
	    panelContent.add(new JLabel("Bienvenue"));
	    getContentPane().add(panelMenu, BorderLayout.WEST);
	    getContentPane().add(panelContent, BorderLayout.CENTER);
	    
		this.setSize(600, 400);
	    
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    
	    
	    this.setVisible(true);
	    
	    
	}
	 public static void main(String[] args) {
	     
	        new MainActivity();
	        
	        

	 }
	 
	 public void ShowLastMatch()
	 {
		 Game lastGame = Utils.LastGame(new File(ParserNHL.Endroit));
		 
		 getContentPane().remove(1);
		 panelContent = new JPanel();
		 panelContent.setLayout(new BorderLayout());
		 panelContent.add(new JLabel("CHI vs " + lastGame.adv), BorderLayout.NORTH);
		 
		
		 JPanel panelcenter = new JPanel(new GridLayout(3, 0));
		 panelcenter.add(new JLabel("Nb. but de l'équipe: " + lastGame.getAllBut()));
		 panelcenter.add(new JLabel("Résultat du match: " + lastGame.getResult()));
		 panelcenter.add(new JLabel("Score: " + lastGame.score ));
		 panelContent.add(panelcenter, BorderLayout.CENTER);
		 
		 getContentPane().add(panelContent, BorderLayout.CENTER);
		 getContentPane().validate();
	 }
	 
	 public void ShowPlayer()
	 {
		 getContentPane().remove(1);
		 panelContent = new JPanel();
		 panelContent.setLayout(new GridLayout(0, 1));
		 Container contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());

		 ListModel<String> listModel = createListModel();
			ListSelectionDocument listSelectionDocument = new ListSelectionDocument();

			list = new JList<String>();
			list.setCellRenderer(new CheckboxListCellRenderer<String>());
			list.setModel(listModel);
			list.setSelectionModel(new DefaultListSelectionModel() {
			    @Override
			    public void setSelectionInterval(int index0, int index1) {
			        if(super.isSelectedIndex(index0)) {
			            super.removeSelectionInterval(index0, index1);
			        }
			        else {
			            super.addSelectionInterval(index0, index1);
			        }
			    }
			});
			//list.addListSelectionListener(listSelectionDocument);

			JPanel exportPanel = new JPanel();
			for (int i = 0; i < plugin.size(); i++) {
				final int idPlugin = i;
				JButton btn = new JButton(((PluginBase)this.plugin.get(i)).getLibelle());
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						exportPlayer(idPlugin);
					}
				});
				exportPanel.add(btn);
			}

			JScrollPane listScroller = new JScrollPane(list);
			contentPane.add(listScroller, BorderLayout.CENTER);
			contentPane.add(exportPanel, BorderLayout.NORTH);

		 
		 
		 panelContent.add(contentPane);
		 getContentPane().add(panelContent, BorderLayout.CENTER);
		 getContentPane().validate();
	 }
	 
	 private void exportPlayer(int i)
	 {
		 //TODO: recup list player & envoyer
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 Date date = new Date();
		 export.setDate(dateFormat.format(date));
		 export.setTeamName("Chicago");
		
		for(int j = 0; j < list.getSelectedIndices().length; j++)
		{
			Player p = lastGame.players.get(list.getSelectedIndices()[j]);
			Player p2 = antelastGame.getPlayerById(p.id);
			if (p2 != null && !p2.id.equals(""))
			{
				ExportPlayer ep = new ExportPlayer();
				ep.setPlayerName(p.name);
				//ep.setPlayerNum(p.id);
				ep.setPlayerPoste(p.position);
				for (String s : TrainningCalc.getTrainningPlayer(p, p2))
				{
					ep.getExercices().add(Exercice.getExerciceByType(s));
				}
				export.getLstExport().add(ep);
			}
			
		}
	
		ExportUtils eu = new ExportUtils();
			
		eu.export(((PluginBase)plugin.get(i)).actionOnString(export));
	 }
	 
	 private static DefaultListModel<String> createListModel() {
			DefaultListModel<String> listModel = new DefaultListModel<String>();
			lastGame = Utils.LastGame(new File(ParserNHL.Endroit));
			antelastGame = Utils.AnteLastGame(new File(ParserNHL.Endroit));
			for(int i = 0; i < lastGame.players.size(); i++)
			{
				listModel.addElement("" + lastGame.players.get(i).name);
			}

			return listModel;
		}

	 
	 public void ShowTeam()
	 {
		 System.err.printf("Team");
	 }
	 
	 public void ShowSetting()
	 {
		 getContentPane().remove(1);
		 panelContent = new JPanel();
		 panelContent.setLayout(new GridLayout(0, 1));
		 String apropos = "<html><h1>Hockey Trainning Manager </h1><br />"
		 		+ "Conçu par :<br />"
		 		+ "Robin PAUQUET<br />"
		 		+ "Bruno VACQUEREL<br />"
		 		+ "Charly MANAI<br />"
		 		+ "<br />"
		 		+ "<h2>Mais où est Charly ?</h2>"
		 		+ "Il était une fois un jeune, mais pas trop, chevalier dans la contrée SEIG quand il décida de partir à l'aventure.<br />"
		 		+ "Il parti ainsi sur son fidèle destrier 'Baième de oublevé' et entra par inadvertance sur le territoire de SANA, un terrible dragon de la contrée NAVA. <br />"
		 		+ "Charly n'avais point envie de combattre ce terrible animal, il avait passer l'âge de faire des efforts."
		 		+ "Mais ce ne fut pas cette aveu qui persuada le dragon, <span style=\"color:red\">SANA ne s</span>e laissa par importuner plus longtemps par cette étranger qui fini en barbecue à dragon.<br />"
		 		+ "FIN"
		 		+ "</html>";
		 panelContent.add(new JLabel(apropos));
		 getContentPane().add(panelContent, BorderLayout.CENTER);
		 getContentPane().validate();
	 }
	 
	 
	 
	 
}

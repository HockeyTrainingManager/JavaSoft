import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Image;

import javafx.scene.layout.Border;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import parser.ParserNHL;
import classobject.Game;


public class MainActivity extends JFrame{
	
	BorderLayout border;
	JButton btn2;
	JPanel panelContent;
	JPanel panelMenu;
	
	public MainActivity() {

        
		this.setTitle("Ma première fenêtre Java");
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
	    JButton btnSetting = new JButton("Setting");
	    btnSetting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShowSetting();
			}
		});

	    
	    
	    
	    panelMenu.add(btnShowLast);
	    panelMenu.add(btnPlayer);
	    panelMenu.add(btnTeam);
	    panelMenu.add(btnSetting);

	    panelContent = new JPanel();
	    panelContent.add(new JButton("test"));
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
		 Game lastGame = Utils.LastGame(new File("/Users/robinpauquet/Desktop/projet_annuel/"));
		 
		 getContentPane().remove(1);
		 panelContent = new JPanel();
		 panelContent.setLayout(new BorderLayout());
		 panelContent.add(new JLabel("CHI vs " + lastGame.adv), BorderLayout.NORTH);
		 
		
		 JPanel panelcenter = new JPanel();
		 ImageIcon icon = new ImageIcon(new ImageIcon("/Users/robinpauquet/Desktop/projet_annuel/but_good_extra_small.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		 JLabel j = new JLabel("" + lastGame.getAllShoot(), icon, SwingConstants.LEFT);
		 panelcenter.add(j);
		 panelcenter.add(new JLabel("But: " + lastGame.getAllBut()));
		 panelcenter.add(new JLabel("Win:" + lastGame.win));
		 panelcenter.add(new JLabel("Score:" + lastGame.score));
		 panelContent.add(panelcenter, BorderLayout.CENTER);
		 
		 getContentPane().add(panelContent, BorderLayout.CENTER);
		 getContentPane().validate();
	 }
	 
	 public void ShowPlayer()
	 {
		 getContentPane().remove(1);
		 panelContent = new JPanel();
		 panelContent.add(new JButton("coucou"));
		 getContentPane().add(panelContent, BorderLayout.CENTER);
		 getContentPane().validate();
	 }
	 
	 public void ShowTeam()
	 {
		 System.err.printf("Team");
	 }
	 
	 public void ShowSetting()
	 {
		 System.err.printf("Setting");
	 }
	 
	 
	 
	 
}

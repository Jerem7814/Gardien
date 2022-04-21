package gui;

import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import config.GameConfiguration;

import map.Map;

import process.GameBuilder;
import process.MobileElementManager;





/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class MainGUI extends JFrame implements Runnable, ActionListener {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(700, GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private GameDisplay dashboard;
	
	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();
	private JFrame frame;
	
	
	private JLabel buttonzone= new JLabel("Zone de commande :");
	private JLabel vide= new JLabel("");
	private JLabel vide1= new JLabel("");
	private JLabel vide2= new JLabel("");
	private JLabel vide3= new JLabel("");
	private JLabel vide4= new JLabel("");
	private JLabel vide5= new JLabel("");
	private JLabel vide6= new JLabel("");
	private JLabel vide7= new JLabel("");
	
	private JLabel infozone= new JLabel("Zone d'Info :");
	private JLabel empty= new JLabel("");
	private JLabel empty1= new JLabel("");
	private JLabel empty2= new JLabel("");
	private JLabel empty3= new JLabel("");
	private JLabel empty4= new JLabel("");
	private JLabel empty5= new JLabel("");
	

	
	private JLabel infoT= new JLabel("Tour actuel :");
	private JLabel infoG= new JLabel("Nb Gardiens :");
	private JLabel infoA= new JLabel("Argent récolté:");
	private JLabel infoI= new JLabel("Nb Items :");
	
	
	private JLabel iT = new JLabel();
	private JLabel iG = new JLabel();
	private JLabel iA = new JLabel();
	private JLabel iI = new JLabel();
	
	
	private JLabel buttonC= new JLabel(" Reprendre le jeu :");
	private JLabel buttonS= new JLabel(" Bouton pour stop :");
	
	private JLabel buttondou= new JLabel(" Double la vitesse :");
	private JLabel buttondemi= new JLabel(" Divise la vitesse par 2 :");
	
	private JLabel buttonG= new JLabel(" Ajouter un Gardien :");
	private JLabel itemG= new JLabel("Ajouter un item :");
	
	private JButton stopG;
	private JButton startG;
	private JButton dou;
	private JButton demi;
	private JButton gardP;
	private JButton itemP;
	private JButton Reset;
	private int etoiles;
	private ImageIcon star;
	
	public MainGUI(String title) {
		super(title);
		init();
	}

	private void init() {
		
		stopG  = new JButton ("Stop");
		stopG.addActionListener(this);
		
		startG  = new JButton ("Start");
		startG.addActionListener(this);
		
		gardP = new JButton ("Gardien");
		gardP.addActionListener(this);
		
		itemP = new JButton ("item");
		itemP.addActionListener(this);
		
		dou = new JButton ("x2");
		dou.addActionListener(this);
		
		demi = new JButton ("x0.5");
		demi.addActionListener(this);
		
		iT = new JLabel();
		
		
		iG = new JLabel();
		
		
		iA = new JLabel();
		
		
		iI = new JLabel();
		
		
		


		
		
		
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		panOuest.setLayout(new GridLayout(5,3));
		panOuest.setPreferredSize(new Dimension(270,300));
		panOuest.setBackground(new Color(230, 230, 250));
		panOuest.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		
		
		
		panOuest.add(empty);
		empty.setBackground(new Color(102, 102, 153));
		
		panOuest.add(infozone);
		infozone.setBackground(new Color(102, 102, 153));
		
		panOuest.add(empty1);
		
		panOuest.add(infoT);
		panOuest.add(empty2);
		panOuest.add(iT);
		
		panOuest.add(infoG);
		panOuest.add(empty3);
		panOuest.add(iG);
		
		
		panOuest.add(infoA);
		panOuest.add(empty4);
		panOuest.add(iA);
		
		panOuest.add(infoI);
		panOuest.add(empty5);
		panOuest.add(iI);
		

		
		
		panEst.setLayout(new GridLayout(7,3));
		panEst.setPreferredSize(new Dimension(400,300));
		panEst.setBackground(new Color(230, 230, 250));
		panEst.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		
		panEst.add(vide);
		panEst.add(buttonzone);
		panEst.add(vide1);
		
		panEst.add(buttonC);
		panEst.add(vide2);
		panEst.add(startG);
		
		panEst.add(buttonS);
		panEst.add(vide3);
		panEst.add(stopG);
		
		panEst.add(buttondou);
		panEst.add(vide4);
		panEst.add(dou);
		
		panEst.add(buttondemi);
		panEst.add(vide5);
		panEst.add(demi);

		panEst.add(buttonG);
		panEst.add(vide6);
		panEst.add(gardP);
		
		panEst.add(itemG);
		panEst.add(vide7);
		panEst.add(itemP);
		
		
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		dashboard = new GameDisplay(map, manager);


		dashboard.setPreferredSize(preferredSize);
		dashboard.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		contentPane.add(panOuest, BorderLayout.WEST);
		contentPane.add(dashboard, BorderLayout.CENTER);
		contentPane.add(panEst, BorderLayout.EAST);
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
		GameConfiguration.playmusic();

	}
	

	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource() ;
		if (Button==stopG) {
			
			GameDisplay.stop = false;
		}
		
		if (Button==startG) {
			
			GameDisplay.stop = true;
		}
		
		if (Button==dou) {
			
			GameConfiguration.GAME_SPEED = GameConfiguration.GAME_SPEED/2 ;
		}
		if (Button==demi) {
			
			if(GameConfiguration.GAME_SPEED>100) {
				
			GameConfiguration.GAME_SPEED = GameConfiguration.GAME_SPEED*2 ;
			
			}
		}
		
		if (Button==gardP) {
			manager.generateGuardian();
		}
		
		if (Button==itemP) {
			manager.generateItem();
		}
	}

	@Override
	public void run() {
		while (true) {
			while(!manager.intrudervoid()) {
				if((GameDisplay.stop==true)) {
					try {
						Thread.sleep(GameConfiguration.GAME_SPEED);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
					iT.setText(String.valueOf(manager.getRound()));
					iG.setText(String.valueOf(manager.getGuardians().size()));
					iA.setText(String.valueOf(manager.getTotalmoney()));
					iI.setText(String.valueOf(manager.getItems().size()));
						
						
					
					manager.nextRound();
					dashboard.repaint();
					}
				else {
					System.out.println("Ok");
				}
			}
			manager.getIncendie().stop();
			GameConfiguration.stopmusic();
            initend();
    		frame.setVisible(true);
			System.out.println("Fin de partie");
			break;
		}

	}
	private void initend() {
		if (Integer.valueOf(manager.getExitmoney())>=1500 && Integer.valueOf(manager.getArrestations())<=GameBuilder.nbrI/2) {
			etoiles = 4;
			star = new ImageIcon("ressources/images/4star.png");
		}
		else if (Integer.valueOf(manager.getExitmoney())>=800 && Integer.valueOf(manager.getArrestations())<=GameBuilder.nbrI/2) {
			etoiles = 3;
			star = new ImageIcon("ressources/images/3star.png");
		}
		else if (Integer.valueOf(manager.getExitmoney())>=400 && Integer.valueOf(manager.getArrestations())<=GameBuilder.nbrI-1) {
			etoiles = 2;
			star = new ImageIcon("ressources/images/2star.png");
		}
		else if (Integer.valueOf(manager.getExitmoney())>100 && Integer.valueOf(manager.getArrestations())<=GameBuilder.nbrI-1) {
			etoiles = 1;
			star = new ImageIcon("ressources/images/1star.png");
			
		}
		else {
			etoiles = 0;
			star = new ImageIcon("ressources/images/0star.png");


		}
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setBounds(100, 100, 751, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JLabel Title = new JLabel("Bravo ! Vous avez obtenu " + etoiles + " étoiles !");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(200, 27, 356, 52);
		Title.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(Title);
		
		JLabel nbEtoile = new JLabel (star);
		nbEtoile.setBounds(0, 74, 723, 241);
		panel.add(nbEtoile);
		nbEtoile.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel Or = new JLabel("Or amassé : "+ manager.getExitmoney());
		Or.setBounds(269, 307, 168, 26);
		panel.add(Or);

		
		JLabel lblArrestations = new JLabel("Arrestations :" + manager.getArrestations() );
		lblArrestations.setBounds(269, 355, 223, 26);
		panel.add(lblArrestations);
		
		JLabel lblDuelsRemports = new JLabel("Duels remportés par l'intrus : " + manager.getDuels());
		lblDuelsRemports.setBounds(269, 404, 223, 26);
		panel.add(lblDuelsRemports);
		
		Reset = new JButton("Reset");
		Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				dispose();
				GameConfiguration.GAME_SPEED=700;
				OpenGame og = new OpenGame();
				og.build();
			}
		});
		Reset.setBounds(253, 496, 251, 46);
		panel.add(Reset);
	}
	
	
	
	
}
	


	
	


	





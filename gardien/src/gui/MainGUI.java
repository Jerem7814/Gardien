package gui;

import java.awt.BorderLayout;


import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.SwingConstants;
import java.awt.Font;


import java.awt.Color;

import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;


import javax.swing.JPanel;


import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import config.GameConfiguration;

import map.Map;

import process.GameBuilder;
import process.MobileElementManager;





/**
* initialize the window with panels that allows us to see informations, the dashboard running and to make some change on the game running
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
*/
public class MainGUI extends JFrame implements Runnable, ActionListener {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(700, GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private GameDisplay dashboard;
	
	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();
	private JPanel buttonSA = new JPanel();
	private JPanel buttonCA = new JPanel();
	private JPanel buttonDA = new JPanel();
	private JPanel buttonFA = new JPanel();
	private JPanel buttonGA = new JPanel();
	private JPanel buttonHA = new JPanel();
	private JFrame frame;
	
	
	private JLabel buttonzone= new JLabel("<html><body><font color='#DABDE0' size='5'>Zone de commande :</body></html>");
	private JLabel vide= new JLabel("");
	private JLabel vide1= new JLabel("");
	private JLabel vide2= new JLabel("");
	private JLabel vide3= new JLabel("");
	private JLabel vide4= new JLabel("");
	private JLabel vide5= new JLabel("");
	private JLabel vide6= new JLabel("");
	private JLabel vide7= new JLabel("");
	
	private JLabel videA= new JLabel("");
	private JLabel videA1= new JLabel("");
	private JLabel videA2= new JLabel("");
	private JLabel videA3= new JLabel("");
	private JLabel videA4= new JLabel("");
	private JLabel videA5= new JLabel("");
	private JLabel videA6= new JLabel("");
	private JLabel videA7= new JLabel("");
	private JLabel videA8= new JLabel("");
	private JLabel videA9= new JLabel("");
	private JLabel videA10= new JLabel("");
	
	
	
	private JLabel infozone= new JLabel("<html><body><font color='yellow' size='5'>Zone d'Info :</body></html>");
	private JLabel empty= new JLabel("");
	private JLabel empty1= new JLabel("");
	private JLabel empty2= new JLabel("");
	private JLabel empty3= new JLabel("");
	private JLabel empty4= new JLabel("");
	private JLabel empty5= new JLabel("");
	
	
	private JLabel empty6= new JLabel("");
	private JLabel empty7= new JLabel("");
	private JLabel empty8= new JLabel("");
	private JLabel empty9= new JLabel("");
	private JLabel empty10= new JLabel("");
	private JLabel empty11= new JLabel("");
	private JLabel empty12= new JLabel("");
	private JLabel empty13= new JLabel("");
	private JLabel empty14= new JLabel("");
	private JLabel empty15= new JLabel("");
	private JLabel empty16= new JLabel("");
	
	
	
	
	
	private JLabel infoT= new JLabel("<html><body><font color='yellow' size='4'>Tour actuel :</body></html>");
	
	private JLabel infoG= new JLabel("<html><body><font color='yellow' size='4'>Nombre Gardien :</body></html>");
	
	private JLabel infoA= new JLabel("<html><body><font color='yellow' size='4'>Argent r&eacute;colt&eacute; :</body></html>");
	private JLabel infoI= new JLabel("<html><body><font color='yellow' size='4'>Nombre d'Item :</body></html>");
	

	private JLabel iT = new JLabel();
	private JLabel iG = new JLabel();
	private JLabel iA = new JLabel();
	private JLabel iI = new JLabel();
	
	
	private JLabel buttonC= new JLabel("<html><body><font color='#DABDE0' size='4'>Reprendre le jeu :</body></html>");
	private JLabel buttonS= new JLabel("<html><body><font color='#DABDE0' size='4'>Bouton pour stop :</body></html>");
	
	private JLabel buttondou= new JLabel("<html><body><font color='#DABDE0' size='4'>Double la vitesse :</body></html>");
	private JLabel buttondemi= new JLabel("<html><body><font color='#DABDE0' size='4'>Divise la vitesse par 2 :</body></html>");
	
	private JLabel buttonG= new JLabel("<html><body><font color='#DABDE0' size='4'>Ajouter un Gardien :</body></html>");
	private JLabel itemG= new JLabel("<html><body><font color='#DABDE0' size='4'>Ajouter un item :</body></html>");
	
	private JButton stopG;
	private JButton startG;
	private JButton dou;
	private JButton demi;
	private JButton gardP;
	private JButton itemP;
	private JButton Reset;
	private int etoiles;
	private ImageIcon star;
	
	/**
	 * initialize the game with a title
	 * @param title the name of the window
	 */
	public MainGUI(String title) {
		super(title);
		init();
	}

		
	/**
	 * Initialize a window with the dashboard and panels
	 */
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
		
		
		


		
		
		
		JLabel background=new JLabel(new ImageIcon("ressources/images/MainGui.jpg"));
		add(background);
		background.setLayout(new BorderLayout());
		
		panOuest.setLayout(new GridLayout(9,3));
		panOuest.setPreferredSize(new Dimension(270,300));
		//panOuest.setBackground(new Color(230, 230, 250));
		panOuest.setOpaque(false);
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
		
		panOuest.add(empty6);
		panOuest.add(empty16);
		panOuest.add(empty7);
		
		panOuest.add(empty8);
		panOuest.add(empty9);
		panOuest.add(empty10);
		
		panOuest.add(empty11);
		panOuest.add(empty12);
		panOuest.add(empty13);
		
		panOuest.add(empty11);
		panOuest.add(empty14);
		panOuest.add(empty15);
		
		
		panEst.setLayout(new GridLayout(7,3));
		panEst.setPreferredSize(new Dimension(400,300));
		//panEst.setBackground(new Color(230, 230, 250));
		panEst.setOpaque(false);
		panEst.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		
		panEst.add(vide);
		panEst.add(buttonzone);
		panEst.add(vide1);
		
		
		
		
		buttonCA.setLayout(new GridLayout(3,1));
		buttonCA.setBackground(new Color(230, 230, 250));
		buttonCA.setOpaque(false);
		buttonCA.add(videA);
		buttonCA.add(startG);
		buttonCA.add(videA1);
		
		panEst.add(buttonC);
		panEst.add(vide2);
		panEst.add(buttonCA);
		
	
		buttonSA.setLayout(new GridLayout(3,1));
		buttonSA.setBackground(new Color(230, 230, 250));
		buttonSA.setOpaque(false);
		buttonSA.add(videA2);
		buttonSA.add(stopG);
		buttonSA.add(videA3);
		
		panEst.add(buttonS);
		panEst.add(vide3);
		panEst.add(buttonSA);
		
		
		
		
		buttonDA.setLayout(new GridLayout(3,1));
		buttonDA.setBackground(new Color(230, 230, 250));
		buttonDA.setOpaque(false);
		buttonDA.add(videA4);
		buttonDA.add(dou);
		buttonDA.add(videA5);
		
		panEst.add(buttondou);
		panEst.add(vide4);
		panEst.add(buttonDA);
		
		
		
		buttonFA.setLayout(new GridLayout(3,1));
		buttonFA.setBackground(new Color(230, 230, 250));
		buttonFA.setOpaque(false);
		buttonFA.add(videA5);
		buttonFA.add(demi);
		buttonFA.add(videA6);
		
		panEst.add(buttondemi);
		panEst.add(vide5);
		panEst.add(buttonFA);

		
		
		
		buttonGA.setLayout(new GridLayout(3,1));
		buttonGA.setBackground(new Color(230, 230, 250));
		buttonGA.setOpaque(false);
		buttonGA.add(videA7);
		buttonGA.add(gardP);
		buttonGA.add(videA8);
		
		panEst.add(buttonG);
		panEst.add(vide6);
		panEst.add(buttonGA);
		
		
		
		
		buttonHA.setLayout(new GridLayout(3,1));
		buttonHA.setBackground(new Color(230, 230, 250));
		buttonHA.setOpaque(false);
		buttonHA.add(videA9);
		buttonHA.add(itemP);
		buttonHA.add(videA10);
		
		panEst.add(itemG);
		panEst.add(vide7);
		panEst.add(buttonHA);

		
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		dashboard = new GameDisplay(map, manager);


		dashboard.setPreferredSize(preferredSize);
		dashboard.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		background.add(panOuest, BorderLayout.WEST);
		background.add(dashboard, BorderLayout.CENTER);
		background.add(panEst, BorderLayout.EAST);
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
		setLocationRelativeTo(null);
		GameConfiguration.playmusic();

	}
	
	
	/**
	 * Different action which will happen after clicking on buttons such as making the dashboard stop running, making it faster, slower, adding guardians or items
	 * @param e An action event
	 */
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
	/**
	 * run the dashboard unless the requirements are not valid
	 */
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
					iT.setForeground(Color.YELLOW);
					iG.setText(String.valueOf(manager.getGuardians().size()));
					iG.setForeground(Color.YELLOW);
					iA.setText(String.valueOf(manager.getTotalmoney()));
					iA.setForeground(Color.YELLOW);
					iI.setText(String.valueOf(manager.getItems().size()));
					iI.setForeground(Color.YELLOW);
						

						
					
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
	/**
	 * create an end window that summarize our achievements
	 */
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
		JLabel backgroundEnd=new JLabel(new ImageIcon("ressources/images/endGame.jpg"));
		add(backgroundEnd);
		backgroundEnd.setLayout(new BorderLayout());
		frame = new JFrame();
		frame.setBounds(100, 100, 751, 699);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(backgroundEnd, BorderLayout.CENTER);
		backgroundEnd.setLayout(null);
		JLabel Title = new JLabel("<html><body><font color='white' size='5'>Bravo ! Vous avez obtenu " + etoiles + " &eacute;toiles !</body></html>");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(200, 27, 356, 52);
		Title.setFont(new Font("Tahoma", Font.PLAIN, 17));
		backgroundEnd.add(Title);
		
		JLabel nbEtoile = new JLabel (star);
		nbEtoile.setBounds(0, 74, 723, 241);
		backgroundEnd.add(nbEtoile);
		nbEtoile.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDuelsRemports = new JLabel("<html><body><font color='white' size='3'>Duels remport&eacute;s par l'intrus : " + manager.getDuels() + "</body></html>");
		lblDuelsRemports.setBounds(269, 307, 223, 26);
		backgroundEnd.add(lblDuelsRemports);

		
		JLabel Or = new JLabel("<html><body><font color='white' size='3'>Or amass&eacute; : "+ manager.getExitmoney() + "</body></html>");
		Or.setBounds(269, 355, 168, 26);
		backgroundEnd.add(Or);
		
		JLabel lblArrestations = new JLabel("<html><body><font color='white' size='3'>Arrestations : " + manager.getArrestations() + "</body></html>" );
		lblArrestations.setBounds(269, 404, 223, 26);
		backgroundEnd.add(lblArrestations);
		
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
		backgroundEnd.add(Reset);
	}
	
	
	
	
}
	


	
	


	





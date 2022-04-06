package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import config.GameConfiguration;
import gui.MainGUIgame.KeyControls;
import gui.MainGUIgame.MouseControls;
import map.Block;
import map.Map;
import process.GameBuilder;
import process.GameBuilderGame;
import process.MobileElementManager;
import process.MobileElementManagerGame;


/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class MainGUIgame extends JFrame implements Runnable {
	private boolean deplace=false;
	
	private MainGUIgame instance=this;

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(700, GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManagerGame manager;

	private GameDisplayGame dashboard;
	
	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();
	private JFrame frame;

	
	
	
	public static JLabel buttonzone= new JLabel("Zone de commande :");
	public JLabel vide= new JLabel("");
	public static JLabel vide1= new JLabel("");
	public static JLabel vide2= new JLabel("");
	public static JLabel vide3= new JLabel("");
	public static JLabel vide4= new JLabel("");
	public static JLabel vide5= new JLabel("");
	public static JLabel vide6= new JLabel("");
	public static JLabel vide7= new JLabel("");
	
	public static JLabel infozone= new JLabel("Zone d'Info :");
	public static JLabel empty= new JLabel("");
	public static JLabel empty1= new JLabel("");
	public static JLabel empty2= new JLabel("");
	public static JLabel empty3= new JLabel("");
	public static JLabel empty4= new JLabel("");
	public static JLabel empty5= new JLabel("");
	
	public static JLabel infoStat= new JLabel("Zone d'Info :");
	
	public static JLabel empty6= new JLabel("");
	public static JLabel empty7= new JLabel("");
	public static JLabel empty8= new JLabel("");
	public static JLabel empty9= new JLabel("");
	public static JLabel empty10= new JLabel("");
	public static JLabel empty11= new JLabel("");
	
	public static JLabel infoT= new JLabel("Tour  :");
	public static JLabel infoG= new JLabel("Gardiens :");
	public static JLabel infoA= new JLabel("Argent actuel/sortis:");
	public static JLabel infoI= new JLabel("Items :");
	
	public static JLabel infoT2= new JLabel("Leurres dispo :");
	public static JLabel infoG2= new JLabel("Capes dispo :");
	public static JLabel infoA2= new JLabel("Tours leurres :");
	public static JLabel infoI2= new JLabel("Tours cape :");


	
	public static JLabel infoGV= new JLabel("Vision :");
	public static JLabel infoGA= new JLabel("Agilite :");
	public static JLabel infoGP= new JLabel("Precision :");
	
	public static JLabel foGV= new JLabel("Vision :");
	public static JLabel foGA= new JLabel("Agilite :");
	public static JLabel foGP= new JLabel("Precision :");
	
	public static JLabel iT = new JLabel();
	public static JLabel iG = new JLabel();
	public static JLabel iA = new JLabel();
	public static JLabel iI = new JLabel();
	
	public static JLabel iT2 = new JLabel();
	public static JLabel iG2 = new JLabel();
	public static JLabel iA2 = new JLabel();
	public static JLabel iI2 = new JLabel();
	
	public JButton Reset;
	private int etoiles;
	public ImageIcon star;
	

	


	public MainGUIgame(String title) {
		super(title);
		init();
	}

	private void init() {
		iT = new JLabel();
		
		
		iG = new JLabel();
		
		
		iA = new JLabel();
		
		
		iI = new JLabel();
		
		iT2 = new JLabel();
		
		
		iG2 = new JLabel();
		
		
		iA2 = new JLabel();
		
		
		iI2 = new JLabel();
		
		

		foGV = new JLabel();
		
		foGA = new JLabel();
		
		foGP = new JLabel();
		
		
		
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		panOuest.setLayout(new GridLayout(9,3));
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
		
		panOuest.add(empty6);
		panOuest.add(infoStat);
		panOuest.add(empty7);
		
		panOuest.add(infoGV);
		panOuest.add(empty8);
		panOuest.add(foGV);
		
		panOuest.add(infoGA);
		panOuest.add(empty9);
		panOuest.add(foGA);
		
		panOuest.add(infoGP);
		panOuest.add(empty10);
		panOuest.add(foGP);
		
		panEst.setLayout(new GridLayout(4,2));
		panEst.setPreferredSize(new Dimension(270,300));
		panEst.setBackground(new Color(230, 230, 250));
		panEst.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		 
		panEst.add(infoT2);
		panEst.add(vide1);
		panEst.add(iT2);
		
		panEst.add(infoG2);
		panEst.add(vide2);
		panEst.add(iG2);
		
		panEst.add(infoA2);
		panEst.add(vide3);
		panEst.add(iA2);
		
		
		panEst.add(infoI2);
		panEst.add(vide4);
		panEst.add(iI2);
		
		

		

		
		
		

		KeyControls keyControls = new KeyControls();
		JTextField textField = new JTextField();
		textField.addKeyListener(keyControls);
		contentPane.add(textField, BorderLayout.SOUTH);

		map = GameBuilder.buildMap();
		manager = GameBuilderGame.buildInitMobile(map);
		dashboard = new GameDisplayGame(map, manager);
		

		

		MouseControls mouseControls = new MouseControls();
		dashboard.addMouseListener(mouseControls);


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

	@Override
	public void run() {
			if(!manager.intrudervoid()&&deplace) {


				iT.setText(String.valueOf(manager.getRound()));
				iG.setText(String.valueOf(manager.getGuardians().size()));
				iA.setText(String.valueOf(manager.getTotalmoney()+"/"+manager.getExitmoney()));
				iI.setText(String.valueOf(manager.getItems().size()));
				
				iT2.setText(String.valueOf(manager.getIntruders().getItems().get("Lure").getNbre()));
				iG2.setText(String.valueOf(manager.getIntruders().getItems().get("Invisibility cloak").getNbre()));
				if(manager.getMlure()!=null) {
					iA2.setText(String.valueOf(manager.getCountlure()));
				}
				else {
					iA2.setText("");
				}
				if(manager.getIntruders().isTransparent()) {
					iI2.setText(String.valueOf(manager.getCountcloak()));
				}
				else {
					iI2.setText("");
				}

				
				foGV.setText(String.valueOf(manager.getIntruders().getVision()));
				foGA.setText(String.valueOf(manager.getIntruders().getAgility()));
				foGP.setText(String.valueOf(manager.getIntruders().getDodge()));
				
				
				manager.nextRound();

				dashboard.repaint();
				if(manager.intrudervoid()) {
					initend();
		    		frame.setVisible(true);
					manager.getIncendie().stop();
					GameConfiguration.stopmusic();

				}

				
				deplace=false;
			}
			 
		
	}
	private void initend() {
		if (Integer.valueOf(manager.getExitmoney())>=1500 && Integer.valueOf(manager.getDuels())>=0) {
			etoiles = 4;
			star = new ImageIcon("ressources/images/4star.png");
		}
			else if (Integer.valueOf(manager.getExitmoney())>=800 && Integer.valueOf(manager.getDuels())>=0) {
				etoiles = 3;
				star = new ImageIcon("ressources/images/3star.png");
			}
			else if (Integer.valueOf(manager.getExitmoney())>=400 && Integer.valueOf(manager.getDuels())>=0) {
				etoiles = 2;
				star = new ImageIcon("ressources/images/2star.png");
			}
			else if (Integer.valueOf(manager.getExitmoney())>100 && Integer.valueOf(manager.getDuels())>=0) {
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
			}
		});
		Reset.setBounds(253, 496, 251, 46);
		panel.add(Reset);
	}

	public class KeyControls implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			char keyChar = event.getKeyChar();
			switch (keyChar) {

			case 'q':
				manager.uselure();
				break;
			case 'd':
				manager.usecloak();
				break;
			default:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	public class MouseControls implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!manager.intrudervoid()) {
				int x = e.getX();
				int y = e.getY();
	
				Block deplacement = dashboard.getIntruderPosition(x, y);
				List<Block> vision=manager.getIntruders().getVisionzone();
				Block block1=new Block(manager.getIntruders().getPosition().getLine()-manager.getIntruders().getVision(),manager.getIntruders().getPosition().getColumn());
				System.out.println("block4"+block1);

				Block block2=new Block(manager.getIntruders().getPosition().getLine()+manager.getIntruders().getVision(),manager.getIntruders().getPosition().getColumn());
				System.out.println("block4"+block2);

				Block block3=new Block(manager.getIntruders().getPosition().getLine(),manager.getIntruders().getPosition().getColumn()-manager.getIntruders().getVision());
				System.out.println("block4"+block3);

				Block block4=new Block(manager.getIntruders().getPosition().getLine(),manager.getIntruders().getPosition().getColumn()+manager.getIntruders().getVision());
				System.out.println("block4"+block4);
				List<Block> deplacementblock=new ArrayList<Block>();

				for(Block blo:vision) {
					if(!blo.equals(block4)&&!blo.equals(block3)&&!blo.equals(block2)&&!blo.equals(block1)) {
						deplacementblock.add(blo);
					}

					
				}

				for(Block ivision:deplacementblock) {
					if(ivision.equals(deplacement)) {
						manager.deplacement(deplacement);
						deplace=true;
						Thread chronoThread = new Thread(instance);
						chronoThread.start();

					}
				}
				manager.getIntruders().setVisionzone();

				}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}


}

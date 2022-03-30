package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManagerGame manager;

	private GameDisplayGame dashboard;
	
	private JPanel panEst = new JPanel();
	private JPanel panOuest = new JPanel();
	
	
	
	public static JLabel buttonzone= new JLabel("Zone de commande :");
	public static JLabel vide= new JLabel("");
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
	
	public static JLabel infoT= new JLabel("Tour actuel :");
	public static JLabel infoG= new JLabel("Nb Gardien :");
	public static JLabel infoA= new JLabel("Argent r�colt�:");
	public static JLabel infoI= new JLabel("Nombre d'Item :");
	
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
	
	

	


	public MainGUIgame(String title) {
		super(title);
		init();
	}

	private void init() {
		iT = new JLabel();
		
		
		iG = new JLabel();
		
		
		iA = new JLabel();
		
		
		iI = new JLabel();
		
		
		

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
		while (true) {
			if(!manager.intrudervoid()&&deplace) {
				System.out.println(deplace);

				System.out.println("testettt");

				try {
					Thread.sleep(GameConfiguration.GAME_SPEED);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
				iT.setText(String.valueOf(manager.getRound()));
				iG.setText(String.valueOf(manager.getGuardians().size()));
				iA.setText(String.valueOf(manager.getTotalmoney()));
				iI.setText(String.valueOf(manager.getItems().size()));
				
				foGV.setText(String.valueOf(manager.getIntruders().getVision()));
				foGA.setText(String.valueOf(manager.getIntruders().getAgility()));
				foGP.setText(String.valueOf(manager.getIntruders().getDodge()));

				manager.nextRound();
				dashboard.repaint();
				
				deplace=false;
			}
			else {
				//nouvelle fenetre 
			}
		}
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

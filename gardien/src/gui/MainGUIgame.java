package gui;

import java.awt.BorderLayout;


import java.awt.Color;
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

import map.Block;
import map.Map;
import process.GameBuilder;
import process.GameBuilderGame;
import process.MobileElementManagerGame;


/**
* initialize the window with panels that allows us to see informations, the dashboard running and to make some change on the game running
* @version 14.0.1
* @author jeremybureau
* @author quentinvilley
* @author abdallahelballadi
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

	
	
	
	private JLabel vide1= new JLabel("");
	private JLabel vide2= new JLabel("");
	private JLabel vide3= new JLabel("");
	private JLabel vide4= new JLabel("");

	
	private JLabel infozone= new JLabel("<html><body><font color='yellow' size='5'>Zone d'Info :</body></html>");
	private JLabel empty= new JLabel("");
	private JLabel empty1= new JLabel("");
	private JLabel empty2= new JLabel("");
	private JLabel empty3= new JLabel("");
	private JLabel empty4= new JLabel("");
	private JLabel empty5= new JLabel("");
	
	private JLabel infoStat= new JLabel("<html><body><font color='white' size='5'>Zone de Stat :");
	
	private JLabel empty6= new JLabel("");
	private JLabel empty7= new JLabel("");
	private JLabel empty8= new JLabel("");
	private JLabel empty9= new JLabel("");
	private JLabel empty10= new JLabel("");
	
	private JLabel infoT= new JLabel("<html><body><font color='yellow' size='4'>Tour  :</body></html>");
	private JLabel infoG= new JLabel("<html><body><font color='yellow' size='4'>Gardiens :</body></html>");
	private JLabel infoA= new JLabel("<html><body><font color='yellow' size='4'>Argent actuel/sortis:</body></html>");
	private JLabel infoI= new JLabel("<html><body><font color='yellow' size='4'>Items :</body></html>");
	
	private JLabel infoT2= new JLabel("<html><body><font color='#DABDE0' size='4'>Leurres dispo :</body></html>");
	private JLabel infoG2= new JLabel("<html><body><font color='#DABDE0' size='4'>Capes dispo :</body></html>");
	private JLabel infoA2= new JLabel("<html><body><font color='#DABDE0' size='4'>Tours leurres :</body></html>");
	private JLabel infoI2= new JLabel("<html><body><font color='#DABDE0' size='4'>Tours cape :</body></html>");


	
	private JLabel infoGV= new JLabel("<html><body><font color='white' size='4'>Vision :</body></html>");
	private JLabel infoGA= new JLabel("<html><body><font color='white' size='4'>Agilite :</body></html>");
	private JLabel infoGP= new JLabel("<html><body><font color='white' size='4'>Precision :</body></html>");
	
	private JLabel foGV= new JLabel("<html><body><font color='white' size='4'>Vision </body></html>:");
	private JLabel foGA= new JLabel("<html><body><font color='white' size='4'>Agilite :</body></html>");
	private JLabel foGP= new JLabel("<html><body><font color='white' size='4'>Precision :</body></html>");
	
	private JLabel iT = new JLabel();
	private JLabel iG = new JLabel();
	private JLabel iA = new JLabel();
	private JLabel iI = new JLabel();
	
	private JLabel iT2 = new JLabel();
	private JLabel iG2 = new JLabel();
	private JLabel iA2 = new JLabel();
	private JLabel iI2 = new JLabel();
	
	private JButton Reset;
	private int etoiles;
	private ImageIcon star;
	

	


	public MainGUIgame(String title) {
		super(title);
		init();
	}

	private void init() {
		iT = new JLabel("<html><body><font color='yellow'>0</body></html>");
		
		
		iG = new JLabel();
		
		
		iA = new JLabel("<html><body><font color='yellow'>0</body></html>");
		
		
		iI = new JLabel("<html><body><font color='yellow'>0</body></html>");
		
		iT2 = new JLabel();
		
		
		iG2 = new JLabel();
		
		
		iA2 = new JLabel();
		
		
		iI2 = new JLabel();
		
		

		foGV = new JLabel();
		
		foGA = new JLabel();
		
		foGP = new JLabel();
		
		
		
		
		JLabel background=new JLabel(new ImageIcon("ressources/images/MainGuiGame.jpg"));
		add(background);
		background.setLayout(new BorderLayout());
		
		panOuest.setLayout(new GridLayout(9,3));
		panOuest.setPreferredSize(new Dimension(270,300));
		panOuest.setBackground(new Color(230, 230, 250));
		panOuest.setOpaque(false);
		panOuest.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		
		
		
		panOuest.add(empty);
		empty.setBackground(new Color(102, 102, 153));
		empty.setOpaque(false);
		
		panOuest.add(infozone);
		infozone.setBackground(new Color(102, 102, 153));
		infozone.setOpaque(false);
		
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
		panEst.setOpaque(false);
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
		background.add(textField, BorderLayout.SOUTH);

		map = GameBuilder.buildMap();
		manager = GameBuilderGame.buildInitMobile(map);
		dashboard = new GameDisplayGame(map, manager);
		

		

		MouseControls mouseControls = new MouseControls();
		dashboard.addMouseListener(mouseControls);


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

	@Override
	public void run() {
			if(!manager.intrudervoid()&&deplace) {


				iT.setText(String.valueOf(manager.getRound()));
				iT.setForeground(Color.YELLOW);
				iG.setText(String.valueOf(manager.getGuardians().size()));
				iG.setForeground(Color.YELLOW);
				iA.setText(String.valueOf(manager.getTotalmoney()));
				iA.setForeground(Color.YELLOW);
				iI.setText(String.valueOf(manager.getItems().size()));
				iI.setForeground(Color.YELLOW);
				
				iT2.setText(String.valueOf(manager.getIntruders().getItems().get("Lure").getNbre()));
				iT2.setForeground(new Color(218,189,224));
				iG2.setText(String.valueOf(manager.getIntruders().getItems().get("Invisibility cloak").getNbre()));
				iG2.setForeground(new Color(218,189,224));
				if(manager.getMlure()!=null) {
					iA2.setText(String.valueOf(manager.getCountlure()));
					iA2.setForeground(new Color(218,189,224));
				}
				else {
					iA2.setText("");
				}
				if(manager.getIntruders().isTransparent()) {
					iI2.setText(String.valueOf(manager.getCountcloak()));
					iI2.setForeground(new Color(218,189,224));
				}
				else {
					iI2.setText("");
				}
				
				if(manager.isFilettrap() && manager.getCountfilet()==7) {
					TrapPannel tp=new TrapPannel();
					tp.setVisible(true);
				}

				
				foGV.setText(String.valueOf(manager.getIntruders().getVision()));
				foGV.setForeground(Color.WHITE);
				foGA.setText(String.valueOf(manager.getIntruders().getAgility()));
				foGA.setForeground(Color.WHITE);
				foGP.setText(String.valueOf(manager.getIntruders().getDodge()));
				foGP.setForeground(Color.WHITE);
				
				
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
		if (Integer.valueOf(manager.getExitmoney())>=1500 ) {
			etoiles = 4;
			star = new ImageIcon("ressources/images/4star.png");
		}
			else if (Integer.valueOf(manager.getExitmoney())>=800) {
				etoiles = 3;
				star = new ImageIcon("ressources/images/3star.png");
			}
			else if (Integer.valueOf(manager.getExitmoney())>=400) {
				etoiles = 2;
				star = new ImageIcon("ressources/images/2star.png");
			}
			else if (Integer.valueOf(manager.getExitmoney())>100 || Integer.valueOf(manager.getDuels())>=1 || Integer.valueOf(manager.getTotalmoney())>=1000) {
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
		
		JLabel Or = new JLabel("<html><body><font color='white' size='3'>Or amass&eacute; : </body><html>"+ manager.getExitmoney() + "</body></html>");
		Or.setBounds(269, 307, 168, 26);
		backgroundEnd.add(Or);

		
		JLabel lblArrestations = new JLabel("<html><body><font color='white' size='3'>Arrestations : " + manager.getArrestations() + "</body></html>" );
		lblArrestations.setBounds(269, 355, 223, 26);
		backgroundEnd.add(lblArrestations);
		
		JLabel lblDuelsRemports = new JLabel("<html><body><font color='white' size='3'>Duels remport&eacute;s par l'intrus : " + manager.getDuels() + "</body></html>");
		lblDuelsRemports.setBounds(269, 404, 223, 26);
		backgroundEnd.add(lblDuelsRemports);
		
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

	/**
	 * Allow the utilisation of key
	 */
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

	/**
	 * Allow the utilisation of key
	 */
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

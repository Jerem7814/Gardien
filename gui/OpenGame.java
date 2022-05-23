package gui;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.GameConfiguration;
import process.GameBuilder;
/**
 * Initialize a window that allow us to choose our parameters of the game such as how much intruders, how many tiles and if it's a simulation or playable mode.
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class OpenGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -541698616292452515L;
	private JPanel panCenter = new JPanel();
	private JLabel taille = new JLabel("Quelle taille de grille voulez-vous ?");
	private JLabel intrus = new JLabel("Combien d'intrus voulez vous ?");
	private JLabel experience = new JLabel("Quel type d'experience voulez vous ?");
	private JLabel error = new JLabel("");

	private ImageIcon icone = new ImageIcon("src/images/logo2.png");
	private JLabel logo = new JLabel(icone);

	private JComboBox<Integer> taille1 = new JComboBox<Integer>();
	private JComboBox<Integer> intrus1 = new JComboBox<Integer>();
	private JComboBox<String> experience1 = new JComboBox<String>();

	private JButton start = new JButton("Start");
	private JButton regle = new JButton("Regle");
	

	
	/**
	 * create the very first window of our game
	 */
	public void build() {
		
		this.setTitle("OpenGame");
		setDefaultCloseOperation(EXIT_ON_CLOSE) ; 
		this.setSize(700, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon("src/images/Menu.jpg"));
		add(background);
		background.setLayout(new FlowLayout());
		
		
		start.addActionListener(this);
		regle.addActionListener(this);
		
		
		taille1.addActionListener(new ItemAction1());
		intrus1.addActionListener(new ItemAction2());
		experience1.addActionListener(new ItemAction3());
		

		experience1.addItem("Simulation");
		experience1.addItem("Jeu");	
		if(experience1.getSelectedItem()=="Simulation") {
			for(int f = 1; f<6; f++) {
				intrus1.addItem(f);
			}
		}
		else {
			intrus1.addItem(1);
		}
		for(int f = 7; f<16; f++) {
			taille1.addItem(f);
		}




		

		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(4,2));
		container.add(experience);
		container.add(experience1);
		container.add(taille);
		container.add(taille1);
		container.add(intrus);
		container.add(intrus1);
		container.add(error);
		


		container.setOpaque(false);
		JPanel squette = new JPanel();
		squette.setLayout(new GridLayout(3,1));
		squette.setOpaque(false);
		squette.add(logo);
		squette.add(container);
		
		JPanel start1 = new JPanel();
		start1.setLayout(null);
		start1.setOpaque(false);
		
		regle.setBounds(120, 30, 150, 80);
		start.setBounds(310, 30, 150, 80);


		start1.add(regle);
		start1.add(start);

		
		
		squette.add(start1);
		
		//panCenter.setBackground(new Color(230, 230, 250)) ;
		panCenter.setOpaque(false);
		panCenter.setSize(1200,700);
		panCenter.add(squette);
		background.add(panCenter, BorderLayout.CENTER);
		
		File htmlFile = new File("src/html/index.html");
		regle.addActionListener(e->{
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		});

		
		this.setContentPane(background);
		setVisible(true) ;
		GameConfiguration.playmusicOpen();

	}
	
	/**
	 * Depending on what we click, to create a game with the parameter we decided
	 */
	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource();
		if (Button == start) {
			if((int) taille1.getSelectedItem()/2 >= (int)intrus1.getSelectedItem()) {
				if((experience1.getSelectedItem()=="Jeu"&&(int)intrus1.getSelectedItem()==1)||experience1.getSelectedItem()=="Simulation") {
					GameConfiguration.BLOCK_SIZE = (GameConfiguration.WINDOW_HEIGHT)/((int) taille1.getSelectedItem());
					GameConfiguration.LINE_COUNT = GameConfiguration.WINDOW_HEIGHT / GameConfiguration.BLOCK_SIZE;
					GameConfiguration.COLUMN_COUNT = GameConfiguration.WINDOW_WIDTH / GameConfiguration.BLOCK_SIZE;
					GameBuilder.nbrI=(int) intrus1.getSelectedItem();
					if(experience1.getSelectedItem()=="Simulation") {
						MainGUI gameMainGUI = new MainGUI("Guardian");
		
						Thread gameThread = new Thread(gameMainGUI);
						gameThread.start();
					}
					else {
						GameConfiguration.GAME_SPEED=5;
						MainGUIgame gameMainGUI = new MainGUIgame("Guardian");
	
						Thread gameThread = new Thread(gameMainGUI);
						gameThread.start();
					}
					GameConfiguration.stopmusicOpen();
					dispose();
				}

			}
			if(experience1.getSelectedItem()=="Jeu"&&(int)intrus1.getSelectedItem()>1) {
				error.setFont(new Font("Serif", Font.BOLD, 15));
				error.setForeground(Color.BLACK);
				error.setText("<html><body style='text-aligne:center'>Un seul intrus pour le mode 'jeu'</body></html>");
			}
			
			else {
			
				error.setFont(new Font("Serif", Font.BOLD, 15));
				error.setForeground(Color.BLACK);
				error.setText("<html><body  style='text-aligne:center'>Trop d'intrus s&eacute;l&eacute;ctionn&eacute;s pour la taille de grille</body></html>");
				
				
			}


			
		}
	}
	
	class ItemState implements ItemListener{
	    public void itemStateChanged(ItemEvent e) {
	    	
	    }               
	  }
	
	class ItemAction1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		    }
	}

	class ItemAction2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ItemAction3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		      
		    }
	}
	
	class ItemAction4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
		}
	}
	
	class ItemAction5 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		
		}
	}



	
	
	
}
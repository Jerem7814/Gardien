package gui;


import java.awt.BorderLayout;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import config.GameConfiguration;
import process.GameBuilder;

public class OpenGame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -541698616292452515L;
	private JPanel pan  = new JPanel();
	private JPanel panCenter = new JPanel();
	private JLabel taille = new JLabel("Quelle taille de grille voulez-vous ?");
	private JLabel intrus = new JLabel("Combien d'intrus voulez vous ?");
	private JLabel experience = new JLabel("Quel type d'experience voulez vous ?");
	private JLabel error = new JLabel("");

	private ImageIcon icone = new ImageIcon("ressources/images/logo2.png");
	private JLabel logo = new JLabel(icone);

	private JComboBox<Integer> taille1 = new JComboBox<Integer>();
	private JComboBox<Integer> intrus1 = new JComboBox<Integer>();
	private JComboBox<String> experience1 = new JComboBox<String>();

	private JButton start = new JButton("Start");
	private JButton règles = new JButton("Règles du jeu");
	private URI uris;

	
	
	public void build(){
		
		
		this.setTitle("Welcome");
		setDefaultCloseOperation(EXIT_ON_CLOSE) ; 
		this.setSize(700, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		pan.setLayout(new BorderLayout());
		
		start.addActionListener(this);
			
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
		JPanel container2 = new JPanel();
		
		container.setLayout(new GridLayout(4,2));
		container.add(experience);
		container.add(experience1);
		container.add(taille);
		container.add(taille1);
		container.add(intrus);
		container.add(intrus1);
		container.add(error);
		
		container2.setLayout(new GridLayout(1,2));
		container2.add(règles);
		container2.add(start);
		container.setBackground(new Color(230, 230, 250));
		container2.setBackground(new Color(230, 230, 250));

		
		JPanel squette = new JPanel();
		squette.setLayout(new GridLayout(3,1));
		squette.add(logo);
		squette.add(container);
		squette.add(container2);
		squette.setBackground(new Color(230, 230, 250));


		
		
		panCenter.setBackground(new Color(230, 230, 250)) ;
		panCenter.setSize(1200,700);
		panCenter.add(squette);
		pan.add(panCenter, BorderLayout.CENTER);
		try {
			uris = new URI("https://gardien.alwaysdata.net/index.php");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		règles.addActionListener(e-> {
			try {
				Desktop.getDesktop().browse(uris); 
			} catch (IOException e1) {
			}
		});

		this.setContentPane(pan);
		setVisible(true) ;
		GameConfiguration.playmusicOpen();

	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object Button = e.getSource();
		if (Button == start) {
			System.out.println((int) taille1.getSelectedItem()/2 +" "+ (int)intrus1.getSelectedItem());
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
				error.setForeground(Color.RED);
				error.setText("Le mode 'Jeu' n'est jouable qu'à un intrus");
			}
			
			else {
			
				error.setFont(new Font("Serif", Font.BOLD, 15));
				error.setForeground(Color.RED);
				error.setText("Trop d'intrus séléctionnés pour la taille de grille");
				
				
			}


			
		}
	}
	
	class ItemState implements ItemListener{
	    public void itemStateChanged(ItemEvent e) {
	    	
	    }               
	  }
	
	class ItemAction1 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
//		     	System.out.println("La taille est : " + (int)taille1.getSelectedItem());
		    }
	}

	class ItemAction2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
//			System.out.println("Le nombre de b�te est : " + (int)bete1.getSelectedItem());
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
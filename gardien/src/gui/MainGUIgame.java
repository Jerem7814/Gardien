package gui;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import config.GameConfiguration;
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

	public MainGUIgame(String title) {
		super(title);
		init();
	}

	private void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

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
		contentPane.add(dashboard, BorderLayout.CENTER);

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

				manager.nextRound();
				dashboard.repaint();
				
				deplace=false;
			}
			else {
				//nouvelle fenetre 
			}
		}
	}

	private class KeyControls implements KeyListener {

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

	private class MouseControls implements MouseListener {

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
		
		public boolean isIn(List<Block> vision, Block block) {
			for(Block blok:vision) {
				if(blok.equals(block)) {
					return true;
				}
			}
			return false;
		}
	}


}

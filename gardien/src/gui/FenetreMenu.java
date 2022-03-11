package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class FenetreMenu {

	private JFrame frmEntrygui;
	private JTextField entreeNxN;
	private JTextField entreeNbGardien;
	private JTextField entreeNbIntrue;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreMenu window = new FenetreMenu();
					window.frmEntrygui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FenetreMenu() {
		initialize();
	}


	private void initialize() {
		frmEntrygui = new JFrame();
		frmEntrygui.getContentPane().setBackground(new Color(102, 153, 153));
		frmEntrygui.setTitle("EntryGui");
		frmEntrygui.setBounds(100, 100, 1063, 726);
		frmEntrygui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEntrygui.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		panel.setBackground(new Color(102, 102, 153));
		panel.setBounds(132, 47, 714, 49);
		frmEntrygui.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSettTitre = new JLabel("Settings");
		lblSettTitre.setFont(new Font("Arial", Font.PLAIN, 26));
		lblSettTitre.setBounds(286, 11, 148, 32);
		panel.add(lblSettTitre);
		
		JPanel panelSettings = new JPanel();
		panelSettings.setBackground(new Color(230, 230, 250));
		panelSettings.setBorder(new LineBorder(UIManager.getColor("Viewport.foreground")));
		panelSettings.setBounds(132, 95, 714, 258);
		frmEntrygui.getContentPane().add(panelSettings);
		panelSettings.setLayout(null);
		
		JLabel lblTailleGrille = new JLabel("Taille de la grille :");
		lblTailleGrille.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTailleGrille.setBounds(64, 48, 130, 27);
		panelSettings.add(lblTailleGrille);
		
		entreeNxN = new JTextField();
		entreeNxN.setText("                              NxN");
		entreeNxN.setBounds(365, 50, 201, 27);
		panelSettings.add(entreeNxN);
		entreeNxN.setColumns(10);
		
		JLabel lblNbGarInitial = new JLabel("Nombre initial de gardien :");
		lblNbGarInitial.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNbGarInitial.setBackground(new Color(240, 240, 240));
		lblNbGarInitial.setBounds(62, 108, 193, 27);
		panelSettings.add(lblNbGarInitial);
		
		entreeNbGardien = new JTextField();
		entreeNbGardien.setText("                                N");
		entreeNbGardien.setBounds(365, 110, 201, 27);
		panelSettings.add(entreeNbGardien);
		entreeNbGardien.setColumns(10);
		
		JLabel lblNbIntInitial = new JLabel("Nombre initial d'intrus :");
		lblNbIntInitial.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNbIntInitial.setBounds(62, 176, 193, 27);
		panelSettings.add(lblNbIntInitial);
		
		entreeNbIntrue = new JTextField();
		entreeNbIntrue.setText("                                N");
		entreeNbIntrue.setBounds(365, 180, 201, 22);
		panelSettings.add(entreeNbIntrue);
		entreeNbIntrue.setColumns(10);
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new LineBorder(UIManager.getColor("Tree.selectionBorderColor")));
		panelButton.setBackground(new Color(102, 102, 153));
		panelButton.setBounds(132, 425, 714, 49);
		frmEntrygui.getContentPane().add(panelButton);
		panelButton.setLayout(null);
		
		JLabel lblButtonTitre = new JLabel("Start Button Zone");
		lblButtonTitre.setFont(new Font("Arial", Font.PLAIN, 25));
		lblButtonTitre.setBounds(246, 11, 225, 27);
		panelButton.add(lblButtonTitre);
		
		JPanel panelStart = new JPanel();
		panelStart.setBackground(new Color(230, 230, 250));
		panelStart.setBorder(new LineBorder(UIManager.getColor("Tree.foreground")));
		panelStart.setBounds(132, 473, 714, 143);
		frmEntrygui.getContentPane().add(panelStart);
		panelStart.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Arial", Font.PLAIN, 14));
		btnStart.setBounds(204, 46, 313, 45);
		panelStart.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FenetreMenu2 fenetre2 = new FenetreMenu2();
				fenetre2.main(null);
			}
		});
	}
}

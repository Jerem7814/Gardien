package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;



public class TrapPannel extends JFrame{
 
	/**
	* initialize a window that appear when we get stuck because of a Filet
	* @version 14.0.1
	* @author jeremybureau
	* @author quentinvilley
	* @author abdallahelballadi
	*/
	private static final long serialVersionUID = 1L;
	public TrapPannel(){
		super();
 
		build();//On initialise notre fenetre
	}
 
	/**
	 * create a window with it's dimension, and get the content thanks to an another method buildContentPane()
	 */
	private void build(){
		setTitle("Bloquer"); //On donne un titre a  l'application
		setSize(700,240); //On donne une taille a  notre fenetre
		setLocationRelativeTo(null); //On centre la fenetre sur l'ecran
		setResizable(true); //On permet le redimensionnement
		setContentPane(buildContentPane());
	}
	
	/**
	 * @return panel A panel with labels in it which ask us to do something to get out of the window
	 */
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		
		
	 
		JLabel label = new JLabel("<html><body>Vous &ecirc;tes pi&eacute;g&eacute; !</body></html>");
		label.setFont(new Font("Serif", Font.BOLD, 50));
		label.setForeground(Color.red);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label2 = new JLabel("<html><body>Faites 6 clics sur l'une des cases de votre champ de vision pour vous lib&eacute;rer</body></html>");
		label2.setFont(new Font("Serif", Font.BOLD, 15));
		label2.setForeground(Color.black);
		label2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);

		panel.add(label2);
	 
		return panel;
	}
}
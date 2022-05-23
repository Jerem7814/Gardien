package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;


/**
 * Initialize a window that show us we are trapped.
 * @version 14.0.1
 * @author jeremybureau
 * @author quentinvilley
 * @author abdallahelballadi
 */
public class TrapPannel extends JFrame{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TrapPannel(){
		super();
 
		build();//On initialise notre fenêtre
	}
 
	private void build(){
		setTitle("TrapPannel"); //On donne un titre à l'application
		setSize(700,240); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On permet le redimensionnement
		setContentPane(buildContentPane());
	}
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		
		
	 
		JLabel label = new JLabel("Vous êtes piégé !");
		label.setFont(new Font("Serif", Font.BOLD, 50));
		label.setForeground(Color.red);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label2 = new JLabel("Faites 6 clics sur l'une des cases de votre champ de vision pour vous libérer");
		label2.setFont(new Font("Serif", Font.BOLD, 15));
		label2.setForeground(Color.black);
		label2.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(label);

		panel.add(label2);
	 
		return panel;
	}
}
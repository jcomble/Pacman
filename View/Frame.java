package View;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JFrame;

/*
 * 
 * 
 * 
 * 
 * Frame : on depose les panels et JlayeredPane ,label.
 * Jpanel : on depose les murs , teleporteurs.
 * JlayeredPane : on depose les label du ghost , pacman , fruits
 * Jlabel : on depose les images du pacman , ghost , fruits
 * 
 *  
 *  Frame __ Jpane____Walls
 *  	  |		   |__Telep
 *        |
 *		  |_JlayeredPane___Jlabel___ img.pacman 
 *		  |				         |__ img.ghost
 *		  |						 |___img.frauit
 * 		  |
 * 		  |__Jlabel__information.jeu
 * 
 * 
 * 
 * 
 * 
 */

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Frame() {
		// Metter icon au fram
		Image inst_img=new Image();
		ImageIcon img=inst_img.icon_pacman;
		this.setIconImage(img.getImage());
		// Mettre le titre
		this.setTitle("PACMAN_GR_6_TP2");
		// Fermer le programme quand la fenetre se ferme
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		this.setLayout(null);
		// Les dimension du fenetre sont inchangable
		this.setResizable(false);
		// Definir les dimensions du frame
		this.setSize(1000, 960);
		// Definir la coleur du background
		this.getContentPane().setBackground(Color.black);
	}
}

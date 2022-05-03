package View;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JFrame;

public class Frame extends JFrame {
		
	private static final long serialVersionUID = 1L;
	/**
	 * Constructeur.
	 */
	public Frame() {
		Image inst_img=new Image();
		ImageIcon img=inst_img.icon_pacman;
		this.setIconImage(img.getImage());
		this.setTitle("PACMAN_GR_6_TP2");
        this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1000, 960);
        this.getContentPane().setBackground(Color.black);
	}
}

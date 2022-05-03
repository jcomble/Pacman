package View;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Listes.Liste;
import Logic.Controller;
import Logic.Fruits;
import Logic.Ghost;

public class Game {
	
	private static char actual_direction = '→';
	private Controller controller;

	// Instancier la classe Image qui charger tout les images du jeu
	private Image img = new Image();	
	
	/**
	 * Associer à une label les informations du jeu
	 * @param info_score lable où on ajoute les informations du jeu (Score, Level , Points de Vie) 
	 */
	private void read_score(JLabel info_score) {
		int score = this.controller.get_score();
        int niveau = this.controller.get_actual_level();
        int nombre_points_vie = this.controller.get_point_vie();
        String result_score = String.format("   SCORE : %02d   Level : %d   Points de Vie : %d", score, niveau, nombre_points_vie);
        info_score.setText(result_score);
	}
	
	/**
	 * Supprimer tout les murs donnée du frame 
	 * @param frame le frame ou on veut supprimer les murs
	 * @param list_murs quand veut les supprimer 
	 */
	private void frame_reset(JFrame frame) {
		frame.getContentPane().removeAll();
	}
	
	/**
	 * Ajouter les murs au frame
	 * @param frame 
	 * @return la list des label qui represente  les murs 
	 */
	private void put_walls(JFrame frame) {
		for (int i = 0; i < this.controller.get_walls().size(); i++) {
            int pos_x = this.controller.get_walls().get(i).getPos_X();
            int pos_y = this.controller.get_walls().get(i).getPos_Y();
            JPanel mur = new JPanel();
            mur.setBackground(Color.blue);
            mur.setBounds(pos_x, pos_y, 40, 40);
            frame.add(mur);
        }
	}
	
	/**
	 * Ajouter les fuits au panel 
	 * @param panel où on ajoute les fuits
	 * @return la liste qui contient les labels qui representent les fruits
	 */
	private Liste<JLabel> put_apples(JLayeredPane panel) {
		Liste<JLabel> apples = new Liste<JLabel>();
		for (int i = 0; i < this.controller.get_fruits().size(); i++) {
			Fruits tmp_fruit = this.controller.get_fruits().get(i);
			int pos_x = this.controller.get_fruits().get(i).getPos_X();
			int pos_y = this.controller.get_fruits().get(i).getPos_Y();
			JLabel apple = new JLabel();
			if (tmp_fruit.getType() == 'g') {
				apple.setIcon(this.img.pomme_normal);
            } else {
				apple.setIcon(this.img.pomme_super);
            }
			apple.setBounds(pos_x, pos_y, 40, 40);
			apples.add(apple);
			panel.add(apple, Integer.valueOf(2));
        }
		return apples;
	}
	
	private void put_teleps(JFrame frame) {
		for (int i = 0; i < this.controller.get_telep().size(); i++) {
            int pos_x = this.controller.get_telep().get(i).getPos_X();
            int pos_y = this.controller.get_telep().get(i).getPos_Y();
            JPanel telep = new JPanel();
            telep.setBackground(Color.gray);
            telep.setBounds(pos_x, pos_y, 40, 40);
            frame.add(telep);
        }
	}
	
	/**
	 * Mettre les ghosts au layeredpane
	 * @param panel où on met les ghosts
	 * @return la liste qui contient les labels qui representent les ghostes
	 */
	private Liste<JLabel> put_ghosts(JLayeredPane panel) {
		Liste<JLabel> list_ghosts_label = new Liste<JLabel>();
		for (int i = 0; i < this.controller.get_ghosts().size(); i++) {
			Ghost tmp_ghost = this.controller.get_ghosts().get(i);
			int Pos_X = tmp_ghost.get_init_Pos_X();
			int Pos_Y = tmp_ghost.get_init_Pos_Y();
			tmp_ghost.set_Pos_x(Pos_X);
			tmp_ghost.set_Pos_y(Pos_Y);
            int pos_x = this.controller.get_ghosts().get(i).getPos_X();
            int pos_y = this.controller.get_ghosts().get(i).getPos_Y();
            ImageIcon image_ghost =  null;
            JLabel ghost_label = new JLabel();
            list_ghosts_label.add(ghost_label);
            switch (tmp_ghost.getDirection()) {
                case '↑':
                    image_ghost = this.img.image_gost_up;
                    ghost_label.setIcon(image_ghost); 
                    break;
                case '↓':
                    image_ghost = this.img.image_gost_down;
                    ghost_label.setIcon(image_ghost); 
                    break;
                case '←':
                    image_ghost = this.img.image_gost_left;
                    ghost_label.setIcon(image_ghost); 
                    break;
                case '→':
                    image_ghost = this.img.image_gost_right;
                    ghost_label.setIcon(image_ghost); 
                    break;
            }   
            ghost_label.setBounds(pos_x, pos_y, 40, 40);
            panel.add(ghost_label, Integer.valueOf(4));
        }
		return list_ghosts_label;
	}
	/**
	 * Constructeur.
	 */
    public Game() {
    	Controller controller = new Controller();
    	this.controller = controller;
        Frame frame = new Frame();
        JLayeredPane panel = new JLayeredPane();
        panel.setBounds(0, 0, 1000, 1000);
        frame.add(panel);
        JLabel barre_info_Score = new JLabel();
        read_score(barre_info_Score);
        barre_info_Score.setBounds(360, 860, 1000, 10);
        barre_info_Score.setForeground(Color.white);
        barre_info_Score.setBackground(Color.black);
        barre_info_Score.setOpaque(true);
        panel.add(barre_info_Score, Integer.valueOf(4));
        put_walls(frame);
        put_teleps(frame);
        Liste<JLabel> apples = put_apples(panel);
        Liste<JLabel> list_ghosts_label = put_ghosts(panel);
        for (int i = 0; i < controller.get_telep().size(); i++) {
            int pos_x = controller.get_telep().get(i).getPos_X();
            int pos_y = controller.get_telep().get(i).getPos_Y();
            JPanel telep = new JPanel();
            telep.setBackground(Color.gray);
            telep.setBounds(pos_x, pos_y, 40, 40);
            frame.add(telep);
        }
        JLabel pacman = new JLabel();
        ImageIcon image_pacman = null;
        switch (controller.get_pacman().getDirection()) {
            case '↑':
                image_pacman = this.img.image_pacman_up;
                pacman.setIcon(image_pacman); 
                break;
            case '↓':
                image_pacman = this.img.image_pacman_down;
                pacman.setIcon(image_pacman); 
                break;
            case '←':
                image_pacman = this.img.image_pacman_left;
                pacman.setIcon(image_pacman); 
                break;
            case '→':
                image_pacman = this.img.image_pacman_right;
                pacman.setIcon(image_pacman); 
                break;
        }
        pacman.setBounds(controller.get_pacman().getPos_X(), controller.get_pacman().getPos_Y(), 40, 40);
        panel.add(pacman, Integer.valueOf(4));
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                int keycode = e.getKeyCode();
	                switch(keycode) {
	                    case KeyEvent.VK_UP:
	                        Game.set_direction('↑');
	                        break;
	                    case KeyEvent.VK_LEFT:
	                    	Game.set_direction('←');
	                    	break;
	                    case KeyEvent.VK_RIGHT:
	                    	Game.set_direction('→');
	                    	break;
	                    case KeyEvent.VK_DOWN:
	                    	Game.set_direction('↓');
	                    break;
	                }
	            }
	            @Override
	            public void keyReleased(KeyEvent e) {
	            }
	            @Override
				public void keyTyped(KeyEvent e) {
				}
	        }
        );
	    while (true) {
	    	int index = controller.update(actual_direction);
	    	switch (controller.get_pacman().getDirection()) {
	    		case '↑':
	    			pacman.setIcon(this.img.image_pacman_up);
	    			break;
	    		case '↓':
	    			pacman.setIcon(this.img.image_pacman_down);
	    			break;
	    		case '→':
	    			pacman.setIcon(this.img.image_pacman_right);
	    			break;
	    		case '←':
	    			pacman.setIcon(this.img.image_pacman_left);
	    			break;
	    	}
	    	if (index != -1 && index != -2) {
	    		JLabel apple = apples.get(index);
	    		panel.remove(apple);
	    		apples.remove(index);
	    	}
	    	if (apples.size() == 0 || index == -2) {
	    		Game.set_direction('→');
	    		frame.setVisible(false);
	    		frame_reset(frame);
	    		panel = new JLayeredPane();
	            panel.setBounds(0, 0, 1000, 1000);
	            frame.add(panel);
	            panel.add(pacman, Integer.valueOf(4));
	            panel.add(barre_info_Score, Integer.valueOf(4));
	    		put_teleps(frame);
	    		put_walls(frame);
	            apples = put_apples(panel);
	            list_ghosts_label = put_ghosts(panel);
	            frame.setVisible(true);
	    	}
	    	for (int i = 0; i < this.controller.get_ghosts().size(); i++) {
	    		JLabel lghost = list_ghosts_label.get(i);
	    		Ghost ghost = this.controller.get_ghosts().get(i);
	    		this.img.image_ghost = null;
	    		if (!ghost.is_vulnerable()) {
		    		switch (ghost.getDirection()) {
		                case '↑':
		                	this.img.image_ghost = this.img.image_gost_up;
		                    break;
		                case '↓':
		                    this.img.image_ghost = this.img.image_gost_down;
		                    break;
		                case '←':
		                    this.img.image_ghost = this.img.image_gost_left;
		                    break;
		                case '→':
		                    this.img.image_ghost = this.img.image_gost_right;
		                    break;
		            }
	    		} else {
	    			this.img.image_ghost = this.img.image_gost_vul;
	    		}
	    		lghost.setIcon(this.img.image_ghost);
	    		lghost.setBounds(ghost.getPos_X(), ghost.getPos_Y(), 40, 40);
	    	}
	    	pacman.setBounds(controller.get_pacman().getPos_X(), controller.get_pacman().getPos_Y(), 40, 40);
	    	read_score(barre_info_Score);
	    	switch(this.controller.get_actual_level()) {
	    		case 1:
	    			wait(100);
	    			break;
	    		case 2:
	    			wait(80);
	    			break;
	    		case 3:
	    			wait(60);
	    			break;
	    		default:
	    			wait(40);
	    			break;
	    	}
	        
	    }
    }
    
    private static void set_direction(char direction) {
    	actual_direction = direction;
    }
    
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }   
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
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
	/**
	 * La direction du pacman 
	 */
	private static char actual_direction = '→';

	/**
	 * Le contrroleur qui va rassembller tout les éléments du jeu (pacman, ghost, fruit, walls ,...)
	 */
	private Controller controller = new Controller();

	/**
	 * Tout les objets Type ImageIcon sont dans l'attribut img à l'aide de la classe Image (pour organier le code )
	 */
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
	 * Supprimer tout les élément du frame
	 * @param frame 
	 */
	private void frame_reset(JFrame frame) {
		frame.getContentPane().removeAll();
	}

	/**
	 * Ajouter les murs au frame
	 * @param frame 
	 * @return la list des label qui represente les murs 
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
			switch (tmp_fruit.getType()) {
				case '.':
					apple.setIcon(this.img.gomme);
					break;
				case '*':
					apple.setIcon(this.img.super_gomme);
					break;
				case 'c':
					apple.setIcon(this.img.cherry);
					break;
				case 'f':
					apple.setIcon(this.img.strawberry);
					break;
				case 'o':
					apple.setIcon(this.img.orange);
					break;
				case 'p':
					apple.setIcon(this.img.pomme);
					break;
				case 'm':
					apple.setIcon(this.img.mellon);
					break;
				case 'g':
					apple.setIcon(this.img.ship);
					break;
				case 'b':
					apple.setIcon(this.img.bell);
					break;
				case 'k':
					apple.setIcon(this.img.key);
					break;
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
	 * Mettre les ghosts au panel
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
			ImageIcon image_ghost = null;
			JLabel ghost_label = new JLabel();
			list_ghosts_label.add(ghost_label);
			switch (tmp_ghost.getDirection()) {
				case '↑':
					switch (i % 4) {
						case 0:
							image_ghost = this.img.image_blinky_up;
							break;
						case 1:
							image_ghost = this.img.image_clyde_up;
							break;
						case 2:
							image_ghost = this.img.image_inky_up;
							break;
						case 3:
							image_ghost = this.img.image_pinky_up;
							break;
					}
					ghost_label.setIcon(image_ghost); 
					break;
				case '↓':
					switch (i % 4) {
						case 0:
							image_ghost = this.img.image_blinky_down;
							break;
						case 1:
							image_ghost = this.img.image_clyde_down;
							break;
						case 2:
							image_ghost = this.img.image_inky_down;
							break;
						case 3:
							image_ghost = this.img.image_pinky_down;
							break;
					}
					ghost_label.setIcon(image_ghost); 
					break;
				case '←':
					switch (i % 4) {
						case 0:
							image_ghost = this.img.image_blinky_left;
							break;
						case 1:
							image_ghost = this.img.image_clyde_left;
							break;
						case 2:
							image_ghost = this.img.image_inky_left;
							break;
						case 3:
							image_ghost = this.img.image_pinky_left;
							break;
					}
					ghost_label.setIcon(image_ghost); 
					break;
				case '→':
					switch (i % 4) {
						case 0:
							image_ghost = this.img.image_blinky_right;
							break;
						case 1:
							image_ghost = this.img.image_clyde_right;
							break;
						case 2:
							image_ghost = this.img.image_inky_right;
							break;
						case 3:
							image_ghost = this.img.image_pinky_right;
							break;
					}
					ghost_label.setIcon(image_ghost); 
					break;
			}
			ghost_label.setBounds(pos_x, pos_y, 40, 40);
			panel.add(ghost_label, Integer.valueOf(4));
		}
		return list_ghosts_label;
	}

	/**
	 * Charegement et lancement du jeu 
	 */
	public Game() {
		//##################### Mise en place les éléments du jeu #########################
		// Instancier la classe frame
		Frame frame = new Frame();
		// Instancier la classe JLayeredPane puis on l'ajoute au objet frame
		JLayeredPane panel = new JLayeredPane();
		panel.setBounds(0, 0, 1000, 1000);
		frame.add(panel);
		// Instancier la classe Jlabel qui va contient les informations du jeu (score, points de vie , niveau )
		// et mise en place du style (dimention, coleur , position ...) puis l'ajouer au panel 
		JLabel barre_info_Score = new JLabel();
		read_score(barre_info_Score);
		barre_info_Score.setBounds(360, 860, 1000, 10);
		barre_info_Score.setForeground(Color.white);
		barre_info_Score.setBackground(Color.black);
		barre_info_Score.setOpaque(true);
		panel.add(barre_info_Score, Integer.valueOf(4));
		// Ajouter les murs au frame
		put_walls(frame);
		// Ajouter les Teleporteurs au frame
		put_teleps(frame);
		// Poser les fruits sur le panel et les stocker dans la variables apples
		Liste<JLabel> apples = put_apples(panel);
		// Poser les ghost sur le panel et les stocker dans la variables lis_ghosts_label
		Liste<JLabel> list_ghosts_label = put_ghosts(panel);
		// Instancier une label qui va representé le pacman 
		JLabel pacman = new JLabel();
		// Instancier une Imageicon qui va contient l'image du pacman
		ImageIcon image_pacman = null;
		// Image du pacman depend de sa direction alors pour ca on fait un switch selon la direction
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
		// Modifier la direction du pcman selon les touches cliqué par l'utilisateur
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
		//##################### Mettre à jour les éléments du jeu #########################
		while (true) {
			// Mettre à jour le jeu " partie logique"
			int index = this.controller.update(actual_direction);
			Fruits tmp_fruit = this.controller.get_bonus();
			if (tmp_fruit != null) {
				int pos_x = tmp_fruit.getPos_X();
				int pos_y = tmp_fruit.getPos_Y();
				JLabel apple = new JLabel();
				switch (tmp_fruit.getType()) {
					case '.':
						apple.setIcon(this.img.gomme);
						break;
					case '*':
						apple.setIcon(this.img.super_gomme);
						break;
					case 'c':
						apple.setIcon(this.img.cherry);
						break;
					case 'f':
						apple.setIcon(this.img.strawberry);
						break;
					case 'o':
						apple.setIcon(this.img.orange);
						break;
					case 'p':
						apple.setIcon(this.img.pomme);
						break;
					case 'm':
						apple.setIcon(this.img.mellon);
						break;
					case 'g':
						apple.setIcon(this.img.ship);
						break;
					case 'b':
						apple.setIcon(this.img.bell);
						break;
					case 'k':
						apple.setIcon(this.img.key);
						break;
				}
				apple.setBounds(pos_x, pos_y, 40, 40);
				apples.add(apple);
				panel.add(apple, Integer.valueOf(2));
			}
			// Modifier l'image de pacman selon sa direction 
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
			// Verifier s'il y'a une fruit en contacte avec pacman ; si oui on la supprime
			if (index != -1 && index != -2) {
				JLabel apple = apples.get(index);
				panel.remove(apple);
				panel.repaint();
				apples.remove(index);
			}
			// Verifier s'il tout les fruits sont mangé par pacman ; si oui on passe au niveau suivant
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
			// Image du ghost 
			// Parcourir tout les ghosts
			for (int i = 0; i < this.controller.get_ghosts().size(); i++) {
				JLabel lghost = list_ghosts_label.get(i);
				Ghost ghost = this.controller.get_ghosts().get(i);
				this.img.image_ghost = null;
				// verifier si le ghost est non vulnerable
				if (!ghost.is_vulnerable()) {
					// Mettre l'image selon la direction
					switch (ghost.getDirection()) {
						case '↑':
							switch (i % 4) {
								case 0:
									this.img.image_ghost = this.img.image_blinky_up;
									break;
								case 1:
									this.img.image_ghost = this.img.image_clyde_up;
									break;
								case 2:
									this.img.image_ghost = this.img.image_inky_up;
									break;
								case 3:
									this.img.image_ghost = this.img.image_pinky_up;
									break;
							}
							lghost.setIcon(this.img.image_ghost); 
							break;
						case '↓':
							switch (i % 4) {
								case 0:
									this.img.image_ghost = this.img.image_blinky_down;
									break;
								case 1:
									this.img.image_ghost = this.img.image_clyde_down;
									break;
								case 2:
									this.img.image_ghost = this.img.image_inky_down;
									break;
								case 3:
									this.img.image_ghost = this.img.image_pinky_down;
									break;
							}
							lghost.setIcon(this.img.image_ghost); 
							break;
						case '←':
							switch (i % 4) {
								case 0:
									this.img.image_ghost = this.img.image_blinky_left;
									break;
								case 1:
									this.img.image_ghost = this.img.image_clyde_left;
									break;
								case 2:
									this.img.image_ghost = this.img.image_inky_left;
									break;
								case 3:
									this.img.image_ghost = this.img.image_pinky_left;
									break;
							}
							lghost.setIcon(this.img.image_ghost); 
							break;
						case '→':
							switch (i % 4) {
								case 0:
									this.img.image_ghost = this.img.image_blinky_right;
									break;
								case 1:
									this.img.image_ghost = this.img.image_clyde_right;
									break;
								case 2:
									this.img.image_ghost = this.img.image_inky_right;
									break;
								case 3:
									this.img.image_ghost = this.img.image_pinky_right;
									break;
							}
							lghost.setIcon(this.img.image_ghost); 
							break;
					}
				} else {
					// on met l'image du ghost vulnerable
					this.img.image_ghost = this.img.image_gost_vul;
				}
				// Mettre à jour l'image du ghost ainsi sa position
				lghost.setIcon(this.img.image_ghost);
				lghost.setBounds(ghost.getPos_X(), ghost.getPos_Y(), 40, 40);
			}
			// Mettre la position du label qui represente le pacman 
			pacman.setBounds(controller.get_pacman().getPos_X(), controller.get_pacman().getPos_Y(), 40, 40);
			// Mettre à jour la barre qui contient des informations 
			read_score(barre_info_Score);
			switch (this.controller.get_actual_level()) {
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

	/**
	 * Modifier la valeur de la variable static 
	 * @param direction La nouvelle valeur
	 */
	private static void set_direction(char direction) {
		actual_direction = direction;
	}

	/**
	 * Mettre le programme courant en mode pause
	 * @param ms Le temps d'attend en milliseconde
	 */
	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
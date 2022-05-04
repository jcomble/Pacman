package Logic;
import Data.Board;
import Listes.Liste;

import java.util.Random;

public class Controller {
	/**
	 * Numéro du niveau actuel.
	 */
	private int actual_level;
	
	/**
	 * Numéro du dernier Niveau.
	 */
	private int max_level;
	
	/**
	 * Liste contenant tous les murs du plateau à initialiser.
	 */
	private Liste<Walls> list_walls;
	
	/**
	 * Liste contenant tous les murs et téléporteurs à initialiser qui doivent empêcher les fantômes.
	 */
	private Liste<Walls> list_ghost_walls;
	
	/**
	 * Liste contenant tous les fruits du plateau à initialiser.
	 */
	private Liste<Fruits> list_fruits;
	
	/**
	 * Liste contenant tous les fantômes du plateau à initialiser.
	 */
	private Liste<Ghost> list_ghosts;
	
	/**
	 * Liste des téléporteurs du plateau à initialiser.
	 */
	private Liste<Telep> list_telep;
	
	/**
	 * Liste des plateaux du jeu.
	 */
	private Liste<Board> list_boards;
	
	/**
	 * Instance de Pacman du plateau à initialiser.
	 */
	private Pacman pacman;
	
	/**
	 * Constructeur.
	 */
	public Controller() {
		Liste<Board> list_boards = new Liste<Board>();
		list_boards.add(new Board(1));
		list_boards.add(new Board(2));
		list_boards.add(new Board(3));
		list_boards.add(new Board(4));
		list_boards.add(new Board(5));
		Board tmp_board = list_boards.get(0);
		Liste <Walls> list_walls = tmp_board.get_Walls();
		Liste<Telep> list_telep = tmp_board.get_Telep();
		Liste<Walls> list_ghost_walls = new Liste<Walls>();
		Liste<Fruits> tmp_list_fruits = tmp_board.get_Fruits();
		Liste<Ghost> tmp_list_ghosts = tmp_board.get_Ghost();
		for (int i = 0; i < list_walls.size(); i++) {
			Walls tmp_wall = list_walls.get(i);
			list_ghost_walls.add(tmp_wall);
		}
		for (int i = 0; i < list_telep.size(); i++) {
			Telep tmp_telep = list_telep.get(i);
			int Pos_X = tmp_telep.getPos_X();
			int Pos_Y = tmp_telep.getPos_Y();
			Walls tmp_wall = new Walls(Pos_X, Pos_Y);
			list_ghost_walls.add(tmp_wall);
		}
		Pacman pacman = tmp_board.get_Pacman();
		this.actual_level = 1;
		this.max_level = 5;
		this.list_walls = list_walls;
		this.list_ghost_walls = list_ghost_walls;
		this.list_fruits = fruits_copy(tmp_list_fruits);
		this.list_ghosts = ghosts_copy(tmp_list_ghosts);
		this.list_telep = list_telep;
		this.list_boards = list_boards;
		this.pacman = pacman;
	}
	
	/**
	 * Copie d'une liste de fruits.
	 * @param list_fruits Liste de fruits à copier.
	 * @return Copie de la liste de fruits dont chaque fruit est une nouvelle copie.
	 */
	private Liste<Fruits> fruits_copy(Liste<Fruits> list_fruits) {
		Liste<Fruits> tmp_list_fruits = new Liste<Fruits>();
		for (int i = 0; i < list_fruits.size(); i++) {
			Fruits tmp_fruit = list_fruits.get(i);
			int Pos_X = tmp_fruit.getPos_X();
			int Pos_Y = tmp_fruit.getPos_Y();
			char type = tmp_fruit.getType();
			int value = tmp_fruit.getValue();
			Fruits new_fruit = new Fruits(Pos_X, Pos_Y, type, value);
			tmp_list_fruits.add(new_fruit);
		}
		return tmp_list_fruits;
	}
	
	/**
	 * Copie d'une liste de fantômes.
	 * @param list_ghosts Liste de fantômes à copier.
	 * @return Copier de la liste de fantômes dont chaque fantôme est une nouvelle copie.
	 */
	private Liste<Ghost> ghosts_copy(Liste<Ghost> list_ghosts) {
		Liste<Ghost> tmp_list_ghosts = new Liste<Ghost>();
		for (int i = 0; i < list_ghosts.size(); i++) {
			Ghost tmp_ghost = list_ghosts.get(i);
			int Pos_X = tmp_ghost.getPos_X();
			int Pos_Y = tmp_ghost.getPos_Y();
			int id = tmp_ghost.get_id();
			int speed = tmp_ghost.get_speed();
			Ghost new_ghost = new Ghost(Pos_X, Pos_Y, '↑', id, speed, 0);
			tmp_list_ghosts.add(new_ghost);
		}
		return tmp_list_ghosts;
	}
	
	/**
	 * Mise à jour des données à exploiter du controlleur pour charger le niveau suivant:
	 * -Détermine le niveau actuel
	 * -Détermine la liste des murs
	 * -Détermine la liste des murs bloquants les fantômes
	 * -Détermine la liste des fruits
	 * -Détermine la liste des téléporteurs
	 * -Repositionne le pacman au bon endroit.
	 */
	private void maj_controller() {
		int numero_board = get_actual_level() % this.max_level;
		this.actual_level = numero_board + 1;
		Board tmp_board = this.list_boards.get(numero_board);
		Liste<Walls> list_walls = tmp_board.get_Walls();
		Liste<Telep> list_telep = tmp_board.get_Telep();
		Liste<Walls> list_ghost_walls = new Liste<Walls>();
		for (int i = 0; i < list_walls.size(); i++) {
			Walls tmp_wall = list_walls.get(i);
			list_ghost_walls.add(tmp_wall);
		}
		for (int i = 0; i < list_telep.size(); i++) {
			Telep tmp_telep = list_telep.get(i);
			int Pos_X = tmp_telep.getPos_X();
			int Pos_Y = tmp_telep.getPos_Y();
			Walls tmp_wall = new Walls(Pos_X, Pos_Y);
			list_ghost_walls.add(tmp_wall);
		}
		this.list_walls = list_walls;
		this.list_ghost_walls = list_ghost_walls;
		Liste<Fruits> list_fruits = tmp_board.get_Fruits();
		Liste<Ghost> list_ghosts = tmp_board.get_Ghost();
		this.list_fruits = fruits_copy(list_fruits);
		this.list_ghosts = ghosts_copy(list_ghosts);
		this.list_telep = list_telep;
		Pacman tmp_pacman = tmp_board.get_Pacman();
		int Pos_X = tmp_pacman.get_init_Pos_X();
		int Pos_Y = tmp_pacman.get_init_Pos_Y();
		this.pacman.set_init_Pos_x(Pos_X);
		this.pacman.set_init_Pos_y(Pos_Y);
		this.pacman.set_Pos_x(Pos_X);
		this.pacman.set_Pos_y(Pos_Y);
	}
	
	/**
	 * Déplace Pacman dans la direction voulue si possible ou sinon pacman continuera à suivre la même direction.
	 * @param direction Direction voulue.
	 */
	private void move_pacman(char direction) {
		Liste<Walls> list_walls = this.list_walls;
		Pacman pacman = this.pacman;
		char direction_pacman = pacman.getDirection();
		if (pacman.is_valid_move(direction, list_walls) && direction != direction_pacman) {
			pacman.set_Direction(direction);
			direction_pacman = pacman.getDirection();
		}
		switch(direction_pacman) {
			case '↑':
				pacman.moveUP(list_walls);
				break;
			case '↓':
				pacman.moveDOWN(list_walls);
				break;
			case '→':
				pacman.moveRIGHT(list_walls);
				break;
			case '←':
				pacman.moveLEFT(list_walls);
				break;
		}
	}
	
	/**
	 * Détermine les directions que peut prendre un certain fantôme.
	 * @param ghost Fantôme.
	 * @return La liste des directions que peut prendre le fantôme.
	 */
	private char[] allowed_direction(Ghost ghost) {
		char[] list_char_base = new char[] {'↑','↓','→','←'};
		char[] list_char_iterate = new char[] {'↑','↓','→','←'};
		for (char direction : list_char_iterate) {
			if (!ghost.is_valid_move(direction, this.list_ghost_walls)){
				int new_length = list_char_base.length - 1;
				char[] tmp_list_char_base = new char[new_length];
				int counter = 0;
				for (char direction2 : list_char_base) {
					if (direction != direction2) {
						tmp_list_char_base[counter] = direction2;
						counter += 1;
					}
				}
				list_char_base = tmp_list_char_base;
			}
		}
		return list_char_base;
	}
	
	/**
	 * Détermine si une direction est dans une liste de directions
	 * @param direction Direction représenté par un char.
	 * @param list_allowed_direction Liste de directions.
	 * @return Renvoie true si la direction est bien dans la liste, false sinon.
	 */
	private boolean is_in(char direction, char[] list_allowed_direction) {
		for (char tmp_direction : list_allowed_direction) {
			if (tmp_direction == direction) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fait mouvoir les fantômes de manière aléatoire en respectant des règles:
	 * -Si le fantôme est dans un couloir (ne peut que avancer ou reculer), il doit garder sa direction de déplacement hormis s'il ne suit pas déjà le couloir (direction vers un mur).
	 * -Si le fantôme est dans une impasse, il doit prendre la seule direction qu'il peut prendre.
	 * -Sinon il doit faire le choix de prendre la direction qu'il veut (si dans un embranchement ou si dans un couloir mais n'a pas encore pris une des directions possibles).
	 * Réduit le temps de vulnérabilité des fantômes (si les fantômes sont vulnérables).
	 */
	private void ghost_random_move() {
		Random random = new Random();
		Liste<Ghost> list_ghosts = this.list_ghosts;
		int count_for_combo = 0;
		for (int i = 0; i < list_ghosts.size(); i++) {
			Ghost ghost = list_ghosts.get(i);
			char[] list_allowed_direction = allowed_direction(ghost);
			char ghost_actual_direction = ghost.getDirection();
			char direction;
			switch (list_allowed_direction.length) {
				case 0:
					direction = '↑';
					break;
				case 1:
					direction = list_allowed_direction[0];
					break;
				case 2:
					if ((ghost_actual_direction == '↑' || ghost_actual_direction == '↓') && is_in('↑', list_allowed_direction) && is_in('↓', list_allowed_direction)
						||(ghost_actual_direction == '→' || ghost_actual_direction == '←') && is_in('→', list_allowed_direction) && is_in('←', list_allowed_direction)) {
								direction = ghost_actual_direction;
					} else {
						int random_value = random.nextInt(2);
						direction = list_allowed_direction[random_value];
					}
					break;
				default:
					int number_allowed_direction = list_allowed_direction.length;
					int random_value = random.nextInt(number_allowed_direction);
					direction = list_allowed_direction[random_value];
					break;
			}
			ghost.setDirection(direction);
			Liste<Walls> list_ghost_walls = this.list_ghost_walls;
			int tmp_d = ghost.max_distance(direction, list_ghost_walls);
			int pos_X = ghost.getPos_X();
			int pos_Y = ghost.getPos_Y();
			int new_pos_X;
			int new_pos_Y;
			switch (direction) {
				case '↑':
					new_pos_Y = pos_Y - tmp_d;
					ghost.set_Pos_y(new_pos_Y);
					break;
				case '↓':
					new_pos_Y = pos_Y + tmp_d;
					ghost.set_Pos_y(new_pos_Y);
					break;
				case '→':
					new_pos_X = pos_X + tmp_d;
					ghost.set_Pos_x(new_pos_X);
					break;
				case '←':
					new_pos_X = pos_X - tmp_d;
					ghost.set_Pos_x(new_pos_X);
					break;
			}
			if (!ghost.is_vulnerable()) {
				count_for_combo += 1;
			}
			ghost.dec_vul();
		}
		if (count_for_combo == list_ghosts.size()) {
			this.pacman.set_combo(0);
		}
	}
	
	/**
	 * Enlève un fruit de la liste des fruits.
	 * @param fruit Fruit à retirer de la liste.
	 * @return Indice de la position du fruit retiré (-1 si échec).
	 */
	private int erase_fruit(Fruits fruit) {
		Liste<Fruits> list_fruits = this.list_fruits;
		for (int i = 0; i < list_fruits.size(); i++) {
			if (list_fruits.get(i) == fruit) {
				list_fruits.remove(i);
				return i; 
			}
		}
		return -1;
	}
	
	/**
	 * Détermine si tous les fruits ont été consommés.
	 * @return Renvoie true si tous les fruits sont consommés, false sinon.
	 */
	private boolean all_fruits_are_consumed() {
		return (this.list_fruits.size() == 0);
	}
	
	/**
	 * Incrément le score selon le fruit mangé.
	 * Si Pacman a consommé la super-gomme, les fantômes deviennent vulnérables.
	 * @param tmp_fruit Le fruit mangé.
	 * @return L'index du fruit mangé (-1 en cas d'échec).
	 */
	private int fruit_eaten(Fruits tmp_fruit) {
		int index =- 1;
		if (tmp_fruit != null) {
			int value = tmp_fruit.getValue();
			this.pacman.inc_Score(value);
			if (tmp_fruit.getType() == '*') {
				this.pacman.set_combo(0);
				Liste<Ghost> list_ghosts = this.list_ghosts;
				for (int i = 0; i < list_ghosts.size(); i++) {
					Ghost ghost = list_ghosts.get(i);
					ghost.set_vul();
				}
			}
			index = erase_fruit(tmp_fruit);
		}
		return index;
	}
	
	/**
	 * Détermine le comportement en cas de contact de Pacman avec un fantôme:
	 * Si pacman touche un fantôme et que ce fantôme est vulnérable, le score est incrémenté, le fantôme est téléporté à sa position initiale et son temps de vulnérabilité est mise à 0.
	 * @param tmp_ghost Fantôme en contact avec Pacman.
	 */
	private void maj_ghost_pacman(Ghost tmp_ghost) {
		if (tmp_ghost != null) {
			Pacman pacman = this.pacman;
			if (tmp_ghost.is_vulnerable()) {
				pacman.set_combo(pacman.get_combo() + 1);
				pacman.inc_Score((int) (100 * Math.pow(2, pacman.get_combo())));
				int Pos_X = tmp_ghost.get_init_Pos_X();
				int Pos_Y = tmp_ghost.get_init_Pos_Y();
				tmp_ghost.set_Pos_x(Pos_X);
				tmp_ghost.set_Pos_y(Pos_Y);
				tmp_ghost.remove_vul();
			} else {
				pacman.dec_Points_vie();
				int Pos_X = pacman.get_init_Pos_X();
				int Pos_Y = pacman.get_init_Pos_Y();
				pacman.set_Pos_x(Pos_X);
				pacman.set_Pos_y(Pos_Y);
				Liste<Ghost> list_ghosts = this.list_ghosts;
				for (int i = 0; i < list_ghosts.size(); i++) {
					Ghost ghost = list_ghosts.get(i);
					Pos_X = ghost.get_init_Pos_X();
					Pos_Y = ghost.get_init_Pos_Y();
					ghost.set_Pos_x(Pos_X);
					ghost.set_Pos_y(Pos_Y);
					ghost.remove_vul();
				}
			}
		}
	}
	
	/**
	 * Renvoie le numéro du niveau actuel pour l'affichage.
	 * @return Numéro du niveau actuel.
	 */
	public int get_actual_level() {
		return this.actual_level;
	}
	
	/**
	 * Renvoie le score pour l'affichage.
	 * @return Score actuel.
	 */
	public int get_score() {
		return this.pacman.getScore();
	}
	
	/**
	 * Renvoie le nombre de vies pour l'affichage.
	 * @return Le nombre de vies.
	 */
	public int get_point_vie() {
		return this.pacman.getPoints_vie();
	}
	
	/**
	 * Renvoie la liste des murs pour l'affichage.
	 * @return La liste des murs du niveau actuel.
	 */
	public Liste<Walls> get_walls() {
		return this.list_walls;
	}
	
	/**
	 * Renvoie la liste des fruits pour l'affichage.
	 * @return La liste des fruits du niveau actuel.
	 */
	public Liste<Fruits> get_fruits() {
		return this.list_fruits;
	}
	
	/**
	 * Renvoie la liste des fantômes pour l'affichage.
	 * @return La liste des fantômes du niveau actuel.
	 */
	public Liste<Ghost> get_ghosts() {
		return this.list_ghosts;
	}
	
	/**
	 * Renvoie la liste des téléporteurs pour l'affichage.
	 * @return La liste des téléporteurs  du niveau actuel.
	 */
	public Liste<Telep> get_telep() {
		return this.list_telep;
	}
	
	/**
	 * Renvoie une instance de Pacman, utile pour initialiser un premier pacman ou pour lire la position initial du pacman à placer à l'affichage.
	 * @return Une instance de Pacman.
	 */
	public Pacman get_pacman() {
		return this.pacman;
	}
	
	/**
	 * Met à jour les données exploitables par l'affichage à chaque étape:<br>
	 * -Déplace Pacman<br>
	 * -Change l'orientation de Pacman<br>
	 * -Déplace les fantômes<br>
	 * -Détermine si Pacman mange un fruit et le supprime de la liste des fruits<br>
	 * -Change de niveau si tous les fruits ont été consommés.<br>
	 * -Détermine le comportement du jeu en cas de contact de Pacman avec un fantôme.<br>
	 * -Détermine le comportement du jeu en cas de contact de Pacman avec un téléporteur.<br>
	 * @param direction Direction de déplacement de Pacman choisie par le joueur.<br>
	 * @return Renvoie l'index du fruit en contact avec pacman dans cette étape ou -2 si Pacman n'a plus de vie.<br>
	 */
	public int update(char direction) {
		Pacman pacman = this.pacman;
		move_pacman(direction);
		ghost_random_move();
		Fruits tmp_fruit = pacman.touched_Fruit(this.list_fruits);
		int index=fruit_eaten(tmp_fruit);
		if (all_fruits_are_consumed()) {
			maj_controller();
		}
		Ghost tmp_ghost = pacman.touched_Ghost(this.list_ghosts);
		maj_ghost_pacman(tmp_ghost);
		if (pacman.is_Dead() && this.actual_level != 5) {
			this.actual_level = 4;
			maj_controller();
			return -2;
		}
		pacman.check_teleport(this.list_telep, direction);
		return index;
	}
}
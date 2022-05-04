package Logic;
import Listes.Liste;

public class Ghost implements Movement {
	/**
	 * Abscisse de la position initiale du fantôme.
	 */
	private int init_pos_X;

	/**
	 * Ordonnée de la position initiale du fantôme.
	 */
	private int init_pos_Y;

	/**
	 * Abscisse de la position actuelle du fantôme.
	 */
	private int pos_X;

	/**
	 * Ordonnée de la position actuelle du fantôme.
	 */
	private int pos_Y;

	/**
	 * Direction actuelle de déplacement du fantôme.
	 */
	private char direction;

	/**
	 * Identifiant du fantôme pour associer la bonne couleur au fantôme.
	 */
	private int id;

	/**
	 * Vitesse de déplacement du fantôme en pixels/frame.
	 */
	private int speed;

	/**
	 * Temps de vulnérabilité du fantôme représenté par un compteur.
	 * Si le temps est nul alors le fantôme n'est pas vulnérable, sinon il est vulnérable.
	 * Ce temps est décrémenté à chaque frame.
	 */
	private int vulnerable_time;

	/**
	 * Constructeur
	 * @param pos_x Abscisse de la position initiale du fantôme.
	 * @param pos_y Ordonnée de la position initiale du fantôme.
	 * @param direction Direction initiale de déplacement du fantôme.
	 * @param id Identifiant du fantôme pour associer la bonne couleur au fantôme.
	 * @param speed Vitesse de déplacement du fantôme en pixels/frame.
	 * @param vulnerable_time Temps de vulnérabilité initial du fantôme.
	 */
	public Ghost(int pos_x, int pos_y, char direction, int id, int speed, int vulnerable_time) {
		this.pos_X = pos_x;
		this.pos_Y = pos_y;
		this.init_pos_X = pos_x;
		this.init_pos_Y = pos_y;
		this.direction = direction;
		this.id = id;
		this.speed = speed;
		this.vulnerable_time = vulnerable_time;
	}

	/**
	 * Détermine si le fantôme est vulnérable.
	 * @return Renvoie true si le fantôme est vulnérable, false sinon.
	 */
	public boolean is_vulnerable() {
		return (vulnerable_time != 0);
	}

	/**
	 * Renvoie l'abscisse de la position actuelle du fantôme.
	 * @return Abscisse de la position actuelle du fantôme.
	 */
	public int getPos_X() {
		return this.pos_X;
	}

	/**
	 * Renvoie l'ordonnée de la position actuelle du fantôme.
	 * @return Ordonnée de la position actuelle du fantôme.
	 */
	public int getPos_Y() {
		return this.pos_Y;
	}

	/**
	 * Renvoie l'abscisse de la position initiale du fantôme.
	 * @return Abscisse de la position initiale du fantôme.
	 */
	public int get_init_Pos_X() {
		return this.init_pos_X;
	}

	/**
	 * Renvoie l'ordonnée de la position initiale du fantôme.
	 * @return Ordonnée de la position initiale du fantôme.
	 */
	public int get_init_Pos_Y() {
		return this.init_pos_Y;
	}

	/**
	 * Renvoie l'identifiant du fantôme;
	 * @return Identifiant du fantôme pour associer la bonne couleur au fantôme.
	 */
	public int get_id() {
		return this.id;
	}

	/**
	 * Renvoie la vitesse de déplacement du fantôme.
	 * @return Vitesse de déplacement du fantôme en pixels/frame.
	 */
	public int get_speed() {
		return this.speed;
	}

	/**
	 * Renvoie la direction actuelle de déplacement du fantôme.
	 * @return Direction actuelle de déplacement du fantôme.
	 */
	public char getDirection() {
		return this.direction;
	}

	/**
	 * Redéfinit la vitesse de déplacement du fantôme.
	 * @param speed Vitesse de déplacement du fantôme en pixels/frame.
	 */
	public void set_speed(int speed) {
		this.speed = speed;
	}

	/**
	 * Redéfinit la direction actuelle de déplacement du fantôme.
	 * @param direction Nouvelle direction à prendre représenté par un caractère flèche.
	 */
	public void setDirection(char direction) {
		this.direction = direction;
	}

	/**
	 * Désincrémente la vulnérabilité si possible.
	 */
	public void dec_vul() {
		if (this.vulnerable_time != 0) {
			this.vulnerable_time -= 1; 
		} 
	}

	/**
	 * Rend le fantôme vulnérable.
	 */
	public void set_vul() {
		this.vulnerable_time = 100;
	}

	/**
	 * Enlève la vulnérabilité du fantôme.
	 */
	public void remove_vul() {
		this.vulnerable_time = 0;
	}

	/**
	 * Détermine si le déplacement du fantôme est possible dans la direction voulue.
	 * @return Renvoie true si le déplacement du fantôme est possible dans la direction voulue, false sinon.
	 */
	public boolean is_valid_move(char direction, Liste<Walls> list_walls) {
		return (max_distance(direction, list_walls) != 0); 
	}

	/**
	 * Détermine la distance du fantôme avec un mur idéal placé pour bloquer le fantôme dans la direction voulue.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Distance du fantôme avec le mur idéal.
	 */
	private int distance_ideal_wall(char direction) {
		switch (direction) {
			case '↑':
				return pos_Y % 40;
			case '↓':
				return (40 - pos_Y % 40) % 40;
			case '→':
				return (40 - pos_X % 40) % 40;
			case '←':
				return pos_X % 40;
		}
		return 0;
	}
	
	/**
	 * Abscisse de la position du mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme est aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Abscisse de la position du mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_x(char direction) {
		switch (direction) {
			case '↑':
				return this.pos_X;
			case '↓':
				return this.pos_X;
			case '←':
				return this.pos_X - this.pos_X % 40 - 40;
			case '→':
				return this.pos_X + 40 + (40 - this.pos_X % 40) % 40;
		}
		return 0;
	}

	/**
	 * Ordonnée de la position du mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme est aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Ordonnée de la position du mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_y(char direction) {
		switch (direction) {
			case '↑':
				return pos_Y - pos_Y % 40 - 40;
			case '↓':
				return pos_Y + 40 + (40 - pos_Y % 40) % 40;
			case '←':
				return pos_Y;
			case '→':
				return pos_Y;
		}
		return 0;
	}

	/**
	 * Abscisse de la position du premier mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme n'est pas aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Abscisse de la position du premier mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_x1(char direction) {
		switch (direction) {
			case '↑':
				return this.pos_X - this.pos_X % 40;
			case '↓':
				return this.pos_X - this.pos_X % 40;
			case '←':
				return this.pos_X - this.pos_X % 40 - 40;
			case '→':
				return this.pos_X + 40 + (40 - this.pos_X % 40) % 40;
		}
		return 0;
	}

	/**
	 * Ordonnée de la position du premier mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme n'est pas aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Ordonnée de la position du premier mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_y1(char direction) {
		switch (direction) {
			case '↑':
				return this.pos_Y - this.pos_Y % 40 - 40;
			case '↓':
				return this.pos_Y + 40 + (40 - this.pos_Y % 40) % 40;
			case '←':
				return this.pos_Y - this.pos_Y % 40;
			case '→':
				return this.pos_Y - this.pos_Y % 40;
		}
		return 0;
	}

	/**
	 * Abscisse de la position du second mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme n'est pas aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Abscisse de la position du second mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_x2(char direction) {
		switch (direction) {
			case '↑':
				return this.pos_X - this.pos_X % 40 + 40;
			case '↓':
				return this.pos_X - this.pos_X % 40 + 40;
			case '←':
				return this.pos_X - this.pos_X % 40 - 40;
			case '→':
				return this.pos_X + 40 + (40 - this.pos_X % 40) % 40;
		}
		return 0;
	}

	/**
	 * Ordonnée de la position du second mur idéal pour bloquer le fantôme dans la direction voulue<br>
	 * si le fantôme n'est pas aligné avec une ligne ou une colonne selon la direction prise.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @return Ordonnée de la position du second mur idéal pour bloquer le fantôme dans la direction voulue.
	 */
	private int ideal_wall_Pos_y2(char direction) {
		switch (direction) {
			case '↑':
				return this.pos_Y - this.pos_Y % 40 - 40;
			case '↓':
				return this.pos_Y + 40 + (40 - this.pos_Y % 40) % 40;
			case '←':
				return this.pos_Y - this.pos_Y % 40 + 40;
			case '→':
				return this.pos_Y - this.pos_Y % 40 + 40;
		}
		return 0;
	}

	/**
	 * Détermine la distance maximale parcourable en une frame en fonction de la vitesse de déplacement des fantômes, la direction de déplacement et les murs à proximité.
	 * @param direction Direction actuelle de déplacement du fantôme représenté par un caractère flèche.
	 * @param list_walls Liste des murs du plateau.
	 * @return Distance maximale parcourable en une frame dans la direction choisie.
	 */
	public int max_distance(char direction, Liste<Walls> list_walls) {
		int length_list = list_walls.size();
		int ideal_wall_x = ideal_wall_Pos_x(direction);
		int ideal_wall_y = ideal_wall_Pos_y(direction);
		int ideal_wall_x1 = ideal_wall_Pos_x1(direction);
		int ideal_wall_y1 = ideal_wall_Pos_y1(direction);
		int ideal_wall_x2 = ideal_wall_Pos_x2(direction);
		int ideal_wall_y2 = ideal_wall_Pos_y2(direction);
		int speed = this.speed;
		int alignement = 0;
		int distance = distance_ideal_wall(direction);
		if (direction == '↑' || direction == '↓') {
			alignement = pos_X % 40;
		} else {
			alignement = pos_Y % 40;
		}
		switch (alignement) {
			case 0:
				for (int i = 0; i < length_list; i++) {
					Walls tmp_wall = list_walls.get(i);
					if (tmp_wall.getPos_X() == ideal_wall_x &&
							tmp_wall.getPos_Y() == ideal_wall_y) {
						return Math.min(speed, distance);
					}
				}
				break;
			default:
				for (int i = 0; i < length_list; i++) {
					Walls tmp_wall = list_walls.get(i);
					if (tmp_wall.getPos_X() == ideal_wall_x1 &&
						tmp_wall.getPos_Y() == ideal_wall_y1 || 
						tmp_wall.getPos_X() == ideal_wall_x2 &&
						tmp_wall.getPos_Y() == ideal_wall_y2) {
						return Math.min(speed, distance);
					}
				}
				break;
		}
		return speed;
	}

	/**
	 * Redéfinit l'abscisse de la position du fantôme.
	 * @param x Abscisse de la nouvelle position du fantôme.
	 */
	@Override
	public void set_Pos_x(int x) {
		this.pos_X = x;
	}

	/**
	 * Redéfinit l'ordonnée de la position du fantôme.
	 * @param y Ordonnée de la nouvelle position du fantôme.
	 */
	@Override
	public void set_Pos_y(int y) {
		this.pos_Y = y;
	}

	/**
	 * Déplace le fantôme vers le haut si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveUP(Liste<Walls> list_walls) {
		if (is_valid_move('↑', list_walls)) {
			int tmp_d = max_distance('↑', list_walls);
			this.pos_Y -= tmp_d;
		}
	}

	/**
	 * Déplace le fantôme vers le bas si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveDOWN(Liste<Walls> list_walls) {
		if (is_valid_move('↓', list_walls)) {
			int tmp_d = max_distance('↓', list_walls);
			this.pos_Y += tmp_d;
		}
	}

	/**
	 * Déplace le fantôme vers le bas si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveLEFT(Liste<Walls> list_walls) {
		if (is_valid_move('←', list_walls)) {
			int tmp_d = max_distance('←', list_walls);
			this.pos_X -= tmp_d;
		}
	}

	/**
	 * Déplace le fantôme vers la droite si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveRIGHT(Liste<Walls> list_walls) {
		if (is_valid_move('→', list_walls)) {
			int tmp_d = max_distance('→', list_walls);
				this.pos_X += tmp_d;
		}
	}
}
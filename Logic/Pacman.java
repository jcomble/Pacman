package Logic;
import Listes.Liste;

public class Pacman implements Colision, Movement, Score {
	/**
	 * Abscisse de la position initiale de Pacman.
	 */
	private int init_pos_X;
	
	/**
	 * Ordonnée de la position initiale de Pacman.
	 */
	private int init_pos_Y;
	
	/**
	 * Abscisse de la position actuelle de Pacman.
	 */
	private int pos_X;
	
	/**
	 * Ordonnée de la position actuelle de Pacman.
	 */
	private int pos_Y;
	
	/**
	 * Nombre de points de vie.
	 */
	private int points_Vie;
	
	/**
	 * Score du jeu.
	 */
	private int score;
	
	/**
	 * Caractère flèche représentant la direction de déplacement.
	 */
	private char direction;
	
	/**
	 * Vitesse de déplacement de Pacman en pixels/frame.
	 */
	private int speed;
	
	/**
	 * Constructeur. La vitesse de Pacman sera toujours inférieur ou égal à 40 pixels/frame (déplacement d'un carreau/frame)
	 * @param speed Vitesse de déplacement de Pacman en pixels/frame.
	 * @param pos_x_base Abscisse de la position initiale de Pacman.
	 * @param pos_y_base Ordonnée de la position actuelle de Pacman.
	 */
	public Pacman(int speed, int pos_x_base, int pos_y_base) {
		this.pos_X = pos_x_base;
		this.pos_Y = pos_y_base;
		this.init_pos_X = pos_x_base;
		this.init_pos_Y = pos_y_base;
		this.points_Vie = 3;
		this.score = 0;
		this.direction = '→';
		this.speed = Math.max(0, Math.min(40, speed));
	}
	
	/**
	 * Renvoie l'abscisse de la position actuelle de Pacman.
	 * @return Abscisse de la position actuelle de Pacman.
	 */
	public int getPos_X() {
		return this.pos_X;
	}
	
	/**
	 * Renvoie l'ordonnée de la position actuelle de Pacman.
	 * @return Ordonnée de la position actuelle de Pacman.
	 */
	public int getPos_Y() {
		return this.pos_Y;
	}
	
	/**
	 * Renvoie l'abscisse de la position initiale de Pacman.
	 * @return Abscisse de la position initiale de Pacman.
	 */
	public int get_init_Pos_X() {
		return this.init_pos_X;
	}
	
	/**
	 * Renvoie l'ordonnée de la position initiale de Pacman.
	 * @return Ordonnée de la position initiale de Pacman.
	 */
	public int get_init_Pos_Y() {
		return this.init_pos_Y;
	}
	
	/**
	 * Renvoie le nombre de vie de Pacman.
	 * @return Nombre de vie de Pacman.
	 */
	@Override
	public int getPoints_vie() {
		return this.points_Vie;
	}
	
	/**
	 * Renvoie le score actuel.
	 * @return Score actuel.
	 */
	@Override
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Renvoie la direction de déplacement actuelle de Pacman.
	 * @return Le caractère flèche correspondant à la direction de déplacement actuelle de Pacman.
	 */
	public char getDirection() {
		return this.direction;
	}
	
	/**
	 * Renvoie la vitesse de déplacement de Pacman en pixels/frame.
	 * @return Vitesse de déplacement de Pacman.
	 */
	public int get_speed() {
		return this.speed;
	}
	
	/**
	 * Détermine si la direction de déplacement permet un déplacement de Pacman.
	 * @param list_walls Liste des murs du plateau.
	 * @param direction Caractère flèche représentant la direction à prendre.
	 * @return Renvoie true le déplacement est possible dans la direction voulue.
	 */
	@Override
	public boolean is_valid_move(char direction, Liste<Walls> list_walls) {
		return (max_distance(direction, list_walls) != 0); 
	}
	
	/**
	 * Détermine la distance maximale parcourable en une frame en fonction de la vitesse de déplacement de Pacman, la direction de déplacement et les murs à proximité.
	 * @param direction Direction actuelle de déplacement de Pacman.
	 * @param list_walls Liste des murs du plateau.
	 * @return Distance maximale parcourable en une frame dans la direction choisie.
	 */
	public int max_distance(char direction, Liste<Walls> list_walls) {
		int length_list = list_walls.size();
		int ideal_wall_x = 0;
		int ideal_wall_y = 0;
		int ideal_wall_x1 = 0;
		int ideal_wall_y1 = 0;
		int ideal_wall_x2 = 0;
		int ideal_wall_y2 = 0;
		int pos_X = this.pos_X;
		int pos_Y = this.pos_Y;
		int speed = this.speed;
		int alignement = 0;
		int distance = 0;
		if (direction == '↑' || direction == '↓') {
			alignement = pos_X % 40;
		} else {
			alignement = pos_Y % 40;
		}
		switch (direction) {
			case '↑':
				ideal_wall_x = pos_X;
				ideal_wall_y = pos_Y - pos_Y % 40 - 40;
				ideal_wall_x1 = pos_X - pos_X % 40;
				ideal_wall_y1 = pos_Y - pos_Y % 40 - 40;
				ideal_wall_x2 = pos_X - pos_X % 40 + 40;
				ideal_wall_y2 = pos_Y - pos_Y % 40 - 40;
				distance = pos_Y % 40;
				break;
			case '↓':
				ideal_wall_x = pos_X;
				ideal_wall_y = pos_Y + 40 + (40 - pos_Y % 40) % 40;
				ideal_wall_x1 = pos_X - pos_X % 40;
				ideal_wall_y1 = pos_Y + 40 + (40 - pos_Y % 40) % 40;
				ideal_wall_x2 = pos_X - pos_X % 40 + 40;
				ideal_wall_y2 = pos_Y + 40 + (40 - pos_Y % 40) % 40;
				distance = (40 - pos_Y % 40) % 40;
				break;
			case '←':
				ideal_wall_x = pos_X - pos_X % 40 - 40;
				ideal_wall_y = pos_Y;
				ideal_wall_x1 = pos_X - pos_X % 40 - 40;
				ideal_wall_y1 = pos_Y - pos_Y % 40;
				ideal_wall_x2 = pos_X - pos_X % 40 - 40;
				ideal_wall_y2 = pos_Y - pos_Y % 40 + 40;
				distance = pos_X % 40;
				break;
			case '→':
				ideal_wall_x = pos_X + 40 + (40 - pos_X % 40) % 40;
				ideal_wall_y = pos_Y;
				ideal_wall_x1 = pos_X + 40 + (40 - pos_X % 40) % 40;
				ideal_wall_y1 = pos_Y - pos_Y % 40;
				ideal_wall_x2 = pos_X + 40 + (40 - pos_X % 40) % 40;
				ideal_wall_y2 = pos_Y - pos_Y % 40 + 40;
				distance = (40 - pos_X % 40) % 40;
				break;
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
	 * Détermine la distance entre deux points.
	 * @param x_1 Abscisse du point 1.
	 * @param y_1 Ordonnée du point 1.
	 * @param x_2 Abscisse du point 2.
	 * @param y_2 Ordonnée du point 2.
	 * @return Distance entre les deux points.
	 */
	private double distance(int x_1, int y_1, int x_2, int y_2) {
		return Math.sqrt(Math.pow(x_2 - x_1, 2) + Math.pow(y_2 - y_1, 2));
	}
	
	/**
	 * Détermine si Pacman est en contact avec un fantôme.
	 * @param list_ghosts Liste des fantômes du plateau.
	 * @return Renvoie true si Pacman est bien en contact avec un fantôme, false sinon.
	 */
	@Override
	public boolean is_touching_Ghost(Liste<Ghost> list_ghosts) {
		int length = list_ghosts.size();
		for (int i = 0; i < length; i++) {
			Ghost tmp_ghost = list_ghosts.get(i);
			int x_1 = this.pos_X;
			int y_1 = this.pos_Y;
			int x_2 = tmp_ghost.getPos_X();
			int y_2 = tmp_ghost.getPos_Y();
			 if (distance(x_1, y_1, x_2, y_2) < 40.0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Détermine si Pacman est en contact avec un fruit.
	 * @param list_fruits Liste des fruits du plateau.
	 * @return Renvoie true si Pacman est bien en contact avec un fruit, false sinon.
	 */
	@Override
	public boolean is_touching_Fruit(Liste<Fruits> list_fruits) {
		int length = list_fruits.size();
		for (int i = 0; i < length; i++) {
			Fruits tmp_fruits = list_fruits.get(i);
			int x_1 = this.pos_X;
			int y_1 = this.pos_Y;
			int x_2 = tmp_fruits.getPos_X();
			int y_2 = tmp_fruits.getPos_Y();
			 if (distance(x_1, y_1, x_2, y_2) < 40.0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Détermine si Pacman est en contact avec un téléporteur.
	 * @param list_telep Liste des téléporteurs du jeu.
	 * @param direction Direction de déplacement de Pacman.
	 */
	public void check_teleport(Liste<Telep> list_telep, char direction) {
		for (int i = 0; i < list_telep.size(); i++) {
			Telep tmp_telep = list_telep.get(i);
			int x_1 = this.pos_X;
			int y_1 = this.pos_Y;
			int x_2 = tmp_telep.getPos_X();
			int y_2 = tmp_telep.getPos_Y();
			double d_1 = distance(x_1, y_1, x_2, y_2);
			double d_2 = 0;
			int speed = this.speed;
			switch (direction) {
				case '↑':
					d_2 = distance(x_1, y_1 - speed, x_2, y_2);
					break;
				case '↓':
					d_2 = distance(x_1, y_1 - speed, x_2, y_2);
					break;
				case '→':
					d_2 = distance(x_1 + speed, y_1, x_2, y_2);
					break;
				case '←':
					d_2 = distance(x_1 - speed, y_1,  x_2, y_2);
					break;
			}
			if (d_1 < 40.0 && d_2 < d_1 || d_1 == 0.0) {
				Telep destination = tmp_telep.getNext_Telep();
				this.pos_X = destination.getPos_X();
				this.pos_Y = destination.getPos_Y();
				switch (direction) {
					case '↑':
						this.pos_Y -= 40;
						break;
					case '↓':
						this.pos_Y += 40;
						break;
					case '→':
						this.pos_X += 40;
						break;
					case '←':
						this.pos_X -= 40;
						break;
				}
			}
		}
	}
	
	/**
	 * Détermine si Pacman est définitivement mort.
	 * @return Renvoie la véracité de la mort de Pacman.
	 */
	@Override
	public boolean is_Dead() {
		return (getPoints_vie() == 0);
	}
	
	/**
	 * Redéfinit l'abscisse de la position actuelle de Pacman.
	 * @param x Abscisse de la position actuelle de Pacman.
	 */
	@Override
	public void set_Pos_x(int x) {
		this.pos_X = x;
	}
	
	/**
	 * Redéfinit l'ordonnée de la position actuelle de Pacman.
	 * @param y Ordonnée de la position actuelle de Pacman.
	 */
	@Override
	public void set_Pos_y(int y) {
		this.pos_Y = y;
	}
	
	/**
	 * Redéfinit l'abscisse de la position initiale de Pacman.
	 * @param x Abscisse de la position initiale de Pacman.
	 */
	public void set_init_Pos_x(int x) {
		this.init_pos_X = x;
	}
	
	/**
	 * Redéfinit l'ordonnée de la position initiale de Pacman.
	 * @param y Ordonnée de la position initiale de Pacman.
	 */
	public void set_init_Pos_y(int y) {
		this.init_pos_Y = y;
	}
	
	/**
	 * Redéfinit le nombre de point de vies de Pacman.
	 * @param number_lives Nombre de point de vies.
	 */
	@Override
	public void set_Points_Vie(int number_lives) {
		this.points_Vie = number_lives;
	}
	
	/**
	 * Redéfinit le score du jeu.
	 * @param score Valeur de score.
	 */
	@Override
	public void set_Score(int score) {
		this.score = score;
	}
	
	/**
	 * Redéfinit la direction actuelle de déplacement de Pacman.
	 * @param direction Nouvelle sirection de déplacement de Pacman.
	 */
	public void set_Direction(char direction) {
		this.direction = direction;
	}
	
	/**
	 * Redéfinit la vitesse de déplacement du fantôme.
	 * @param speed Vitesse de déplacement du fantôme en pixels/frame.
	 */
	@Override
	public void set_speed(int speed) {
		this.speed = speed;
	}

	/**
	 * Incrémente le score du jeu et le nombre de vie de 1 à chaque tranche de 10000 points.
	 * @param number_score Valeur de l'incrément.
	 */
	@Override
	public void inc_Score(int number_score) {
		int last_score = this.score;
		int new_score = last_score + number_score;
		this.points_Vie += new_score / 10000 - last_score / 10000;
		this.score = new_score;
	}
	
	/**
	 * Incrémente le nombre de vie de Pacman de 1.
	 */
	@Override
	public void inc_Points_vie() {
		this.points_Vie += 1;
	}
	
	/**
	 * Décrémente le nombre de vie de Pacman de 1.
	 */
	@Override
	public void dec_Points_vie() {
		this.points_Vie -= 1;
	}
	
	/**
	 * Déplace Pacman vers le haut si possible.
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
	 * Déplace Pacman vers le bas si possible.
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
	 * Déplace Pacman vers la gauche si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveLEFT(Liste<Walls> list_walls) {
		if (is_valid_move('←', list_walls)) {
			int tmp_d = max_distance('←', list_walls);
			this.pos_X -=tmp_d;
		}
	}
	
	/**
	 * Déplace Pacman vers la droite si possible.
	 * @param list_walls Liste des murs du plateau.
	 */
	@Override
	public void moveRIGHT(Liste<Walls> list_walls) {
		if (is_valid_move('→', list_walls)) {
			int tmp_d = max_distance('→', list_walls);
			this.pos_X += tmp_d;
		}
	}
	
	/**
	 * Détermine si Pacman est en contact avec un fantôme.
	 * @param list_ghosts Liste des fantômes du plateau.
	 * @return Renvoie le fantôme en contact avec Pacman, null si échéant.
	 */
	public Ghost touched_Ghost(Liste<Ghost> list_ghosts) {
		int length = list_ghosts.size();
		for (int i = 0; i < length; i++) {
			Ghost tmp_ghost = list_ghosts.get(i);
			int x_1 = getPos_X();
			int y_1 = getPos_Y();
			int x_2 = tmp_ghost.getPos_X();
			int y_2 = tmp_ghost.getPos_Y();
			 if (distance(x_1, y_1, x_2, y_2) < 40.0) {
				return tmp_ghost;
			}
		}
		return null;
	}
	
	/**
	 * Détermine si Pacman est en contact avec un fruit.
	 * @param list_fruits Liste des fruits du plateau.
	 * @return Renvoie le fruit en contact avec Pacman, null si échéant.
	 */
	public Fruits touched_Fruit(Liste<Fruits> list_fruits) {
        int length = list_fruits.size();
        for (int i = 0; i < length; i++) {
            Fruits tmp_fruits = list_fruits.get(i);
            int x_1 = this.pos_X;
            int y_1 = this.pos_Y;
            int x_2 = tmp_fruits.getPos_X();
            int y_2 = tmp_fruits.getPos_Y();
            if (distance(x_1, y_1, x_2, y_2) < 40.0) {
                return tmp_fruits;
            }
        }
        return null;
    }
}

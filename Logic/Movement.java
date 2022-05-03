package Logic;
import Listes.Liste;

public interface Movement {
	/**
	 * Positionne une entité à la position x.
	 * @param x un entier positif correspondant à une case du plateau inférieur à la longueur du plateau.
	 */
	public void set_Pos_x(int x);
	
	/**
	 * Positionne une entité à la position y.
	 * @param y un entier positif correspondant à une case du plateau inférieur à la hauteur du plateau.
	 */
	public void set_Pos_y(int y);
	
	/**
	 * Déplace une entité vers le haut pas à pas.
	 * @param list_walls Liste des murs du plateau.
	 */
	public void moveUP(Liste<Walls> list_walls);
	
	/**
	 * Déplace une entité vers le bas pas à pas.
	 * @param list_walls Liste des murs du plateau.
	 */
	public void moveDOWN(Liste<Walls> list_walls);
	
	/**
	 * Déplace une entité vers la droite pas à pas.
	 * @param list_walls Liste des murs du plateau.
	 */
	public void moveRIGHT(Liste<Walls> list_walls);
	
	/**
	 * Déplace une entité vers la gauche pas à pas.
	 * @param list_walls Liste des murs du plateau.
	 */
	public void moveLEFT(Liste<Walls> list_walls);
	
	/**
	 * Détermine si le déplacement est valide selon la direction à prendre et les murs.
	 * @param direction Caractère flèche représentant la direction à prendre.
	 * @param list_walls Liste des murs du plateau.
	 * @return true s'il s'agit d'un déplacement valide, false sinon.
	 */
	public boolean is_valid_move(char direction, Liste<Walls> list_walls);
	
	/**
	 * Détermine la distance maximale parcourable en une frame en fonction de la vitesse de déplacement de Pacman, la direction de déplacement et les murs à proximité.
	 * @param direction Direction actuelle de déplacement de Pacman.
	 * @param list_walls Liste des murs du plateau.
	 * @return Distance maximale parcourable en une frame dans la direction choisie.
	 */
	public int max_distance(char direction, Liste<Walls> list_walls);
	
	/**
	 * Définit la vitesse de déplacement des entités de jeu.
	 * @param speed Un nombre entier représentant la vitesse de déplacement des entités de jeu en pixel par frame.
	 */
	public void set_speed(int speed);
}

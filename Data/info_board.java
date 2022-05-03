package Data;

import Logic.Fruits;
import Logic.Ghost;
import Logic.Pacman;

public interface info_board {
	/**
	 * Place le Pacman à une position du plateau.
	 * @param pacman Objet représentant Pacman
	 * @param x abscisse de la position initiale
	 * @param y ordonnée de la position initiale
	 */
	public void put_Pacman(Pacman pacman, int x, int y);
	
	/**
	 * Place le fantôme à une position du plateau.
	 * @param ghost Objet représentant le fantôme
	 * @param x abscisse de la position initiale
	 * @param y ordonnée de la position initiale
	 */
	public void put_Ghost(Ghost ghost, int x, int y);
	
	/**
	 * Place le fruit à une position du plateau.
	 * @param fruit Objet représentant le fruit
	 * @param x abscisse de la position initiale
	 * @param y ordonnée de la position initiale
	 */
	public void put_Fruit(Fruits fruit, int x, int y);
	
	/**
	 * Renvoie le nombre de fruits restant.
	 * @return Entier représentant le nombre de fruit restant
	 */
	public int number_of_Fruits();
}

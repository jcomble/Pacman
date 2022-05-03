package Logic;
import Listes.Liste;

public interface Colision {
	/**
	 * Détermine si Pacman est en contact avec au moins un fantôme.
	 * @param list_ghosts Liste des fantômes du plateau.
	 * @return true si Pacman est en contact avec au moins un fantôme, false sinon.
	 */
	public boolean is_touching_Ghost(Liste<Ghost> list_ghosts);
	
	/**
	 * Détermine si Pacman est en contact avec un fruit.
	 * @param list_fruits Liste des fruits du plateau.
	 * @return true si Pacman est en contact avec un fruit, false sinon.
	 */
	public boolean is_touching_Fruit(Liste<Fruits> list_fruits);
	
	/**
	 * Détermine le fruit en contact avec Pacman.
	 * @param list_fruits Liste des fruits du plateau.
	 * @return Le fruit en contact avec Pacman, null le cas échéant.
	 */
	public Fruits touched_Fruit(Liste<Fruits> list_fruits);
}
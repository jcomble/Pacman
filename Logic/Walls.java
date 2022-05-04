package Logic;

public class Walls {
	/**
	 * Abscisse de la position du mur.
	 */
	private int pos_X;

	/**
	 * Ordonnée de la position du mur.
	 */
	private int pos_Y;

	/**
	 * Constructeur.
	 * @param pos_x Abscisse de la position du mur.
	 * @param pos_y Ordonnée de la position du mur.
	 */
	public Walls(int pos_x, int pos_y) {
		this.pos_X = pos_x;
		this.pos_Y = pos_y;
	}

	/**
	 * Renvoie l'abscisse de la position du mur.
	 * @return Abscisse de la position du mur.
	 */
	public int getPos_X() {
		return this.pos_X;
	}

	/**
	 * Renvoie l'ordonnée de la position du mur.
	 * @return Ordonnée de la position du mur.
	 */
	public int getPos_Y() {
		return this.pos_Y;
	}
}


















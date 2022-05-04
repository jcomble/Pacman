package Data;

public interface Position {
	/**
	 * Détermine la position de l'entité de jeu.
	 * @return un entier représentant l'abscisse de la position de l'entité.
	 */
	public int get_Pos_x();

	/**
	 * Détermine la position de l'entité de jeu.
	 * @return un entier représentant l'ordonnée de la position de l'entité.
	 */
	public int get_Pos_y();

	/**
	 * Détermine la vitesse de déplacement des entités de jeu.
	 * @return Un nombre décimal représentant la vitesse des entités de jeu.
	 */
	public float get_speed();

	/**
	 * Détermine la direction de déplacement des entités de jeu
	 * @return Un caractère flèche représentant la direction de déplacement des entités de jeu
	 */
	public char get_Direction();
}



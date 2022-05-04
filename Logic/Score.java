package Logic;

public interface Score {
	/**
	 * Mise à jour du score.
	 * @param number_score Nouveau Score.
	 * @inv number_score supérieur ou égal à 0.
	 */
	public void set_Score(int number_score);

	/**
	 * Augmentation du score par un nombre de points entier positif.
	 * @param number_Score Nombre de points à ajouter.
	 * @inv number_Score Le score doit être supérieur ou égal à 0.
	 */
	public void inc_Score(int number_Score);

	/**
	 * Mise à jour du nombre de vie de Pacman.
	 * @param number_lives Nombre de vie.
	 */
	public void set_Points_Vie(int number_lives);

	/**
	 * Incrément du nombre de vie de 1.
	 * @inv number_lives Le nombre de vie doit être supérieur ou égal à 0.
	 */
	public void inc_Points_vie();

	/**
	 * Décrement du nombre de vie de 1.
	 * @inv number_lives Le nombre de vie doit être supérieur à 0.
	 */
	public void dec_Points_vie();

	/**
	 * Détermine si le nombre de vie de Pacman est égal à 0.
	 * @return true si le nombre de vie est supérieur à 0, false sinon.
	 */
	public boolean is_Dead();

	/**
	 * Détermine le nombre de point de vie de Pacman.
	 * @return Un entier représentant le nombre de point de vie de Pacman.
	 */
	public int getPoints_vie();

	/**
	 * Détermine le score de la partie.
	 * @return Un entier représentant le score de la partie
	 */
	public int getScore();
}

package Logic;

public class Telep {
	/**
	 * Pointeur téléporteur d'arrivée.
	 */
    private Telep telep;

    /**
     * Abscisse de la position du téléporteur.
     */
    private int pos_X;

    /**
     * Ordonnée de la position du téléporteur.
     */
    private int pos_Y;

    /**
     * Constructeur du téléporteur sans destination.
     * @param pos_x Abscisse de la position du téléporteur.
     * @param pos_y Ordonnée de la position du téléporteur.
     */
    public Telep(int pos_x, int pos_y) {
        this.telep = null;
        this.pos_X = pos_x;
        this.pos_Y = pos_y;
    }

    /**
     * Constructeur du téléporteur avec destination.
     * @param pos_x Abscisse de la position du téléporteur.
     * @param pos_y Ordonnée de la position du téléporteur.
     * @param telep Pointeur téléporteur d'arrivée.
     */
    public Telep(int pos_x, int pos_y, Telep telep) {
        this.telep = telep;
        this.pos_X = pos_x;
        this.pos_Y = pos_y;
    }

    /**
     * Redéfinit le téléporteur d'arrivée.
     * @param telep Téléporteur d'arrivée.
     */
    public void set_Telep(Telep telep) {
        this.telep = telep;
    }

    /**
     * Renvoie le téléporteur d'arrivée.
     * @return Téléporteur d'arrivée.
     */
    public Telep getNext_Telep() {
    	return this.telep;
    }

    /**
     * Renvoie l'abscisse de la position du téléporteur.
     * @return Abscisse de la position du téléporteur.
     */
    public int getPos_X() {
        return this.pos_X;
    }

    /**
     * Renvoie l'ordonnée de la position du téléporteur.
     * @return Ordonnée de la position du téléporteur.
     */
    public int getPos_Y() {
        return this.pos_Y;
    }
}

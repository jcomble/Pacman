package Logic;

public class Fruits{
	/**
	 * Abscisse de la position du fruit.
	 */
    private int pos_X;
    
    /**
     * Ordonnée de la position du fruit.
     */
    private int pos_Y;
    
    /**
     * Caractère correspondant au fruit:
     * g : Pomme
     * . : Super-Gomme
     */
    private char type;
    
    /**
     * Valeur du fruit à sa consommation.
     */
    private int valeur;

    /**
     * Constructeur.
     * @param pos_x Abscisse de la position du fruit.
     * @param pos_y Ordonnée de la position du fruit.
     * @param type Caractère correspondant au fruit:
     * g : Pomme
     * . : Super-Gomme
     * @param valeur Valeur du fruit à sa consommation.
     */
    public Fruits(int pos_x, int pos_y, char type, int valeur) {
        this.pos_X = pos_x;
        this.pos_Y = pos_y;
        this.type = type;
        this.valeur = valeur;
    }
    
    /**
     * Renvoie l'abscisse de la position du fruit.
     * @return Abscisse de la position du fruit.
     */
    public int getPos_X() {
        return this.pos_X;
    }

    /**
     * Renvoie l'ordonnée de la position du fruit.
     * @return Ordonnée de la position du fruit.
     */
    public int getPos_Y() {
        return this.pos_Y;
    }

    /**
     * Renvoie la valeur du fruit à sa consommation.
     * @return Valeur du fruit à sa consommation.
     */
    public int getValue() {
        return this.valeur;
    }
    
    /**
     * Renvoie le caractère correspondant au fruit
     * @return Caractère correspondant au fruit:
     * g : Pomme
     * . : Super-Gomme
     */
    public char getType() {
        return this.type;
    }
}







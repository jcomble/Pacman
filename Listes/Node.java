package Listes;

public class Node<Type> {
	/**
	 * Contenu du Noeud.
	 */
	private Type content;

	/**
	 * Pointeur sur le noeud précédent.
	 */
	private Node<Type> before;

	/**
	 * Pointeur sur le noeud suivant de la liste.
	 */
	private Node<Type> next;

	/**
	 * Constructeur.
	 * @param content Contenu du noeud.
	 */
	public Node(Type content) {
		this.content = content;
	}

	/**
	 * Renvoie le contenu du noeud.
	 * @return Contenu du noeud.
	 */
	public Type get_content() {
		return this.content;
	}

	/**
	 * Renvoie le pointeur du noeud précédent.
	 * @return Pointeur du noeud précédent.
	 */
	public Node<Type> get_before() {
		return this.before;
	}

	/**
	 * Renvoie le pointeur du noeud suivant.
	 * @return Pointeur du noeud suivant.
	 */
	public Node<Type> get_next() {
		return this.next;
	}

	/**
	 * Redéfinit le noeud précédant.
	 * @param node Noeud précédant.
	 */
	public void set_before(Node<Type> node) {
		this.before = node;
	}

	/**
	 * Redéfinit le noeud suivant.
	 * @param node Noeud suivant.
	 */
	public void set_next(Node<Type> node) {
		this.next = node;
	}
}

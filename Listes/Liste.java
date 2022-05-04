package Listes;

public class Liste<Type> {
	/**
	 * Tête de la liste.
	 */
	private Node<Type> Heap;

	/**
	 * Constructeur de la liste doublement chaînée.
	 */
	public Liste() {
		this.Heap = null;
	}

	/**
	 * Ajout d'un élément dans la liste à la fin.
	 * @param element Nouvel élément de la liste.
	 */
	public void add(Type element) {
		Node<Type> tmp_node = new Node<Type>(element);
		if (Heap == null) {
			tmp_node.set_before(tmp_node);
			tmp_node.set_next(tmp_node);
			this.Heap=tmp_node;
		} else {
			Node<Type> last_note = this.Heap.get_before();
			this.Heap.set_before(tmp_node);
			tmp_node.set_before(last_note);
			tmp_node.set_next(this.Heap);
			last_note.set_next(tmp_node);
		}
	}

	/**
	 * Détermine la taille de la liste.
	 * @return Taille de la liste.
	 */
	public int size() {
		if (this.Heap != null) {
			int count = 1;
			Node<Type> tmp_node = this.Heap;
			while (tmp_node.get_next() != this.Heap) {
				count += 1;
				tmp_node = tmp_node.get_next();
			}
			return count;
		}
		return 0;
	}

	/**
	 * Retrait d'un élément dans la liste à la fin.
	 * @param index Index de l'élément à retirer de la liste.
	 */
	public void remove(int index) {
		assert index < size() && index >= 0 : "Index hors intervalle";
		if (size() == 1) {
			this.Heap = null;
		} else {
			if (index == 0) {
				Node<Type> next = this.Heap.get_next();
				Node<Type> before = this.Heap.get_before();
				next.set_before(before);
				before.set_next(next);
				this.Heap = next;
			} else {
				int tmp_index = 0;
				Node<Type> tmp_node = this.Heap;
				while (index != tmp_index) {
					tmp_node = tmp_node.get_next();
					tmp_index += 1;
				}
				Node<Type> next = tmp_node.get_next();
				Node<Type> before = tmp_node.get_before();
				next.set_before(before);
				before.set_next(next);
			}
		}
	}

	/**
	 * Détermine l'élément de la liste par son index.
	 * @param index Index de la liste.
	 * @return Renvoie l'élémént de la liste par son index.
	 */
	public Type get(int index) {
		assert index < size() && index >= 0 : "Index hors intervalle";
		Node<Type> tmp_node = this.Heap;
		int tmp_index = 0;
		while (index != tmp_index) {
			tmp_node = tmp_node.get_next();
			tmp_index += 1;
		}
		return tmp_node.get_content();
	}
}

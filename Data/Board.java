package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import Listes.Liste;
import Logic.Walls;
import Logic.Fruits;
import Logic.Ghost;
import Logic.Telep;
import Logic.Pacman;

public class Board {
	/**
	 * Longueur du plateau en pixels.
	 */
	private int dim_X;

	/**
	 * Hauteur du plateau en pixels.
	 */
	private int dim_Y;

	/**
	 * Nombre de lignes du plateau.
	 */
	private int number_lines;

	/**
	 * Nombre de colonnes du plateau.
	 */
	private int number_columns;

	/**
	 * Liste des murs du plateau.
	 */
	private Liste<Walls> list_walls = new Liste<Walls>();

	/**
	 * Liste des fruits (qui contient les fruits déjà à  plaçables) du plateau.
	 */
	private Liste<Fruits> list_fruits = new Liste<Fruits>();

	/**
	 * Liste des fantômes du plateau.
	 */
	private Liste<Ghost> list_ghosts = new Liste<Ghost>();

	/**
	 * Liste des téléporteurs du plateau.
	 */
	private Liste<Telep> list_teleporteurs = new Liste<Telep>();

	/**
	 * Instance de Pacman à  récupérer pour son placement sur la carte.
	 */
	private Pacman pacman;

	/**
	 * Identifiant du plateau correspondant au niveau actuel.
	 */
	private int id = 1;

	/**
	 * Vitesse de déplacement des fantômes et Pacman de base en pixels/frame.
	 */
	private int speed;

	/**
	 * Détermine le contenu du fichier texte.
	 * @param path_map Chemin d'accès du fichier texte.
	 * @return Chaine de caractère correspondant au contenu du fichier texte.
	 */
	private String readString(String path_map) {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(path_map));
			String content = "";
			for (byte tmp_byte : bytes) {
				content = content + (char) tmp_byte;
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Convertit un fichier texte contenant la synthèse des éléments du plateau à à  initialiser en une matrice exploitable.
	 * @param path_map Chemin d'accès du fichier texte contenant la synthèse des éléments du plateau à à  initialiser.
	 * @return Une matrice de char déterminant quel élément se situe sur chaque ligne et chaque colonne du plateau.
	 * @throws Renvoie une erreur si les lignes de la map ne sont pas de la même taille. Les lignes vides sont ignorées.
	 */
	private char[][] get_map(String path_map) throws Exception {
		int number_lines = 0;
		int number_column = 0;
		char[][] tableau = null;
		String content = readString(path_map);
		String[] lines = content.split("\n");
		Liste<String> list_lines = new Liste<String>();
		for (String line : lines) {
			if (!line.equals("")) {
				list_lines.add(line);
			}
		}
		number_lines = list_lines.size();
		if (number_lines != 0) {
			number_column = list_lines.get(0).length();
			tableau = new char[number_lines][number_column];
			for (int i = 0; i < number_lines; i++) {
				if (number_column != list_lines.get(i).length()) {
					throw new Exception(String.format("Ligne %d pas de la même longueur que la ligne 1. (S'il d'agit de la dernière ligne vérifier si les retours\nà  la ligne sont en CRLF, si oui faire un retour à  la ligne après la dernière ligne)", i + 1));
				}
				for (int j = 0; j < number_column - 1; j++) {
					tableau[i][j] = list_lines.get(i).charAt(j);
				}
			}
		}
		this.number_columns = number_column;
		this.number_lines = number_lines;
		this.dim_X = 40 * number_column;
		this.dim_Y = 40 * number_lines;
		return tableau;
	}

	/**
	 * Détermine la vitesse de base de déplacement de Pacman et des fantômes.
	 * @param path_speed Chemin d'accès du fichier contenant la vitesse de déplacement de base de Pacman et les fantômes.
	 * @return La vitesse de déplacement de base de Pacman et des fantômes en pixels/frame.
	 */
	private int read_speed(String path_speed) {
		int speed = 0;
		try {
			String content = Files.readString(Paths.get(path_speed));
			speed = Integer.parseInt(content);
		} catch (IOException e){
			e.printStackTrace();
		}
		return speed;
	}

	/**
	 * Construit des listes exploitables disjointes contenants les murs, les fruits ou les fantômes. Détermine la position de base de Pacman. 
	 * @param matrice La matrice correspondant aux éléments à placer sur le plateau.
	 */
	private void build_lists(char[][] matrice) {
		for (int i = 0; i < this.number_lines; i++) {
			for (int j = 0; j < this.number_columns; j++) {
				char elem = matrice[i][j];
				int pos_X = j * 40;
				int pos_Y = i * 40;
				int speed = this.speed;
				Fruits tmp_fruit = null;
				switch (elem) { 
					case '#':
						Walls tmp_wall = new Walls(pos_X, pos_Y);
						this.list_walls.add(tmp_wall);
						break;
					case 'P':
						this.pacman = new Pacman(speed, pos_X, pos_Y);
						break;
					case '.':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, speed);
						this.list_fruits.add(tmp_fruit);
						break;
					case '*':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 1);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'c':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 100);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'f':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 300);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'o':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 500);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'p':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 700);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'm':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 1000);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'g':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 2000);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'b':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 3000);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'k':
						tmp_fruit = new Fruits(pos_X, pos_Y, elem, 5000);
						this.list_fruits.add(tmp_fruit);
						break;
					case 'F':
						Ghost tmp_ghost = new Ghost(pos_X, pos_Y, '↑', 0, 2 * speed, 0);
						this.list_ghosts.add(tmp_ghost);
						break;
					case 'T':
						if (this.list_teleporteurs.size() == 0) {
							Telep tmp_telep = new Telep(pos_X, pos_Y);
							this.list_teleporteurs.add(tmp_telep);
						} else {
							Telep first_telep = list_teleporteurs.get(0);
							Telep second_telep = new Telep(pos_X, pos_Y, first_telep);
							this.list_teleporteurs.add(second_telep);
							first_telep.set_Telep(second_telep);
						}
						break;
				}
			}
		}
	}

	/**
	 * Constructeur.
	 * @param level Le numéro du niveau.
	 */
	public Board(int level) {
		String chemin_map = null;
		switch (level) {
			case 1:
				chemin_map = "Data/Map1.txt";
				break;
			case 2:
				chemin_map = "Data/Map2.txt";
				break;
			case 3:
				chemin_map = "Data/Map3.txt";
				break;
			case 4:
				chemin_map = "Data/Map4.txt";
				break;
			case 5:
				chemin_map = "Data/Map5.txt";
				break;
		}
		String chemin_speed = "Data/speed.txt";
		char[][] matrice = null;
		try {
			matrice = get_map(chemin_map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.speed = read_speed(chemin_speed);
		build_lists(matrice);
		this.id = level;
	}

	/**
	 * Renvoie la longueur en pixel du plateau.
	 * @return Entier représentant la longueur en pixel du plateau.
	 */
	public int get_dim_X() {
		return this.dim_X;
	}

	/**
	 * Renvoie la hauteur en pixel du plateau.
	 * @return Entier représentant la hauteur en pixel du plateau.
	 */
	public int get_dim_Y() {
		return this.dim_Y;
	}

	/**
	 * Renvoie l'identifiant du plateau correspondant au niveau actuel.
	 * @return Entier représentant l'identifiant du plateau correspondant au niveau actuel.
	 */
	public int get_id() {
		return this.id;
	}

	/**
	 * Renvoie la liste des murs générée à  la construction.
	 * @return La liste des murs.
	 */
	public Liste<Walls> get_Walls() {
		return this.list_walls;
	}

	/**
	 * Renvoie la liste des fruits générée à à lag construction.
	 * @return La liste des fruits déjà à  plaçables sur le plateau.
	 */
	public Liste<Fruits> get_Fruits() {
		return this.list_fruits;
	}

	/**
	 * Renvoie la liste des fantômes générée à  la construction.
	 * @return La liste des fantômes.
	 */
	public Liste<Ghost> get_Ghost() {
		return this.list_ghosts;
	}

	/**
	 * Renvoie l'instance de Pacman du plateau.
	 * @return L'instance de Pacman du plateau.
	 */
	public Pacman get_Pacman() {
		return this.pacman;
	}

	/**
	 * Renvoie la vitesse de déplacement de base de Pacman et des fantômes.
	 * @return La vitesse de déplacement de base de Pacman et des fantômes en pixels/frame.
	 */
	public int get_speed() {
		return this.speed;
	}

	/**
	 * Renvoie la liste des fruits générée à  la construction.
	 * @return La liste des fruits déjà à  plaçables sur le plateau.
	 */
	public Liste<Telep> get_Telep() {
		return this.list_teleporteurs;
	}
}
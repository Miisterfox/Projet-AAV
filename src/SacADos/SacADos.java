package SacADos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Solutions.Solution;
import Solutions.SolutionDynamique;
import Solutions.SolutionHeuristique;
import Solutions.SolutionPSE;


/**
 * @author Yanis et Audran
 * Classe permettant la cr�ation d'un sac � dos.
 * Ce sac poss�de un poids et une capacit� maximale.
 */
public class SacADos {
	private ArrayList<Objet> Sac;
	private ArrayList<Objet> Objets;
	private float poids;
	private float valeurTotale;
	private float capacit�;

	
	/**
	 * Constructeur d'un Sac � dos vide
	 */
	public SacADos() {
		this.Objets = new ArrayList<Objet>();
		this.Sac = new ArrayList<Objet>();
		this.poids = 0;
	}
	
	/**
	 * Constructeur d'un sac � dos et initialisation de la liste 
	 * d'objet � partir d'un fichier .txt
	 * 
	 * @param chemin le chemin du fichier .txt
	 * @param capacit�MAX la capacit� maximale du sac � dos
	 * @throws FileNotFoundException si le fichier sp�cifi� n'existe 
	 * pas dans le r�pertoire
	 * 			
	 */
	public SacADos(String chemin, float capacit�MAX) throws FileNotFoundException {
		this();
		this.capacit� = capacit�MAX;
		try {
			Scanner read = new Scanner(new File(chemin));
			read.useDelimiter(";|\n");
			while (read.hasNextLine() && read.hasNext()) {
				Objets.add(
						new Objet(read.next().strip(), Float.parseFloat(read.next()),
								Float.parseFloat(read.next())));
			}

			read.close();
		}

		catch (FileNotFoundException e) {
			throw e;
		}
	}
	
	/**
	 * @return le sac
	 */
	public ArrayList<Objet> getSac() {
		return Sac;
	}
	
	/**
	 * @return le prix total du sac
	 */
	public float getValeurTotale() {
		float p = 0;
		for(Objet o: Sac) {
			p+= o.getPrix();
		}
		return p;
		
	}
	
	/**
	 * @return le poids total du sac
	 */
	public float getPoids() {
		float p = 0;
		for(Objet o: Sac) {
			p+= o.getPoids();
		}
		return p;
		
	}
	
	
	
	/**
	 * M�thode permettant de remplir le sac, suivant la m�thode
	 * choisie par l'utilisateur
	 * 
	 * @param m�thode la m�thode de r�solution du probl�me
	 * @return false dans le cas o� l'entr�e utilisateur est fausse
	 * (faute ou m�thode inexistante) sinon return true.
	 */
	public boolean remplir(String m�thode) {
		Sac.clear();
		Solution solution;
		switch(m�thode.toUpperCase()) {
			case "GLOUTONNE":
			case "HEURISTIQUE":
				solution = new SolutionHeuristique(Objets,capacit�);
				Sac = solution.r�soudre();
				return true;
			case "PROG DYNAMIQUE":
			case "DYNAMIQUE":
				solution = new SolutionDynamique(Objets,capacit�);
				Sac = solution.r�soudre();
				return true;
			case "PSE":
				solution = new SolutionPSE(Objets,capacit�);
				Sac = solution.r�soudre();
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * M�thode permettant d'obtenir des informations sur le 
	 * sac � dos sous la forme d'une cha�ne de caract�res
	 * 
	 * @return une cha�ne de caract�res contenant des informations
	 * sur le sac � dos et une liste des objets qui y sont plac�s
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\nLa capacit� maximale du sac est de " + capacit� + " kg\n");
		str.append("Les objets mis dans le sac sont les suivants :\n");
		for(Objet o: Sac) {
			str.append("\t - " + o.toString() + "\n");
			poids+= o.getPoids();
			valeurTotale+= o.getPrix();
		}
		str.append("\nLe poids total du sac est de " + poids + " kg");
		str.append(" pour un prix total de " + valeurTotale + " �\n");
		
		return str.toString();
	}
}
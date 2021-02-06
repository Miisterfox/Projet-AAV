package Solutions;

import java.util.ArrayList;
import SacADos.Objet;

/**
 * @author Yanis et Audran 
 * Classe abstraite permettant la cr�ation de m�thode de
 * r�solution du probl�me du sac � dos
 */
public abstract class Solution {
	private float capacit�;
	private ArrayList<Objet> Objets;

	/**
	 * Constructeur d'une solution
	 * 
	 * @param Objets   la liste d'objets � mettre dans le sac
	 * @param capacit� la capacit� maximale du sac
	 */
	public Solution(ArrayList<Objet> Objets, float capacit�) {
		this.Objets = Objets;
		this.capacit� = capacit�;
	}

	/**
	 * @return la capacit�
	 */
	public float getCapacit�() {
		return capacit�;
	}

	/**
	 * @return la liste des objets
	 */
	public ArrayList<Objet> getObjets() {
		return Objets;
	}


	/**
	 * M�thode permettant de r�soudre le probl�me du sac � dos
	 * 
	 * @return la liste d'objet optimale suivant la m�thode de r�solution choisie
	 * 
	 * @see SolutionHeuristique.r�soudre()
	 * @see SolutionDynamique.r�soudre()
	 * @see SolutionPSE.r�soudre()
	 */
	public abstract ArrayList<Objet> r�soudre();
}

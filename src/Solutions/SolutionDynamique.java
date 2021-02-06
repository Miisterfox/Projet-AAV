package Solutions;

import java.util.ArrayList;

import SacADos.Objet;

/**
 * @author Yanis et Audran
 * Classe h�ritant de la super classe Solution et permettant
 * de r�soudre le probl�me du sac � dos par la programmation
 * dynamique.
 */

public class SolutionDynamique extends Solution {

	/**
	 * Constructeur d'une solution dynamique
	 * 
	 * @param Objets la liste d'objets � mettre dans le sac
	 * @param capacit� la capacit� maximale du sac
	 */
	public SolutionDynamique(ArrayList<Objet> Objets, float capacit�) {
		super(Objets, capacit�);
	}
	
	/**
	 * Permet de r�soudre le probl�me du sac � dos par la programmation dynamique
	 * 
	 * @return la liste d'objet optimale suivant la m�thode de r�solution choisie
	 * @see Solution.r�soudre()
	 */

	public ArrayList<Objet> r�soudre() {
		ArrayList<Objet> choix = new ArrayList<>();
		float[][] m = new float[getObjets().size()][(int) (getCapacit�()) + 1];

		for (int j = 0; j < getCapacit�() + 1; j++) {
			if (getObjets().get(0).getPoids() > j) {
				m[0][j] = 0;
			} else
				m[0][j] = getObjets().get(0).getPrix();
		}

		for (int i = 1; i < getObjets().size(); i++) {
			for (int j = 0; j < getCapacit�() + 1; j++) {
				if (getObjets().get(i).getPoids() > j) {
					m[i][j] = m[i - 1][j];
				} else {
					m[i][j] = Math.max(m[i - 1][j],
							m[i - 1][j - (int) (getObjets().get(i).getPoids())] + getObjets().get(i).getPrix());
				}
			}
		}
		
		int i = getObjets().size() - 1;
		int j = (int) getCapacit�();

		while (m[i][j] == m[i][j - 1]) {
			i--;
		}

		while (i >= 0) {
			if (i == 0 || m[i][j] != m[i - 1][j]) {
				if (j - getObjets().get(i).getPoids() >= 0) {
					choix.add(getObjets().get(i));
					j -= getObjets().get(i).getPoids();
				}
			}
			i--;
		}
		return choix;
	}
}

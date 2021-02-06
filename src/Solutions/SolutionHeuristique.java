package Solutions;

import java.util.ArrayList;
import SacADos.Objet;

/**
 * @author Yanis et Audran
 * Classe h�ritant de la super classe Solution et permettant
 * de r�soudre le probl�me du sac � dos par une m�thode 
 * heuristique (ou gloutonne)
 */
public class SolutionHeuristique extends Solution{
	
	/**
	 * Constructeur d'une solution heuristique
	 * 
	 * @param Objets la liste d'objets � mettre dans le sac
	 * @param capacit� la capacit� maximale du sac
	 */
	public SolutionHeuristique(ArrayList<Objet> Objets, float capacit�) {
		super(Objets,capacit�);
	}
	
	
	/**
	 * Permet de r�soudre le probl�me du sac � dos � l'aide d'un algorithme heuristique
	 * 
	 * @return la liste d'objet optimale suivant la m�thode de r�solution choisie
	 * @see Solution.r�soudre()
	 */
	
	@Override
	public ArrayList<Objet> r�soudre() {
		ArrayList<Objet> choix = new ArrayList<Objet>();
		int poids = 0;
		this.quicksort(0, getObjets().size() - 1);
		for (Objet o : getObjets()) {
			if (poids + o.getPoids() < getCapacit�()) {
				poids += o.getPoids();
				choix.add(o);
			}
		}
		return choix;
	}
	
	/**
	 * M�thode permettant le tri d�croissant d'une liste
	 * par appel r�cursif � une m�thode de "partition" de 
	 * cette liste.
	 * (m�thode de tri rapide)
	 * 
	 * @param d�but le d�but de la liste/sous-liste
	 * @param fin la fin de la liste/sous-liste
	 */
	public void quicksort(int d�but,int fin) {
		if(d�but<fin) {
			int indexPivot = partition(d�but,fin);
			quicksort(d�but,indexPivot-1);
			quicksort(indexPivot+1,fin);
		}
	}
	
	/**
	 * M�thode permettant de trier par ordre d�croissant la
	 * liste/sous-liste d'objet (suivant leur valeur moyenne) et de d�finir
	 * un nouveau pivot
	 * 
	 * @param d�but le d�but de la liste/sous-liste
	 * @param fin la fin de la liste/sous-liste
	 * @return la nouvelle valeur du pivot (permettant la partition des listes)
	 */
	private int partition(int d�but, int fin){
		float valeurPivot = getObjets().get(fin).getvMoy();
		int d = d�but-1;
		for(int j=d�but;j<fin;j++) {
			if(getObjets().get(j).getvMoy() >= valeurPivot) {
				d++;
				Objet swapObjet = getObjets().get(d);
				getObjets().set(d, getObjets().get(j));
				getObjets().set(j, swapObjet);
				
			}
		}
		Objet swapObjet = getObjets().get(d+1);
		getObjets().set(d+1, getObjets().get(fin));
		getObjets().set(fin, swapObjet);
	
		return d+1;
	}
}
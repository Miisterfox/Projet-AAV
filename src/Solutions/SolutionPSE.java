package Solutions;

import java.util.ArrayList;

import SacADos.Objet;
import SacADos.SacADos;

/**
 * @author Yanis et Audran
 * Classe h�ritant de la super classe Solution et permettant
 * de r�soudre le probl�me du sac � dos par une proc�dure de 
 * s�paration et d'�valuation (PSE)
 */

public class SolutionPSE extends Solution {
	SacADos solution;

	/**
	 * Constructeur d'une solution PSE
	 * 
	 * @param Objets la liste d'objets � mettre dans le sac
	 * @param capacit� la capacit� maximale du sac
	 */
	public SolutionPSE(ArrayList<Objet> Objets, float capacit�) {
		super(Objets, capacit�);
		solution = new SacADos();
	}

	/**
	 * @author Yanis et Audran
	 * Classe interne priv�e permettant la cr�ation de
	 * noeudObjet, permettant la cr�ation d'un arbre de recherche.
	 */
	private class NoeudObjet {
		private NoeudObjet noeudGauche;
		private NoeudObjet noeudDroit;
		private float borneInf�rieure;
		private float borneSup�rieure;
		private int profondeur;
		private boolean coup�;
		private SacADos choix;

		/**
		 * Constructeur du premier noeud de l'arbre de recherche.
		 * 
		 * @param Objets la liste d'objets � mettre dans le sac
		 * @param Capacit� la capacit� maximale du sac
		 */
		public NoeudObjet(ArrayList<Objet> Objets, float capacit�) {
			Solution solution = new SolutionHeuristique(Objets, capacit�);
			for (Objet o : solution.r�soudre()) {
				borneInf�rieure += o.getPrix();
			}
			for (Objet o : Objets) {
				borneSup�rieure += o.getPrix();
			}
			profondeur = 0;
			choix = new SacADos();
			coup� = false;
		}

		/**
		 * Constructeur d'un noeud
		 * 
		 * @param parent le noeud parent
		 * @param Objets la liste des objets � mettre dans le sac
		 */
		public NoeudObjet(NoeudObjet parent, ArrayList<Objet> Objets) {
			this.profondeur = parent.profondeur + 1;
			this.borneInf�rieure = parent.borneInf�rieure;
			this.borneSup�rieure = parent.borneSup�rieure;
			this.choix = new SacADos();
			this.choix.getSac().addAll(parent.choix.getSac());
		}


		/**
		 * M�thode permettant de calculer la borne
		 * sup�rieure du noeud
		 * 
		 * @param Objets la liste d'objets � mettre dans le sac
		 */
		public void calculerBorneSup�rieure(ArrayList<Objet> Objets) {
			float tempSup = this.choix.getValeurTotale();
			for(int i=profondeur;i<Objets.size();i++) {
				tempSup += Objets.get(i).getPrix();
			}
			this.borneSup�rieure = tempSup;
		}
		
		/**
		 * M�thode permettant de calculer la borne
		 * inf�rieure du noeud
		 * 
		 * @param Objets la liste d'objets � mettre dans le sac
		 */
		public void calculerBorneInf�rieure(ArrayList<Objet> Objets) {
			if(this.borneInf�rieure < this.choix.getValeurTotale()) {
				this.borneInf�rieure = this.choix.getValeurTotale();
			}
		}
		
		/**
		 * M�thode permettant de calculer les deux bornes
		 * du noeud, et de couper le noeud si n�cessaire.
		 * 
		 * @param Objets la liste d'objets � mettre dans le sac
		 */
		public void calculerBorne(ArrayList<Objet> Objets) {
			this.calculerBorneSup�rieure(Objets);
			this.calculerBorneInf�rieure(Objets);
			if(this.borneInf�rieure > this.borneSup�rieure) {
				this.coup� = true;
			}
		}

		/**
		 * M�thode permettant la construction d'un arbre
		 * de recherche des solutions possibles pour la r�solution
		 * du probl�me du sac � dos
		 * 
		 * @param Objets la liste d'objets � mettre dans le sac
		 * @param capacit� la capacit� maximale du sac
		 */
		public void construire(ArrayList<Objet> Objets, float capacit�) {
			if (profondeur < Objets.size()) {
				this.noeudDroit = new NoeudObjet(this, Objets);
				this.noeudGauche = new NoeudObjet(this, Objets);
				this.noeudDroit.choix.getSac().add(Objets.get(profondeur));
				this.calculerBorne(Objets);
				if (noeudDroit.choix.getPoids() <= capacit�) {
					noeudDroit.construire(Objets, capacit�);
					noeudGauche.construire(Objets, capacit�);
				} else {
					noeudDroit.coup� = true;
					noeudGauche.construire(Objets, capacit�);
				}
			}
		}

		/**
		 * M�thode permettant de parcourir l'arbre de recherche
		 * de mani�re pr�fix� et d'obtenir une solution optimale 
		 * au probl�me du sac � dos.
		 * 
		 * @return la liste d'objet solution au probl�me du sac � dos
		 */
		public ArrayList<Objet> parcoursPrefixeMax() {
			if (this.noeudDroit != null && !this.noeudDroit.coup�) {
				if(this.choix.getValeurTotale() > solution.getValeurTotale()) {
					solution = this.choix;
				}
				this.noeudDroit.parcoursPrefixeMax();
			}
			if (this.noeudGauche != null && !this.noeudGauche.coup�) {
				if(this.choix.getValeurTotale() > solution.getValeurTotale()) {
					solution = this.choix;
				}
				this.noeudGauche.parcoursPrefixeMax();
			}
			return solution.getSac();
		}

	}

	
	/**
	 * Permet de r�soudre le probl�me du sac � dos par la proc�dure de s�paration et
	 * d'�valuation (PSE)
	 * 
	 * @return la liste d'objet optimale suivant la m�thode de r�solution choisies
	 * @see Solution.r�soudre()
	 */
	@Override
	public ArrayList<Objet> r�soudre() {
		NoeudObjet root = new NoeudObjet(getObjets(), getCapacit�());
		root.construire(getObjets(), getCapacit�());
		return root.parcoursPrefixeMax();
	}
}
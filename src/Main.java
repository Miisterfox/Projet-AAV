import java.io.FileNotFoundException;
import java.util.Scanner;
import SacADos.SacADos;
import ObjetsGenerator.ObjetGenerator;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * G�n�rateur de liste d'objet d�velopper en amont du projet
		 */
		ObjetGenerator gen = new ObjetGenerator(System.getProperty("user.dir").concat("\\Objets.txt"),15);

		boolean userIn = false;
		SacADos sac;
		Scanner sc = new Scanner(System.in);
		while (!userIn) {
			String chemin = "";
			int capacit� = 0;
			String m�thode = "";
			System.out.println("-- R�solution du probl�me du sac � dos --");
			System.out.println("Il existe trois m�thodes de r�solution");
			System.out.println("Veuillez saisir le chemin du fichier contenant les objets � choisir");
			chemin = sc.next();
			System.out.println("Veuillez saisir la capacit� maximale du sac � remplir (en kg)");
			capacit� = (int) sc.nextFloat();
			System.out.println("Veuillez maintenant choisir la m�thode de r�solution (Heuristique - Dynamique - PSE)");
			m�thode = sc.next();
			try {
				sac = new SacADos(chemin, capacit�);
				long startTime = System.nanoTime();
				userIn = sac.remplir(m�thode);
				long stopTime = System.nanoTime();
				if (userIn) {
					System.out.println(sac);
					System.out.println("Temps d'ex�cution de l'algorithme " + m�thode + " " + (stopTime - startTime) + " nanosecondes");
					System.out.println("Temps d'ex�cution de l'algorithme " + m�thode + " " + (stopTime - startTime)/1000000 + " millisecondes");
				}
			} catch (FileNotFoundException e) {
				System.out.println("Le fichier que vous avez saisi n'existe pas ou n'est pas dans le bon r�pertoire");
				continue;
			}
		}
		sc.close();
	}

}

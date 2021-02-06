package ObjetsGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Yanis
 * Classe permettant la g�n�ration d'une liste d'objet d'apr�s
 * un format pr�d�fini
 */
public class ObjetGenerator {
	@SuppressWarnings("unused")
	private File myObj;
	private char nomObj;
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	/**
	 * Constructeur du g�n�rateur d'objet
	 * @param file l'emplacement et le nom du fichier � cr�er/ouvrir
	 * @param nbObjets le nombre d'objet � g�n�rer 
	 */
	public ObjetGenerator(String file,int nbObjets){
		this.nomObj = 'A';
		this.myObj = new File(file);
		FileWriter FW;
		try {
			FW = new FileWriter(file);
			for(int i=0;i<nbObjets;i++) {
				FW.write(GenerationObjet());
				this.nomObj += 1;
			}
			FW.close();
		} catch (IOException e) {
			System.out.println("Error reading the file");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * M�thode permettant de g�n�rer un objet avec
	 * des attributs al�atoire, respectant un format particulier
	 * 
	 * @return l'objet sous forme de cha�ne de caract�res
	 */
	private String GenerationObjet() {
		Random rand = new Random();	
		float poids = rand.nextInt(10);
		float prix = rand.nextInt(100);
		return nomObj + " ; " + df.format(poids) + " ; " + df.format(prix) + "\n";
	}
}

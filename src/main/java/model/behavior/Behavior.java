package model.behavior;

import java.io.Serializable;

/**
 * Classe abstraite représentant un comportement pour un Tamagotchi.
 * Les comportements peuvent être exprimés par des images et des descriptions spécifiques.
 * Chaque sous-classe de `Behavior` doit définir la manière dont l'image et l'expression sont fournies.
 */
public abstract class Behavior implements Serializable {

	/**
	 * Retourne le chemin de l'image représentant ce comportement.
	 *
	 * @return Le chemin de l'image représentant le comportement.
	 */
	public abstract String getPicture();  

	/**
	 * Exprime une description du comportement.
	 * Cette méthode fournit une représentation textuelle du comportement du Tamagotchi.
	 *
	 * @return Une chaîne de caractères décrivant le comportement.
	 */
	public abstract String express();  
}



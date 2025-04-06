package model.emotion;

import java.io.Serializable;

import model.behavior.Behavior;

/**
 * Classe abstraite représentant une émotion d'un Tamagotchi.
 * Chaque émotion a une valeur qui détermine son intensité et un comportement extérieur associé.
 */
public abstract class Emotion implements Serializable {
	/** La valeur de l'émotion, représentant son intensité. */
	protected int value = 0;

	/** Le comportement extérieur associé à cette émotion. */
	protected Behavior outwardBehavior;


	/**
	 * Retourne l'image représentant le comportement extérieur de l'émotion.
	 *
	 * @return Le chemin de l'image du comportement extérieur.
	 */
	public String getPicture() {
		return outwardBehavior.getPicture();
	};  

	/**
	 * Retourne le comportement extérieur associé à cette émotion.
	 *
	 * @return Le comportement extérieur de l'émotion.
	 */
	public Behavior getOutwardBehaviour() {
		return outwardBehavior;
	}

	/**
	 * Retourne la valeur actuelle de l'émotion.
	 *
	 * @return La valeur de l'émotion.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Définit la valeur de l'émotion.
	 *
	 * @param value La nouvelle valeur de l'émotion.
	 */
	public void setValue(int value) {
		this.value = value;

	}

	/**
	 * Augmente la valeur de l'émotion d'un certain montant.
	 *
	 * @param value Le montant par lequel augmenter la valeur de l'émotion.
	 */
	public void increaseValue(int value) {
		this.value +=value;
	}

}

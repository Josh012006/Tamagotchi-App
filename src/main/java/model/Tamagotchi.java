package model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import model.action.TamagotchiAction;
import model.behavior.Behavior;
import model.behavior.Content;
import model.emotion.Anger;
import model.emotion.Emotion;
import model.emotion.EmotionType;
import model.emotion.Fear;
import model.emotion.Joy;
import model.emotion.Sadness;

/**
 * Classe représentant un Tamagotchi.
 * Contient les émotions du Tamagotchi et les méthodes pour les gérer.
 * Permet de manipuler les émotions, d'appliquer des actions et d'obtenir un comportement extérieur en fonction de l'état émotionnel.
 */
public class Tamagotchi implements Serializable {

	/**
	 * Carte contenant les différentes émotions du Tamagotchi.
	 * La clé est le type d'émotion et la valeur est l'émotion associée.
	 */
	private Map<EmotionType, Emotion> emotions = new HashMap<>();

	/**
	 * Constructeur de la classe Tamagotchi.
	 * Initialise les émotions par défaut (Joie, Tristesse, Colère, Peur).
	 */
	public Tamagotchi() {
		emotions.put(EmotionType.JOY, new Joy());
		emotions.put(EmotionType.SADNESS, new Sadness());
		emotions.put(EmotionType.ANGER, new Anger());
		emotions.put(EmotionType.FEAR, new Fear());
	}

	/**
	 * Récupère l'émotion associée à un type spécifique.
	 *
	 * @param type Le type d'émotion (par exemple, JOY, SADNESS, etc.).
	 * @return L'émotion correspondante au type spécifié.
	 */
	public Emotion getEmotion(EmotionType type) {
		return emotions.get(type);
	}


	/**
	 * Récupère l'émotion dominante du Tamagotchi.
	 * L'émotion dominante est celle ayant la plus grande valeur, si la différence entre la plus grande et la plus petite valeur est supérieure à 3.
	 *
	 * @return L'émotion dominante, ou null si aucune émotion dominante n'est trouvée.
	 */
	public Emotion getDominantEmotion() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (Emotion emotion : emotions.values()) {
			int val = emotion.getValue();
			if (val < min) min = val;
			if (val > max) max = val;
		}
		Emotion dominant = null;
		if (max - min > 3) {

			int dominantValue = Integer.MIN_VALUE;
			for (Emotion emotion : emotions.values()) {
				if (emotion.getValue() > dominantValue) {
					dominantValue = emotion.getValue();
					dominant = emotion;
				}
			}

		}

		return dominant;
	}

	/**
	 * Récupère le comportement extérieur du Tamagotchi en fonction de son émotion dominante.
	 *
	 * @return Le comportement extérieur correspondant à l'émotion dominante du Tamagotchi.
	 */
	public Behavior getTamagotchiOutwardBehavior() {
		Emotion dominantEmotion = this.getDominantEmotion();
		return (dominantEmotion != null) ? dominantEmotion.getOutwardBehaviour() : new Content();
	}

	/**
	 * Applique une action spécifique au Tamagotchi.
	 * Cette méthode permet de modifier l'état du Tamagotchi en fonction de l'action reçue.
	 *
	 * @param action L'action à appliquer au Tamagotchi (par exemple, jouer, nourrir, etc.).
	 */
	public void treat(TamagotchiAction action) {
		action.apply(this); 
	}

	/**
	 * Augmente la valeur d'une émotion donnée par un certain montant.
	 *
	 * @param type Le type d'émotion à augmenter (par exemple, JOY, ANGER, etc.).
	 * @param increaseValue La valeur par laquelle l'émotion sera augmentée.
	 */
	public void increaseEmotionValue(EmotionType type, int increaseValue) {
		emotions.get(type).increaseValue(increaseValue);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Tamagotchi Emotions:\n");
		for (Map.Entry<EmotionType, Emotion> entry : emotions.entrySet()) {
			sb.append(entry.getKey())
			.append(": ")
			.append(entry.getValue().getValue())
			.append("\n");
		}
		return sb.toString();
	}

}

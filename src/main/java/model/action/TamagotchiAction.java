package model.action;

import java.io.Serializable;

import model.Tamagotchi;

/**
 * Interface représentant une action pouvant être appliquée à un Tamagotchi.
 * Toute action implémentant cette interface aura pour but de modifier l'état du Tamagotchi.
 */
public interface TamagotchiAction extends Serializable {

	/**
	 * Applique l'action spécifique au Tamagotchi.
	 * Cette méthode permet de modifier l'état du Tamagotchi en fonction de l'action.
	 *
	 * @param tamagotchi Le Tamagotchi sur lequel l'action doit être appliquée.
	 */
	void apply(Tamagotchi tamagotchi);

}

package controllers;

import java.io.IOException;
import java.net.Socket;

import javafx.application.Platform;
import model.behavior.Behavior;
import model.network.Network;
import model.util.Constants;
import view.ViewBase;

/**
 * Classe abstraite représentant un contrôleur de base pour une vue dans
 * l'application.
 * Les classes qui héritent de ControllerBase sont responsables de la gestion
 * des actions
 * et des interactions liées à une vue spécifique.
 */
public abstract class ControllerBase extends Network {

	/**
	 * Vue associée au contrôleur.
	 */
	protected ViewBase view;


	protected static Behavior tamagotchiBehavior;

	/**
	 * Constructeur protégé pour initialiser le contrôleur avec une vue.
	 *
	 * @param view La vue associée.
	 */
	protected ControllerBase(ViewBase view) {
		this.view = view;
		initialize();
	}

	/**
	 * Configure la connexion au serveur sur le port spécifié.
	 *
	 * @param port Le port sur lequel se connecter.
	 * @throws IOException En cas d'erreur d'entrée/sortie.
	 */
	protected void setUpSocket(int port) throws IOException {
		super.initialize(new Socket(Constants.SERVER_ADDRESS, port));
		super.listen();
	}

	/**
	 * Ferme l'application en cours.
	 */
	protected void closeApp() {
		Platform.exit();
	}

	/**
	 * Méthode statique pour obtenir le contrôleur approprié pour une vue donnée.
	 *
	 * @param view La vue pour laquelle obtenir le contrôleur.
	 * @return Le contrôleur correspondant à la vue donnée.
	 * @throws IllegalArgumentException Si le nom de la vue est invalide.
	 */
	public static ControllerBase getController(ViewBase view) {
		switch (view.getName().toLowerCase()) {
		case "main":
			return new MainController(view);

		case "tamagotchi":
			return new TamagotchiController(view);

		default:
			throw new IllegalArgumentException("Invalid view name...!");
		}
	}



	/**
	 * Méthode abstraite permettant d'initialiser le contrôleur.
	 * Cette méthode doit être implémentée par les classes filles pour initialiser
	 * le contrôleur spécifique.
	 */
	protected abstract void initialize();
}
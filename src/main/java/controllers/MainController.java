package controllers;


import javafx.scene.control.Button;
import model.App;
import model.util.Constants;
import view.MainView;
import view.ViewBase;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Contrôleur pour la vue principale de l'application TamagotchiClient.
 * Ce contrôleur gère la lecture du port, la connexion au serveur et la transition vers la vue Tamagotchi.
 */
public class MainController extends ControllerBase {

	/**
	 * Vue spécifique utilisée pour la saisie du port.
	 */
	private MainView mainView;



	/**
	 * Constructeur pour le contrôleur principal.
	 *
	 * @param view La vue associée à ce contrôleur.
	 */
	public MainController(ViewBase view) {
		super(view);
	}

	/**
	 * Méthode d'initialisation du contrôleur principal.
	 * Configure l'action du bouton de connexion.
	 */
	@Override
	protected void initialize() {
		this.mainView = (MainView) super.view;

		Button connectButton = mainView.getConnectButton();
		connectButton.setOnAction(event -> onConnectButtonClick());
	}

	/**
	 * Lit le numéro de port saisi par l'utilisateur et tente de se connecter au serveur Tamagotchi.
	 * Si le champ est vide, le port par défaut est utilisé.
	 * En cas de connexion réussie, la vue Tamagotchi est affichée.
	 */
	private void onConnectButtonClick() {
		int port = -1;
		
		try {
			String enteredText = mainView.getPortField().getText();
			if(enteredText.isEmpty()) {
				port = Constants.DEFAULT_PORT;
			}
			else {
				int enteredPort = Integer.parseInt(enteredText);
				
				if(enteredPort < 0 || enteredPort > 65535) {
					throw new Exception();
				}
				
				port = enteredPort;
			}
			
			super.setUpSocket(port);
			App.showView("Tamagotchi");
			
			
		} catch (IOException e) {
			showError("Une erreur est survenue lors de la connexion.");
			e.printStackTrace();
		} catch (Exception e) {
			showError("Port de connexion invalide. Veuillez entrer un entier situé entre 0 et 65535 inclusivement.");
			e.printStackTrace();
		}


	}


	/**
	 * Méthode utilitaire pour afficher une alerte d'erreur.
	 *
	 * @param title   Le titre de l'alerte.
	 * @param message Le message à afficher.
	 */
	private void showError(String message) {
		mainView.getErrorLabel().setText(message);
		mainView.getErrorLabel().setVisible(true);
	}
}

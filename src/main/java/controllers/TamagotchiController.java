package controllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.action.Feed;
import model.action.Ignore;
import model.action.Judge;
import model.action.Play;
import model.action.Punish;
import model.action.TamagotchiAction;
import model.behavior.Behavior;
import model.network.Event;
import model.util.Constants;
import view.TamagotchiView;
import view.ViewBase;

import java.io.IOException;
import java.io.Serializable;

/**
 * Contrôleur pour la gestion du Tamagotchi dans l'application.
 * Ce contrôleur gère les interactions avec le Tamagotchi,
 * y compris jouer, nourrir, punir et ignorer.
 */
public class TamagotchiController extends ControllerBase {
	/**
	 * Vue spécifique utilisée pour le Tamagotchi.
	 */
	private TamagotchiView tamagotchiView;

	/**
	 * Constructeur pour le contrôleur du Tamagotchi.
	 *
	 * @param view La vue associée à ce contrôleur.
	 */
	public TamagotchiController(ViewBase view) {
		super(view);
	}

	/**
	 * Méthode d'initialisation du contrôleur du Tamagotchi.
	 */
	@Override
	protected void initialize() {
		this.tamagotchiView = (TamagotchiView) super.view;


		Button playButton = tamagotchiView.getPlayButton();
		playButton.setOnAction(event -> play());

		Button feedButton = tamagotchiView.getFeedButton();
		feedButton.setOnAction(event -> feed());

		Button punishButton = tamagotchiView.getPunishButton();
		punishButton.setOnAction(event -> punish());

		Button ignoreButton = tamagotchiView.getIgnoreButton();
		ignoreButton.setOnAction(event -> ignore());

		Button judgeButton = tamagotchiView.getJudgeButton();
		judgeButton.setOnAction(event -> judge());

		Button exitButton = tamagotchiView.getExitButton();
		exitButton.setOnAction(event -> exit());


		super.addEventHandler((Event event) -> {
			handleServerEvent(event);
		});

	}

	/**
	 * Gère l'événement reçu du serveur.
	 *
	 * @param event L'événement reçu du serveur.
	 */
	private void handleServerEvent(Event event) {
		switch (event.getType()) {
		case Constants.EVENT_TAMAGOTCHI_BEHAVIOR: 
			super.tamagotchiBehavior = (Behavior)event.getData();
			updateUI();
			break;

		default:
			break;
		}
	}

	/**
	 * Met à jour l'interface utilisateur avec l'état actuel du Tamagotchi.
	 */
	private void updateUI() {
		Platform.runLater(() -> {
			if(super.tamagotchiBehavior != null) {
				showCurrentImage();
				showBehaviorNotification();
			}

		});
	}

	/**
	 * Affiche l'image actuelle du Tamagotchi.
	 */
	private void showCurrentImage() {
		if(super.tamagotchiBehavior != null) {
			ImageView imageView = tamagotchiView.getImageView();
			Image image = new Image(getClass().getResource(super.tamagotchiBehavior.getPicture()).toExternalForm());

			imageView.setImage(image);
		}


	}

	/**
	 * Affiche une notification de comportement du Tamagotchi.
	 */
	private void showBehaviorNotification() {
		Label  notificationLabel = tamagotchiView.getNotificationLabel();
		notificationLabel.setText(super.tamagotchiBehavior.express());
		notificationLabel.setVisible(true);

		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(e -> notificationLabel.setVisible(false));
		pause.play();
	}

	/**
	 * Action pour jouer avec le Tamagotchi.
	 */
	private void play() {
		try {
			sendBehaviorToServer(new Play());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Action pour nourrir le Tamagotchi.
	 */
	private void feed() {
		try {
			sendBehaviorToServer(new Feed());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Action pour punir le Tamagotchi.
	 */
	private void punish() {
		try {
			sendBehaviorToServer(new Punish());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Action pour ignorer le Tamagotchi.
	 */
	private void ignore() {
		try {
			sendBehaviorToServer(new Ignore());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Action pour critiquer le Tamagotchi.
	 */
	private void judge() {
		try {
			sendBehaviorToServer(new Judge());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Action pour quitter l'application.
	 */
	private void exit() {
		Event message = new Event(Constants.EVENT_EXIT, null);
		super.closeApp();
		try {
			super.sendSerializableData(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode pour envoyer les données de comportement au serveur.
	 *
	 * @param action L'objet de comportement spécifique à envoyer.
	 * @throws IOException Si une erreur se produit lors de l'envoi des données au serveur.
	 */
	private void sendBehaviorToServer(TamagotchiAction action) throws IOException {
		Event event = new Event(Constants.EVENT_TREAT_TAMAGOTCHI, (Serializable) action);
		super.sendSerializableData(event);
	}

}

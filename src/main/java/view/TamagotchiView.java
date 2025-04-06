package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.util.Constants;
import javafx.geometry.Pos;

/**
 * Vue pour l'affichage et l'interaction avec le Tamagotchi.
 * Cette vue gère l'affichage de l'image du Tamagotchi, divers boutons pour interagir (jouer, nourrir, punir, ignorer, juger)
 * ainsi qu'un bouton pour quitter l'application, et un label pour afficher des notifications.
 */
public class TamagotchiView extends ViewBase {

	/**
	 * Conteneur principal de la vue Tamagotchi.
	 */
	private VBox rootPane;

	/**
	 * ImageView pour afficher l'image du Tamagotchi.
	 */
	private ImageView imageView;

	/**
	 * Bouton pour jouer avec le Tamagotchi.
	 */
	private Button playButton;

	/**
	 * Bouton pour nourrir le Tamagotchi.
	 */
	private Button feedButton;

	/**
	 * Bouton pour punir le Tamagotchi.
	 */
	private Button punishButton;

	/**
	 * Bouton pour ignorer le Tamagotchi.
	 */
	private Button ignoreButton;

	/**
	 * Bouton pour critiquer le Tamagotchi.
	 */
	private Button judgeButton;

	/**
	 * Bouton pour arrêter le Serveur.
	 */
	private Button exitButton;

	/**
	 * Label pour afficher les notifications.
	 */
	private Label notificationLabel;


	/**
	 * Constructeur par défaut pour la vue Tamagotchi.
	 */
	public TamagotchiView() {
		super();
	}

	/**
	 * Retourne le nom de la vue Tamagotchi.
	 *
	 * @return Le nom de la vue ("Tamagotchi").
	 */
	@Override
	public String getName() {
		return "Tamagotchi";
	}

	/**
	 * Renvoie la racine (root) de la vue Tamagotchi.
	 *
	 * @return La racine de la vue.
	 */
	@Override
	public Parent getRoot() {
		return rootPane;
	}

	/**
	 * Renvoie l'ImageView pour afficher l'image du Tamagotchi.
	 *
	 * @return L'ImageView du Tamagotchi.
	 */
	public ImageView getImageView() {
		return imageView;
	}

	/**
	 * Renvoie le bouton "Play".
	 *
	 * @return Le bouton Play.
	 */
	public Button getPlayButton() {
		return playButton;
	}

	/**
	 * Renvoie le bouton "Feed".
	 *
	 * @return Le bouton Feed.
	 */
	public Button getFeedButton() {
		return feedButton;
	}

	/**
	 * Renvoie le bouton "Punish".
	 *
	 * @return Le bouton Punish.
	 */
	public Button getPunishButton() {
		return punishButton;
	}

	/**
	 * Renvoie le bouton "Ignore".
	 *
	 * @return Le bouton Ignore.
	 */
	public Button getIgnoreButton() {
		return ignoreButton;
	}

	/**
	 * Renvoie le bouton "Judge".
	 *
	 * @return Le bouton Judge.
	 */
	public Button getJudgeButton() {
		return judgeButton;
	}

	/**
	 * Renvoie le bouton "Exit".
	 *
	 * @return Le bouton Judge.
	 */
	public Button getExitButton() {
		return exitButton;
	}

	public Label getNotificationLabel() {
		return notificationLabel;
	}

	/**
	 * Crée l'interface utilisateur pour la vue Tamagotchi.
	 * Initialise les composants graphiques tels que l'image, les boutons d'interaction et le label de notification,
	 * et les organise au sein de conteneurs appropriés.
	 */
	@Override
	protected void createUI() {
		rootPane = new VBox();
		rootPane.setPadding(new Insets(10));
		rootPane.setSpacing(10);
		rootPane.setAlignment(Pos.CENTER);

		notificationLabel = new Label();
		notificationLabel.setVisible(false);

		imageView = new ImageView();
		imageView.setFitHeight(256);
		imageView.setFitWidth(256);

		HBox gameButtonBox = new HBox();
		gameButtonBox.setAlignment(Pos.CENTER);
		gameButtonBox.setSpacing(10);

		HBox settingButtonBox = new HBox();
		settingButtonBox.setAlignment(Pos.CENTER);
		settingButtonBox.setSpacing(10);

		
		playButton = new Button(Constants.BUTTON_PLAY);
		feedButton = new Button(Constants.BUTTON_FEED);
		punishButton = new Button(Constants.BUTTON_PUNISH);
		ignoreButton = new Button(Constants.BUTTON_IGNORE);
		judgeButton = new Button(Constants.BUTTON_JUDGE);
		exitButton = new Button(Constants.BUTTON_EXIT);


		gameButtonBox.getChildren().addAll(playButton, feedButton, punishButton, ignoreButton, judgeButton);
		settingButtonBox.getChildren().add(exitButton);

		rootPane.getChildren().addAll(notificationLabel, imageView, gameButtonBox, settingButtonBox);
	}
}

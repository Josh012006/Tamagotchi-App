package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.util.Constants;

/**
 * Vue principale pour la saisie du numéro de port.
 * Cette vue affiche un champ pour entrer le numéro de port, un bouton pour se connecter
 * et un label pour afficher les messages d'erreur.
 */
public class MainView extends ViewBase {

	/**
	 * Racine de la vue principale.
	 */
	private VBox root;

	/**
	 * Champ de texte pour saisir le numéro de port.
	 */
	private TextField portField;

	/**
	 * Bouton pour initier la connexion.
	 */
	private Button connectButton;

	/**
	 * Label pour afficher les messages d'erreur.
	 */
	private Label errorLabel;

	/**
	 * Constructeur par défaut pour la vue principale.
	 */
	public MainView() {
		super();
	}

	/**
	 * Retourne le nom de la vue principale.
	 *
	 * @return Le nom de la vue ("Main").
	 */
	@Override
	public String getName() {
		return "Main";
	}

	/**
	 * Renvoie la racine (root) de la vue principale.
	 *
	 * @return La racine de la vue.
	 */
	@Override
	public Parent getRoot() {
		return root;
	}

	/**
	 * Renvoie le champ de texte pour le numéro de port.
	 *
	 * @return Le champ de texte.
	 */
	public TextField getPortField() {
		return portField;
	}

	/**
	 * Renvoie le bouton de connexion.
	 *
	 * @return Le bouton de connexion.
	 */
	public Button getConnectButton() {
		return connectButton;
	}

	/**
	 * Renvoie le label destiné à afficher les messages d'erreur.
	 *
	 * @return Le label d'erreur.
	 */
	public Label getErrorLabel() {
		return errorLabel;
	}

	/**
	 * Crée l'interface utilisateur pour la vue principale.
	 * Initialise les éléments graphiques tels que le champ de texte, le bouton de connexion,
	 * et le label d'erreur.
	 */
	@Override
	protected void createUI() {


		root = new VBox(10);
		root.setPadding(new Insets(10));

		
		Text prompt = new Text("Entrez le numéro de port (par défaut " + Constants.DEFAULT_PORT + "):");
		connectButton = new Button(Constants.BUTTON_CONNECT);
		portField = new TextField();
		
		errorLabel = new Label();
		errorLabel.setVisible(false);
		
		
		root.getChildren().addAll(prompt, portField, connectButton, errorLabel);
		
		
	}
}


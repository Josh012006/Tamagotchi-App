package view;


import javafx.scene.Parent;

/**
 * Classe de base pour les vues de l'application.
 * Toutes les vues de l'application doivent hériter de cette classe et
 * implémenter
 * les méthodes abstraites définies ici.
 */
public abstract class ViewBase {

	/**
	 * Constructeur par défaut pour les vues.
	 * Appelle la méthode de création de l'interface utilisateur {@code createUI()}.
	 */
	protected ViewBase() {
		createUI();
	}

	/**
	 * Méthode statique pour obtenir une instance de vue basée sur son nom.
	 * 
	 * @param viewName - Le nom de la vue à obtenir.
	 * @return Une instance de la vue correspondant au nom spécifié.
	 * @throws IllegalArgumentException si le nom de la vue est invalide.
	 */
	public static ViewBase getView(String viewName) {
		switch (viewName.toLowerCase()) {
		case "main":
			return new MainView();

		case "tamagotchi":
			return new TamagotchiView();

		default:
			throw new IllegalArgumentException("Invalid view name...! " + viewName);
		}
	}

	/**
	 * Renvoie la racine (root) de la vue.
	 * 
	 * @return La racine (root) de la vue, un élément Parent de l'interface
	 *         utilisateur.
	 */
	public abstract Parent getRoot();

	/**
	 * Renvoie le nom de la vue.
	 * 
	 * @return Le nom de la vue en tant que chaîne de caractères.
	 */
	public abstract String getName();

	/**
	 * Méthode abstraite pour créer l'interface utilisateur de la vue.
	 * Cette méthode doit être implémentée par les sous-classes pour initialiser
	 * les composants graphiques de la vue.
	 */
	protected abstract void createUI();
}
package model.util;

/**
 * Classe utilitaire contenant les constantes globales de l'application.
 */
public final class Constants {

    // Configuration Réseau
    public static final String SERVER_ADDRESS = "localhost";
    public static final int DEFAULT_PORT = 2025;

    // Noms des événements
    public static final String EVENT_TAMAGOTCHI_BEHAVIOR = "TAMAGOTCHI_BEHAVIOR";
    public static final String EVENT_TREAT_TAMAGOTCHI = "TREAT_TAMAGOTCHI";
    public static final String EVENT_EXIT = "EXIT";

    // Titres des Boutons
    public static final String BUTTON_CONNECT = "Connecter";
    public static final String BUTTON_PLAY = "Jouer";
    public static final String BUTTON_FEED = "Nourrir";
    public static final String BUTTON_PUNISH = "Punir";
    public static final String BUTTON_IGNORE = "Ignorer";
    public static final String BUTTON_JUDGE = "Critiquer";
    public static final String BUTTON_EXIT = "Quitter";
    

    // Expressions des comportements
    public static final String EXPRESS_ANGER = "Je suis FURIEUX ! Mais... je t'aime quand même... un petit peu.";
    public static final String EXPRESS_CONTENT = "Tout est parfait... Tu es l'humain le plus génial du monde !";
    public static final String EXPRESS_HAPPINESS = "Je suis trop heureux ! La vie, c'est la fête !";
    public static final String EXPRESS_SADNESS = "Je me sens tout triste... Les bons moments me manquent...";
    public static final String EXPRESS_FEAR = "Je tremble... J'ai besoin d'un peu de douceur...";


    private Constants() {
        // Constructeur privé pour éviter l'instanciation
    }
}


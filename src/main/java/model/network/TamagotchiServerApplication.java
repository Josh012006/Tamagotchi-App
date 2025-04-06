package model.network;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Tamagotchi;
import model.action.TamagotchiAction;
import model.behavior.Behavior;
import model.emotion.EmotionType;
import model.util.Constants;

/**
 * Classe représentant le serveur pour l'application Tamagotchi.
 */
public class TamagotchiServerApplication {

	/**
	 * Instance unique de Tamagotchi.
	 */
	private static Tamagotchi tamagotchi = new Tamagotchi();

	private static int port = Constants.DEFAULT_PORT;
	private static String inputFile = null;
	private static String serializedFile = null;
	private static String outputFile = null;

	/**
	 * Démarre le serveur et écoute les événement des clients.
	 */
	public static void run() {
		try {

			Server s = new Server(port);

			sendTamagotchiBehaviorToClient(s);

			s.addEventHandler((Event event) -> {
				handleClientEvent(event, s);
			});

			s.listen();


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sendTamagotchiBehaviorToClient(Server s) {
		try {
			Behavior TamagotchiBehavior = tamagotchi.getTamagotchiOutwardBehavior();
			s.sendSerializableData(new Event(Constants.EVENT_TAMAGOTCHI_BEHAVIOR, TamagotchiBehavior));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Traite l'événement reçu du client.
	 * @param event L'événement contenant la commande et le comportement.
	 * @param s Le serveur utilisé pour envoyer des données.
	 */
	private static void handleClientEvent(Event event, Server s) {
		switch (event.getType()) {
		case Constants.EVENT_TREAT_TAMAGOTCHI:
		{
			TamagotchiAction action = (TamagotchiAction)event.getData();
			tamagotchi.treat(action);
			sendTamagotchiBehaviorToClient(s);
			break;
		}
		case Constants.EVENT_EXIT:{
			System.exit(0);

			break;
		}
		default:
			System.out.println("l'événement inconnu reçu sur le serveur: " + event);
			break;
		}
	}

	/**
	 * Affiche le message d'aide sur l'utilisation de l'application.
	 */
	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("  java TamagotchiServer -p N -i <path> [-o <path>]");
		System.out.println("  java TamagotchiServer -p N -s <path> [-o <path>]");
		System.out.println("Options:");
		System.out.println("  -p N      : Numéro de port (par défaut " + Constants.DEFAULT_PORT + ")");
		System.out.println("  -i <path> : Chemin du fichier texte avec les valeurs initiales pour un nouveau Tamagotchi");
		System.out.println("  -s <path> : Chemin du fichier avec un objet Tamagotchi sérialisé");
		System.out.println("  -o <path> : (Optionnel) Chemin du fichier pour sérialiser le Tamagotchi lors de la fermeture");
		System.out.println("  -h        : Affiche ce message d'aide");
		System.out.println("\nNote: Vous devez fournir exactement l'un des arguments -i ou -s.");
	}

	/**
	 * Analyse les arguments de la ligne de commande et définit les variables correspondantes.
	 * @param args Les arguments de la ligne de commande.
	 */
	private static void parseArguments(String[] args) {
		for (int i = 0; i < args.length; i++) {
			switch (args[i]) {
			case "-p":
				if (i + 1 < args.length) {
					try {
						port = Integer.parseInt(args[++i]);
						System.out.println("Port set to: "+ port);
					} catch (NumberFormatException e) {
						System.out.println("Mauvais numéro de port.");
						printHelp();
						System.exit(1);
					}
				} else {
					System.out.println("Numéro de port manquant.");
					printHelp();
					System.exit(1);
				}
				break;
			case "-i":
				if (i + 1 < args.length) {
					inputFile = args[++i];
				} else {
					System.out.println("Fichier d'entrée manquant.");
					printHelp();
					System.exit(1);
				}
				break;
			case "-s":
				if (i + 1 < args.length) {
					serializedFile = args[++i];
				} else {
					System.out.println("Fichier sérialisé manquant.");
					printHelp();
					System.exit(1);
				}
				break;
			case "-o":
				if (i + 1 < args.length) {
					outputFile = args[++i];
				} else {
					System.out.println("Fichier de sortie manquant.");
					printHelp();
					System.exit(1);
				}
				break;
			case "-h":
				printHelp();
				break;
			default:
				System.out.println("Argument inconnu: " + args[i]);
				printHelp();
				System.exit(1);
			}
		}
	}

	/**
	 * Valide que les arguments fournis sont corrects.
	 */
	private static void validateArguments() {
		if ((inputFile == null && serializedFile == null) || (inputFile != null && serializedFile != null)) {
			System.out.println("Erreur : vous devez fournir exactement l'un des éléments suivants : -i ou -s.");
			printHelp();
			System.exit(1);
		}
	}

	/**
	 * Initialise le Tamagotchi soit à partir d'un fichier texte, soit par désérialisation d'un objet.
	 */
	private static void initializeTamagotchi() {
		if (inputFile != null) 
			parseInitialValuesFromFile(inputFile);
		else if (serializedFile != null) 
			deserializeTamagotchiFromFile(serializedFile);

	}

	/**
	 * Désérialise un objet Tamagotchi à partir d'un fichier.
	 * @param filePath Chemin du fichier contenant l'objet sérialisé.
	 */
	private static void deserializeTamagotchiFromFile(String filePath) {
		try (ObjectInputStream or = new ObjectInputStream(new FileInputStream(filePath))) {
			
			Object read = (Object) or.readObject();
			if (read != null) {
				tamagotchi = (Tamagotchi) read;
			}
			
			or.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Analyse les valeurs initiales du Tamagotchi à partir d'un fichier texte.
	 * @param filePath Chemin du fichier d'entrée contenant les valeurs initiales.
	 */
	private static void parseInitialValuesFromFile(String filePath) {
		tamagotchi = new Tamagotchi();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				processLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} 


	}

	/**
	 * Traite une ligne du fichier d'entrée pour extraire l'émotion et sa valeur associée.
	 * @param line Ligne du fichier à traiter.
	 */
	private static void processLine (String line) {
		line = line.trim();
		if (line.isEmpty()) {
			return;
		}
		String[] parts = line.split(":");
		if (parts.length != 2) {
			System.out.println("Format de ligne non valide : " + line);
			return;
		}

		String emotionStr = parts[0].trim();
		String valueStr = parts[1].trim();
		try {
			int value = Integer.parseInt(valueStr);
			EmotionType emotion = EmotionType.valueOf(emotionStr.toUpperCase());
			updateTamagotchiEmotion(emotion, value);
		} catch (NumberFormatException e) {
			System.out.println("Format de numéro non valide dans la ligne: " + line);
		} catch (IllegalArgumentException e) {
			System.out.println("Émotion inconnue : " + emotionStr);
		}
	}

	/**
	 * Met à jour l'émotion du Tamagotchi avec la valeur spécifiée.
	 * @param emotion Type d'émotion.
	 * @param value Valeur de l'émotion.
	 */
	private static void updateTamagotchiEmotion(EmotionType emotion, int value) {
		switch (emotion) {
		case JOY:
			tamagotchi.increaseEmotionValue(emotion, value);
			break;
		case SADNESS:
			tamagotchi.increaseEmotionValue(emotion, value);
			break;
		case ANGER:
			tamagotchi.increaseEmotionValue(emotion, value);
			break;
		case FEAR:
			tamagotchi.increaseEmotionValue(emotion, value);
			break;
		default:
			System.out.println("Commande d'émotion inconnue: " + emotion);
			break;

		}
	}

	/**
	 * Enregistre un hook pour sérialiser l'objet Tamagotchi à la fermeture de l'application.
	 */
	private static void registerShutdownHook() {
		if (outputFile != null) {
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				System.out.println("Arrêt du serveur... Sérialisation de Tamagotchi dans le fichier :" + outputFile);

				try (ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(outputFile))) {
					
					oo.writeObject(tamagotchi);
					oo.close();
					
				} catch(IOException e) {
					System.out.println("Une erreur est survenue lors de la sérialisation de Tamagotchi dasn le fichier :" + outputFile);
					e.printStackTrace();
				}
			}));
		}
	}


	/**
	 * Point d'entrée principal de l'application.
	 * @param args Les arguments de la ligne de commande.
	 */
	public static void main(String[] args) {
		parseArguments(args);

		validateArguments();

		initializeTamagotchi();

		registerShutdownHook();

		run();
	}

	/**
	 * Retourne l'instance actuelle de Tamagotchi.
	 * @return L'objet Tamagotchi.
	 */
	public static Tamagotchi getTamagotchi() {
		return tamagotchi;
	}

	/**
	 * Définit une nouvelle instance de Tamagotchi.
	 * @param tamagotchi La nouvelle instance de Tamagotchi.
	 */
	public static void setTamagotchi(Tamagotchi tamagotchi) {
		TamagotchiServerApplication.tamagotchi = tamagotchi;
	}

}

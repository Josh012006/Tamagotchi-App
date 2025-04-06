package model.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un composant réseau.
 * Cette classe encapsule la gestion des sockets, des flux d'objets et le dispatch d'événements.
 */
public abstract class Network {

	/** La socket utilisée pour la communication réseau. */
	protected Socket socket;

	/** Le flux d'entrée pour lire des objets depuis la socket. */
	protected static ObjectInputStream in;

	/** Le flux de sortie pour envoyer des objets vers la socket. */
	protected static ObjectOutputStream out;

	/** Liste des gestionnaires d'événements enregistrés. */
	protected static List<EventHandler> eventHandlers = new ArrayList<>();

	/**
	 * Construit un composant réseau à partir d'une socket déjà créée.
	 * Initialise les flux d'objets.
	 *
	 * @param socket La socket utilisée pour la communication.
	 * @throws IOException Si une erreur survient lors de l'initialisation des flux.
	 */
	public Network(Socket socket) throws IOException{
		initialize(socket);
	}

	public Network() {}

	/**
	 * Initialise le composant réseau en configurant les flux d'entrée et de sortie.
	 *
	 * @param socket La socket à utiliser pour la communication.
	 * @throws IOException Si une erreur survient lors de l'initialisation des flux.
	 */
	public void initialize(Socket socket) throws IOException {
		this.socket = socket;

		this.out = new ObjectOutputStream(socket.getOutputStream());
		this.out.flush();
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	/**
	 * Ajoute un gestionnaire d'événements.
	 *
	 * @param handler Le gestionnaire d'événements à ajouter.
	 */
	public void addEventHandler(EventHandler handler) {
		eventHandlers.add(handler);
	}

	/**
	 * Envoie un objet sérialisable via le flux d'objets.
	 *
	 * @param data L'objet à envoyer.
	 * @throws IOException En cas d'erreur lors de l'envoi.
	 */
	public void sendSerializableData(Object data) throws IOException {

		if (out != null) {
			out.reset();
			out.writeObject(data);
			out.flush();
			System.out.println("Données envoyées : " + data);
		}
	}

	/**
	 * Démarre un thread d'écoute qui lit en continu les objets depuis le flux
	 * et transmet les événements aux gestionnaires enregistrés.
	 */
	public void listen() {
		new Thread(() -> {
			try {
				while (true) {
					Object obj = in.readObject();
					if (obj instanceof Event) {
						Event event = (Event) obj;
						System.out.println("Événement reçu : " + event.getType());
						alertEventHandlers(event);
					}
				}
			} catch (Exception e) {
				System.out.println("Erreur dans le thread d'écoute : " + e.getMessage());
			} finally {
				close();
			}
		}).start();
	}

	/**
	 * Notifie tous les gestionnaires d'événements enregistrés.
	 *
	 * @param event L'événement à diffuser.
	 */
	protected void alertEventHandlers(Event event) {
		for (EventHandler handler : eventHandlers) {
			handler.handle(event);
		}
	}

	/**
	 * Ferme les flux et la socket.
	 */
	public void close() {
		System.out.println("Fermeture du composant réseau.");
		try {
			if (in != null) in.close();
			if (out != null) out.close();
			if (socket != null) socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

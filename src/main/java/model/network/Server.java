package model.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe représentant un serveur qui attend une connexion client.
 * Cette classe hérite de la classe `Network` pour gérer la communication avec le client.
 */
public class Server extends Network {

	/** Le serveur socket utilisé pour écouter les connexions des clients. */
	private static ServerSocket server;


	/**
	 * Construit un serveur qui écoute sur le port spécifié.
	 * Il attend une connexion client avant d'initialiser la communication.
	 *
	 * @param port Le port sur lequel le serveur écoute.
	 * @throws IOException En cas d'erreur lors de la création du serveur ou de l'acceptation d'un client.
	 */
	public Server(int port) throws IOException {
		super(initialiserSockets(port));
		System.out.println("Serveur démarré sur le port " + port);
	}

	/**
	 * Initialise le serveur et attend la connexion d'un client.
	 *
	 * @param port Le port sur lequel le serveur doit écouter.
	 * @return La socket de connexion du client.
	 * @throws IOException Si une erreur survient lors de la création du serveur ou de l'acceptation d'un client.
	 */
	private static Socket initialiserSockets(int port) throws IOException {
		System.out.println("Démarrage du serveur...");

		// Création du serveur socket
		server = new ServerSocket(port, 1);
		System.out.println("Serveur en attente d'une connexion sur le port " + port + "...");

		// Acceptation de la connexion d'un client
		Socket clientSocket = server.accept();

		return clientSocket;
	}


	/**
	 * Ferme le serveur ainsi que la socket client.
	 */
	@Override
	public void close() {
		super.close();
		try {
			if (server != null) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


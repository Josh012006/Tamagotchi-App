package model.network;

import java.io.Serializable;

/**
 * Classe représentant un événement dans l'application.
 * Chaque événement a un type et des données associées à cet événement.
 */
public class Event implements Serializable {

	/** Le type de l'événement. */
	private String type;

	/** Les données associées à l'événement. */
	private Serializable data;

	/**
	 * Constructeur pour créer un nouvel événement avec un type et des données.
	 *
	 * @param type Le type de l'événement.
	 * @param data Les données associées à l'événement.
	 */
	public Event(String type, Serializable data) {
		this.type = type;
		this.data = data;
	}

	/**
	 * Retourne le type de l'événement.
	 *
	 * @return Le type de l'événement.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Retourne les données associées à l'événement.
	 *
	 * @return Les données associées à l'événement.
	 */
	public Serializable getData() {
		return data;
	}
}

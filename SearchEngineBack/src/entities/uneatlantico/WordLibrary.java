package entities.uneatlantico;

import java.util.List;

public class WordLibrary {

	private String word;
	private List<WordWeight> weight;

	/**
	 * Constructor de la clase WordLibrary, que almacena una palabra y su peso en la
	 * colección de documentos.
	 * 
	 * @param word
	 *            Palabra a almacenar.
	 * @param weight
	 *            Lista de objetos de tipo WordWeight.
	 */
	public WordLibrary(String word, List<WordWeight> weight) {
		super();
		this.word = word;
		this.weight = weight;
	}

	/**
	 * Devuelve la palabra.
	 * 
	 * @return Palabra.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Establece la palabra a almacenar.
	 * 
	 * @param word
	 *            Palabra a almacenar.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Devuelve la lista de objetos de tipo WordWeight.
	 * 
	 * @return Lista de objetos de tipo WordWeight.
	 */
	public List<WordWeight> getWeight() {
		return weight;
	}

	/**
	 * Establce la lista de objetos de tipo WordWeight.
	 * 
	 * @param weight
	 *            Lista de objetos de tipo WordWeight.
	 */
	public void setWeight(List<WordWeight> weight) {
		this.weight = weight;
	}

}

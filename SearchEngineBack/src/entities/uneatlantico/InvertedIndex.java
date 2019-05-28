package entities.uneatlantico;

public class InvertedIndex {

	private String word;
	private TermFrecuency stats;

	/**
	 * Constructor de la clase InvertedIndex que almacena la palabra y sus
	 * estadisticas dentro de un documento.
	 * 
	 * @param word
	 *            Palabra del documento.
	 * @param occurance
	 *            Objeto del tipo TermFrecuency.
	 */
	public InvertedIndex(String word, TermFrecuency occurance) {
		super();
		this.word = word;
		this.stats = occurance;
	}

	/**
	 * Devuelve la palabra.
	 * 
	 * @return Palabra del documento.
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Establece la palabra.
	 * 
	 * @param word
	 *            Palabra a establecer.
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Devuelve el objeto de tipo TermFrecuency.
	 * 
	 * @return Objeto del tipo TermFrecuency.
	 */
	public TermFrecuency getStats() {
		return stats;
	}

	/**
	 * Establece el objeto de tipo TermFrecuency.
	 * 
	 * @param stats
	 *            Objeto del tipo TermFrecuency.
	 */
	public void setStats(TermFrecuency stats) {
		this.stats = stats;
	}

}

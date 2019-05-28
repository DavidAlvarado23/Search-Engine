package entities.uneatlantico;

public class WordWeight {

	private Document document;
	private double idf;

	/**
	 * Constructor de la clase WordWeight, que almacena el Inverse Document
	 * Frecuency y el objeto de tipo Documento.
	 * 
	 * @param document
	 *            Objero de tipo Document.
	 * @param idf
	 *            Cifra decimal calculada, Inverse Document Frecuency.
	 */
	public WordWeight(Document document, double idf) {
		super();
		this.document = document;
		this.idf = idf;
	}

	/**
	 * Devuelve el objeto de tipo Document.
	 * 
	 * @return Objeto de tipo Document.
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Establece el objeto de tipo Document.
	 * 
	 * @param document
	 *            Objeto de tipo Document.
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * Devuelve el Inverse Document Frecuency.
	 * 
	 * @return IDF.
	 */
	public double getIdf() {
		return idf;
	}

	/**
	 * Esteable el Inverse Document Frecuency.
	 * 
	 * @param idf
	 *            Cifra decimal, IDF.
	 */
	public void setIdf(double idf) {
		this.idf = idf;
	}

}

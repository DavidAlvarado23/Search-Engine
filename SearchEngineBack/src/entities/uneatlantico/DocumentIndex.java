package entities.uneatlantico;

import java.util.List;

public class DocumentIndex {

	private Document doc;
	private List<InvertedIndex> docIndex;

	/**
	 * Constructor de la clase DocumentIndex que tiene el documento y sus
	 * estadisticas.
	 * 
	 * @param doc
	 *            Objeto de tipo Documento.
	 * @param docIndex
	 *            Lista de objetos de tipo InvertedIndex.
	 */
	public DocumentIndex(Document doc, List<InvertedIndex> docIndex) {
		super();
		this.doc = doc;
		this.docIndex = docIndex;
	}

	/**
	 * Devuelve el objeto de tipo Documento.
	 * 
	 * @return Objeto de tipo Documento.
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * Establece el objeto de tipo Documento.
	 * 
	 * @param doc
	 *            Objeto del tipo Documento.
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	/**
	 * Devuelve lista de objetos de tipo InvertedIndex.
	 * 
	 * @return Lista de objetos de tipo InvertedIndex.
	 */
	public List<InvertedIndex> getDocIndex() {
		return docIndex;
	}

	/**
	 * Establece la lista de objetos de tipo InvertedIndex
	 * 
	 * @param docIndex
	 *            Lista de objetos de tipo InvertedIndex.
	 */
	public void setDocIndex(List<InvertedIndex> docIndex) {
		this.docIndex = docIndex;
	}

}

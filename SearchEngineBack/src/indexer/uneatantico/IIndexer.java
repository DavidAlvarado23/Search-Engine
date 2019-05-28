package indexer.uneatantico;

import java.util.List;

import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.WordLibrary;

public interface IIndexer {

	/**
	 * Indexa el documento y lo almacena en una librer�a global.
	 * 
	 * @param doc
	 *            Documento que se quiere a�adir.
	 * @param documents
	 *            Coleccion total de documentos.
	 * @return Lista de objetos de tipo WordLibrary.
	 */
	public void index(DocumentIndex doc, List<DocumentIndex> documents);

	public List<WordLibrary> getWordLibrary();

	public void setWordLibrary(List<WordLibrary> wordLibrary);
}

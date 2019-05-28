package indexer.uneatantico;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Singleton;

import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.WordLibrary;
import entities.uneatlantico.WordWeight;

@Singleton
public class Indexer implements IIndexer {

	private List<WordLibrary> wordLibrary;
	private static Logger log = Logger.getLogger(Indexer.class.getName());

	/**
	 * Constructor de la clase Indexer, que indexará un documento de una colección.
	 */
	public Indexer() {
		super();
		this.wordLibrary = null;
	}

	/**
	 * Devuelve la libreria global de palabras.
	 * 
	 * @return Lista de objetos de tipo WordLibrary.
	 */
	public List<WordLibrary> getWordLibrary() {
		return wordLibrary;
	}

	/**
	 * Establece la libreria global de palabras.
	 * 
	 * @param wordLibrary
	 *            Lista de objetos de tipo WordLibrary.
	 */
	public void setWordLibrary(List<WordLibrary> wordLibrary) {
		this.wordLibrary = wordLibrary;
	}

	@Override
	public void index(DocumentIndex doc, List<DocumentIndex> totalDocuments) {
		if (this.wordLibrary == null)
			this.wordLibrary = new ArrayList<>();
		log.info("Indexing document located in: " + doc.getDoc().getPath());
		doc.getDocIndex().forEach(stat -> {
			Object[] wordFound = this.wordLibrary.stream().filter(library -> library.getWord().equals(stat.getWord()))
					.toArray();
			if (wordFound.length == 0) {
				// No existe la palabra en el diccionario global.
				List<WordWeight> wordStat = new ArrayList<>();
				double tf = stat.getStats().getAppearance();
				double idf = InverseDocumentFrecuency.calculateIDF(totalDocuments.size(),
						InverseDocumentFrecuency.getDocumentsContaining(totalDocuments, stat.getWord()));
				double weight = DocumentWeight.calculateWeight(tf, idf);
				wordStat.add(new WordWeight(doc.getDoc(), weight));
				this.wordLibrary.add(new WordLibrary(stat.getWord(), wordStat));
			} else {
				// Existe la palabra.
				double tf = stat.getStats().getAppearance();
				double idf = InverseDocumentFrecuency.calculateIDF(totalDocuments.size(),
						InverseDocumentFrecuency.getDocumentsContaining(totalDocuments, stat.getWord()));
				double weight = DocumentWeight.calculateWeight(tf, idf);
				((WordLibrary) wordFound[0]).getWeight().add(new WordWeight(doc.getDoc(), weight));
			}
		});
	}

}

package indexer.uneatantico;

import java.util.ArrayList;
import java.util.List;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;


public class InverseDocumentFrecuency {

	/**
	 * Devuelve el numero de documentos en el cual aparece la palabra dada.
	 * 
	 * @param documents
	 *            Lista de objetos de tipo DocumentIndex.
	 * @param word
	 *            Palabra dada.
	 * @return Número de documentos en el cual aparece la palabra.
	 */
	public static int getDocumentsContaining(List<DocumentIndex> documents, String word) {
		List<Document> appearance = new ArrayList<>();
		documents.forEach(doc -> {
			Object[] found = doc.getDocIndex().stream().filter(stats -> stats.getWord().equals(word)).toArray();
			if (found.length > 0)
				appearance.add(doc.getDoc());
		});
		return appearance.size();
	}

	/**
	 * Calcula el IDF (Inverse Document Frecuency)
	 * 
	 * @param numberOfTotalDocuments
	 *            Número total de documentos de la colección.
	 * @param numberContainingWord
	 *            Número de documentos que contienen la palabra.
	 * @return IDF de la palabra en la colección, entre 0-1.
	 */
	public static Double calculateIDF(int numberOfTotalDocuments, int numberContainingWord) {
		return Math.log10(1 + ((float) numberOfTotalDocuments / numberContainingWord));
	}

}

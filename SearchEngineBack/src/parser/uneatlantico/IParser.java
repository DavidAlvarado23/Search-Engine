package parser.uneatlantico;

import entities.uneatlantico.DocumentIndex;

public interface IParser {

	/**
	 * Parsea el documento.
	 * 
	 * @param filePath
	 *            Ruta del documento.
	 * @return Objeto del tipo DocumentIndex.
	 */
	public DocumentIndex parse(String filePath);
}

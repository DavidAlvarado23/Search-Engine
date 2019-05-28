package service.uneatlantico;

import java.util.List;

import entities.uneatlantico.Document;

public interface IService {

	/**
	 * Indexa una serie de documentos.
	 * 
	 * @param fullPath
	 *            Ruta del directorio que contiene los documentos.
	 */
	public void Index(String fullPath);

	/**
	 * Busca la palabra en los documentos indexados.
	 * 
	 * @param wordToSearch
	 *            Palabra a buscar.
	 * @return Lista de objetos de tipo Documento que contienen la palabra.
	 */
	public List<Document> Search(String wordToSearch);
}

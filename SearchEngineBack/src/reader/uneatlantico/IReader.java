package reader.uneatlantico;

import java.util.List;

public interface IReader {

	/**
	 * Lee el directorio que contiene los documentos.
	 * 
	 * @param fullPath
	 *            Ruta del directorio.
	 * @return Lista de cadenas de texto con las rutas individuales de los
	 *         documentos.
	 */
	public List<String> read(String fullPath);
}

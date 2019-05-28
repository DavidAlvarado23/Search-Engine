package factory.uneatlantico;

import org.apache.commons.io.FilenameUtils;

import reader.uneatlantico.DataAccessType;

public class FileFactory {

	/**
	 * Método Factory que devuelve el tipo de fichero como Enum.
	 * 
	 * @param path
	 *            Ruta del fichero.
	 * @return Enum del tipo de archivo.
	 */
	public static DataAccessType getFileExtension(String path) {
		String extension = FilenameUtils.getExtension(path);
		if (extension.equals("txt"))
			return DataAccessType.FILE;
		if (extension.equals("docx"))
			return DataAccessType.WORD;
		if (extension.equals("doc"))
			return DataAccessType.WORDOLD;
		if (extension.equals("xlsx"))
			return DataAccessType.EXCEL;
		if (extension.equals("pdf"))
			return DataAccessType.PDF;
		if (extension.equals("xml"))
			return DataAccessType.XML;
		return null;
	}
}

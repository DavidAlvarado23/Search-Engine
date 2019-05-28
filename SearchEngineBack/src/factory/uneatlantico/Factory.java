package factory.uneatlantico;

import parser.uneatlantico.ExcelParser;
import parser.uneatlantico.FileParser;
import parser.uneatlantico.IParser;
import parser.uneatlantico.OldWordParser;
import parser.uneatlantico.PDFParser_;
import parser.uneatlantico.WordParser;
import parser.uneatlantico.XMLParser;
import reader.uneatlantico.DataAccessType;

public class Factory {

	/**
	 * Método Factory que devuelve el objeto para parsear el documento.
	 * 
	 * @param dt
	 *            Enum del tipo de documento
	 * @return Objeto parseador del fichero.
	 */
	public static IParser getAccessType(DataAccessType dt) {
		if (dt == DataAccessType.FILE)
			return new FileParser();
		if (dt == DataAccessType.WORD)
			return new WordParser();
		if (dt == DataAccessType.WORDOLD)
			return new OldWordParser();
		if (dt == DataAccessType.EXCEL)
			return new ExcelParser();
		if (dt == DataAccessType.PDF)
			return new PDFParser_();
		if (dt == DataAccessType.XML)
			return new XMLParser();
		return null;
	}

}

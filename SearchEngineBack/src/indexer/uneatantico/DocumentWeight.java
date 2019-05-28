package indexer.uneatantico;

public class DocumentWeight {

	/**
	 * Calcula el peso del documento en la colecci�n.
	 * 
	 * @param tf
	 *            El Term Frecuency.
	 * @param idf
	 *            El Inverse Document Frecuency.
	 * @return El n�mero de peso del documento en la colecci�n.
	 */
	public static double calculateWeight(double tf, double idf) {
		return tf * idf;
	}

}

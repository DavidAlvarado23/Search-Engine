package indexer.uneatantico;

public class DocumentWeight {

	/**
	 * Calcula el peso del documento en la colección.
	 * 
	 * @param tf
	 *            El Term Frecuency.
	 * @param idf
	 *            El Inverse Document Frecuency.
	 * @return El número de peso del documento en la colección.
	 */
	public static double calculateWeight(double tf, double idf) {
		return tf * idf;
	}

}

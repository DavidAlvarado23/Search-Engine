package entities.uneatlantico;

public class Document {

	private String name;
	private String path;

	/**
	 * Constructor de la clase Document, que tiene los datos de un documento.
	 * 
	 * @param name
	 *            Nombre del documento.
	 * @param path
	 *            Ruta de ubicación del documento.
	 */
	public Document(String name, String path) {
		super();
		this.name = name;
		this.path = path;
	}

	/**
	 * Develve el nombre del documento.
	 * 
	 * @return Nombre del documento.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Establece el nombre del documento.
	 * 
	 * @param name
	 *            Nombre del documento.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Devuelve la ruta del documento.
	 * 
	 * @return Ruta del documento.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Establece la ruta del documento.
	 * 
	 * @param path
	 *            Ruta del documento.
	 */
	public void setPath(String path) {
		this.path = path;
	}

}

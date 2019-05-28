package entities.uneatlantico;

import java.util.List;

public class TermFrecuency {

	private int appearance;
	private List<Integer> pages;

	/**
	 * Constructor de la clase TermFrecuency que establece las estadísticas de la
	 * palabra en un documento.
	 * 
	 * @param appearance
	 *            Número de veces que aparece.
	 * @param pages
	 *            Lista de números en cual aparece la palabra.
	 */
	public TermFrecuency(int appearance, List<Integer> pages) {
		super();
		this.appearance = appearance;
		this.pages = pages;
	}

	/**
	 * Devuelve el número de veces que aparece la palabra en el documento.
	 * 
	 * @return Apariciones de la palabra.
	 */
	public int getAppearance() {
		return appearance;
	}

	/**
	 * Establece el numero de apariciones de la palabra en el documento.
	 * 
	 * @param appearance
	 *            Número de apariciones.
	 */
	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	/**
	 * Devuelve el numero de las páginas en las que la palabra aparece.
	 * 
	 * @return Lista de número de página.
	 */
	public List<Integer> getPages() {
		return pages;
	}

	/**
	 * Establece el número de las páginas en la que la palabra aparece.
	 * 
	 * @param pages
	 *            Lista de número de páginas.
	 */
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

}

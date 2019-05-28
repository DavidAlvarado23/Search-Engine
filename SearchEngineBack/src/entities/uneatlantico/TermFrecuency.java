package entities.uneatlantico;

import java.util.List;

public class TermFrecuency {

	private int appearance;
	private List<Integer> pages;

	/**
	 * Constructor de la clase TermFrecuency que establece las estad�sticas de la
	 * palabra en un documento.
	 * 
	 * @param appearance
	 *            N�mero de veces que aparece.
	 * @param pages
	 *            Lista de n�meros en cual aparece la palabra.
	 */
	public TermFrecuency(int appearance, List<Integer> pages) {
		super();
		this.appearance = appearance;
		this.pages = pages;
	}

	/**
	 * Devuelve el n�mero de veces que aparece la palabra en el documento.
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
	 *            N�mero de apariciones.
	 */
	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	/**
	 * Devuelve el numero de las p�ginas en las que la palabra aparece.
	 * 
	 * @return Lista de n�mero de p�gina.
	 */
	public List<Integer> getPages() {
		return pages;
	}

	/**
	 * Establece el n�mero de las p�ginas en la que la palabra aparece.
	 * 
	 * @param pages
	 *            Lista de n�mero de p�ginas.
	 */
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

}

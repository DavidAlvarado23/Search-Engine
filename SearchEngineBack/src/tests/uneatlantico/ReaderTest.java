package tests.uneatlantico;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import reader.uneatlantico.Reader;

public class ReaderTest {

	@Test
	public void test() {
		String directoryPath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents";
		List<String> paths = new Reader().read(directoryPath);

		List<String> expected = new ArrayList<>();
		expected.add(directoryPath + "\\Conferencia Arquitecturas BD.docx");
		expected.add(directoryPath + "\\Fichero.txt");
		expected.add(directoryPath + "\\PDF.pdf");
		expected.add(directoryPath + "\\provincias.xlsx");
		expected.add(directoryPath + "\\Excel.xlsx");
		expected.add(directoryPath + "\\Word.docx");

		Assert.assertEquals(expected.get(0), paths.get(0));
		Assert.assertEquals(expected.get(1), paths.get(1));
		Assert.assertEquals(expected.get(2), paths.get(2));
		Assert.assertEquals(expected.get(3), paths.get(3));
	}

}

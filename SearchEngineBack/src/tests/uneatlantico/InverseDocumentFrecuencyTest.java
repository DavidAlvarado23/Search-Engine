package tests.uneatlantico;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.DocumentIndex;
import factory.uneatlantico.Factory;
import factory.uneatlantico.FileFactory;
import indexer.uneatantico.InverseDocumentFrecuency;
import reader.uneatlantico.Reader;

public class InverseDocumentFrecuencyTest {

	@Test
	public void getDocumentsContainingTest() {
		List<String> paths = new Reader().read(
				"C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents");
		List<DocumentIndex> documents = new ArrayList<>();
		paths.forEach(path -> {
			documents.add(Factory.getAccessType(FileFactory.getFileExtension(path)).parse(path));
		});
		int actual = InverseDocumentFrecuency.getDocumentsContaining(documents, "en");
		Assert.assertEquals(4, actual);
	}

	@Test
	public void calculateIDFTest() {
		double actual = InverseDocumentFrecuency.calculateIDF(7, 4);
		Assert.assertEquals(0.4393, actual, 0.05);
	}

}

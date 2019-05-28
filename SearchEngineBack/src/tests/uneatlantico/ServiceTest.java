package tests.uneatlantico;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import indexer.uneatantico.Indexer;
import reader.uneatlantico.Reader;
import serializer.uneatlantico.Serializer;
import service.uneatlantico.Service;

public class ServiceTest {

	@Test
	public void indexTest() {
		Service service = new Service(new Reader(), new Indexer(), new Serializer());
		service.Index(
				"C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents");

		List<String> documentsPath = new ArrayList<>();
		service.getIndexer().getWordLibrary().forEach(book -> {
			book.getWeight().forEach(weight -> {
				String name = weight.getDocument().getName();
				if (!documentsPath.contains(name))
					documentsPath.add(name);
			});
		});

		Assert.assertEquals(9, documentsPath.size());
		
		Assert.assertEquals("Conferencia Arquitecturas BD.docx", documentsPath.get(0));
		Assert.assertEquals("PDF.pdf", documentsPath.get(1));
		Assert.assertEquals("Fichero.txt", documentsPath.get(2));
		Assert.assertEquals("Word.docx", documentsPath.get(3));
		Assert.assertEquals("solr-word.pdf", documentsPath.get(4));
		Assert.assertEquals("version_control.txt", documentsPath.get(5));
		Assert.assertEquals("version_control.xml", documentsPath.get(6));
		Assert.assertEquals("word2003.doc", documentsPath.get(7));
		Assert.assertEquals("provincias.xlsx", documentsPath.get(8));
	}

	@Test
	public void searchTest() {
		Service service = new Service(new Reader(), new Indexer(), new Serializer());
		service.Index(
				"C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents");
		List<Document> documents = service.Search("en");

		Assert.assertEquals("Conferencia Arquitecturas BD.docx", documents.get(0).getName());
		Assert.assertEquals("PDF.pdf", documents.get(1).getName());
		Assert.assertEquals("Fichero.txt", documents.get(2).getName());
		Assert.assertEquals("Word.docx", documents.get(3).getName());
	}

}

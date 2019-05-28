package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.XMLParser;

public class XMLParserTest {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\version_control.xml";
		DocumentIndex actual = new XMLParser().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("version_control.xml", filePath), new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("Solr", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("Version", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("Control", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("System", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("Overview", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("The", new TermFrecuency(3, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("here", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().add(new InvertedIndex("Here", new TermFrecuency(2, new ArrayList<>())));

		// Para la información del documento.
		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: Solr.
		Assert.assertEquals(expected.getDocIndex().get(0).getWord(), actual.getDocIndex().get(0).getWord());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getAppearance(),
				actual.getDocIndex().get(0).getStats().getAppearance());
		
		// Para la palabra: The.
				Assert.assertEquals(expected.getDocIndex().get(5).getWord(), actual.getDocIndex().get(5).getWord());
				Assert.assertEquals(expected.getDocIndex().get(5).getStats().getAppearance(),
						actual.getDocIndex().get(5).getStats().getAppearance());
	}

}

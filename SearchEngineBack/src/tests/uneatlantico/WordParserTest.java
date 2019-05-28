package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.WordParser;

public class WordParserTest {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\Conferencia Arquitecturas BD.docx";
		DocumentIndex actual = new WordParser().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("Conferencia Arquitecturas BD.docx", filePath),
				new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("sistemas", new TermFrecuency(97, new ArrayList<>())));
		expected.getDocIndex().get(0).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("memoria", new TermFrecuency(64, new ArrayList<>())));
		expected.getDocIndex().get(1).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("datos", new TermFrecuency(164, new ArrayList<>())));
		expected.getDocIndex().get(2).getStats().getPages().add(1);

		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: sistemas.
		Object[] found1 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("sistemas")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(0).getWord(), ((InvertedIndex) found1[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getAppearance(),
				((InvertedIndex) found1[0]).getStats().getAppearance());

		// Para la palabra: memoria.
		Object[] found2 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("memoria")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(1).getWord(), ((InvertedIndex) found2[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getAppearance(),
				((InvertedIndex) found2[0]).getStats().getAppearance());

		// Para la palabra: datos.
		Object[] found3 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("datos")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(2).getWord(), ((InvertedIndex) found3[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(2).getStats().getAppearance(),
				((InvertedIndex) found3[0]).getStats().getAppearance());
	}

}

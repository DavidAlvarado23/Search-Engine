package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.OldWordParser;

public class OldWordParserTest {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\word2003.doc";
		DocumentIndex actual = new OldWordParser().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("word2003.doc", filePath), new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("This", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().get(0).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("is", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(1).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("a", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(2).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("test", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(2).getStats().getPages().add(1);

		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: is.
		Object[] found1 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("is")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(1).getWord(), ((InvertedIndex) found1[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getAppearance(),
				((InvertedIndex) found1[0]).getStats().getAppearance());

		// Para la palabra: test.
		Object[] found2 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("test")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(3).getWord(), ((InvertedIndex) found2[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(3).getStats().getAppearance(),
				((InvertedIndex) found2[0]).getStats().getAppearance());
	}

}

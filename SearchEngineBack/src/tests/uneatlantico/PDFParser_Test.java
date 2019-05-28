package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.PDFParser_;

public class PDFParser_Test {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\PDF.pdf";
		DocumentIndex actual = new PDFParser_().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("PDF.pdf", filePath), new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("valores", new TermFrecuency(9, new ArrayList<>())));
		expected.getDocIndex().get(0).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("algoritmos", new TermFrecuency(142, new ArrayList<>())));
		expected.getDocIndex().get(1).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("java", new TermFrecuency(65, new ArrayList<>())));
		expected.getDocIndex().get(2).getStats().getPages().add(1);

		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: valores.
		Object[] found1 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("valores")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(0).getWord(), ((InvertedIndex) found1[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getAppearance(),
				((InvertedIndex) found1[0]).getStats().getAppearance());

		// Para la palabra: valores.
		Object[] found2 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("algoritmos")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(1).getWord(), ((InvertedIndex) found2[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getAppearance(),
				((InvertedIndex) found2[0]).getStats().getAppearance());

		// Para la palabra: valores.
		Object[] found3 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("java")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(2).getWord(), ((InvertedIndex) found3[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(2).getStats().getAppearance(),
				((InvertedIndex) found3[0]).getStats().getAppearance());

	}

}

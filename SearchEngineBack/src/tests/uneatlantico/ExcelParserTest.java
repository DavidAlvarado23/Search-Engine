package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.ExcelParser;

public class ExcelParserTest {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\provincias.xlsx";
		DocumentIndex actual = new ExcelParser().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("provincias.xlsx", filePath), new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("Total", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().get(0).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("Generada", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().get(1).getStats().getPages().add(1);

		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: Total.
		Object[] found1 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("Total")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(0).getWord(), ((InvertedIndex) found1[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getAppearance(),
				((InvertedIndex) found1[0]).getStats().getAppearance());

		// Para la palabra: Generada.
		Object[] found2 = expected.getDocIndex().stream().filter(x -> x.getWord().equals("Generada")).toArray();
		Assert.assertEquals(expected.getDocIndex().get(1).getWord(), ((InvertedIndex) found2[0]).getWord());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getAppearance(),
				((InvertedIndex) found2[0]).getStats().getAppearance());
	}

}

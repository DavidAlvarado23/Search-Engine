package tests.uneatlantico;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.FileParser;

public class FileParserTest {

	@Test
	public void test() {
		String filePath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents\\Fichero.txt";
		DocumentIndex actual = new FileParser().parse(filePath);

		DocumentIndex expected = new DocumentIndex(new Document("Fichero.txt", filePath), new ArrayList<>());
		expected.getDocIndex().add(new InvertedIndex("Fichero", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(0).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("ejemplo", new TermFrecuency(3, new ArrayList<>())));
		expected.getDocIndex().get(1).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("para", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(2).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("parsear", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(3).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("en", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(4).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("el", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(5).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("proyecto", new TermFrecuency(3, new ArrayList<>())));
		expected.getDocIndex().get(6).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("final", new TermFrecuency(2, new ArrayList<>())));
		expected.getDocIndex().get(7).getStats().getPages().add(1);
		expected.getDocIndex().add(new InvertedIndex("otra", new TermFrecuency(1, new ArrayList<>())));
		expected.getDocIndex().get(8).getStats().getPages().add(1);

		// Para la información del documento.
		Assert.assertEquals(expected.getDoc().getName(), actual.getDoc().getName());
		Assert.assertEquals(expected.getDoc().getPath(), actual.getDoc().getPath());

		// Para la palabra: Fichero.
		Assert.assertEquals(expected.getDocIndex().get(0).getWord(), actual.getDocIndex().get(0).getWord());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getAppearance(),
				actual.getDocIndex().get(0).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(0).getStats().getPages().get(0),
				actual.getDocIndex().get(0).getStats().getPages().get(0));

		// Para la palabra: ejemplo.
		Assert.assertEquals(expected.getDocIndex().get(1).getWord(), actual.getDocIndex().get(1).getWord());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getAppearance(),
				actual.getDocIndex().get(1).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(1).getStats().getPages().get(0),
				actual.getDocIndex().get(1).getStats().getPages().get(0));

		// Para la palabra: para.
		Assert.assertEquals(expected.getDocIndex().get(2).getWord(), actual.getDocIndex().get(2).getWord());
		Assert.assertEquals(expected.getDocIndex().get(2).getStats().getAppearance(),
				actual.getDocIndex().get(2).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(2).getStats().getPages().get(0),
				actual.getDocIndex().get(2).getStats().getPages().get(0));

		// Para la palabra: parsear.
		Assert.assertEquals(expected.getDocIndex().get(3).getWord(), actual.getDocIndex().get(3).getWord());
		Assert.assertEquals(expected.getDocIndex().get(3).getStats().getAppearance(),
				actual.getDocIndex().get(3).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(3).getStats().getPages().get(0),
				actual.getDocIndex().get(3).getStats().getPages().get(0));

		// Para la palabra: proyecto.
		Assert.assertEquals(expected.getDocIndex().get(6).getWord(), actual.getDocIndex().get(6).getWord());
		Assert.assertEquals(expected.getDocIndex().get(6).getStats().getAppearance(),
				actual.getDocIndex().get(6).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(6).getStats().getPages().get(0),
				actual.getDocIndex().get(6).getStats().getPages().get(0));

		// Para la palabra: final.
		Assert.assertEquals(expected.getDocIndex().get(7).getWord(), actual.getDocIndex().get(7).getWord());
		Assert.assertEquals(expected.getDocIndex().get(7).getStats().getAppearance(),
				actual.getDocIndex().get(7).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(7).getStats().getPages().get(0),
				actual.getDocIndex().get(7).getStats().getPages().get(0));

		// Para la palabra: otra.
		Assert.assertEquals(expected.getDocIndex().get(8).getWord(), actual.getDocIndex().get(8).getWord());
		Assert.assertEquals(expected.getDocIndex().get(8).getStats().getAppearance(),
				actual.getDocIndex().get(8).getStats().getAppearance());
		Assert.assertEquals(expected.getDocIndex().get(8).getStats().getPages().get(0),
				actual.getDocIndex().get(8).getStats().getPages().get(0));
	}

}

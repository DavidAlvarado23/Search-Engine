package tests.uneatlantico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.Document;
import entities.uneatlantico.WordWeight;
import indexer.uneatantico.SortByWeight;

public class SortByWeightTest {

	@Test
	public void test() {
		List<WordWeight> wordWeight = new ArrayList<>();
		wordWeight.add(new WordWeight(new Document("Word.docx", "c://Word.docx"), 85.265));
		wordWeight.add(new WordWeight(new Document("File.txt", "c://File.txt"), 0.265));
		wordWeight.add(new WordWeight(new Document("pdf.pdf", "c://pdf.pdf"), 56.265));

		Collections.sort(wordWeight, new SortByWeight());

		Assert.assertEquals("Word.docx", wordWeight.get(0).getDocument().getName());
		Assert.assertEquals("File.txt", wordWeight.get(2).getDocument().getName());
		Assert.assertEquals("pdf.pdf", wordWeight.get(1).getDocument().getName());
	}

}

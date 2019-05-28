package tests.uneatlantico;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.InvertedIndex;
import entities.uneatlantico.TermFrecuency;
import parser.uneatlantico.TextParser;

public class TextParserTest {

	@Test
	public void test() {
		String paragraph = "Florida Georgia Line is a duo comprised \n"
				+ "of members Tyler Hubbard (from Monroe, Georgia) and \n"
				+ "Brian Kelley (from Ormond Beach, Florida). Their \n"
				+ "sound combines electrifying, hard-driving rock with \n" + "honest, expressive lyrics.";

		List<InvertedIndex> actual = TextParser.parseText(paragraph);

		List<InvertedIndex> expected = new ArrayList<>();
		expected.add(new InvertedIndex("Florida", new TermFrecuency(2, new ArrayList<Integer>())));
		expected.add(new InvertedIndex("Georgia", new TermFrecuency(2, new ArrayList<Integer>())));
		expected.add(new InvertedIndex("Line", new TermFrecuency(1, new ArrayList<Integer>())));

		// Para la palabra: Florida.
		Assert.assertEquals(expected.get(0).getWord(), actual.get(0).getWord());
		Assert.assertEquals(expected.get(0).getStats().getAppearance(), actual.get(0).getStats().getAppearance());

		// Para la palabra: Georgia.
		Assert.assertEquals(expected.get(1).getWord(), actual.get(1).getWord());
		Assert.assertEquals(expected.get(1).getStats().getAppearance(), actual.get(1).getStats().getAppearance());

		// Para la palabra: Line.
		Assert.assertEquals(expected.get(2).getWord(), actual.get(2).getWord());
		Assert.assertEquals(expected.get(2).getStats().getAppearance(), actual.get(2).getStats().getAppearance());
	}

}

package indexer.uneatantico;

import java.util.Comparator;

import entities.uneatlantico.WordWeight;

public class SortByWeight implements Comparator<WordWeight> {

	@Override
	public int compare(WordWeight o1, WordWeight o2) {
		return (int) (o2.getIdf() - o1.getIdf());
	}

}
package tests.uneatlantico;

import org.junit.Assert;
import org.junit.Test;

import indexer.uneatantico.DocumentWeight;

public class DocumentWeightTest {

	@Test
	public void test() {
		double actual = DocumentWeight.calculateWeight(85, 0.2563);
		Assert.assertEquals(21.7855, actual, 0.05);
	}

}

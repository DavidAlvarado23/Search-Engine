package tests.uneatlantico;

import org.junit.Assert;
import org.junit.Test;

import factory.uneatlantico.Factory;
import parser.uneatlantico.ExcelParser;
import parser.uneatlantico.FileParser;
import parser.uneatlantico.IParser;
import parser.uneatlantico.PDFParser_;
import parser.uneatlantico.WordParser;
import reader.uneatlantico.DataAccessType;

public class FactoryTest {

	@Test
	public void fileTest() {
		DataAccessType dt = DataAccessType.FILE;
		IParser iParser = Factory.getAccessType(dt);
		Assert.assertEquals(new FileParser().getClass(), iParser.getClass());
	}

	@Test
	public void wordTest() {
		DataAccessType dt = DataAccessType.WORD;
		IParser iParser = Factory.getAccessType(dt);
		Assert.assertEquals(new WordParser().getClass(), iParser.getClass());
	}

	@Test
	public void excelTest() {
		DataAccessType dt = DataAccessType.EXCEL;
		IParser iParser = Factory.getAccessType(dt);
		Assert.assertEquals(new ExcelParser().getClass(), iParser.getClass());
	}

	@Test
	public void pdfTest() {
		DataAccessType dt = DataAccessType.PDF;
		IParser iParser = Factory.getAccessType(dt);
		Assert.assertEquals(new PDFParser_().getClass(), iParser.getClass());
	}

}

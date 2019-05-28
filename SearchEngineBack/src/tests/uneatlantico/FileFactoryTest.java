package tests.uneatlantico;

import org.junit.Assert;
import org.junit.Test;

import factory.uneatlantico.FileFactory;
import reader.uneatlantico.DataAccessType;

public class FileFactoryTest {

	@Test
	public void txtTest() {
		String path = "document.txt";
		DataAccessType dt = FileFactory.getFileExtension(path);
		Assert.assertEquals(DataAccessType.FILE, dt);
	}

	@Test
	public void wordTest() {
		String path = "document.docx";
		DataAccessType dt = FileFactory.getFileExtension(path);
		Assert.assertEquals(DataAccessType.WORD, dt);
	}

	@Test
	public void excelTest() {
		String path = "document.xlsx";
		DataAccessType dt = FileFactory.getFileExtension(path);
		Assert.assertEquals(DataAccessType.EXCEL, dt);
	}

	@Test
	public void pdfTest() {
		String path = "document.pdf";
		DataAccessType dt = FileFactory.getFileExtension(path);
		Assert.assertEquals(DataAccessType.PDF, dt);
	}

}

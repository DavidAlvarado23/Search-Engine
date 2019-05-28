package parser.uneatlantico;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;

public class PDFParser_ implements IParser {

	private static Logger log = Logger.getLogger(PDFParser_.class.getName());

	@Override
	public DocumentIndex parse(String filePath) {
		Document doc = new Document(filePath.split("\\\\")[filePath.split("\\\\").length - 1], filePath);
		List<InvertedIndex> invertedList = new ArrayList<>();

		DocumentIndex docIndex = new DocumentIndex(doc, invertedList);

		File file = new File(filePath);
		try {
			log.info("Reading PDF file from: " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
			PDFParser parser = new PDFParser(randomAccessFile);
			parser.parse();
			COSDocument cosDoc = parser.getDocument();
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDDocument pdDoc = new PDDocument(cosDoc);
			String parsedText = pdfStripper.getText(pdDoc);

			docIndex.setDocIndex(TextParser.parseText(parsedText));
			pdDoc.close();
			cosDoc.close();
			randomAccessFile.close();

		} catch (IOException e) {
			log.error("Failed reading:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return docIndex;
	}

}

package parser.uneatlantico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;

public class WordParser implements IParser {

	private static Logger log = Logger.getLogger(WordParser.class.getName());

	@Override
	public DocumentIndex parse(String filePath) {
		Document doc = new Document(filePath.split("\\\\")[filePath.split("\\\\").length - 1], filePath);
		List<InvertedIndex> invertedList = new ArrayList<>();

		DocumentIndex docIndex = new DocumentIndex(doc, invertedList);

		try {
			log.info("Reading Word file from: " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);
			String fileData = extractor.getText();
			extractor.close();

			docIndex.setDocIndex(TextParser.parseText(fileData));

		} catch (FileNotFoundException e) {
			log.error("Did not found file:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Failed reading:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docIndex;
	}

}

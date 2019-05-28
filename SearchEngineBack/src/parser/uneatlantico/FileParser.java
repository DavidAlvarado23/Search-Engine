package parser.uneatlantico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;

public class FileParser implements IParser {

	private static Logger log = Logger.getLogger(FileParser.class.getName());

	@Override
	public DocumentIndex parse(String filePath) {
		Document doc = new Document(filePath.split("\\\\")[filePath.split("\\\\").length - 1], filePath);
		List<InvertedIndex> invertedList = new ArrayList<>();

		DocumentIndex docIndex = new DocumentIndex(doc, invertedList);

		try {
			log.info("Reading file from: " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			String fileData = "";
			for (String line : lines) {
				fileData += line + "\n";
			}

			docIndex.setDocIndex(TextParser.parseText(fileData));

		} catch (IOException e1) {
			log.error("Failed reading:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			e1.printStackTrace();
		}

		return docIndex;
	}

}

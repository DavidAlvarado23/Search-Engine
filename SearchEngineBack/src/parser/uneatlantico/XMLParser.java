package parser.uneatlantico;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.InvertedIndex;

public class XMLParser implements IParser {

	private static Logger log = Logger.getLogger(XMLParser.class.getName());
	private String fileData = "";

	@Override
	public DocumentIndex parse(String filePath) {
		entities.uneatlantico.Document doc = new entities.uneatlantico.Document(
				filePath.split("\\\\")[filePath.split("\\\\").length - 1], filePath);
		List<InvertedIndex> invertedList = new ArrayList<>();

		DocumentIndex docIndex = new DocumentIndex(doc, invertedList);

		File file = new File(filePath);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			log.info("Reading xml from: " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);

			intoNode(document.getChildNodes());
			docIndex.setDocIndex(TextParser.parseText(this.fileData));

		} catch (SAXException | IOException e) {
			log.error("Failed reading:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			log.error("Failed parser in reading:  " + filePath + " "
					+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
		}
		return docIndex;
	}

	/**
	 * Recorre la lista de nodos que recibe, si es un elemento se vuelve a llamar y
	 * si es texto se concatena con el texto final.
	 * 
	 * @param nodeList
	 *            Objeto de tipo NodeList que contiene nodos del documento.
	 * @return Texto del documento.
	 */
	public String intoNode(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node an = nodeList.item(i);
			if (an.getNodeType() == Node.ELEMENT_NODE) {
				intoNode(an.getChildNodes());
			} else if (an.getNodeType() == Node.TEXT_NODE) {
				this.fileData += an.getNodeValue();
			}
		}
		return this.fileData;
	}

}

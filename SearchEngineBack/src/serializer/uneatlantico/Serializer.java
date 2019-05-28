package serializer.uneatlantico;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Singleton;

import entities.uneatlantico.WordLibrary;

@Singleton
public class Serializer implements ISerializer {

	private String fileName = "c:\\logs\\wordLibrary.json";
	private static Logger log = Logger.getLogger(Serializer.class.getName());

	@Override
	public void serialize(List<WordLibrary> list) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
			new Gson().toJson(list, writer);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}

	@Override
	public List<WordLibrary> deserialize() {
		List<WordLibrary> wordLibrary = null;

		try {
			java.lang.reflect.Type listType = new TypeToken<List<WordLibrary>>() {
			}.getType();
			FileInputStream fIS = new FileInputStream(fileName); 
			InputStreamReader iSR = new InputStreamReader(fIS, "UTF-8");
			wordLibrary = new Gson().fromJson(iSR, listType);
			
			fIS.close();
			iSR.close();

		} catch (FileNotFoundException e) {
			log.error("Serialized file was not found.");
		} catch (JsonIOException e) {
			log.error("Error converting to JSON in serialization.");
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			log.error("Syntax Error converting to JSON in serialization.");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			log.error("Encoding Unsupported in serialization.");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Could not close the document.");
			e.printStackTrace();
		}

		return wordLibrary;
	}

}

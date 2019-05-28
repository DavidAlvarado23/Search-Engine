package reader.uneatlantico;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.apache.log4j.Logger;

@Singleton
public class Reader implements IReader {

	private static Logger log = Logger.getLogger(Reader.class.getName());

	@Override
	public List<String> read(String fullPath) {
		log.info("Reading documents from: " + fullPath);
		List<String> fullPaths = new ArrayList<>();
		File folder = new File(fullPath);
		if (folder.isDirectory()) {
			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {
				if (file.isFile())
					fullPaths.add(file.getPath());
			}
		} else if (folder.isFile())
			fullPaths.add(fullPath);
		return fullPaths;
	}

}

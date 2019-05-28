package tests.uneatlantico;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import entities.uneatlantico.WordLibrary;
import indexer.uneatantico.Indexer;
import reader.uneatlantico.Reader;
import serializer.uneatlantico.Serializer;
import service.uneatlantico.Service;

public class SerializerTest {

	@Test
	public void Serialize() {
		String directoryPath = "C:\\Users\\David23\\Desktop\\Uneatlántico\\Ciclo IV\\Estructura de Datos y Algoritmos II\\Documents";
		new Service(new Reader(), new Indexer(), new Serializer()).Index(directoryPath);
		
		Path path = Paths.get("C:\\logs\\wordLibrary.json");
		Assert.assertTrue(Files.exists(path));
	}
	
	@Test
	public void Deserialize() {
		List<WordLibrary> actual = new Serializer().deserialize();
		Assert.assertEquals(3909, actual.size());
	}
	
}

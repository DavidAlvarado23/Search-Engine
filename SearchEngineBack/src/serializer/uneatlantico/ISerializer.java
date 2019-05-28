package serializer.uneatlantico;

import java.util.List;

import entities.uneatlantico.WordLibrary;

public interface ISerializer {

	/**
	 * 
	 */
	public void serialize(List<WordLibrary> list);
	
	public List<WordLibrary> deserialize();

}

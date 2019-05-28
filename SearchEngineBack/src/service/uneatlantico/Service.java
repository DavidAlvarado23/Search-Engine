package service.uneatlantico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import entities.uneatlantico.Document;
import entities.uneatlantico.DocumentIndex;
import entities.uneatlantico.WordLibrary;
import factory.uneatlantico.Factory;
import factory.uneatlantico.FileFactory;
import indexer.uneatantico.IIndexer;
import indexer.uneatantico.SortByWeight;
import reader.uneatlantico.IReader;
import serializer.uneatlantico.ISerializer;

@Singleton
public class Service implements IService {

	private IReader reader;
	private IIndexer indexer;
	private ISerializer serializer;

	@Inject
	public Service(IReader reader, IIndexer indexer, ISerializer serializer) {
		super();
		this.reader = reader;
		this.indexer = indexer;
		this.serializer = serializer;
	}

	@Override
	public void Index(String directoryPath) {
		List<String> paths = this.reader.read(directoryPath);
		List<DocumentIndex> documents = new ArrayList<>();
		paths.forEach(path -> documents.add(Factory.getAccessType(FileFactory.getFileExtension(path)).parse(path)));
		documents.forEach(doc -> this.indexer.index(doc, documents));
		this.indexer.getWordLibrary().forEach(book -> Collections.sort(book.getWeight(), new SortByWeight()));
		this.serializer.serialize(this.indexer.getWordLibrary());
	}

	@Override
	public List<Document> Search(String wordToSearch) {
		if (this.indexer.getWordLibrary() != null) {
			Object[] wordFound = this.indexer.getWordLibrary().stream()
					.filter(book -> book.getWord().equals(wordToSearch)).toArray();
			List<Document> documents = new ArrayList<>();
			if (wordFound.length > 0)
				((WordLibrary) wordFound[0]).getWeight().forEach(weight -> documents.add(weight.getDocument()));
			return (documents.size() > 0) ? documents : null;
		}
		return null;
	}

	public IReader getReader() {
		return this.reader;
	}

	public void setReader(IReader reader) {
		this.reader = reader;
	}

	public IIndexer getIndexer() {
		return indexer;
	}

	public void setIndexer(IIndexer indexer) {
		this.indexer = indexer;
	}

	public ISerializer getSerializer() {
		return serializer;
	}

	public void setSerializer(ISerializer serializer) {
		this.serializer = serializer;
	}

}

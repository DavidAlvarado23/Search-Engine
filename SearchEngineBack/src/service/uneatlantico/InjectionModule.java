package service.uneatlantico;

import com.google.inject.AbstractModule;

import indexer.uneatantico.IIndexer;
import indexer.uneatantico.Indexer;
import reader.uneatlantico.IReader;
import reader.uneatlantico.Reader;
import serializer.uneatlantico.ISerializer;
import serializer.uneatlantico.Serializer;

public class InjectionModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(IReader.class).to(Reader.class);
		bind(IIndexer.class).to(Indexer.class);
		bind(ISerializer.class).to(Serializer.class);
		
		bind(IService.class).to(Service.class);
	}

}

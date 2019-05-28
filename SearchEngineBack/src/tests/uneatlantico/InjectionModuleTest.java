package tests.uneatlantico;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import indexer.uneatantico.IIndexer;
import indexer.uneatantico.Indexer;

import reader.uneatlantico.IReader;
import reader.uneatlantico.Reader;
import serializer.uneatlantico.ISerializer;
import serializer.uneatlantico.Serializer;
import service.uneatlantico.InjectionModule;
import service.uneatlantico.Service;

public class InjectionModuleTest {

	@Test
	public void test() {
		Injector injector = Guice.createInjector(new InjectionModule());
		IReader iReader = injector.getInstance(Reader.class);
		IIndexer iIndexer = injector.getInstance(Indexer.class);
		ISerializer iSerializer = injector.getInstance(Serializer.class);
		
		Service service = new Service(iReader, iIndexer, iSerializer);
		
		Assert.assertEquals(new Reader().getClass(), service.getReader().getClass());
		Assert.assertEquals(new Indexer().getClass(), service.getIndexer().getClass());
		Assert.assertEquals(new Serializer().getClass(), service.getSerializer().getClass());
	}

}

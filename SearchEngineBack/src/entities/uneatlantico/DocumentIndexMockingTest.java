package entities.uneatlantico;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;

public class DocumentIndexMockingTest {

	@Mock
	private DocumentIndex docMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addDocuments_returnsNewDocument() {
		when(docMock.getDoc()).thenReturn(new Document("Nombre", "Ruta"));

		Document document = new Document("Name", "Path");
		DocumentIndex documentIndex = new DocumentIndex(document, new ArrayList<>());

		Assert.assertEquals(docMock.getDoc().getName(), "Nombre");
		Assert.assertTrue(!documentIndex.getDoc().getName().isEmpty());
	}

}

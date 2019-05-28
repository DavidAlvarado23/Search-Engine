package entities.uneatlantico;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.junit.Test;

public class IDFMockito {

	@Test
	public void test() {
		TermFrecuency test = mock(TermFrecuency.class);
        when(test.getAppearance()).thenReturn(23);
        assertEquals(test.getAppearance(), 23);
	}

}

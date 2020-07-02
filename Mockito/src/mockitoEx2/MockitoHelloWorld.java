package mockitoEx2;

import static mockitoEx2.Foo.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;



public class MockitoHelloWorld {
	private Foo foo;
    
    @Before
    public void setupMock() {       
        foo = mock(Foo.class);
        when(foo.greet()).thenReturn(HELLO_WORLD);
    }
     
    @Test
    public void fooGreets() {
        System.out.println("Foo greets: " + foo.greet());
        assertEquals(HELLO_WORLD, foo.greet());
    }
     
    @Test
    public void barGreets() {
        Bar bar = new Bar();
        assertEquals(HELLO_WORLD, bar.greet(foo));
    }
}
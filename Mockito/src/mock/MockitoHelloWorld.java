package mock;

import static mock.Foo.HELLO_WORLD;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;



public class MockitoHelloWorld {
    @Test
    public void fooGreets() {
        Foo foo = mock(Foo.class);
        when(foo.greet()).thenReturn(HELLO_WORLD);
        System.out.println("Foo greets: " + foo.greet());
        assertEquals(foo.greet(), HELLO_WORLD);
    }
}
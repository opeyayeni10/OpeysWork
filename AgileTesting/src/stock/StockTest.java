package stock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;

class StockTest {
private StockBroker broker;
private Stock stock;
private StockListener listener; 
	@Before
	public void setUp() {
		broker = mock(StockBroker.class);
		stock = new Stock("A123", 100.0);
	}

	@Test
	public void sellStockWhenPriceGoesUp() {
	when(broker.getQoute(stock)).thenReturn(150.0);
	listener.takeAction(stock);
	verify(broker).sell(stock, 10);
	}
	@Test
	public void buyStockWhenPriceGoesUp() {
		when(broker.getQoute(stock)).thenReturn(90.0);
		listener.takeAction(stock);
		verify(broker).buy(stock, 10);
	}

}

package stock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;


public class StockListenerTest {
	StockListener listener;
	Stock stock;
	private StockBroker stockBroker=mock(StockBroker.class);

	@Before
	public void setup() {
		listener = new StockListener(stockBroker);
		stock = new Stock("FDI", 100.0);
	}
	@Test
	public void sellStocksWhenPriceGoesUp(){
		
		when(stockBroker.getQoute(stock)).thenReturn(150.00);
		listener.takeAction(stock);
		verify(stockBroker).sell(stock, 10);
	}
	@Test
	public void buyStocksWhenPriceGoesDown() {
		stock = new Stock("FDI", 100.0);
		when(stockBroker.getQoute(stock)).thenReturn(90.00);
		listener.takeAction(stock);
		verify(stockBroker).buy(stock, 100);
	}

}

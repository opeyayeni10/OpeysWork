package retailers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.isA;

import java.util.ArrayList;

import static org.mockito.Mockito.doThrow;
class DiscountTest {
	private Inventory inventory;
	private PublicAddressSystem pas;
	private BiggestBazarRetail bbr;
	private ArrayList<Item> expiredList;
	private Offer offer;
	@Before
	void setUp() {
		inventory =  mock(Inventory.class);
		pas = mock(PublicAddressSystem.class);
		bbr = new BiggestBazarRetail(inventory, pas);
		expiredList = new ArrayList<Item>();
		
	}

	@Test
	void testDiscountIsIssuedOnOneItem() {
		Item tv = new Item("A123", "Samsung", 300.0, 200.0);
		expiredList.add(tv);
		when(inventory.getItemsExpireInAMonth()).thenReturn(expiredList);
		int numItems = bbr.issueDiscountForItemsExpireIn30Days(0.1);
		verify((inventory), new Times(1)).update(tv, 270);
		verify(pas).announce(isA(Offer.class));
		assertEquals(1, numItems);
	}

}

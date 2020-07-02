package com.ericsson.NetworkManager.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.NetworkManager.data.UserEquipmentDAO;
import com.ericsson.NetworkManager.model.UserEquipment;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class UserEquipmentDAOTest {

	@EJB
	private UserEquipmentDAO userEquipmentDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UtilsDAO.class, UserEquipmentDAO.class, UserEquipment.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteUserEquipmentTable();
	}

	@Test
	public void testCreateUserEquipmentTableIntoDB() {
		UserEquipment userEquipment1 = new UserEquipment();
		userEquipment1.setUserEquipmentID(100100);
		userEquipment1.setMarketingName("G410");
		userEquipment1.setManufacturer("Mitsubishi");
		userEquipment1.setAccessCapability("GSM 1800, GSM 900");
		userEquipment1.setModel("G410");
		userEquipment1.setVendorName("Mitsubishi");
		userEquipment1.setUserEquipmanetType("Handheld");
		userEquipment1.setOperatingSystem("Blackberry");
		userEquipment1.setInputMode("querty");
		userEquipmentDAO.create(userEquipment1);
		UserEquipment userEquipment2 = new UserEquipment();
		userEquipment2.setUserEquipmentID(100200);
		userEquipment2.setMarketingName("G420");
		userEquipment2.setManufacturer("Mitsubishi");
		userEquipment2.setAccessCapability("GSM 1800, GSM 900");
		userEquipment2.setModel("G410");
		userEquipment2.setVendorName("Mitsubishi");
		userEquipment2.setUserEquipmanetType("Handheld");
		userEquipment2.setOperatingSystem("Blackberry");
		userEquipment2.setInputMode("querty");
		userEquipmentDAO.create(userEquipment2);
		List<UserEquipment> userEquipmentList = userEquipmentDAO.getAllUserEquipment();
		assertEquals("Data fetch = data persisted", userEquipmentList.size(), 2);
		UserEquipment userEquipment;
		userEquipment = userEquipmentList.get(0);
		assertEquals(100100, userEquipment.getUserEquipmentID());
		assertEquals("G410", userEquipment.getMarketingName());
		assertEquals("Mitsubishi", userEquipment.getManufacturer());
		assertEquals("GSM 1800, GSM 900", userEquipment.getAccessCapability());
		assertEquals("G410", userEquipment.getModel());
		assertEquals("Mitsubishi", userEquipment.getVendorName());
		assertEquals("Handheld", userEquipment.getUserEquipmanetType());
		assertEquals("Blackberry", userEquipment.getOperatingSystem());
		assertEquals("querty", userEquipment.getInputMode());
		userEquipment = userEquipmentList.get(1);
		assertEquals(100200, userEquipment.getUserEquipmentID());
		assertEquals("G420", userEquipment.getMarketingName());
		assertEquals("Mitsubishi", userEquipment.getManufacturer());
		assertEquals("GSM 1800, GSM 900", userEquipment.getAccessCapability());
		assertEquals("G410", userEquipment.getModel());
		assertEquals("Mitsubishi", userEquipment.getVendorName());
		assertEquals("Handheld", userEquipment.getUserEquipmanetType());
		assertEquals("Blackberry", userEquipment.getOperatingSystem());
		assertEquals("querty", userEquipment.getInputMode());
	}
}
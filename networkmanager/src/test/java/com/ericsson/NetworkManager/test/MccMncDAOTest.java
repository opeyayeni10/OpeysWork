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

import com.ericsson.NetworkManager.data.MccMncDAO;
import com.ericsson.NetworkManager.model.MccMnc;
import com.ericsson.NetworkManager.model.MccMncPK;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class MccMncDAOTest {

	@EJB
	private MccMncDAO mccMncDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UtilsDAO.class, MccMncDAO.class, MccMnc.class, MccMncPK.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteMccMncTable();
	}

	@Test
	public void testCreateMccMncIntoDB() {
		MccMnc mccMnc1 = new MccMnc();
		MccMncPK mccMncPK1 = new MccMncPK();
		mccMncPK1.setMcc(238);
		mccMncPK1.setMnc(1);
		mccMnc1.setId(mccMncPK1);
		mccMnc1.setCountry("Denmark");
		mccMnc1.setOperator("TDC-DK");
		mccMncDAO.create(mccMnc1);
		MccMnc mccMnc2 = new MccMnc();
		MccMncPK mccMncPK2 = new MccMncPK();
		mccMncPK2.setMcc(248);
		mccMncPK2.setMnc(2);
		mccMnc2.setId(mccMncPK2);
		mccMnc2.setCountry("Portugal");
		mccMnc2.setOperator("TDC-PT");
		mccMncDAO.create(mccMnc2);
		List<MccMnc> mccMncList = mccMncDAO.getAllMccMnc();
		assertEquals("Data fetch = data persisted", mccMncList.size(), 2);
		MccMnc mccMnc;
		MccMncPK mccMncPK;
		mccMnc = mccMncList.get(0);
		mccMncPK = mccMnc.getId();
		assertEquals(238, mccMncPK.getMcc());
		assertEquals(1, mccMncPK.getMnc());
		assertEquals("Denmark", mccMnc.getCountry());
		assertEquals("TDC-DK", mccMnc.getOperator());
		mccMnc = mccMncList.get(1);
		mccMncPK = mccMnc.getId();
		assertEquals(248, mccMncPK.getMcc());
		assertEquals(2, mccMncPK.getMnc());
		assertEquals("Portugal", mccMnc.getCountry());
		assertEquals("TDC-PT", mccMnc.getOperator());
	}
}
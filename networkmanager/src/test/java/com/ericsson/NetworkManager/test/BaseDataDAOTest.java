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

import com.ericsson.NetworkManager.data.BaseDataDAO;
import com.ericsson.NetworkManager.model.BaseData;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class BaseDataDAOTest {

	@EJB
	private BaseDataDAO baseDataDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UtilsDAO.class, BaseDataDAO.class, BaseData.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteBaseDataTable();
	}

	@Test
	public void testCreateBaseDataIntoDB() {
		BaseData baseData1 = new BaseData();
		baseData1.setEventId("4098");
		baseData1.setFailureClass("1");
		baseData1.setUEType(21060800);
		baseData1.setMarket(344);
		baseData1.setOperator(930);
		baseData1.setCellId(4);
		baseData1.setDuration(1000);
		baseData1.setCauseCode("0");
		baseData1.setNEVersion("11B");
		baseData1.setImsi("344930000000011");
		baseData1.setHier3Id("4809532081614990000");
		baseData1.setHier32Id("8226896360947470000");
		baseData1.setHier321Id("1150444940909480000");
		baseDataDAO.create(baseData1);
		BaseData baseData2 = new BaseData();
		baseData2.setEventId("4093");
		baseData2.setFailureClass("2");
		baseData2.setUEType(21060810);
		baseData2.setMarket(342);
		baseData2.setOperator(931);
		baseData2.setCellId(5);
		baseData2.setDuration(2000);
		baseData2.setCauseCode("0");
		baseData2.setNEVersion("12B");
		baseData2.setImsi("344930100000011");
		baseData2.setHier3Id("4801532081614990000");
		baseData2.setHier32Id("8216896360947470000");
		baseData2.setHier321Id("1150444940909480000");
		baseDataDAO.create(baseData2);

		List<BaseData> baseDataList = baseDataDAO.getAllBaseData();
		assertEquals("Data fetch = data persisted", baseDataList.size(), 2);
		BaseData baseData;
		baseData = baseDataList.get(0);
		assertEquals("4098", baseData.getEventId());
		assertEquals("0", baseData.getCauseCode());
		assertEquals("11B", baseData.getNEVersion());
		assertEquals("344930000000011", baseData.getImsi());
		assertEquals("4809532081614990000", baseData.getHier3Id());
		assertEquals("8226896360947470000", baseData.getHier32Id());
		assertEquals("1150444940909480000", baseData.getHier321Id());
		baseData = baseDataList.get(1);
		assertEquals("4093", baseData.getEventId());
		assertEquals("0", baseData.getCauseCode());
		assertEquals("12B", baseData.getNEVersion());
		assertEquals("344930100000011", baseData.getImsi());
		assertEquals("4801532081614990000", baseData.getHier3Id());
		assertEquals("8216896360947470000", baseData.getHier32Id());
		assertEquals("1150444940909480000", baseData.getHier321Id());
	}

}
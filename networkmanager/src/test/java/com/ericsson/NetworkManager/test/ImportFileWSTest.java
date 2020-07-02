/*
package com.ericsson.NetworkManager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.ericsson.NetworkManager.data.EventCauseDAO;
import com.ericsson.NetworkManager.data.FailureClassDAO;
import com.ericsson.NetworkManager.data.InvalidEventDAO;
import com.ericsson.NetworkManager.data.MccMncDAO;
import com.ericsson.NetworkManager.data.UserEquipmentDAO;
import com.ericsson.NetworkManager.model.EventCause;
import com.ericsson.NetworkManager.model.InvalidEvent;
import com.ericsson.NetworkManager.rest.ImportFileWS;
import com.ericsson.NetworkManager.rest.JaxRsActivator;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class ImportFileWSTest {

	private ImportFileWS importFile;
	
	@EJB
	private InvalidEvent invalidEvent;

	@EJB
	private InvalidEventDAO invalidEventDAO;
	
	@EJB
	private ImportFileWS importFileWS;

	@EJB
	private UserEquipmentDAO userEquipmentDAO;

	@EJB
	private FailureClassDAO failureClassDAO;

	@EJB
	private MccMncDAO mccMncDAO;

	@EJB
	private EventCauseDAO eventCauseDAO;

	@EJB
	private BaseDataDAO baseDataDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(InvalidEventDAO.class, InvalidEvent.class,ImportFileWS.class, EncryptedDocumentException.class,  org.apache.poi.openxml4j.exceptions.InvalidFormatException.class,
						IOException.class, JaxRsActivator.class, UtilsDAO.class, WorkbookFactory.class,
						HSSFWorkbook.class, Sheet.class)
				.addPackage(UserEquipmentDAO.class.getPackage()).addPackage(EventCause.class.getPackage())
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteAllDataTables();
		
		Date d = new Date();
		utilsDAO.deleteInvalidEventTable();
		InvalidEvent invalidEvent = new InvalidEvent();
		invalidEvent.setCause_code("1");
		invalidEvent.setCell_id(4);
		invalidEvent.setDate_time(d);
		invalidEvent.setDuration(1000);
		invalidEvent.setEvent_id("4099");
		invalidEvent.setFailure_class("null");
		invalidEvent.setHier321_id("4809532081614990000");
		invalidEvent.setHier321_id("8226896360947470000");
		invalidEvent.setHier3_id("1150444940909480000");
		invalidEventDAO.save(invalidEvent);
		importFile = new ImportFileWS();
	}

	@Test
	public void testimportExcelToDatabase() throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		when(WorkbookFactory.create(any(File.class))).thenReturn(workbook);
		Sheet sheetBaseData = workbook.createSheet("BaseData");
		Sheet sheetEventCause = workbook.createSheet("EventCause");
		Sheet sheetFailureClass = workbook.createSheet("FailureClass");
		Sheet sheetUserEquipment = workbook.createSheet("UserEquipment");
		Sheet sheetMccMnc = workbook.createSheet("MccMnc");

		sheetFailureClass.createRow(0).createCell(0).setCellValue("Failure Class");
		sheetFailureClass.createRow(0).createCell(1).setCellValue("Description");
		sheetFailureClass.createRow(1).createCell(0).setCellValue("0");
		sheetFailureClass.createRow(1).createCell(1).setCellValue("EMERGENCY");
		sheetFailureClass.createRow(2).createCell(0).setCellValue("1");
		sheetFailureClass.createRow(2).createCell(1).setCellValue("HIGH PRIORITY ACCESS");

		Response response = importFileWS.importExcelToDatabase();
		assertEquals(HttpStatus.SC_OK, response.getStatus());

	}
	
	@Test
	public void testCauseCodeForNull() {
		// importFile.createBaseData();

	}

	@Test
	public void testComparingEventId() {
		assertFalse(importFile.checkEventID("4099"));
	}
}
*/
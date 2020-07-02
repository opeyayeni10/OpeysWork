package com.ericsson.NetworkManager.rest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.ericsson.NetworkManager.data.BaseDataDAO;
import com.ericsson.NetworkManager.data.EventCauseDAO;
import com.ericsson.NetworkManager.data.FailureClassDAO;
import com.ericsson.NetworkManager.data.InvalidEventDAO;
import com.ericsson.NetworkManager.data.MccMncDAO;
import com.ericsson.NetworkManager.data.UserEquipmentDAO;
import com.ericsson.NetworkManager.model.BaseData;
import com.ericsson.NetworkManager.model.EventCause;
import com.ericsson.NetworkManager.model.EventCausePK;
import com.ericsson.NetworkManager.model.FailureClass;
import com.ericsson.NetworkManager.model.InvalidEvent;
import com.ericsson.NetworkManager.model.MccMnc;
import com.ericsson.NetworkManager.model.MccMncPK;
import com.ericsson.NetworkManager.model.UserEquipment;

@Path("/import")
@Stateless
@LocalBean
public class ImportFileWS {

	// ******PLEASE CHOOSE YOUR FILE PATH TO TH SAMPLEDATASET DOC
	// Andreia
	public static final String FILE_PATH = "C:\\Users\\trigu\\SampleDataset.xls";

	// Sean
	// public static final String FILE_PATH =
	// "C:\\Users\\A00258299\\Desktop\\SampleData.xls";

	// Amulya
	// public static final String FILE_PATH =
	// "C:\\Users\\a00258346\\SampleDataset.xls";

	// Sorcha's path
	// public static final String FILE_PATH = "E:\\1.Team
	// Project\\SampleDataset.xls";

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
	private InvalidEventDAO invalidEventDAO;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response importExcelToDatabase() {
		fileProcessor();
		return Response.status(200).build();
	}

	private void fileProcessor() {
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(new File(FILE_PATH));
			createBaseData(workbook.getSheetAt(0));
			createEventCause(workbook.getSheetAt(1));
			createFailureClass(workbook.getSheetAt(2));
			createUserEquipment(workbook.getSheetAt(3));
			createMccMnc(workbook.getSheetAt(4));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void createEventCause(final Sheet sheet) {
		for (final Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			final DataFormatter formatter = new DataFormatter();
			final EventCause eventCause = new EventCause();
			if (formatter.formatCellValue(row.getCell(0)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(1)).matches("[0-9]+")) {
				eventCause.setId(new EventCausePK((formatter.formatCellValue(row.getCell(0))),
						(int) row.getCell(1).getNumericCellValue()));
				eventCause.setDescription(formatter.formatCellValue(row.getCell(2)));
				eventCauseDAO.create(eventCause);
			}
		}
		System.out.println("MccMnc table created successfuly");
	}

	private void createFailureClass(final Sheet sheet) {
		for (final Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			final DataFormatter formatter = new DataFormatter();
			final FailureClass failureClass = new FailureClass();
			failureClass.setFailureClass(formatter.formatCellValue(row.getCell(0)));
			failureClass.setDescription(formatter.formatCellValue(row.getCell(1)));
			failureClassDAO.create(failureClass);
		}
		System.out.println("FailureClass table created successfuly");
	}

	private void createUserEquipment(final Sheet sheet) {
		for (final Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			final DataFormatter formatter = new DataFormatter();
			final UserEquipment userEquipment = new UserEquipment();
			if (formatter.formatCellValue(row.getCell(0)).matches("[0-9]+")) {

				userEquipment.setUserEquipmentID((int) row.getCell(0).getNumericCellValue());
				userEquipment.setMarketingName(formatter.formatCellValue(row.getCell(1)));
				userEquipment.setManufacturer(formatter.formatCellValue(row.getCell(2)));
				userEquipment.setAccessCapability(formatter.formatCellValue(row.getCell(3)));
				userEquipment.setModel(formatter.formatCellValue(row.getCell(4)));
				userEquipment.setVendorName(formatter.formatCellValue(row.getCell(5)));
				userEquipment.setUserEquipmanetType(formatter.formatCellValue(row.getCell(6)));
				userEquipment.setOperatingSystem(formatter.formatCellValue(row.getCell(7)));
				userEquipment.setInputMode(formatter.formatCellValue(row.getCell(8)));
				userEquipmentDAO.create(userEquipment);
			}
		}
		System.out.println("UserEquipment table created successfuly");
	}

	private void createMccMnc(final Sheet sheet) {
		for (final Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			final DataFormatter formatter = new DataFormatter();
			final MccMnc mccMnc = new MccMnc();
			if (formatter.formatCellValue(row.getCell(0)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(1)).matches("[0-9]+")) {
				mccMnc.setId(new MccMncPK(((int) row.getCell(0).getNumericCellValue()),
						(int) row.getCell(1).getNumericCellValue()));
				mccMnc.setCountry(formatter.formatCellValue(row.getCell(2)));
				mccMnc.setOperator(formatter.formatCellValue(row.getCell(3)));
				mccMncDAO.create(mccMnc);
			}
		}
		// System.out.println("MccMnc table created
		// successfuly");^([A-Z][a-z]*)+(?:[\\\\s-][A-Z][a-z]*)*$
	}

	private void createBaseData(final Sheet sheet) {
		for (final Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue;
			}
			final DataFormatter formatter = new DataFormatter();
			final BaseData baseData = new BaseData();
			if (formatter.formatCellValue(row.getCell(1)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(2)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(3)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(4)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(5)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(6)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(7)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(8)).matches("[0-9]+")
					&& formatter.formatCellValue(row.getCell(9)).matches("[0-9][0-9][A-Z]")
					&& formatter.formatCellValue(row.getCell(8)).matches("[0-9]+")) {
				baseData.setDateTime(((Date) row.getCell(0).getDateCellValue()));
				baseData.setEventId(formatter.formatCellValue(row.getCell(1)));
				baseData.setFailureClass(formatter.formatCellValue(row.getCell(2)));
				baseData.setUEType(((int) row.getCell(3).getNumericCellValue()));
				baseData.setMarket(((int) row.getCell(4).getNumericCellValue()));
				baseData.setOperator(((int) row.getCell(5).getNumericCellValue()));
				baseData.setCellId(((int) row.getCell(6).getNumericCellValue()));
				baseData.setDuration(((int) row.getCell(7).getNumericCellValue()));
				baseData.setCauseCode(formatter.formatCellValue(row.getCell(8)));
				baseData.setNEVersion(formatter.formatCellValue(row.getCell(9)));
				baseData.setImsi(NumberToTextConverter.toText(row.getCell(10).getNumericCellValue()));
				baseData.setHier3Id(NumberToTextConverter.toText(row.getCell(11).getNumericCellValue()));
				baseData.setHier32Id(NumberToTextConverter.toText(row.getCell(12).getNumericCellValue()));
				baseData.setHier321Id(NumberToTextConverter.toText(row.getCell(13).getNumericCellValue()));
				if (checkEventID(baseData.getEventId())) {
					baseDataDAO.create(baseData);
				} else {
					if (!checkEventID(baseData.getEventId())) {
						baseData.setEventId("Unknown Event ID");
					}
					createNullEvent(baseData.getDateTime(), baseData.getEventId(), baseData.getFailureClass(),
							baseData.getUEType(), baseData.getMarket(), baseData.getOperator(), baseData.getCellId(),
							baseData.getDuration(), baseData.getCauseCode(), baseData.getNEVersion(),
							baseData.getImsi(), baseData.getHier3Id(), baseData.getHier32Id(), baseData.getHier321Id());
				}
			} else {
				String EventID = (formatter.formatCellValue(row.getCell(1)));
				if (!checkEventID(formatter.formatCellValue(row.getCell(1)))) {
					EventID = "Unkown Event ID";
				}
				createNullEvent((Date) row.getCell(0).getDateCellValue(), EventID,
						formatter.formatCellValue(row.getCell(2)), (int) row.getCell(3).getNumericCellValue(),
						(int) row.getCell(4).getNumericCellValue(), (int) row.getCell(5).getNumericCellValue(),
						(int) row.getCell(6).getNumericCellValue(), (int) row.getCell(7).getNumericCellValue(),
						formatter.formatCellValue(row.getCell(8)), formatter.formatCellValue(row.getCell(9)),
						NumberToTextConverter.toText(row.getCell(10).getNumericCellValue()),
						NumberToTextConverter.toText(row.getCell(11).getNumericCellValue()),
						NumberToTextConverter.toText(row.getCell(12).getNumericCellValue()),
						NumberToTextConverter.toText(row.getCell(13).getNumericCellValue()));
			}
		}
	}

	public boolean checkEventID(final String EventID) {
		try {
			final DataFormatter formatter = new DataFormatter();
			final Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));
			final Sheet sheet = workbook.getSheetAt(1);
			for (final Row r : sheet) {
				if (r.getRowNum() == 0) {
					continue;
				}
				if (EventID.equals(formatter.formatCellValue(r.getCell(1)))) {
					return true;
				}
				continue;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void createNullEvent(final Date dateTime, final String EventID, final String failureClass, final int UEType,final  int market,
			final int operator,final  int cellID,final  int duration, final String causeCode, final String NEVersion, final String IMSI, final String heir3Id,
			final String hier32Id,final  String hier321Id) {
		final InvalidEvent invalidEvent = new InvalidEvent();
		invalidEvent.setDate_time(dateTime);
		invalidEvent.setEvent_id(EventID);
		invalidEvent.setFailure_class(failureClass);
		invalidEvent.setUe_type(UEType);
		invalidEvent.setMarket(market);
		invalidEvent.setOperator(operator);
		invalidEvent.setCell_id(cellID);
		invalidEvent.setDuration(duration);
		invalidEvent.setCause_code(causeCode);
		invalidEvent.setNe_version(NEVersion);
		invalidEvent.setImsi(IMSI);
		invalidEvent.setHier3_id(heir3Id);
		invalidEvent.setHier32_id(hier32Id);
		invalidEvent.setHier321_id(hier321Id);
		invalidEventDAO.save(invalidEvent);
		System.out.println("InvalidEvent table created successfuly");
	}
}
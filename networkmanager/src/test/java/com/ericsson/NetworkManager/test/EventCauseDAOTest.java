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

import com.ericsson.NetworkManager.data.EventCauseDAO;
import com.ericsson.NetworkManager.model.EventCause;
import com.ericsson.NetworkManager.model.EventCausePK;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class EventCauseDAOTest {

	@EJB
	private EventCauseDAO eventCauseDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UtilsDAO.class, EventCauseDAO.class, EventCause.class, EventCausePK.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteEventCauseTable();
	}

	@Test
	public void testCreateEventCauseIntoDB() {
		EventCause eventCause1 = new EventCause();
		EventCausePK eventCausePK1 = new EventCausePK();
		eventCausePK1.setCauseCode("0");
		eventCausePK1.setEventID(4097);
		eventCause1.setId(eventCausePK1);
		eventCause1.setDescription("RRC");
		eventCauseDAO.create(eventCause1);
		EventCause eventCause2 = new EventCause();
		EventCausePK eventCausePK2 = new EventCausePK();
		eventCausePK2.setCauseCode("1");
		eventCausePK2.setEventID(4098);
		eventCause2.setId(eventCausePK2);
		eventCause2.setDescription("RRC");
		eventCauseDAO.create(eventCause2);
		List<EventCause> eventCauseList = eventCauseDAO.getAllEventCause();
		assertEquals("Data fetch = data persisted", eventCauseList.size(), 2);
		EventCausePK eventCausePK;
		EventCause eventCause;
		eventCause = eventCauseList.get(0);
		eventCausePK = eventCause.getId();
		assertEquals("0", eventCausePK.getCauseCode());
		eventCause = eventCauseList.get(1);
	}

}
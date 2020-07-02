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

import com.ericsson.NetworkManager.data.FailureClassDAO;
import com.ericsson.NetworkManager.model.FailureClass;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class FailureClassDAOTest {

	@EJB
	private FailureClassDAO failureClassDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UtilsDAO.class, FailureClassDAO.class, FailureClass.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() {
		utilsDAO.deleteFailureClassTable();
	}

	@Test
	public void testCreateFailureClassIntoDB() {
		FailureClass failureClass1 = new FailureClass();
		failureClass1.setFailureClass("0");
		failureClass1.setDescription("Emergency");
		failureClassDAO.create(failureClass1);
		FailureClass failureClass2 = new FailureClass();
		failureClass2.setFailureClass("1");
		failureClass2.setDescription("Urgent");
		failureClassDAO.create(failureClass2);
		List<FailureClass> failureClassList = failureClassDAO.getAllFailureClass();
		assertEquals("Data fetch = data persisted", failureClassList.size(), 2);
		FailureClass failureClass;
		failureClass = failureClassList.get(0);
		assertEquals("0", failureClass.getFailureClass());
		assertEquals("Emergency", failureClass.getDescription());
		failureClass = failureClassList.get(1);
		assertEquals("1", failureClass.getFailureClass());
		assertEquals("Urgent", failureClass.getDescription());
	}
}
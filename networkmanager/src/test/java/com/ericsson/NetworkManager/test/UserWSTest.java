package com.ericsson.NetworkManager.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.NetworkManager.data.UserDAO;
import com.ericsson.NetworkManager.model.User;
import com.ericsson.NetworkManager.rest.JaxRsActivator;
import com.ericsson.NetworkManager.rest.UserWS;
import com.ericsson.NetworkManager.test.utils.UtilsDAO;

@RunWith(Arquillian.class)
public class UserWSTest {

	@EJB
	private UserWS userWs;

	@EJB
	private UserDAO userDAO;

	@EJB
	private UtilsDAO utilsDAO;

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "Test.jar")
				.addClasses(UserDAO.class, User.class, JaxRsActivator.class, UserWS.class, UtilsDAO.class)
				// .addPackage(EventCause.class.getPackage())
				// .addPackage(EventCauseDAO.class.getPackage())
				// this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@Before
	public void setUp() {
		// this function means that we start with an empty table
		// And add one wine
		// it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteUserTable();
		User user = new User();
		user.setName("Sorcha");
		user.setPassword("1234");
		user.setType(1);
		userDAO.save(user);
		user = new User();
		user.setName("Opey");
		user.setPassword("1234");
		user.setType(2);
		userDAO.save(user);
	}

	@Test
	public void testGetAllUserWS() {
		Response response = userWs.findAllUsers();
		List<User> userList = (List<User>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", userList.size(), 2);
		User user = userList.get(0);
		assertEquals("Sorcha", user.getName());
		user = userList.get(1);
		assertEquals("Opey", user.getName());
	}
}

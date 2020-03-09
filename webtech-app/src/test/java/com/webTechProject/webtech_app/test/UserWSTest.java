package com.webTechProject.webtech_app.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.webTechProject.webtech_app.data.UserDAO;
import com.webTechProject.webtech_app.model.Credentials;
import com.webTechProject.webtech_app.model.Users;
import com.webTechProject.webtech_app.rest.UserWS;



import com.webTechProject.webtech_app.rest.JaxRsActivator;

@RunWith(Arquillian.class)
public class UserWSTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "Test.jar")
				.addClasses( UserDAO.class, Users.class, JaxRsActivator.class, 
					UserWS.class, UtilsDAO.class, Credentials.class).addAsManifestResource("META-INF/persistence.xml", "persistense.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,ArchivePaths.create("beans.xml"));
		
	}
	
	@EJB
	private UserWS UserWS;
	
	@EJB
	private UserDAO UserDAO;
	
	@EJB
	private UtilsDAO utilsDAO;
	
	@Before
	public void setUp() {
		utilsDAO.deleteTable();
		Users user = new Users();
		user.setFirstName("Opey");
		user.setLastName("Ayeni");
		user.setUserName("opeyayeni10");
		user.setPassword("12345");
		user.setUserType("Admin");
		user.setPicture("image");
		UserDAO.save(user);
		
		Users user1 = new Users();
		user1.setFirstName("Paul");
		user1.setLastName("Wall");
		user1.setUserName("paulwall10");
		user1.setPassword("12345");
		user1.setUserType("Customer");
		user1.setPicture("image1");
		UserDAO.save(user1);
	}
	
	@Test
	public void testGetAllUsers() {
		Response response = UserWS.findAll();
		List<Users> usersList =(List<Users>) response.getEntity();
		assertEquals(HttpStatus.SC_OK, response.getStatus());
		assertEquals("Data fetch = data persisted", usersList.size(), 2);
		Users user = usersList.get(0);
		assertEquals("opeyayeni10", user.getUserName());
		user = usersList.get(1);
		assertEquals("paulwall10", user.getUserName());
	}
	
	@Test
	public void testGetUserById() {
		Response response = UserWS.findUserById(1);
		Users user = (Users) response.getEntity();
		assertEquals(user.getUserId(), 1);
		assertEquals(user.getUserName(), "paulwall10");
		
	}
	
	@Test
	public void testAddUser() {
	Users User = new Users();
	User.setFirstName("Dolla");
	User.setLastName("Fred");
	User.setUserName("DFred");
	User.setPassword("1234");
	User.setUserType("Admin");
	User.setPicture("image");
	Response response = UserWS.saveUser(User);
	assertEquals(HttpStatus.SC_CREATED, response.getStatus());
	User = (Users) response.getEntity();
	assertEquals(User.getUserId(), 3);
	assertEquals(User.getUserName(), "DFred");
	}

	@Test
	public void testRemoveUser() {
	Response response = UserWS.findAll();
	List<Users> UserList = (List<Users>) response.getEntity();
	assertEquals(UserList.size(), 2);
	UserWS.deleteUser(2);
	response = UserWS.findAll();
	UserList = (List<Users>) response.getEntity();
	assertEquals(UserList.size(), 1);
	response = UserWS.findUserById(2);
	Users User = (Users) response.getEntity();
	assertEquals(HttpStatus.SC_OK, response.getStatus());
	assertEquals(null, User);

	}

	@Test
	public void testUpdateUser() {
	Response response = UserWS.findUserById(2);
	Users User = (Users) response.getEntity();
	User.setFirstName("Richard");
	User.setUserName("NEW User");
	User.setLastName("Branson");
	response = UserWS.updateUser(User);
	assertEquals(HttpStatus.SC_OK, response.getStatus());
	User = (Users) response.getEntity();
	assertEquals(User.getFirstName(), "Richard");
	assertEquals(User.getUserName(), "NEW User");
	assertEquals(User.getLastName(), "Branson");
	}

 
}

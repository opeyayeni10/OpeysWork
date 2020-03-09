package com.webTechProject.webtech_app.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webTechProject.webtech_app.data.UserDAO;
import com.webTechProject.webtech_app.model.Credentials;
import com.webTechProject.webtech_app.model.Users;



@Path("/users")
@Stateless
@LocalBean
public class UserWS {

	@EJB
	private UserDAO userDao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		List<Users> users= userDao.getAllUsers();
		return Response.status(200).entity(users).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findUserById(@PathParam("id") int id) {
		Users users = userDao.getUser(id);
		return Response.status(200).entity(users).build();
	}
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveUser(Users user) {
		userDao.save(user);
		return Response.status(201).entity(user).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUser(Users user) {
		userDao.update(user);
		return Response.status(200).entity(user).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") int id) {
		userDao.delete(id);
		return Response.status(204).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/search/{userName}")
	public Response findBookByName(@PathParam("userName") String userName) {
		List<Users> user = userDao.getuserByUserName(userName);
		return Response.status(200).entity(user).build();
	}
	
	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response loginUser(final Credentials credentials) {
		final List<Users> users = userDao.loginUser(credentials.getName(), credentials.getPassword());
		if(users.isEmpty()) {
			return Response.status(500).build();
		}
		return Response.status(201).build();
	}

}

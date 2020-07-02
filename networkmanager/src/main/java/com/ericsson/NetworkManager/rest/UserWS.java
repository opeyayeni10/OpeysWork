package com.ericsson.NetworkManager.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.NetworkManager.data.UserDAO;
import com.ericsson.NetworkManager.model.Credentials;
//A00258305@bitbucket.org/aitcse8/networkmanager.git
import com.ericsson.NetworkManager.model.User;

@Path("/users")
@Stateless
@LocalBean
public class UserWS {
	@EJB
	private UserDAO userDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllUsers() {
		final List<User> users = userDao.getAllUsers();
		return Response.status(200).entity(users).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveUser(final User user) {
		userDao.save(user);
		//return Response.status(201).build();
		return null;
	}



	
	@POST
	@Path("/availability")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkUserAvailability(final User user) {
		if(user.getName() != null) {
		userDao.findUserByName(user.getName());
		return Response.status(201).entity(user).build();
		}else {
			return Response.status(204).entity(user).build();
		}
		
		/*return userDao.checkUserAvailability(userName);*/
	}

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response loginUser(final Credentials credentials) {
		final List<User> user = userDao.loginUser(credentials.getName(), credentials.getPassword());
		return Response.status(201).entity(user).build();

	}

}

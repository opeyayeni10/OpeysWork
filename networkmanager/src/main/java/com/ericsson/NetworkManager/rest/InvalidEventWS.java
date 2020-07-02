package com.ericsson.NetworkManager.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.NetworkManager.data.InvalidEventDAO;
import com.ericsson.NetworkManager.model.InvalidEvent;

@Path("/invalidevents")
@Stateless
@LocalBean
public class InvalidEventWS {

	@EJB
	private InvalidEventDAO invalidEventDAO;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		final List<InvalidEvent> invalidEvents = invalidEventDAO.getAllInvalidEvents();
		return Response.status(200).entity(invalidEvents).build();
	}
}

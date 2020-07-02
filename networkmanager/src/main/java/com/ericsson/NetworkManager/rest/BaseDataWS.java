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

import com.ericsson.NetworkManager.data.BaseDataDAO;
import com.ericsson.NetworkManager.model.BaseData;

@Path("/baseData")
@Stateless
@LocalBean
public class BaseDataWS {
	
	
	@EJB
	private BaseDataDAO baseDataDAO;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		final List<BaseData> baseData= baseDataDAO.getAllBaseData();
		return Response.status(200).entity(baseData).build();
	}
	/*
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{imsi}")
	public Response findByIMSI(@PathParam("imsi") final String imsi) {
		final List<BaseData> baseData= baseDataDAO.findByIMSI(imsi);
		return Response.status(200).entity(baseData).build();
	}
*/

}

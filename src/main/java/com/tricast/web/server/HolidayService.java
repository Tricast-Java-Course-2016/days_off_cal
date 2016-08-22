package com.tricast.web.server;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tricast.beans.Holiday;
import com.tricast.web.dao.OutOfTransactionException;
import com.tricast.web.dao.Workspace;
import com.tricast.web.dao.WorkspaceImpl;
import com.tricast.web.manager.HolidayManager;

/**
 * User: Renato
 */
@Path("holidays")
public class HolidayService extends LVSResource {

	private static final Logger log = LogManager.getLogger(HolidayService.class);

	private final HolidayManager manager;
	private Workspace workspace;

	@Inject
	public HolidayService(HolidayManager manager, WorkspaceImpl workspace) {
		this.manager = manager;
		this.workspace = workspace;
	}

	@GET
	@Produces(APPLICATION_JSON)
	public Response getHolidays() throws OutOfTransactionException {
		log.trace("Trying to get all holidays");
		try {
			return respondGet(manager.getAllHolidays(workspace));
		} catch (SQLException ex) {
			return respondGet(ex.getMessage(), 500);
		}
	}

	@GET
	@Path("{id}")
	@Produces(APPLICATION_JSON)
	public Response getById(@PathParam("id") long id) throws SQLException, OutOfTransactionException {
		log.trace("Requested to get ID = " + id);
		try {
			return respondGet(manager.getById(workspace, id));
		} catch (SQLException ex) {

			return respondGet(ex.getMessage(), 500);
		}
	}

	@POST
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response createHoliday(Holiday holiday) throws OutOfTransactionException {
		log.trace("Trying to create new holiday for account #" + holiday.getAccountId());
		try {
			return respondPost(manager.createHoliday(workspace, holiday), "\\holidays");
		} catch (SQLException ex) {
			return respondPost(ex.getMessage(), "\\holidays", 500);
		}
	}

	@PUT
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response updateHoliday(Holiday holiday) throws SQLException, OutOfTransactionException {
		log.trace("Trying to update a holiday for account #" + holiday.getAccountId());
		try {
			return respondPut(manager.updateHoliday(workspace, holiday));
		} catch (SQLException ex) {
			return respondPut(ex.getMessage(), 500);
		}
	}

	@DELETE
	@Path("{id}")
	@Produces(APPLICATION_JSON)
	@Consumes(APPLICATION_JSON)
	public Response deleteHoliday(@PathParam("id") long id) throws SQLException, OutOfTransactionException {
		log.trace("Trying to delete holiday #" + id);
		try {
			return respondDelete(manager.deleteHoliday(workspace, id));
		} catch (SQLException ex) {
			return respondDeleteNotOK(ex.getMessage(), null, 500);
		}
	}

}

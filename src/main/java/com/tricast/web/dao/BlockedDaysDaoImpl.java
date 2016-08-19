package com.tricast.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.tricast.web.annotations.JdbcUnitOfWork;

public class BlockedDaysDaoImpl implements BlockedDaysDao {
	
	private static final Logger log = LogManager.getLogger(BlockedDaysDao.class);

	@Inject
	Provider<Connection> connections;

	private static List<String> blockedDays = null;

	@Override
	@JdbcUnitOfWork(commit = false)
	public List<String> getBlockedDays() {
		if (blockedDays == null) {
			Connection con = connections.get();

			blockedDays = new ArrayList<String>();

			try (PreparedStatement ps = con
					.prepareStatement("SELECT DAY FROM CALENDAR.BLOCKEDDAYS");
					ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					blockedDays.add(rs.getString(1));
				}

			} catch (SQLException ex) {
				log.error(ex, ex);
			}

		}
		return blockedDays;
	}

}

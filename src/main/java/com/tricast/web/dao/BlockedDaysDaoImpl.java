package com.tricast.web.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tricast.database.SqlManager;
import com.tricast.database.Workspace;
import com.tricast.web.annotations.JdbcTransaction;

public class BlockedDaysDaoImpl implements BlockedDaysDao {

	private static final Logger log = LogManager.getLogger(BlockedDaysDao.class);

	private static List<String> blockedDays = null;
	private static final SqlManager sqlManager = SqlManager.getInstance();

	@Override
	@JdbcTransaction
	public List<String> getBlockedDays(Workspace workspace) throws IOException {

		if (blockedDays == null) {

			blockedDays = new ArrayList<String>();

			String sql = sqlManager.get("blockeddaysget.sql");

			try (PreparedStatement ps = workspace.getPreparedStatement(sql); ResultSet rs = ps.executeQuery()) {

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

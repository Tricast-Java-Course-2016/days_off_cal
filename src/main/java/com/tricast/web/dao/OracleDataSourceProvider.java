package com.tricast.web.dao;

import javax.sql.DataSource;

import com.google.inject.Provider;

public class OracleDataSourceProvider implements Provider<DataSource> {

	@Override
	public DataSource get() {
		// TODO Auto-generated method stub
		return null;
	}

	// private static final Logger log =
	// LogManager.getLogger(OracleDataSourceProvider.class);
	//
	// private final String url;
	// private final String username;
	// private final String password;
	//
	// @Inject
	// public OracleDataSourceProvider(@Named("url") final String url,
	// @Named("username") final String username,
	// @Named("password") final String password) {
	// this.url = url;
	// this.username = username;
	// this.password = password;
	// }
	//
	// @Override
	// public DataSource get() {
	// OracleDataSource dataSource = null;
	// try {
	// dataSource = new OracleDataSource();
	// } catch (SQLException ex) {
	// log.error(ex, ex);
	// }
	// dataSource.setURL(url);
	// dataSource.setUser(username);
	// dataSource.setPassword(password);
	// return dataSource;
	// }
}

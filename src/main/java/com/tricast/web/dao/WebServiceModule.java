package com.tricast.web.dao;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import java.util.Properties;

import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.tricast.web.annotations.JdbcTransaction;
import com.tricast.web.manager.AccountManager;
import com.tricast.web.manager.AccountManagerImpl;
import com.tricast.web.manager.HolidayManager;
import com.tricast.web.manager.HolidayManagerImpl;
import com.tricast.web.server.AccountService;
import com.tricast.web.server.HolidayService;

public class WebServiceModule extends JerseyServletModule {

	@Override
	protected void configureServlets() {
		final ResourceConfig rc = new PackagesResourceConfig("com.tricast.web.server");

		Names.bindProperties(binder(), loadProperties());

		// Force the Injector to bind a Provider that provides DataSources
		requireBinding(DataSource.class);

		// Bind the DataSource class to be provided by OracleDataSourceProvider
		// in Singleton
		bind(DataSource.class).toProvider(PostGreSQLDataSourceProvider.class).in(Singleton.class);
		bind(WorkspaceImpl.class).toProvider(WorkspaceProvider.class);

		// Binding certain Interfaces to their Implementations. This can be
		// easily modified for testing, to use Test Implementations
		bind(AccountDao.class).to(AccountDaoImpl.class);
		bind(AccountManager.class).to(AccountManagerImpl.class);
		bind(HolidayDao.class).to(HolidayDaoImpl.class);
		bind(HolidayManager.class).to(HolidayManagerImpl.class);
		bind(BlockedDaysDao.class).to(BlockedDaysDaoImpl.class);

		// Binding the REST endpoints
		bind(AccountService.class);
		bind(HolidayService.class);

		// Injections to use our own ObjectMappers. Only needed to pretty print
		// the JSON output, not necessary at all.
		bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class).in(Singleton.class);
		bind(JacksonJsonProvider.class).toProvider(JacksonJsonProviderProvider.class).in(Singleton.class);

		// Creating the JdbcUnitOfWorkInterceptor and also stating that it is
		// required to bind this class.
		JdbcTransactionInterceptor jdbcTransactionInterceptor = new JdbcTransactionInterceptor();
		requestInjection(jdbcTransactionInterceptor);

		// Bind to intercept any calls annotated with JdbcUnitOfWork by
		bindInterceptor(any(), annotatedWith(JdbcTransaction.class), jdbcTransactionInterceptor);

		for (Class<?> resource : rc.getClasses()) {
			System.out.println("Binding resource: " + resource.getName());
			bind(resource);
		}

		// Bind to serve all /services calls by this Servlet
		serve("/services/*").with(GuiceContainer.class);
	}

	private Properties loadProperties() {
		Properties props = new Properties();
		props.put("datasourceName", "PostGreSQL Portable DB");
		props.put("serverName", "localhost");
		props.put("portNumber", "5432");
		props.put("databaseName", "postgres");
		props.put("user", "postgres");
		props.put("password", "password");
		return props;
	}

}

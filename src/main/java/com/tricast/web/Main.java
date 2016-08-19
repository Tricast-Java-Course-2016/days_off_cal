package com.tricast.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.tricast.web.dao.WebServiceModule;

public class Main extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		return Guice.createInjector(new WebServiceModule());
	}
}

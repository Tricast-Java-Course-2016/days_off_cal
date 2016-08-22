package com.tricast.web.dao;

import javax.sql.DataSource;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class WorkspaceProvider implements Provider<WorkspaceImpl> {

	private DataSource ds;

	@Inject
	public WorkspaceProvider(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public WorkspaceImpl get() {
		WorkspaceImpl workspace = new WorkspaceImpl(ds);
		return workspace;
	}

}

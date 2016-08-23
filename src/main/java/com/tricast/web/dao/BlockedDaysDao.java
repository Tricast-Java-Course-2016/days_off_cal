package com.tricast.web.dao;

import java.io.IOException;
import java.util.List;

import com.tricast.database.Workspace;

public interface BlockedDaysDao {

	List<String> getBlockedDays(Workspace workspace) throws IOException;

}

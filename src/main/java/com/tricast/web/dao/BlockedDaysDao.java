package com.tricast.web.dao;

import java.util.List;

public interface BlockedDaysDao {

	List<String> getBlockedDays(Workspace workspace);

}

package com.tricast.repositories.customs;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQuery;
import com.tricast.repositories.entities.BlockedDay;
import com.tricast.repositories.entities.QBlockedDay;

@Repository
public class BlockedDaysRepositoryImpl extends QueryDslRepositorySupport implements BlockedDayRepositoryCustom {

    public BlockedDaysRepositoryImpl() {
        super(BlockedDay.class);
    }

	@Override
	public List<BlockedDay> getBlockedDays() {
		
		QBlockedDay blockedDay = QBlockedDay.blockedDay;
		
		JPQLQuery<BlockedDay> query = from(blockedDay);
	
		return query.fetch();
	}
}

package com.tricast.repositories.customs;

import java.util.List;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQuery;
import com.tricast.repositories.entities.BlockedDayEntity;
import com.tricast.repositories.entities.QBlockedDayEntity;

@Repository
public class BlockedDayRepositoryCustomImpl extends QueryDslRepositorySupport implements BlockedDayRepositoryCustom {

    public BlockedDayRepositoryCustomImpl() {
        super(BlockedDayEntity.class);
    }

	@Override
	public List<BlockedDayEntity> getBlockedDays() {
		
		QBlockedDayEntity blockedDay = QBlockedDayEntity.blockedDayEntity;
		
		JPQLQuery<BlockedDayEntity> query = from(blockedDay);
	
		return query.fetch();
	}
}

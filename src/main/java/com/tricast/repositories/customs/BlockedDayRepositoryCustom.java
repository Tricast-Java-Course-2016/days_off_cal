package com.tricast.repositories.customs;

import java.util.List;

import com.tricast.repositories.entities.BlockedDayEntity;

public interface BlockedDayRepositoryCustom {

    List<BlockedDayEntity> getBlockedDays();
}

package com.tricast.managers.mappers;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.embded.Username;

public class AccountRequestMapper {

    public static Account mapToEntity(AccountRequest requestObject, Account entityObject) {

        if(requestObject == null) {
            return null;
        }

        if(entityObject == null) {
        	throw new IllegalArgumentException("Missing Account entityObject for mapping");
        }
        
        entityObject.setId(requestObject.getId());
        entityObject.setUserName(new Username(requestObject.getUserName()));
        entityObject.setRealName(requestObject.getRealName());
        entityObject.setDaysOffPerYear(requestObject.getDaysOffPerYear());
        entityObject.setSickLeavePerYear(requestObject.getSickLeavePerYear());
    
        return entityObject;
    }
}

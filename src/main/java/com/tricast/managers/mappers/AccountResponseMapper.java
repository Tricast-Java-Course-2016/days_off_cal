package com.tricast.managers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.tricast.controllers.responses.AccountResponse;
import com.tricast.repositories.entities.Account;

public class AccountResponseMapper {

    public static AccountResponse mapToResponse(Account entityObject) {

        if(entityObject == null) {
            return null;
        }

        AccountResponse reponseObject = new AccountResponse();
        reponseObject.setId(entityObject.getId());
        reponseObject.setUserName(entityObject.getUserName());
        reponseObject.setRealName(entityObject.getRealName());
        reponseObject.setDaysOffPerYear(entityObject.getDaysOffPerYear());
        reponseObject.setSickLeavePerYear(entityObject.getSickLeavePerYear());

        return reponseObject;
    }

    public static List<AccountResponse> mapToBeanList(List<Account> entityObjectsList) {

        if(entityObjectsList == null) {
            return null;
        }

        List<AccountResponse> accountsResponseList = new ArrayList<>();

        entityObjectsList.forEach(entity -> accountsResponseList.add(mapToResponse(entity)));

        return accountsResponseList;
    }
}

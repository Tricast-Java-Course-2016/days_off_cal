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

        AccountResponse responseObject = new AccountResponse();
        responseObject.setId(entityObject.getId());
        responseObject.setUserName(entityObject.getUserName().toString());
        responseObject.setPassword(entityObject.getPassword().toString());
        responseObject.setRealName(entityObject.getRealName());
        responseObject.setDaysOffPerYear(entityObject.getDaysOffPerYear());
        responseObject.setSickLeavePerYear(entityObject.getSickLeavePerYear());

        return responseObject;
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

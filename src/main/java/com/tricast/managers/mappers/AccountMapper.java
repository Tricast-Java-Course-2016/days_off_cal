package com.tricast.managers.mappers;

import java.util.ArrayList;
import java.util.List;

import com.tricast.managers.beans.Account;
import com.tricast.repositories.entities.AccountEntity;

public class AccountMapper {

    public static Account mapToBean(AccountEntity entityObject) {

        if(entityObject == null) {
            return null;
        }

        Account beanObject = new Account();
        beanObject.setId(entityObject.getId());
        beanObject.setUserName(entityObject.getUserName());
        beanObject.setPassword(entityObject.getPassword());
        beanObject.setRealName(entityObject.getRealname());
        beanObject.setDaysOffPerYear(entityObject.getDaysoffperyear());
        beanObject.setSickLeavePerYear(entityObject.getSickleaveperyear());

        return beanObject;
    }
    
    public static AccountEntity mapToEntity(Account beanObject) {

        if(beanObject == null) {
            return null;
        }

        AccountEntity entityObject = new AccountEntity();
        entityObject.setId(beanObject.getId());
        entityObject.setUserName(beanObject.getUserName());
        entityObject.setPassword(beanObject.getPassword());
        entityObject.setRealname(beanObject.getRealName());
        entityObject.setDaysoffperyear(Long.valueOf(beanObject.getDaysOffPerYear()).intValue());
        entityObject.setSickleaveperyear(Long.valueOf(beanObject.getSickLeavePerYear()).intValue());

        return entityObject;
    }

    public static List<Account> mapToBeanList(List<AccountEntity> entityObjectsList) {

        if(entityObjectsList == null) {
            return null;
        }

        List<Account> accountsBeanList = new ArrayList<>();

        entityObjectsList.forEach(entity -> accountsBeanList.add(mapToBean(entity)));

        return accountsBeanList;
    }
}

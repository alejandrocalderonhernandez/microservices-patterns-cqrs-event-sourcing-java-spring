package com.viking_bank.account.common.utils;

import com.viking_bank.cqrs.core.domain.entities.BaseEntity;

import java.util.*;

public class CommonUtil {

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static <T extends BaseEntity>  List<T> iterableToList(Iterable<T> iterable) {
        var result = new ArrayList<T>();
        iterable.forEach(result::add);
        return result;
    }
}

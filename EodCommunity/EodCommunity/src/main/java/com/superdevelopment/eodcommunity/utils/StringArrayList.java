package com.superdevelopment.eodcommunity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StringArrayList {
    public static List<String> convert(List<UUID> list) {
        List<String> newList = new ArrayList<>();

        for(UUID u : list) {
            newList.add(String.valueOf(u));
        }

        return newList;
    }
    public static List<UUID> reverseConvert(List<String> list) {
        List<UUID> newList = new ArrayList<>();

        for(String s : list) {
            newList.add(UUID.fromString(s));
        }

        return newList;
    }
}

package com.goosfraba.city_parking.utils;

public class InputFormatter {

    public static String formatCityName(String name) {
        return name.substring(0,1).toUpperCase() +
                name.substring(1).toLowerCase();
    }

    public static String formatCityCode(String code) {
        return  code.substring(0, 3).toUpperCase();
    }


}

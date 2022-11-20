package com.goosfraba.city_parking.utils;

public class Validator {

    public static boolean checkCityCodeLength(String cityCode) {
        if (cityCode.length() != 3) {
            throw new IllegalArgumentException("City codes must be 3 letters long");
        }

        return false;
    }


}

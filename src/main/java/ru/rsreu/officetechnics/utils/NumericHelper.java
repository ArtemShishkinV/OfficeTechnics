package ru.rsreu.officetechnics.utils;

public class NumericHelper {

    public static boolean convertToBool(int status) {
        return status == 1;
    }

    public static int convertToInt(boolean status) {
        return status ? 1 : 0;
    }

}

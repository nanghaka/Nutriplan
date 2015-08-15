package com.app.cryptotunnel.nutriplan.customexception;

/**
 * Created by codephillip on 8/5/15.
 */
public class InvalidValueException extends Exception {
    private Double d2;
    private String s2;

    public InvalidValueException(Double d1) {
        d2 = d1;
    }

    public InvalidValueException(String s1) {
        s2 = s1;
    }

    @Override
    public void printStackTrace() {
        System.out.println("InvalidValueException at " + d2 + "Can not cast zero(0) or negative value into a double");
        super.printStackTrace();
    }


    public void printStringError(String err) {
        super.printStackTrace();
        System.out.println("InvalidValueException, Can not save null value into database");
    }
}

package com.app.cryptotunnel.nutriplan.customexception;

/**
 * Created by codephillip on 8/5/15.
 */
public class InvalidValueException extends Exception {
    Double d2;
    public InvalidValueException(Double d1) {
        d2 = d1;
    }

    @Override
    public void printStackTrace() {
        System.out.println("InvalidValueException at " + d2 + "Can not cast zero(0) or negative value into a double");
        super.printStackTrace();
    }
}

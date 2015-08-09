package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 8/9/15.
 */
public class Bbn {

    int _id;
    String barcode;
    String bc;
    String nutritips;

    public Bbn(int _id, String barcode, String bc, String nutritips) {
        this._id = _id;
        this.barcode = barcode;
        this.bc = bc;
        this.nutritips = nutritips;
    }

    public Bbn(String barcode, String bc, String nutritips) {
        this.barcode = barcode;
        this.bc = bc;
        this.nutritips = nutritips;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public String getNutritips() {
        return nutritips;
    }

    public void setNutritips(String nutritips) {
        this.nutritips = nutritips;
    }
}

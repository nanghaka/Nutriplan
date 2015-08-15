package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 8/9/15.
 */
public class BbnContract {

    private int _id_bbn;
    private String barcode;
    private String bc;
    private String nutritips;
    private String url;

    public BbnContract() {
    }

    public BbnContract(int _id_bbn, String barcode, String bc, String nutritips) {
        this._id_bbn = _id_bbn;
        this.barcode = barcode;
        this.bc = bc;
        this.nutritips = nutritips;
    }

    public BbnContract(String barcode, String bc, String nutritips) {
        this.barcode = barcode;
        this.bc = bc;
        this.nutritips = nutritips;
    }

    public BbnContract(String barcode, String bc, String nutritips, String url) {
        this.barcode = barcode;
        this.bc = bc;
        this.nutritips = nutritips;
        this.url = url;
    }

    public int get_id_bbn() {
        return _id_bbn;
    }

    public void set_id_bbn(int _id_bbn) {
        this._id_bbn = _id_bbn;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

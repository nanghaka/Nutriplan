package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 7/7/15.
 */
public class WeightTrackerContract {

    //private variables
    private int _id;
    private String _weight;
    private String _weight_time;

    public WeightTrackerContract(int _id, String _weight, String _weight_time) {
        this._id = _id;
        this._weight = _weight;
        this._weight_time = _weight_time;
    }

    public WeightTrackerContract() {
    }

    public WeightTrackerContract(String _weight) {
        this._weight = _weight;
    }

    public WeightTrackerContract(int _id, String _weight) {
        this._id = _id;
        this._weight = _weight;
    }

    public WeightTrackerContract(String _weight, String _weight_time) {
        this._weight = _weight;
        this._weight_time = _weight_time;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }

    public String get_weight_time() {
        return _weight_time;
    }

    public void set_weight_time(String _weight_time) {
        this._weight_time = _weight_time;
    }
}

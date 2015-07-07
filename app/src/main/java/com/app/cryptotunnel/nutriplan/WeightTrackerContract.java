package com.app.cryptotunnel.nutriplan;

/**
 * Created by codephillip on 7/7/15.
 */
public class WeightTrackerContract {

    //private variables
    int _id;
    String _weight;

    public WeightTrackerContract() {
    }

    public WeightTrackerContract(String _weight) {
        this._weight = _weight;
    }

    public WeightTrackerContract(int _id, String _weight) {
        this._id = _id;
        this._weight = _weight;
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
}

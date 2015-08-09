package com.app.cryptotunnel.nutriplan.database;

/**
 * Created by codephillip on 8/9/15.
 */
public class MealPlanContract {

    int _id_mealPlan;
    String day, breakfast, lunch, dinner;

    public MealPlanContract() {
    }

    public MealPlanContract(int _id_mealPlan, String day, String breakfast, String lunch, String dinner) {
        this._id_mealPlan = _id_mealPlan;
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public MealPlanContract(String day, String breakfast, String lunch, String dinner) {
        this.day = day;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public int get_id_mealPlan() {
        return _id_mealPlan;
    }

    public void set_id_mealPlan(int _id_mealPlan) {
        this._id_mealPlan = _id_mealPlan;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}

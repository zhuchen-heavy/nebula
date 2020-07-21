package com.nebula.lambdademo.entity;

public class Dish {

    private String dish;

    private boolean ok;

    private int weight;


    public Dish(String dish, boolean ok, int weight) {
        this.dish = dish;
        this.ok = ok;
        this.weight = weight;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dish='" + dish + '\'' +
                ", ok=" + ok +
                ", weight=" + weight +
                '}';
    }

}

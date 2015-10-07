package com.lichblitz.aaoconverter.domain;

/**
 * Created by lichblitz on 6/10/15.
 *
 * This class represents a currency from the result of the json response
 */
public class ResultCurrency {

    private String name;
    private double value;

    public ResultCurrency(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

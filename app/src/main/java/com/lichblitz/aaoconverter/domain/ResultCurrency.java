package com.lichblitz.aaoconverter.domain;

/**
 * Created by lichblitz on 6/10/15.
 *
 * This class represents a currency from the result of the json response
 */
public class ResultCurrency {
    private String name;
    private String initials;
    private double value;


    public ResultCurrency(String name, String initials, double value) {
        this.name = name;
        this.initials = initials;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

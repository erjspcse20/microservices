package com.microservice.demo.limitsservice.bean;

public class LimitsConfigration {
	private Integer maximum;
    private Integer minimum;

    public LimitsConfigration() {
    }

    public LimitsConfigration(Integer maximum, Integer minimum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }
}

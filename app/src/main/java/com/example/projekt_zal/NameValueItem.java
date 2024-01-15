package com.example.projekt_zal;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class NameValueItem implements Serializable {
    private String name;
    private Integer value;

    public NameValueItem(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return name + ": " + value;
    }
}

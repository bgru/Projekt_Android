package com.example.projekt_zal;

import androidx.annotation.NonNull;

import java.io.Serializable;

// można prościej, ale tak jest ciekawiej
public class NameValueItem implements Serializable {
    private final String name;
    private final Integer value;

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

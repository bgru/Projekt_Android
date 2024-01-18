package com.example.projekt_zal;

import androidx.annotation.NonNull;

import java.io.Serializable;

// można prościej, ale tak jest ciekawiej
public class NameValueItem implements Serializable {
    private final String name;
    private final Integer value;
    private int id = -1;

    public NameValueItem(String name, Integer value, Integer id){
        this.name = name;
        this.value = value;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

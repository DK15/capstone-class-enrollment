package com.example.darshan.isd.models;


import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class ClassList {

    public String className;

    public ClassList() {
    }

    public ClassList(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}



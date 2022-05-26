package com.example.autho.utils;

public enum Person {
    
    gender("性别"),
    male("男"),
    female("女"),
    person_name("虎二");

    private final String personStr;

    private Person(String arg0) {
        this.personStr = arg0;
    }

    @Override
    public String toString() {
        return personStr;
    }

    public static String valueOf(int key) {
        if (key==0) {
            return female.personStr;
        }else if (key==1) {
            return male.personStr;
        }
        return "";
    }

    public String getPersonStr() {
        return personStr;
    }
}

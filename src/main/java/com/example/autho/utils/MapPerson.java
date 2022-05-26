package com.example.autho.utils;

import java.util.Map;

public enum MapPerson {

    man2woman(1,0),
    world2human(3,9);

    private final int key;
    private final int value;

    MapPerson(int key,int value) {
        this.key=key;
        this.value=value;
    }



    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key+":"+value;
    }

    public int getV() {
        return this.value;
    }

    public int getK() {
        return key;
    }

    public static int getVByK(int key) {
        switch (key) {
            case 0:
                return man2woman.getV();
            case 1:
                return world2human.getV();
            default:
                return -1;
        }
    }

    public static int getKByV(int value) {
        switch (value) {
            case 0:
                return man2woman.getK();
            case 9:
                return world2human.getK();
            default:
                return -1;
        }
    }
}

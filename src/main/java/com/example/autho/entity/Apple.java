package com.example.autho.entity;

import com.example.autho.annotation.FruitColor;
import com.example.autho.annotation.FruitName;
import com.example.autho.annotation.FruitProvider;

/**
 * 注解使用
 */
public class Apple {
    
    @FruitName("Apple")
    private String appleName;
    
    @FruitColor(fruitColor= FruitColor.Color.RED)
    private String appleColor;
    
    @FruitProvider(id=1,name="红苹果集团",address="华夏路天子脚下1号")
    private String appleProvider;
    
    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }
    public String getAppleColor() {
        return appleColor;
    }
    
    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }
    public String getAppleName() {
        return appleName;
    }
    
    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
    public String getAppleProvider() {
        return appleProvider;
    }
    
    public void displayName(){
        System.out.println("水果的名字是：苹果");
    }
}
package com.tolgaalperkus.survivorbird;

public class Bird {
    private int heightScreen;
    private int widthScreen;
    private float xPlace;
    private float yPlace;

    public Bird(int heightScreen, int widthScreen) {
        this.heightScreen = heightScreen;
        this.widthScreen = widthScreen;
        xPlace = widthScreen*0.3f;
        yPlace = heightScreen*0.5f;
    }
    public void fall(float velocity){
        yPlace = yPlace - velocity;
    }
    public float flapHeight(){
        return 6f;
    }
    public float getxPlace() {
        return xPlace;
    }

    public void setxPlace(float xPlace) {
        this.xPlace = xPlace;
    }

    public float getyPlace() {
        return yPlace;
    }

    public void setDefault(){
        setyPlace(heightScreen*0.5f);
    }
    public void setyPlace(float yPlace) {
        this.yPlace = yPlace;
    }

    public int width(){
        return widthScreen/15;
    }
    public int height(){
        return heightScreen/10;
    }
}

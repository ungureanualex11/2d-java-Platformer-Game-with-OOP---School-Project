package Joc;

import Framework.GameObject;

public class Camera {
    private  float x,y;
    public Camera(float x,float y){
        this.x=x;
        this.y=y;
    }

    public void tick(GameObject player){
    x=-player.getX()+Game.WIDTH/2;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }


}

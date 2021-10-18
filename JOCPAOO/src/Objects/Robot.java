package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;
import Joc.Handler;

import java.awt.*;
import java.util.Date;
import java.util.LinkedList;
import java.lang.Object;
import java.util.Timer;
import java.util.TimerTask;

public class Robot extends GameObject {


    Texture tex = Game.getInstance();
    private Animation robot;
    private int robotLife = 5;
    private Handler handler;

    public Robot(float x, float y, Handler handler, ObjectId id) {

        super(x, y, id);
        this.handler = handler;
        robot = new Animation(10, tex.block[11], tex.block[12]);


    }

    boolean movingRight = true;
    float pozstart = getX();
    float pozmax = pozstart + 270;//DISTANTA

    public void tick(LinkedList<GameObject> object) {

        //MISCARE STANGA DREAPTA
        robot.runAnimation();
        if (movingRight)
            x += 2;
        else
            x -= 2;

        ColiziuneRobot(object);
    }

    public void render(Graphics g) {

            robot.drawAnimation(g, (int) x, (int) y, 48, 48);

        }



    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    private void ColiziuneRobot(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Bullet){
                if (getBounds().intersects(tempObject.getBounds())) {
                    robotLife--;
                    handler.removeObject(tempObject);
                    if(robotLife==0) {
                        handler.removeObject(this);
                        Game.ROBOTIMORTI++;
                    }


                }

            }
            if (tempObject.getId() == ObjectId.Block){
                if (getBounds().intersects(tempObject.getBounds()))
                   movingRight=!movingRight;
            }



        }
    }


}

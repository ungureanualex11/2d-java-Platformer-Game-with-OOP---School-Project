package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;
import Joc.Handler;

import java.awt.*;
import java.util.LinkedList;

public class RobotOpus extends GameObject {


    Texture tex = Game.getInstance();
    private Animation roboto;
    private int robotoLife = 5;
    private Handler handler;

    public RobotOpus(float x, float y, Handler handler, ObjectId id) {

        super(x, y, id);
        this.handler = handler;
        roboto = new Animation(10, tex.block[12], tex.block[11]);


    }

    boolean movingLeft = true;
    float pozstart = getX();
    float pozmax = pozstart - 300;//DISTANTA

    public void tick(LinkedList<GameObject> object) {

        //MISCARE STANGA DREAPTA
        roboto.runAnimation();
        if (movingLeft)
            x -= 2;
        else
            x += 2;

        ColiziuneRobotO(object);
    }

    public void render(Graphics g) {

            roboto.drawAnimation(g, (int) x, (int) y, 48, 48);

        }



    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    private void ColiziuneRobotO(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Bullet){
                if (getBounds().intersects(tempObject.getBounds())) {
                    robotoLife--;
                    handler.removeObject(tempObject);
                    if(robotoLife==0) {
                        handler.removeObject(this);
                        Game.ROBOTIMORTI++;
                    }


                }

            }
            if (tempObject.getId() == ObjectId.Block){
                if (getBounds().intersects(tempObject.getBounds()))
                    movingLeft=!movingLeft;
            }



        }
    }


}

package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;
import Joc.Handler;

import java.awt.*;
import java.util.LinkedList;

public class Platforma extends GameObject {


    Texture tex = Game.getInstance();
    private Animation platforma;

    public Platforma(float x, float y,ObjectId id) {

        super(x, y, id);
        platforma = new Animation(5, tex.block[18], tex.block[19],tex.block[20],tex.block[21],tex.block[22],tex.block[23],tex.block[22],tex.block[21],tex.block[20],tex.block[19]);


    }

    boolean movingLeft = true;
    float pozstart = getX();
    float pozmax = pozstart - 300;//DISTANTA

    public void tick(LinkedList<GameObject> object) {

        //MISCARE STANGA DREAPTA
       platforma.runAnimation();
        if (movingLeft)
            x -= 5;
        else
            x += 5;
        if (x == pozmax)
            movingLeft = false;
        else if (x == pozstart)
            movingLeft = true;

    }

    public void render(Graphics g) {

            platforma.drawAnimation(g, (int) x, (int) y, 48, 48);

        }



    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }


}

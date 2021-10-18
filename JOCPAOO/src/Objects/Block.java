package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {

    Texture tex= Game.getInstance();
    protected int type;

    public Block(float x, float y, int type,ObjectId id){

        super(x,y,id);
        this.type=type;

    }

    public void tick(LinkedList<GameObject> object){


    }

    public void render(Graphics g) {
        if (type == 0) //dirt block
            g.drawImage(tex.block[0], (int) x, (int) y, null);
        if (type == 1) //grass block
            g.drawImage(tex.block[1], (int) x, (int) y, null);
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }



}

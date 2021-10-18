package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Game;

import java.awt.*;
import java.util.LinkedList;

public class BlockDetector extends GameObject {

    Texture tex= Game.getInstance();


    public BlockDetector(float x, float y, ObjectId id){

        super(x,y,id);


    }

    public void tick(LinkedList<GameObject> object){


    }

    public void render(Graphics g) {

            g.drawImage(tex.block[13], (int) x, (int) y, null);
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }



}

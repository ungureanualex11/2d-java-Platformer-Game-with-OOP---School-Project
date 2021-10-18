package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;

import java.awt.*;
import java.util.LinkedList;

public class Coin extends GameObject{

    Texture tex= Game.getInstance();
    private Animation coin;

    public Coin(float x, float y, ObjectId id){

        super(x,y,id);
        coin=new Animation(10,tex.block[2],tex.block[3],tex.block[4],tex.block[5],tex.block[6],tex.block[7],tex.block[8],tex.block[9],tex.block[10]);

    }

    public void tick(LinkedList<GameObject> object) {
        coin.runAnimation();
    }

    public void render(Graphics g) {
        coin.drawAnimation(g,(int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {return new Rectangle((int)x,(int)y,32,32);}


}


package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;

import java.awt.*;
import java.util.LinkedList;

public class Munitie extends GameObject{

    Texture tex= Game.getInstance();
    private Animation munitie;

    public Munitie(float x, float y, ObjectId id){

        super(x,y,id);
        munitie=new Animation(10,tex.block[14],tex.block[15]);

    }

    public void tick(LinkedList<GameObject> object) {
        munitie.runAnimation();
    }

    public void render(Graphics g) {
        munitie.drawAnimation(g,(int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {return new Rectangle((int)x,(int)y,32,32);}


}


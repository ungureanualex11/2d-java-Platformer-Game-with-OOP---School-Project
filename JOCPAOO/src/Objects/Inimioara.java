package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Game;

import java.awt.*;
import java.util.LinkedList;

public class Inimioara extends GameObject{

    Texture tex= Game.getInstance();
    private Animation inimioara;

    public Inimioara(float x, float y, ObjectId id){

        super(x,y,id);
        inimioara=new Animation(10,tex.block[16],tex.block[17]);

    }

    public void tick(LinkedList<GameObject> object) {
        inimioara.runAnimation();
    }

    public void render(Graphics g) {
        inimioara.drawAnimation(g,(int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {return new Rectangle((int)x,(int)y,32,32);}


}


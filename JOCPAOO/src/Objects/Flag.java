package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Joc.BufferedImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Flag extends GameObject {
    public BufferedImage portal=null;
    BufferedImageLoader loader=new BufferedImageLoader();
    public Flag(float x, float y, ObjectId id) {
        super(x, y, id);
        portal=loader.loadImage("/Resources/portal.png");
    }



    public void tick(LinkedList<GameObject> object) {
     

    }

    public void render(Graphics g) {
        g.drawImage(portal,(int)x,(int)y,32,32,null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}

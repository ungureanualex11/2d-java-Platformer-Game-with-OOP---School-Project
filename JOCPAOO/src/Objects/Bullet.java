package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Game;
import Joc.Handler;


import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject {

    Texture tex = Game.getInstance();


    public Bullet(float x, float y, ObjectId id,  int velX) {
        super(x, y, id);
        this.velX = velX;


    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y+=0.3f;
        if (velX > 0) facing = 1;
        else if (velX < 0) facing = -1;


    }

    public void render(Graphics g) {
        if (facing == 1) {
            g.drawImage(tex.bullet[1], (int) x, (int) y, 120, 45, null);
        } else if (facing == -1)
            g.drawImage(tex.bullet[0], (int) x, (int) y, 120, 45, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }


    }


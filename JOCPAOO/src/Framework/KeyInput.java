package Framework;

import Joc.Game;
import Joc.Handler;
import Objects.Bullet;
import Objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static Objects.Player.DEAD;

public class KeyInput extends KeyAdapter  {

    Handler handler;
    public KeyInput(Handler handler){
        this.handler=handler;
    }
    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode();

        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ObjectId.Player){
                if(key==KeyEvent.VK_D && DEAD==0 ) tempObject.setVelX(5);
                if(key==KeyEvent.VK_A && DEAD==0) tempObject.setVelX(-5);
                if(((key==KeyEvent.VK_W||key==KeyEvent.VK_SPACE) && DEAD==0)&&!tempObject.isJumping()) {

                    tempObject.setJumping(true);
                    tempObject.setVelY(-10);

                }
                if(key==KeyEvent.VK_L && DEAD==0)
                {   if(Game.MUNITIE!=0) {
                    Game.MUNITIE--;
                    tempObject.setAtking(true);
                    int facing = tempObject.getFacing();
                    if (facing == 1)
                        handler.addObject(new Bullet(tempObject.getX() + 40, tempObject.getY() + 40, ObjectId.Bullet, tempObject.getFacing() * 10));
                    else
                        handler.addObject(new Bullet(tempObject.getX() - 100, tempObject.getY() + 40, ObjectId.Bullet, tempObject.getFacing() * 10));
                }
                    else System.out.println("Fara munitie");
                }

            }
        }

        if(key==KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }
    public void keyReleased(KeyEvent e){
        int key=e.getKeyCode();

        for(int i=0;i<handler.object.size();i++){
            GameObject tempObject=handler.object.get(i);
            if(tempObject.getId()==ObjectId.Player){
                if(key==KeyEvent.VK_D) tempObject.setVelX(0);
                if(key==KeyEvent.VK_A) tempObject.setVelX(0);
                if(key==KeyEvent.VK_L) { tempObject.setAtking(false); /*System.out.println("nu ataca");*/ }
            }
        }
    }
}

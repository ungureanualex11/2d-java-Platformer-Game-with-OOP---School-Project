package Objects;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Joc.Animation;
import Joc.Camera;
import Joc.Game;
import Joc.Handler;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Player extends GameObject {
    private float width=48,height =96;
    private float gravity=0.5f;
    private float MAX_SPEED=10;
    public static int playerLife=50;
    public static int DEAD=0;
   // private int contor=0;



    private Handler handler;
    private Camera cam;

    Texture tex= Game.getInstance();

    private Animation playerWalk,playerWalkLeft,playerIdle,playerIdleLeft,playerAtking,playerAtkingLeft;

    public Player(float x, float y, Handler handler,Camera cam, ObjectId id) {
        super(x, y, id);
        this.handler= handler;
        this.cam=cam;

        playerWalk=new Animation(5,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6]);
        playerWalkLeft=new Animation(5,tex.player[8],tex.player[9],tex.player[10],tex.player[11],tex.player[12],tex.player[13]);
        playerIdle=new Animation(8,tex.player[0],tex.player[16],tex.player[17],tex.player[0],tex.player[0],tex.player[0],tex.player[0]);
        playerIdleLeft=new Animation(8,tex.player[7],tex.player[14],tex.player[15],tex.player[7],tex.player[7],tex.player[7],tex.player[7]);
        playerAtking=new Animation(5,tex.player[18],tex.player[19],tex.player[20]);
        playerAtkingLeft=new Animation(5,tex.player[23],tex.player[22],tex.player[21]);
    }

    public void tick(LinkedList<GameObject> object) {
        x+=velX;
        y+=velY;

        if(velX<0) facing=-1;
        else if(velX>0) facing=1;

        if(falling||jumping){
            velY+=gravity;

            if(velY>MAX_SPEED)
                velY=MAX_SPEED;
        }
        Collision(object);
        playerWalk.runAnimation();
        playerWalkLeft.runAnimation();
        playerIdle.runAnimation();
        playerIdleLeft.runAnimation();
        playerAtking.runAnimation();
        playerAtkingLeft.runAnimation();
        if(playerLife==0) {
            System.out.println("mort");
            velY=0;
            velX=0;
            DEAD=1;

        }
        if(y>=600) playerLife=0;

    }

    private void Collision(LinkedList<GameObject> object){
        for(int i=0;i<handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            if ((tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.Coin || tempObject.getId() == ObjectId.BlockDetector || tempObject.getId() == ObjectId.Robot|| tempObject.getId() == ObjectId.RobotOpus || tempObject.getId() == ObjectId.Munitie || tempObject.getId() == ObjectId.Inimioara|| tempObject.getId() == ObjectId.Platforma)&& DEAD==0 ) {
                //LIMITE SUS
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    //DACA E BANUT DISPARE
                    if(tempObject.getId() == ObjectId.Coin){ handler.removeObject(tempObject); Game.COINS++; }
                   else
                       if(tempObject.getId() == ObjectId.Munitie)
                       {
                           if(Game.MUNITIE<19)
                           {
                               handler.removeObject(tempObject);
                               Game.MUNITIE++;
                           }
                       }
                       //DACA E INIMIOARA DISPARE
                       else if(tempObject.getId() == ObjectId.Inimioara)
                        {   if (playerLife<=40)
                             {
                                 handler.removeObject(tempObject);
                                  playerLife+=10;
                             }
                        }

                       //SE FACE EFECT COLIZIUNE FIZICA \/ \/ \/ se muta playerul cu 32 pix mai jos
                     else  { y = tempObject.getY() + 32;
                              velY = 0;
                       }


                    if(tempObject.getId() == ObjectId.Robot||tempObject.getId() == ObjectId.RobotOpus) playerLife--;
                }

                //LIMITE JOS
                if (getBounds().intersects(tempObject.getBounds())) {
                    //DACA E BANUT DISPARE
                    if(tempObject.getId() == ObjectId.Coin){ handler.removeObject(tempObject); Game.COINS++;}
                    else
                        if(tempObject.getId() == ObjectId.Munitie)
                        {
                            if(Game.MUNITIE<19)
                            {
                                handler.removeObject(tempObject);
                                Game.MUNITIE++;
                            }
                        }

                         else if(tempObject.getId() == ObjectId.Inimioara)
                         {  if (playerLife<=40)
                            {
                             handler.removeObject(tempObject);
                             playerLife+=10;
                            }
                         }
                            //SE FACE EFECT COLIZIUNE FIZICA \/ \/ \/
                      else   {
                        velY = 0;
                        falling = false;
                        jumping = false;
                    }


                    if(tempObject.getId() == ObjectId.Robot||tempObject.getId() == ObjectId.RobotOpus) playerLife--;
                } else falling = true;

                //LIMITE DREAPTA
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    //DACA E BANUT DISPARE
                    if(tempObject.getId() == ObjectId.Coin)

                    { handler.removeObject(tempObject);
                    Game.COINS++;
                    }

                   else
                    if(tempObject.getId() == ObjectId.Munitie)

                    { if(Game.MUNITIE<19)
                        {
                        handler.removeObject(tempObject);
                        Game.MUNITIE++;
                     }
                    }
                        else if(tempObject.getId() == ObjectId.Inimioara)

                        {   if (playerLife<=40)
                            {
                            handler.removeObject(tempObject);
                            playerLife+=10;
                            }
                        }

                     else  x = tempObject.getX() - width; //SE FACE EFECT COLIZIUNE FIZICA


                   if (tempObject.getId() == ObjectId.Robot||tempObject.getId() == ObjectId.RobotOpus) {
                            playerLife--;
                                y -= 96;

                        }

                }

                //LIMITE STANGA
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    //DACA E BANUT DISPARE
                    if(tempObject.getId() == ObjectId.Coin){ handler.removeObject(tempObject); Game.COINS++; }
                   else
                    if(tempObject.getId() == ObjectId.Munitie)

                    { if(Game.MUNITIE<19)
                        {
                        handler.removeObject(tempObject);
                        Game.MUNITIE++;
                        }
                    }

                     else if(tempObject.getId() == ObjectId.Inimioara)

                     {  if (playerLife<=40)
                         {
                             handler.removeObject(tempObject);
                             playerLife+=10;
                         }
                     }

                     else  x = tempObject.getX() + 35; //SE FACE EFECT COLIZIUNE FIZICA

                    if(tempObject.getId() == ObjectId.Robot||tempObject.getId() == ObjectId.RobotOpus)  { playerLife--; y-=96; }
                }

            }

            else if (tempObject.getId() == ObjectId.Flag) {
                //switch level
                if(getBounds().intersects(tempObject.getBounds())) {
                    if(Game.COINS>=19)
                        handler.switchLevel();
                }}

            if (tempObject.getId() == ObjectId.BlockDetector) {
                if(getBounds().intersects(tempObject.getBounds())) {

                   gravity=0.2f;


                }}
          if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.Robot||tempObject.getId() == ObjectId.RobotOpus|| tempObject.getId() == ObjectId.Platforma )
            if(getBounds().intersects(tempObject.getBounds()))
                gravity=0.5f;

        }

    }



    public void render(Graphics g) {
        if (playerLife <= 0)
        {
            g.drawImage(tex.player[24], (int) x, (int) y, 48, 96, null);

        }
        else {

            //VERIFICAM DACA PLAYERUL ATACA

            if (atking) {
                if (facing == 1) playerAtking.drawAnimation(g, (int) x, (int) y, 48, 96);
                else playerAtkingLeft.drawAnimation(g, (int) x, (int) y, 48, 96);

            }

            //VERIFICAM DACA PLAYERUL SARE
            else if (jumping) {
                if (facing == 1) g.drawImage(tex.player_jump[0], (int) x, (int) y, 48, 96, null);
                else g.drawImage(tex.player_jump[1], (int) x, (int) y, 48, 96, null);
            } else {
                //DACA NU SARE,VERIFICAM DACA PLAYERUL SE MISCA
                if (velX != 0) {
                    //SE VERIFICA ORIENTAREA PLAYERULUI ( facing = 1 sau 0 )
                    if (facing == 1) playerWalk.drawAnimation(g, (int) x, (int) y, 48, 96);
                    else playerWalkLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
                } else {
                    if (facing == 1) playerIdle.drawAnimation(g, (int) x, (int) y, 48, 96);
                    else playerIdleLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
                }
            }

        }
    }







  //  g.drawImage(tex.player[0],(int)x,(int)y,48,96,null);


    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int)y+(height)/2),(int)width/2,(int)height/2+5);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int)((int)x+(width/2)-((width/2)/2)),(int)y,(int)width/2,(int)height/2-10);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x+width-5),(int)y+5,5,(int)height-10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
    }

}

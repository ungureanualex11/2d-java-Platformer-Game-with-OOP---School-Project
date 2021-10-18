package Joc;

import Framework.KeyInput;
import Framework.MouseInput;
import Framework.ObjectId;
import Framework.Texture;
import Objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements  Runnable {
    public static int LEVEL=1;
    public static int COINS=0;
    public static int MUNITIE=0;
    public static int ROBOTIMORTI=0;
    public static int RESTART=0;
    private  boolean running=false;
    private Thread thread;
    private Menu menu;

    public static enum STATE{
        MENU,
        GAME,
        HELP
    }

   public static STATE State=STATE.MENU;

    public static int WIDTH,HEIGHT;

    public BufferedImage level=null,clouds=null;
    public BufferedImage health1=null;
    public BufferedImage health2=null;
    public BufferedImage health3=null;
    public BufferedImage health4=null;
    public BufferedImage health5=null;
    public BufferedImage health6=null;

    public BufferedImage meniu=null;
    public BufferedImage help=null;
    public BufferedImage aimurit=null;
    public BufferedImage fundal=null;
    public BufferedImage fail=null;
    public BufferedImage succes=null;


    //Object

    Handler handler;
    Camera cam;
   static Texture tex;


    Random rand=new Random();



    private void init(){
        WIDTH=getWidth();
        HEIGHT=getHeight();

        tex=new Texture();
        menu=new Menu();


        BufferedImageLoader loader=new BufferedImageLoader();
        level=loader.loadImage("/Resources/level.png"); //loading level
        clouds=loader.loadImage("/Resources/clouds.png");


        aimurit=loader.loadImage("/Resources/aimurit.png");

        //VIETI PLAYER
        health1=loader.loadImage("/Resources/health1.png");
        health2=loader.loadImage("/Resources/health2.png");
        health3=loader.loadImage("/Resources/health3.png");
        health4=loader.loadImage("/Resources/health4.png");
        health5=loader.loadImage("/Resources/health5.png");
        health6=loader.loadImage("/Resources/health6.png");


        fail=loader.loadImage("/Resources/fail.png");
        succes=loader.loadImage("/Resources/succes.png");


        meniu=loader.loadImage("/Resources/meniu.png");
        fundal=loader.loadImage("/Resources/fundal.png");
        help=loader.loadImage("/Resources/help.png");

        cam=new Camera(0,0);
        handler=new Handler(cam);



        handler.LoadImageLevel(level);

       // handler.addObject(new Player(100,100,handler,ObjectId.Player));

       // handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());
    }


    public synchronized  void start(){
        if(running)
            return;
        running=true;
        thread=new Thread(this);
        thread.start();
    }

    public void run(){
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    private void tick(){

        if(State==STATE.GAME)



        handler.tick();
        for (int i=0;i<handler.object.size();i++){
            if(handler.object.get(i).getId()==ObjectId.Player){
                cam.tick(handler.object.get(i));
                if(RESTART==1) //DOAR LA APASARE PLAY RESTART=1 & SE INCARCA LVL 1
                {   Player.DEAD=0;
                    cam.setX(0);
                handler.clearLevel();
                Player.playerLife=50;
                Game.MUNITIE=0;
                Game.COINS=0;
                handler.LoadImageLevel(level);
                System.out.println("S-a incarcat level 1");
                    Game.LEVEL=1;
                }

                    }
                }




    }

    private void render(){
        BufferStrategy bs=this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g=bs.getDrawGraphics();

        Graphics2D g2d=(Graphics2D) g;

        ////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                     //
        //Draw                                                                                      //

            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
        if(State==STATE.GAME) {


            g2d.translate(cam.getX(), cam.getY());       ////////////////////////////////////////////////////////////////////////

            for (int xx = 32; xx < clouds.getWidth() * 3; xx += clouds.getWidth())
                g.drawImage(clouds, xx, 0, this);

            handler.render(g);




        g2d.translate(-cam.getX(),-cam.getY());   ///////////////////////////////////////////////////////////////////////////////

            //AFISARE MESAJ FINAL WIN SAU FAIL
            if(Game.LEVEL==6&&Handler.win==true)
                g.drawImage(succes, 50, 260, this);
            if(Game.LEVEL==6&&Handler.win==false)
                    g.drawImage(fail, 50, 260, this);




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

       //VERIFICARE VIATA PLAYER SI DESENARE PESTE TOATE ELEMENTELE
        if(Player.playerLife<=50 && Player.playerLife>=41)
            g.drawImage(health1,0,0,this);
        if(Player.playerLife<=40 && Player.playerLife>=31)
            g.drawImage(health2,0,0,this);
        if(Player.playerLife<=30 && Player.playerLife>=21)
            g.drawImage(health3,0,0,this);
        if(Player.playerLife<=20 && Player.playerLife>=11)
            g.drawImage(health4,0,0,this);
        if(Player.playerLife<=10 && Player.playerLife>=1)
            g.drawImage(health5,0,0,this);
        if(Player.playerLife<=0)
            g.drawImage(health6,0,0,this);

        for(int k=0;k<20;k++)
            if(MUNITIE==k)
                g.drawImage(tex.munitia[k],32,32,this);
        for(int k=0;k<20;k++)
            if(COINS==k)
                g.drawImage(tex.banuti[k],32,64,this);

           //IMAGINE MENU in JOC CARE VA DEVENI FUNCTIONALA IN MOUSEINPUT
            g.drawImage(meniu,70,32,this);

            if (Player.DEAD==1)
            { g.drawImage(aimurit,150,-15,this);}

        } else if(State==STATE.MENU)
                {
                    g.drawImage(fundal,0,0,this);
                        menu.render(g);



                }
        if (State==STATE.HELP){
            g.drawImage(help,0,0,this);
            g.drawImage(meniu,40,80,this);
        }




        /////////////////////////////////////////////////////////


        g.dispose();
        bs.show();
    }


    public static Texture getInstance(){
        return tex;
    }
    public static void main(String args[]){

        new Window(800,600,"Salveaza planeta",new Game());

       // new Window(800,600,"Salveaza planeta",new Game());
    }
}

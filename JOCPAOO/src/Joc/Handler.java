package Joc;

import Framework.GameObject;
import Framework.ObjectId;
import Objects.*;
import Objects.Robot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Handler {
    private Camera cam;
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    private GameObject tempObject;
    public static boolean win=false;

    private BufferedImage level2,level3,level4,level5;


    public Handler(Camera cam) {

        this.cam = cam;

        BufferedImageLoader loader = new BufferedImageLoader();
        level2 = loader.loadImage("/Resources/level2.png");
        level3 = loader.loadImage("/Resources/level3.png");
        level4 = loader.loadImage("/Resources/level4.png");
        level5 = loader.loadImage("/Resources/level5.png");




    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void LoadImageLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        //System.out.println("width,height " + w + " " + h);

       //////////////////////////////// //SE GENEREAZA MATRICEA PENTRU NIVEL/////////////////////////////////

        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 120 && blue == 0)
                    addObject(new Platforma(xx * 32, yy * 32, ObjectId.Platforma));
                if (red == 255 && green == 255 && blue == 255)
                    addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
                if (red == 128 && green == 128 && blue == 128)
                    addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
                if (red == 0 && green == 0 && blue == 255)
                    addObject(new Player(xx * 32, yy * 32, this, cam, ObjectId.Player));
                if (red == 149 && green == 101 && blue == 194)
                    addObject(new Flag(xx * 32, yy * 32, ObjectId.Flag));
                if (red ==255 && green == 255 && blue == 0)
                    addObject(new Coin(xx * 32, yy * 32, ObjectId.Coin));
               if (red ==255 && green == 0 && blue == 0)
                  addObject(new Robot(xx * 32, yy * 32,this, ObjectId.Robot));
                if (red == 240 && green == 123 && blue == 111)
                    addObject(new BlockDetector(xx * 32, yy * 32, ObjectId.BlockDetector));
                if (red == 255 && green == 0 && blue == 246)
                    addObject(new Munitie(xx * 32, yy * 32, ObjectId.Munitie));
                if (red == 48 && green == 255 && blue == 0)
                    addObject(new Inimioara(xx * 32, yy * 32, ObjectId.Inimioara));
                if (red == 158 && green == 0 && blue == 0)
                    addObject(new RobotOpus(xx * 32, yy * 32,this, ObjectId.RobotOpus));

            }
        }
    }

    public void switchLevel() {
        clearLevel();
        cam.setX(0);
        switch (Game.LEVEL) {
            case 1:
                System.out.println("Case 1");
                Player.playerLife=50;
                Game.MUNITIE=0;
                Game.COINS=0;
                LoadImageLevel(level2);
              //  System.out.println("S-a incarcat level 2");
                System.out.println("Roboti morti"+Game.ROBOTIMORTI);
                break;
            case 2:
                System.out.println("Case 2");
                Player.playerLife=50;
                Game.MUNITIE=0;
                Game.COINS=0;
                LoadImageLevel(level3);
                //  System.out.println("S-a incarcat level 3");
                System.out.println("Roboti morti"+Game.ROBOTIMORTI);
                break;
            case 3:
                System.out.println("Case 2");
                Player.playerLife=50;
                Game.MUNITIE=0;
                Game.COINS=0;
                LoadImageLevel(level4);
                //  System.out.println("S-a incarcat level 4");
                System.out.println("Roboti morti"+Game.ROBOTIMORTI);
                break;
            case 4:
                System.out.println("Case 2");
                Player.playerLife=50;
                Game.MUNITIE=0;
                Game.COINS=0;
                LoadImageLevel(level5);
                //  System.out.println("S-a incarcat level 5");
                System.out.println("Roboti morti"+Game.ROBOTIMORTI);
                break;
            case 5:
                if(Game.ROBOTIMORTI==20)
                    win=true;






        }
        Game.LEVEL++;
        }






    public void clearLevel() {
        object.clear();
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}


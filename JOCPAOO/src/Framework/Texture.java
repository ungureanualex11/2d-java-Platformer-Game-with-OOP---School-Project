package Framework;

import Joc.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet bs,ps,buls,ms,bans;
    private BufferedImage block_sheet=null;
    private BufferedImage player_sheet=null;
    private BufferedImage bullet_sheet=null;
    private BufferedImage munitia_sheet=null;
    private BufferedImage banuti_sheet=null;


    public BufferedImage[] block=new BufferedImage[24];
    public BufferedImage[] player=new BufferedImage[25];
    public BufferedImage[] player_jump=new BufferedImage[2];
    public BufferedImage[] bullet=new BufferedImage[2];
    public BufferedImage[] munitia=new BufferedImage[20];
    public BufferedImage[] banuti=new BufferedImage[20];



    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            block_sheet = loader.loadImage("/Resources/block_sheet.png");
            player_sheet = loader.loadImage("/Resources/player_sheet.png");
            bullet_sheet=loader.loadImage("/Resources/bullet_sheet.png");
            munitia_sheet=loader.loadImage("/Resources/munitie.png");
            banuti_sheet=loader.loadImage("/Resources/banuti.png");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        buls= new SpriteSheet(bullet_sheet);
        ms=new SpriteSheet(munitia_sheet);
        bans=new SpriteSheet(banuti_sheet);

        getTextures();
    }

        private void getTextures(){
            bullet[0]=buls.grabImage(2,1,32,32);
            bullet[1]=buls.grabImage(1,1,32,32);





            block[0]=bs.grabImage(1,1,32,32); // dirt
            block[1]=bs.grabImage(2,1,32,32); //grass block
          // COINURILE
            block[2]=bs.grabImage(1,2,32,32);
            block[3]=bs.grabImage(2,2,32,32);
            block[4]=bs.grabImage(3,2,32,32);
            block[5]=bs.grabImage(4,2,32,32);
            block[6]=bs.grabImage(5,2,32,32);
            block[7]=bs.grabImage(6,2,32,32);
            block[8]=bs.grabImage(1,3,32,32);
            block[9]=bs.grabImage(2,3,32,32);
            block[10]=bs.grabImage(3,3,32,32);


           //Idle spre dreapta
            player[0]=ps.grabImage(1,1,32,64);//idle frame player
            player[16]=ps.grabImage(8,2,32,64);
            player[17]=ps.grabImage(9,2,32,64);

            //Player spre dreapta
            player[1]=ps.grabImage(2,1,32,64);
            player[2]=ps.grabImage(3,1,32,64);
            player[3]=ps.grabImage(4,1,32,64);
            player[4]=ps.grabImage(5,1,32,64);
            player[5]=ps.grabImage(6,1,32,64);
            player[6]=ps.grabImage(7,1,32,64);

            //Idle spre stanga
            player[7]=ps.grabImage(20,1,32,64);
            player[14]=ps.grabImage(12,2,32,64);
            player[15]=ps.grabImage(13,2,32,64);

            //Player spre stanga
            player[8]=ps.grabImage(19,1,32,64);
            player[9]=ps.grabImage(18,1,32,64);
            player[10]=ps.grabImage(17,1,32,64);
            player[11]=ps.grabImage(16,1,32,64);
            player[12]=ps.grabImage(15,1,32,64);
            player[13]=ps.grabImage(14,1,32,64);


            //PLAYER ATACA DREAPTA
            player[18]=ps.grabImage(1,2,32,64);
            player[19]=ps.grabImage(2,2,32,64);
            player[20]=ps.grabImage(3,2,32,64);


            //PLAYER ATACA STANGA

            player[21]=ps.grabImage(18,2,32,64);
            player[22]=ps.grabImage(19,2,32,64);
            player[23]=ps.grabImage(20,2,32,64);

            //PLAYER MORT

            player[24]=ps.grabImage(10,3,32,64);


            //ROBOT
            block[11]=bs.grabImage(1,4,32,32);
            block[12]=bs.grabImage(2,4,32,32);


            //BLOCK DETECTOR CARE MODIF GRAVITATIA
            block[13]=bs.grabImage(3,4,32,32);


            //player sare

            player_jump[0]=ps.grabImage(10,2,32,64);
            player_jump[1]=ps.grabImage(11,2,32,64);


            //MUNITIE
            block[14]=bs.grabImage(4,3,32,32);
            block[15]=bs.grabImage(5,3,32,32);

            //INIMIOARA
            block[16]=bs.grabImage(4,4,32,32);
            block[17]=bs.grabImage(5,4,32,32);

            //PLATFORMA
            block[18]=bs.grabImage(1,5,32,32);
            block[19]=bs.grabImage(2,5,32,32);
            block[20]=bs.grabImage(3,5,32,32);
            block[21]=bs.grabImage(4,5,32,32);
            block[22]=bs.grabImage(5,5,32,32);
            block[23]=bs.grabImage(6,5,32,32);



                //PREIAU IMAGINI DIN MUNITIE.PNG  PT AFISAREA MUNITIEI PE ECRAN
                //munitia[0]=imaginea cu 0 si tot asa
          for(int col=0;col<=9;col++)
              munitia[col]=ms.grabImage(col+1,1,32,32);
            for(int col=10;col<=19;col++)
                munitia[col]=ms.grabImage(col-9,2,32,32);


            //PREIAU BANUTII
            for(int col=0;col<=9;col++)
                banuti[col]=bans.grabImage(col+1,1,32,32);
            for(int col=10;col<=19;col++)
                banuti[col]=bans.grabImage(col-9,2,32,32);
    }
}


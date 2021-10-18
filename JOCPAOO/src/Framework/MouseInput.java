package Framework;

import Joc.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        int mx=e.getX();
        int my=e.getY();

        //public Rectangle playButton=new Rectangle(Game.WIDTH/2-100,150,100,50);
        //public Rectangle helpButton=new Rectangle(Game.WIDTH/2-100,250,100,50);
       // public Rectangle quitButton=new Rectangle(Game.WIDTH/2-100,350,100,50);
       // g.drawImage(meniu,70,32,this);

        if(Game.State!=Game.State.GAME) {
            //Play
            if (mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2)
                if (my >= 150 && my <= 200)
                {
                    Game.State = Game.State.GAME;
                    Game.RESTART=1;

                }


            //HELP
            if (mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2)
                if (my >= 250 && my <= 300)
                    Game.State = Game.State.HELP;

            //QUIT
            if (mx >= Game.WIDTH / 2 - 100 && mx <= Game.WIDTH / 2)
                if (my >= 350 && my <= 400)
                    System.exit(1);
       }else if (Game.State==Game.State.GAME){
            if (mx >= 70 && mx <=200 )
                if (my >= 32 && my <= 72)
                    Game.State = Game.State.MENU;
                //^^^FUNCTIONALITATE BUTON MENIU ^^^


        }
        if (Game.State==Game.State.HELP){
           // g.drawImage(meniu,40,80,this);
            if (mx >= 40 && mx <= 170)
                if (my >= 80 && my <= 120)
                    Game.State = Game.State.MENU;


        }



    }

    public void mouseReleased(MouseEvent e) {
        Game.RESTART=0;
        System.out.println("releaseeeeeee");
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}

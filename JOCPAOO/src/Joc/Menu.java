package Joc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

public class Menu {

    public Rectangle playButton=new Rectangle(Game.WIDTH/2-100,150,100,50);
    public Rectangle helpButton=new Rectangle(Game.WIDTH/2-100,250,100,50);
    public Rectangle quitButton=new Rectangle(Game.WIDTH/2-100,350,100,50);


    public void render(Graphics g){
        Graphics2D g2d=(Graphics2D) g; // cast
        Font fnt0=new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Salveaza planeta",Game.WIDTH/2-150,100);

            Font fnt1=new Font("arial",Font.BOLD,30);
            g.setFont(fnt1);
            g.drawString("Play",playButton.x+19,playButton.y+30);
            g2d.draw(playButton);


        g.drawString("Help",helpButton.x+19,helpButton.y+30);
        g2d.draw(helpButton);


        g.drawString("Quit",quitButton.x+19,quitButton.y+30);
        g2d.draw(quitButton);
    }
}



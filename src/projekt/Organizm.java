package projekt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Organizm {
    public int wiek;
    public int sila;
    public int inicjatywa;
    public int x;
    public int y;
    public int staryx;
    public int staryy;
    public Color color;
    public char symbol;
    public boolean zywy;



    void rysuj(Graphics g, Swiat swiat) {
        g.setColor(color);
        g.fillRect(x*swiat.skala, y*swiat.skala, swiat.skala, swiat.skala);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {
        int range = 1;

        Point p = new Point(x, y);
        ArrayList<Point> points = swiat.getPointsArount(p, range);

        int tempSize=points.size();
        Random random = new Random();
        int number = random.nextInt(tempSize);

        p=points.get(number);
        staryx=x;
        staryy=y;
        x = p.x;
        y = p.y;

        if(mapa[x][y] != null) kolizja(mapa, ruszajacy, organizmy, swiat);
    }
    public void kolizja(Organizm[][] mapa, Organizm atakujacy, ArrayList<Organizm> organizmy, Swiat swiat){
        Organizm atakowany=mapa[atakujacy.x][atakujacy.y];
        if(atakowany.symbol==atakujacy.symbol){

            atakowany.rozmnazanie(swiat, mapa, organizmy);

            System.out.println("kolizja");
        }
        else if(atakowany.sila> atakujacy.sila){
            //if(atakowany.sila==0) atakujacy.zywy=false;
            if(obrona(atakowany, atakujacy, mapa, swiat)==false){
                atakujacy.zywy=false;
                System.out.println("zjadlo");
            }
            rekacja(atakowany, atakujacy);

        }
        else if(atakowany.sila< atakujacy.sila){
            if(atakowany.sila==0) atakowany.zywy=false;
            else if(obrona(atakowany, atakujacy, mapa, swiat)==false){
                atakowany.zywy=false;

                System.out.println("zjadlo");

            }
            rekacja(atakowany, atakujacy);

        }
        int sum= organizmy.size()-1;
        for(int i=sum;i>=0;i--){
            Organizm temp=organizmy.get(i);
            if(temp.zywy==false){

                Collections.swap(organizmy, i, sum);
                organizmy.remove(sum);
                sum--;

            }
        }

    }


    public boolean obrona(Organizm atakowany, Organizm atakujacy, Organizm[][] mapa, Swiat swiat){
        return false;
    }

    public void rekacja(Organizm atakowany, Organizm atakujacy){

    }

    abstract public Organizm klonowanie(int x, int y);

    public void rozsiewanie(int x, int y, Organizm[][] mapa,  ArrayList<Organizm> organizmy, Swiat swiat){

    }
    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
    }

    public void barszcyk(int x, int y, Swiat swiat, Organizm[][] mapa){

    }
    public void rozmnazanie(Swiat swiat, Organizm[][] mapa, ArrayList<Organizm> organizmy){
        int range = 1;

        Point p = new Point(x, y);
        ArrayList<Point> points = swiat.getPointsArount(p, range);

        int tempSize=points.size();
        Random random = new Random();
        int number = random.nextInt(tempSize);

        p=points.get(number);
        boolean wolne =false;
        for(int i=0;i<tempSize;i++){
            p=points.get(number);
            if(mapa[p.x][p.y]==null){
                nowyOrg(p.x, p.y, organizmy);

            }
        }
    }
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {


    }

}

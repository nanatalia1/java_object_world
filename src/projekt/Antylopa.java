package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Antylopa extends Zwierze{
    public Antylopa(){
        color = Color.BLUE;
        sila=4;
        inicjatywa=4;
        symbol='A';
        zywy=true;
    };
    public Antylopa(Swiat swiat,int wiek, int sila, int inicjatywal){

    }

    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Antylopa();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {
        int range = 2;

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

    public boolean obrona(Organizm atakowany, Organizm atakujacy, Organizm[][] mapa, Swiat swiat){
        Random random = new Random();
        int number = random.nextInt(1);
        if(number==1){
            if(ucieczka(mapa, atakowany, swiat)==false) return false;
            else return true;
        }
        else return false;

    }

    boolean ucieczka(Organizm[][] mapa, Organizm ruszajacy, Swiat swiat){
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
                staryx=x;
                staryy=y;
                x = p.x;
                y = p.y;
                wolne=true;
            }
        }
        if(wolne==true) return true;
        else return false;



    }
    @Override
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {
        Organizm nowy = new Antylopa();
        nowy.y = y;
        nowy.x = x;
        organizmy.add(nowy);

    }

}

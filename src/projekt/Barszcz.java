package projekt;

import java.awt.*;
import java.util.ArrayList;

public class Barszcz extends Roslina{
    public Barszcz(){
        color = Color.RED;
        sila=10;
        inicjatywa=0;
        symbol='B';
        zywy=true;
    };
    public Barszcz(Swiat swiat,int wiek, int sila, int inicjatywa){
    }

    @Override
    public void rozsiewanie(int x, int y, Organizm[][] mapa, ArrayList<Organizm> organizmy, Swiat swiat){
        Organizm temp=new Mlecz();
        Point p= new Point(x, y);
        temp.x=x;
        temp.y=y;

        if(swiat.inBounds(p)){
            organizmy.add(temp);
            mapa[x][y]=temp;
            swiat.rysuj();
            kolizja(mapa, temp, organizmy, swiat);

        }

    }

    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Barszcz();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void rekacja(Organizm atakowany, Organizm atakujacy){
        atakujacy.zywy=false;
        System.out.println("Barszcz zjedzony");
    }

    @Override
    public void barszcyk(int x, int y, Swiat swiat, Organizm[][] mapa){
        int range =1;
        Point p = new Point(x, y);
        ArrayList<Point> points = swiat.getPointsArount(p, range);
        int tempSize=points.size();
        for(int i=0;i<tempSize;i++){
            Point punkt= points.get(i);
            if(mapa[punkt.x][punkt.y] != null){
                Organizm temp= mapa[punkt.x][punkt.y];
                temp.zywy=false;
            }

        }

    }

}

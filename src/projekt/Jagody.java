package projekt;

import java.awt.*;
import java.util.ArrayList;

public class Jagody extends Roslina{
    public Jagody(){
        color = Color.MAGENTA;
        sila=0;
        inicjatywa=0;
        symbol='J';
        zywy=true;
    };
    public Jagody(Swiat swiat,int wiek, int sila, int inicjatywa){
    }

    @Override
    public void rekacja(Organizm atakowany, Organizm atakujacy){
        atakujacy.zywy=false;
        System.out.println("Jagody zjedzone");
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
            kolizja(mapa, temp, organizmy, swiat);

        }

    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Jagody();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }

}

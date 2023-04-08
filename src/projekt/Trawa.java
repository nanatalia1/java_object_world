package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Trawa extends Roslina{
    public Trawa(){
        color = Color.GREEN;
        sila=0;
        inicjatywa=0;
        symbol='T';
        zywy=true;
    };
    public Trawa(Swiat swiat,int wiek, int sila, int inicjatywa){


    }
    @Override
    public void rozsiewanie(int x, int y, Organizm[][] mapa, ArrayList<Organizm> organizmy, Swiat swiat){
        Organizm temp=new Mlecz();
        Point p= new Point(x, y);
        temp.x=x;
        temp.y=y;

        if(swiat.inBounds(p)){
            if( mapa[x][y]==null) {
                organizmy.add(temp);
                mapa[x][y] = temp;
                // swiat.rysuj();
                kolizja(mapa, temp, organizmy, swiat);
            }

        }

    }

    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Trawa();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }


}

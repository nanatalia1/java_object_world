package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Mlecz extends Roslina{
    public Mlecz(){
        color = Color.YELLOW;
        sila=0;
        inicjatywa=0;
        symbol='M';
        zywy=true;
    };
    public Mlecz(Swiat swiat,int wiek, int sila, int inicjatywa){


    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Mlecz();
        nowy.y = y;
        nowy.x = x;
        return nowy;
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
}

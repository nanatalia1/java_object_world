package projekt;

import java.awt.*;
import java.util.ArrayList;

public class Guarana extends Roslina{
    public Guarana(){
        color = Color.RED;
        sila=0;
        inicjatywa=0;
        symbol='G';
        zywy=true;
    };
    public Guarana(Swiat swiat,int wiek, int sila, int inicjatywa){
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
        Organizm nowy = new Guarana();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void rekacja(Organizm atakowany, Organizm atakujacy){
        atakujacy.sila=atakujacy.sila+3;
        System.out.println("Guarana zjedzona");
    }

}

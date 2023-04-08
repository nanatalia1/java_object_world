package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Owca extends Zwierze{
    public Owca(){
        color = Color.WHITE;
        sila=4;
        inicjatywa=4;
        symbol='O';
        zywy=true;
    };
    public Owca(Swiat swiat,int wiek, int sila, int inicjatywa){

    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Owca();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {
        Organizm nowy = new Owca();
        nowy.y = y;
        nowy.x = x;
        organizmy.add(nowy);

    }
}

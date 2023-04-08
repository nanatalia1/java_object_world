package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Wilk extends Zwierze{
    public Wilk(){
        color = Color.GRAY;
        sila=9;
        inicjatywa=5;
        symbol='W';
        zywy=true;
    };
    public Wilk(Swiat swiat,int wiek, int sila, int inicjatywal){

    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Wilk();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {
        Organizm nowy = new Wilk();
        nowy.y = y;
        nowy.x = x;
        organizmy.add(nowy);

    }


}

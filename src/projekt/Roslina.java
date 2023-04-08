package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Roslina extends Organizm{


    @Override
    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {

    }
    @Override
    public void rozsiewanie(int x, int y, Organizm[][] mapa,  ArrayList<Organizm> organizmy, Swiat swiat){

    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Roslina();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void rozmnazanie(Swiat swiat, Organizm[][] mapa, ArrayList<Organizm> organizmy) {

    }
}

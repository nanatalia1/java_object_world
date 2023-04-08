package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw() {
        color = Color.CYAN;
        sila = 2;
        inicjatywa = 1;
        symbol = 'W';
        zywy = true;
    }

    ;

    public Zolw(Swiat swiat, int wiek, int sila, int inicjatywal) {

    }

    @Override
    public Organizm klonowanie(int x, int y) {
        Organizm nowy = new Zolw();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }

    @Override
    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {
        Random szansa = new Random();
        int chance = szansa.nextInt(3);

        if (chance == 0) {

            int range = 1;

            Point p = new Point(x, y);
            ArrayList<Point> points = swiat.getPointsArount(p, range);

            int tempSize = points.size();
            Random random = new Random();
            int number = random.nextInt(tempSize);

            p = points.get(number);
            x = p.x;
            y = p.y;

            if (mapa[x][y] != null) kolizja(mapa, ruszajacy, organizmy, swiat);
            swiat.rysuj();

        }
    }

    @Override
    public boolean obrona(Organizm atakowany, Organizm atakujacy, Organizm[][] mapa, Swiat swiat) {
        if (atakujacy.sila > 5) {
            atakujacy.x = atakujacy.staryx;
            atakujacy.y = atakujacy.staryy;
            return true;
        } else return false;
    }
    @Override
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {
        Organizm nowy = new Zolw();
        nowy.y = y;
        nowy.x = x;
        organizmy.add(nowy);

    }

}

package projekt;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Lis extends Zwierze{
    public Lis(){
        color = Color.RED;
        sila=3;
        inicjatywa=7;
        symbol='L';
        zywy=true;
    };
    public Lis(Swiat swiat,int wiek, int sila, int inicjatywal){
    }

    @Override
    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {
        int range = 1;

        Point p = new Point(x, y);
        ArrayList<Point> points = swiat.getPointsArount(p, range);

        int tempSize=points.size();
        Random random = new Random();
        int number = random.nextInt(tempSize);

        p=points.get(number);


        if(mapa[x][y] != null){
            Organizm org= mapa[x][y];
            if(org.sila<=3){
                x = p.x;
                y = p.y;
                if(mapa[x][y] != null) kolizja(mapa, ruszajacy, organizmy, swiat);
            }
        }
        else{
            x = p.x;
            y = p.y;
            if(mapa[x][y] != null) kolizja(mapa, ruszajacy, organizmy, swiat);
        }
    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Lis();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void nowyOrg(int x, int y, ArrayList<Organizm> organizmy) {
        Organizm nowy = new Lis();
        nowy.y = y;
        nowy.x = x;
        organizmy.add(nowy);

    }


}

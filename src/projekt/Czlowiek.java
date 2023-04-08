package projekt;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Czlowiek extends Zwierze{
    private KeyEvent event;
    public int skala = 30;
    public boolean wlaczonaUmiejetnosc=false;
    public int licznikTur;
    public boolean cooldown=false;

    public Czlowiek(){
        color = Color.PINK;
        sila=5;
        inicjatywa=4;
        symbol='C';
        zywy=true;
    };
    public Czlowiek(Swiat swiat,int wiek, int sila, int inicjatywal){
    }
    @Override
    public Organizm klonowanie(int x, int y)
    {
        Organizm nowy = new Czlowiek();
        nowy.y = y;
        nowy.x = x;
        return nowy;
    }
    @Override
    public void akcja(Swiat swiat, Organizm[][] mapa, Organizm ruszajacy, ArrayList<Organizm> organizmy, int kierunek) {

        if(kierunek==0){

            //Point p = new Point(ruszajacy.x-skala, ruszajacy.y);
            if(ruszajacy.y-1>=0) {

                ruszajacy.y = ruszajacy.y-1;
                //y = p.y;
            }
        }
        if(kierunek==1){

            //Point p = new Point(ruszajacy.x-skala, ruszajacy.y);
            if(ruszajacy.y+1<=500) {

                ruszajacy.y = ruszajacy.y+1;
                //y = p.y;
            }
        }
        if(kierunek==2){

            //Point p = new Point(ruszajacy.x-skala, ruszajacy.y);
            if(ruszajacy.x-1>=0) {

                ruszajacy.x = ruszajacy.x-1;
                //y = p.y;
            }
        }
        if(kierunek==3){

            //Point p = new Point(ruszajacy.x-skala, ruszajacy.y);
            if(ruszajacy.x+1<=500) {

                ruszajacy.x = ruszajacy.x+1;
                //y = p.y;
            }
        }
        if(kierunek==4){
            //umiejetnosc
            if(cooldown==false) {
                wlaczonaUmiejetnosc = true;
                licznikTur = 1;
                //cooldown=0;
                System.out.println("wlaczono tarcze Alzura");
                cooldown=true;
            }
        }
        if(licznikTur==6){
            wlaczonaUmiejetnosc=false;
            //licznikTur=0;
            //cooldown=5;
            System.out.println("tarcza wylaczona");
        }
        else if(wlaczonaUmiejetnosc=true && licznikTur<=5){
            licznikTur++;
        }
        else if(licznikTur==10){
            cooldown=false;
        }
        Point p= new Point(x, y);
        if(swiat.inBounds(p)) {
            if (mapa[x][y] != null) kolizja(mapa, ruszajacy, organizmy, swiat);
        }
    }

    @Override
    public boolean obrona(Organizm atakowany, Organizm atakujacy, Organizm[][] mapa, Swiat swiat) {
        if (wlaczonaUmiejetnosc == true) {
            ucieczka(mapa, atakujacy, swiat);
            return true;


        }
        else return false;
    }

    void ucieczka(Organizm[][] mapa, Organizm ruszajacy, Swiat swiat){
        int range = 1;

        Point p = new Point(x, y);
        ArrayList<Point> points = swiat.getPointsArount(p, range);

        int tempSize=points.size();
        Random random = new Random();
        int number = random.nextInt(tempSize);
        p=points.get(number);
        boolean wolne =false;
        for(int i=0;i<tempSize;i++){
            p=points.get(number);
            //if(mapa[p.x][p.y]==null){
            staryx=x;
            staryy=y;
            x = p.x;
            y = p.y;
            wolne=true;
            //}
        }



    }
}


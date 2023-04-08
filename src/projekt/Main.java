package projekt;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static Dimension wymiary = new Dimension(800, 800 );

    public static void main(String[] args)
    {

        Swiat swiat = new Swiat();
        Okno okno = new Okno(wymiary, swiat);
        swiat.zaludnij(swiat);





    }

}


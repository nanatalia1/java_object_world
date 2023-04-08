package projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import static java.awt.SystemColor.text;

public class Swiat extends JPanel
{
    public Graphics g;

    public int tura=0;
    public int skala = 30;
    public Dimension tileDimensions;
    public String content;
    public String fileName;
    ArrayList<Organizm> organizmy;
    public Organizm[][] mapa;
    public int szerokosc=700;
    public int wysokosc= 700;
    private int width;
    private int height;
    //private Object fileName;


    Swiat(){
        setSize(szerokosc, wysokosc);
        organizmy=new ArrayList<Organizm>();
        tileDimensions = new Dimension(szerokosc/skala, wysokosc/skala);
        mapa=new Organizm[wysokosc/skala][szerokosc/skala];
    };


    public void zaludnij(Swiat swiat){
        Random gen = new Random();
        int amOfOrg = szerokosc/20;
        //int xstart=0;
        //in
        for(int i = 0; i < amOfOrg; i++) {
            int x = gen.nextInt(szerokosc/skala);
            int y = gen.nextInt(wysokosc/skala);
            int org = gen.nextInt(9);
            switch(org){
                case 0:organizmy.add(new Antylopa());break;
                case 1: organizmy.add(new Lis());break;
                case 2: organizmy.add(new Owca());break;
                case 3: organizmy.add(new Wilk());break;
                case 4: organizmy.add(new Zolw());break;
                case 5: organizmy.add(new Barszcz());break;
                case 6: organizmy.add(new Jagody());break;
                case 7: organizmy.add(new Mlecz());break;
                case 8: organizmy.add(new Trawa());break;

            }
            organizmy.get(i).x=x;
            organizmy.get(i).y=y;
        }
        Organizm cz = new Czlowiek();
        cz.setX(7);
        cz.setY(7);
        organizmy.add(cz);
        zaludnijMape(mapa, organizmy);
    }



    public void zaludnijMape(Organizm[][] mapa, ArrayList<Organizm> organizmy){
        for(int i=0;i<organizmy.size();i++){
            Organizm temp=organizmy.get(i);
            mapa[temp.y][temp.x]=temp;
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        rysujSwiat(g);
    }
    public void rysujSwiat(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,getWidth(), getHeight());
        for(Organizm temp: organizmy){
            temp.rysuj(g, this);
        }
    }

    void obslugaTury(int kierunek){
        sortuj();
        for(int i=0;i<organizmy.size();i++){
            Organizm temp=organizmy.get(i);
            temp.akcja(this, mapa, temp, organizmy, kierunek);
            Random random = new Random();
            if(temp.symbol=='M'){
                for(int m=0;m<2;m++){
                    int number = random.nextInt(20);
                    if (number == 0) temp.rozsiewanie(temp.x - 1, temp.y - 1, mapa, organizmy, this);
                    if (number == 1) temp.rozsiewanie(temp.x + 1, temp.y - 1, mapa, organizmy, this);
                    if (number == 2) temp.rozsiewanie(temp.x + 1, temp.y + 1, mapa, organizmy, this);
                    if (number == 3) temp.rozsiewanie(temp.x - 1, temp.y + 1, mapa, organizmy, this);
                }
            }
            else {
                int number = random.nextInt(20);
                if (number == 0) temp.rozsiewanie(temp.x - 1, temp.y - 1, mapa, organizmy, this);
                if (number == 1) temp.rozsiewanie(temp.x + 1, temp.y - 1, mapa, organizmy, this);
                if (number == 2) temp.rozsiewanie(temp.x + 1, temp.y + 1, mapa, organizmy, this);
                if (number == 3) temp.rozsiewanie(temp.x - 1, temp.y + 1, mapa, organizmy, this);
            }
            if(temp.symbol=='B') temp.barszcyk( temp.x, temp.y, this, mapa);
        }


        tura++;
        System.out.println("tura ");
        System.out.print(tura);
        System.out.println(" ");
        //rysuj();
    }

    public boolean inBounds(Point p) {
        return p.x >=0 && p.x < tileDimensions.width && p.y >=0 && p.y < tileDimensions.height;
    }

    ArrayList<Point> getPointsArount(Point point, int range) {
        ArrayList<Point> points = new ArrayList<Point>();
        for (int y = -range; y <= range; y++) {
            for (int x = -range; x <= range; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                Point newPoint = new Point(point.x + x, point.y + y);
                if(inBounds(newPoint)) {
                    points.add(newPoint);
                }
            }
        }
        return points;
    }
    private void sortuj(){

        Collections.sort(organizmy, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm o1, Organizm o2) {
                if (o1.inicjatywa != o2.inicjatywa)
                    return Integer.valueOf(o2.inicjatywa).compareTo(o1.inicjatywa);
                else
                    return Integer.valueOf(o1.wiek).compareTo(o2.wiek);
            }
        });
    }

    void rysuj(){

        repaint();
        rysujSwiat(getGraphics());
    }
    public void Save() {

        JFrame input = new JFrame();
        JTextField textField  = new JTextField(30);
        input.add(textField);
        input.setSize(100,100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                //String text = "saved.txt";
                String save = "";
                save+=(width + " " + height + " "+ organizmy.size() + " " + "\n");
                for(Organizm it : organizmy){
                    save+=(it.x + " ");
                    save+=(it.y + " ");
                    save+=(it.sila+ " ");
                    save+=(it.inicjatywa + " ");

                    save+="\n";
                }
                try {
                    SaveToFile(text, save);
                }catch(IOException ie){
                    ie.printStackTrace();
                }


                input.setVisible(false);
                // frame.setVisible(true);
            }
        });



    }

    public void Load() {
        //frame.setVisible(false);
        JFrame input = new JFrame();
        JTextField textField = new JTextField(30);
        input.add(textField);
        input.setSize(100, 100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String text = "saved.txt";
                String text = textField.getText();
                System.out.println(text);
                try {
                    LoadWorldFromFile(text);
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
                input.setVisible(false);

                rysuj();
            }
        });
    }
    private void SaveToFile(String fileName, String content) throws IOException{
        try{
            String[] elements = content.split("\n");
            String path = ".\\src\\projekt\\" + text;
            File file = new File(path);
            System.out.println("Write to " + text);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(String it : elements){
                writer.write(it);
                writer.newLine();
            }
            writer.close();

        }catch(IOException ie){
            ie.printStackTrace();
        }

    }


    public void LoadWorldFromFile(String FileName) throws IOException{
        String path = ".\\src\\projekt\\"+FileName;
        File file = new File(path);
        if(file.exists()){

            mapa=null;
            organizmy.clear();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            String[] elements = line.split(" ");

            width = Integer.parseInt(elements[0]);
            height = Integer.parseInt(elements[1]);
            int tempSize = Integer.parseInt(elements[2]);



            mapa = new Organizm[height][width];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    mapa[i][j] = organizmy.get(i);
                }
            }
            for(int i = 0; i < tempSize; i++){
                line = reader.readLine();

                elements = line.split(" ");
                switch(elements[0]){
                    case "C":
                        Organizm cz=new Czlowiek();
                        cz.x=Integer.parseInt(elements[1]);
                        cz.y=Integer.parseInt(elements[2]);
                        organizmy.add(cz);

                        break;
                    case "A":
                        Organizm an=new Antylopa();
                        an.x=Integer.parseInt(elements[1]);
                        an.y=Integer.parseInt(elements[2]);
                        organizmy.add(an);
                        break;

                    case "w":
                        Organizm wi=new Wilk();
                        wi.x=Integer.parseInt(elements[1]);
                        wi.y=Integer.parseInt(elements[2]);
                        organizmy.add(wi);
                        break;
                    case "O":
                        Organizm ow=new Owca();
                        ow.x=Integer.parseInt(elements[1]);
                        ow.y=Integer.parseInt(elements[2]);
                        organizmy.add(ow);
                        break;
                    case "L":
                        Organizm li=new Lis();
                        li.x=Integer.parseInt(elements[1]);
                        li.y=Integer.parseInt(elements[2]);
                        organizmy.add(li);
                        break;
                    case "Z":
                        Organizm zo=new Zolw();
                        zo.x=Integer.parseInt(elements[1]);
                        zo.y=Integer.parseInt(elements[2]);
                        organizmy.add(zo);
                        break;
                    case "T":
                        Organizm tr=new Trawa();
                        tr.x=Integer.parseInt(elements[1]);
                        tr.y=Integer.parseInt(elements[2]);
                        organizmy.add(tr);
                        break;
                    case "M":
                        Organizm ml=new Mlecz();
                        ml.x=Integer.parseInt(elements[1]);
                        ml.y=Integer.parseInt(elements[2]);
                        organizmy.add(ml);
                        break;
                    case "G":
                        Organizm gu=new Guarana();
                        gu.x=Integer.parseInt(elements[1]);
                        gu.y=Integer.parseInt(elements[2]);
                        organizmy.add(gu);
                        break;
                    case "B":
                        Organizm ba=new Barszcz();
                        ba.x=Integer.parseInt(elements[1]);
                        ba.y=Integer.parseInt(elements[2]);
                        organizmy.add(ba);
                        break;
                }
            }

        }

    }


}

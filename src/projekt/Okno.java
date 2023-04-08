package projekt;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Okno extends JFrame{
    public static final String nazwa = "symulacja";
    private KeyEvent event;
    public int kierunek;
    ArrayList<Organizm> dodawane;
    JComboBox<Organizm> BOX_ORG;
    private int BoxY=20;
    private int BoxX=40;

    public Okno(Dimension dimension, Swiat swiat){
        dodawane=new ArrayList<Organizm>();
        setSize(dimension);
        setTitle(nazwa);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Organizm temp = new Wilk();
        Organizm temp1 = new Owca();
        Organizm temp2 = new Lis();
        Organizm temp3 = new Antylopa();
        Organizm temp4 = new Trawa();
        Organizm temp5 = new Mlecz();
        Organizm temp6 = new Jagody();
        Organizm temp7 = new Barszcz();
        Organizm temp8 = new Zolw();
        Organizm temp9 = new Guarana();
        dodawane.add(temp);
        dodawane.add(temp1);
        dodawane.add(temp2);
        dodawane.add(temp3);
        dodawane.add(temp4);
        dodawane.add(temp5);
        dodawane.add(temp6);
        dodawane.add(temp7);
        dodawane.add(temp8);
        dodawane.add(temp9);
        BOX_ORG= new JComboBox<Organizm>();
        // BOX_ORG.setModel(new DefaultComboBoxModel<Organizm>(dodawane.toArray(new Organizm[0])));
        for(Organizm dodaj: dodawane){
            BOX_ORG.addItem(dodaj);
        }
        BOX_ORG.setFocusable(false);

        BOX_ORG.setBounds(0, 700, 100, 30);
        BOX_ORG.setVisible(true);
        add(BOX_ORG);


        swiat.setBounds(new Rectangle(0, 0, swiat.szerokosc, swiat.wysokosc));
        add(swiat);
        setLayout(null);
        setVisible(true);

        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_UP) {
                    kierunek=0;
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    kierunek=1;
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    kierunek=2;
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    kierunek=3;
                }
                else if (keyCode == KeyEvent.VK_ENTER) {
                    kierunek=4;
                }
                else if (keyCode == KeyEvent.VK_S) {
                    System.out.println("save");
                   swiat.Save();
                }
                else if (keyCode == KeyEvent.VK_W) {
                    System.out.println("wczytaj");
                    swiat.tura=0;
                    swiat.Load();

                }
                Graphics g = swiat.getGraphics();
                swiat.obslugaTury(kierunek);
                repaint();
                swiat.rysujSwiat( swiat.getGraphics());


            }
        });

        swiat.addMouseListener(new MouseAdapter() {
            //  @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX()/swiat.skala + "," + e.getY()/swiat.skala);
                //tworzy punkt
                Point p = new Point(e.getX()/swiat.skala,e.getY()/swiat.skala);
                Organizm dodaj;
                // dodaj.x=p.x;
                //dodaj.y=p.y;

                if(BOX_ORG.getSelectedItem() instanceof Organizm){
                    swiat.organizmy.add(((Organizm) BOX_ORG.getSelectedItem()).klonowanie(p.x, p.y));
                    swiat.mapa[p.x][p.y]=temp1;
                    swiat.repaint();
                    swiat.rysujSwiat(getGraphics());
                }
                //swiat.dodajMyszka(p.x, p.y);

            }






        });



    }
}


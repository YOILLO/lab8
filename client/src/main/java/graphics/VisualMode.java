package graphics;

import client.Client;
import io.Console;

import messages.*;
import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class VisualMode extends AbstractWindow{
    private JPanel map= new JPanel();
    private JButton returnButtn = new JButton();
    private Box legnd;
    private JLabel legendLabel = new JLabel();
    private JTextArea InfoEarea = new JTextArea();
    private JLabel msg = new JLabel();
    private Vector<Flat> collection;
    private Vector<Flat> oldCollection;

    private MainWindow mainWindow;
    private Client client;

    private Rectangle oldRec;

    private boolean isWork;

    protected VisualMode(Client cli, MainWindow mw, Rectangle rec) {
        super("Visual mode");

        isWork = true;

        client = cli;
        mainWindow = mw;

        setLocal();

        this.setBounds(rec);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.setVisible(true);

        legnd = Box.createVerticalBox();
        InfoEarea.setEditable(false);
        legnd.setMaximumSize(new Dimension(200, 500));
        InfoEarea.setMaximumSize(new Dimension(200, 500));
        InfoEarea.setLineWrap(true);

        map.addMouseListener(new PanelMous());

        map.setBackground(Color.WHITE);
        map.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        returnButtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(true);
                mainWindow.setLocal();
                close();
            }
        });

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 3));

        Box infoBox = Box.createVerticalBox();
        infoBox.add(returnButtn);
        infoBox.add(legendLabel);
        infoBox.add(new JScrollPane(legnd));
        infoBox.add(new JScrollPane(InfoEarea));
        infoBox.add(msg);
        container.add(infoBox);

        container.add(map);

        container.add(getLangBox());

        Thread th = new Thread(()->{
            while (isWork) {

                CommandMsg commandMsg = new CommandMsg("get_collection", "", null, client.getUser());
                AnswerMsg ans = client.sendAndAnswer(commandMsg);
                if (ans.getObj() == null) {
                    Console.println("Received error");
                    continue;
                }
                collection = (Vector<Flat>) ans.getObj();
                if (!collection.equals(oldCollection) || !this.getBounds().equals(oldRec)) {
                    synchronized (this) {
                        ArrayList<String> users = new ArrayList<>();
                        Graphics graphics = map.getGraphics();
                        graphics.setColor(Color.white);
                        graphics.fillRect(2, 2, map.getWidth() - 3, map.getHeight() - 3);
                        //graphics.clearRect(0, 0, map.getWidth(), map.getHeight());
                        for (Flat flat : collection) {
                            if (!users.contains(flat.getUser().getUsername().trim())) {
                                users.add(flat.getUser().getUsername().trim());
                            }
                            graphics.setColor(colorFromString(flat.getUser().getUsername()));
                            graphics.fillRect((int) flat.getCoordinates().getX(), (int)flat.getCoordinates().getY(), 5, 5);
                        }
                        map.revalidate();
                        legnd.removeAll();
                        for (String user : users) {
                            JLabel jlab = new JLabel();
                            jlab.setText(user);
                            jlab.setForeground(colorFromString(user));
                            legnd.add(jlab);
                        }
                        legnd.revalidate();
                        oldCollection = collection;
                        oldRec = this.getBounds();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Console.println("error");
                }
            }
        });
        th.start();
    }

    @Override
    protected void setLocal() {

    }
    public Color colorFromString(String str){
        byte[] bytes = str.getBytes();
        long summ = 0;
        for (byte bt:bytes){
            summ += bt*4;
        }
        return new Color((float)((summ%255)/255.0), (float)(((summ/255)%255)/255.0), (float)(((summ/255/255)%255)/255.0));
    }

    class PanelMous extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            synchronized (this) {
                Flat minFl = null;
                double minRast = 10000000;
                for (Flat fl : collection) {
                    double rast = Math.sqrt(Math.pow(e.getX() - fl.getCoordinates().getX(), 2) + Math.pow(e.getY() - fl.getCoordinates().getY(), 2));
                    if (minRast > rast){
                        minRast = rast;
                        minFl = fl;
                    }
                }
                if (minFl != null){
                    InfoEarea.setText(minFl.toString());
                }
                else{
                    msg.setText("Error, empty collection");
                }
            }
        }
    }

}

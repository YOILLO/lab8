package graphics;

import io.Console;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public abstract class AbstractWindow extends JFrame {
    protected static Properties locals;
    private JButton ru = new JButton("RU");
    private JButton es_pr = new JButton("ES(PR)");
    private JButton it = new JButton("IT");
    private JButton ro = new JButton("RO");

    protected AbstractWindow(String name){
        super(name);

        ru.addActionListener(new SetRuListener());
        ro.addActionListener(new SetRoListener());
        it.addActionListener(new SetItListener());
        es_pr.addActionListener(new SetEsPrListener());
    }

    protected abstract void setLocal();

    protected Box getLangBox(){
        Box langBox = Box.createVerticalBox();
        ru.setAlignmentX(Component.RIGHT_ALIGNMENT);
        es_pr.setAlignmentX(Component.RIGHT_ALIGNMENT);
        it.setAlignmentX(Component.RIGHT_ALIGNMENT);
        ro.setAlignmentX(Component.RIGHT_ALIGNMENT);
        langBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
        langBox.add(ru);
        langBox.add(es_pr);
        langBox.add(it);
        langBox.add(ro);
        return  langBox;
    }

    class SetRuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Console.println("Читаю фаил локали");
                locals.load(Main.class.getClassLoader().getResourceAsStream("ru.txt"));
                Console.println("Фаил локали открыт");
            } catch (IOException er) {
                Console.printError("Ошибка чтения файла локали");
            }
            setLocal();
        }
    }
    class SetEsPrListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Console.println("Читаю фаил локали");
                locals.load(Main.class.getClassLoader().getResourceAsStream("es.txt"));
                Console.println("Фаил локали открыт");
            } catch (IOException er) {
                Console.printError("Ошибка чтения файла локали");
            }
            setLocal();
        }
    }
    class SetItListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Console.println("Читаю фаил локали");
                locals.load(Main.class.getClassLoader().getResourceAsStream("it.txt"));
                Console.println("Фаил локали открыт");
            } catch (IOException er) {
                Console.printError("Ошибка чтения файла локали");
            }
            setLocal();
        }
    }
    class SetRoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Console.println("Читаю фаил локали");
                locals.load(Main.class.getClassLoader().getResourceAsStream("ro.txt"));
                Console.println("Фаил локали открыт");
            } catch (IOException er) {
                Console.printError("Ошибка чтения файла локали");
            }
            setLocal();
        }
    }
}

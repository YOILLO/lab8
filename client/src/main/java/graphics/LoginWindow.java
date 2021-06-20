package graphics;

import client.Client;
import io.Console;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;

public class LoginWindow extends AbstractWindow{

    private JTextField loginTextField = new JTextField("", 1);
    private JLabel loginLabel = new JLabel();
    private JPasswordField passwordField = new JPasswordField("", 1);
    private JLabel passwordLabel = new JLabel();
    private JCheckBox isRegistered = new JCheckBox();
    private JLabel info = new JLabel("");

    private JButton loginButton = new JButton();

    private Client client;

    @Override
    protected void setLocal() {
        loginLabel.setText(locals.getProperty("login", "local error"));
        passwordLabel.setText(locals.getProperty("password", "local error"));
        isRegistered.setText(locals.getProperty("are_registered", "local error"));
        loginButton.setText(locals.getProperty("login_button", "local error"));
    }

    public LoginWindow(Client cli){
        super("Login window");

        this.setBounds(0,0,700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(700, 700));

        client = cli;

        locals = new Properties();
        try {
            Console.println("Читаю фаил локали");
            locals.load(Main.class.getClassLoader().getResourceAsStream("ru.txt"));
            Console.println("Фаил локали открыт");
        } catch (IOException e) {
            Console.printError("Ошибка чтения файла локали");
        }

        setLocal();

        Container container = this.getContentPane();

        container.setLayout(new GridLayout(3, 3));
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(getLangBox());
        container.add(Box.createGlue());
        Box box = Box.createVerticalBox();
        box.add(loginLabel);
        box.add(loginTextField);
        box.add(passwordLabel);
        box.add(passwordField);
        box.add(isRegistered);
        box.add(loginButton);
        box.add(info);
        container.add(box);
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());

        this.setVisible(true);
    }
}

package graphics;

import client.Client;
import io.Console;
import main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

import static io.Console.printError;

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

        while (!client.openSocket()) {
            printError("Неполучилось открыть сокет, переподключаюсь");
        }

        locals = new Properties();
        try {
            Console.println("Читаю фаил локали");
            locals.load(Main.class.getClassLoader().getResourceAsStream("ru.txt"));
            Console.println("Фаил локали открыт");
        } catch (IOException e) {
            Console.printError("Ошибка чтения файла локали");
        }

        setLocal();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = client.login(loginTextField.getText(), new String(passwordField.getPassword()), isRegistered.isSelected());
                if(result){
                    MainWindow mn = new MainWindow(client, getBounds());
                    close();
                }else{
                    info.setForeground(Color.red);
                    info.setText(locals.getProperty("login_error", "local error"));
                }
            }
        });

        Container container = this.getContentPane();

        container.setLayout(new GridLayout(3, 3));
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(getLangBox());
        container.add(Box.createGlue());
        Box box = Box.createVerticalBox();
        loginTextField.setMinimumSize(new Dimension(10, 45));
        loginTextField.setMaximumSize(new Dimension(10000, 45));
        passwordField.setMinimumSize(new Dimension(10, 45));
        passwordField.setMaximumSize(new Dimension(10000, 45));
        box.add(loginLabel);
        box.add(loginTextField);
        box.add(passwordLabel);
        box.add(passwordField);
        box.add(isRegistered);
        box.add(loginButton);
        box.add(info);
        box.add(Box.createGlue());
        container.add(box);
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());

        this.setVisible(true);
    }
}

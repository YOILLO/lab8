package graphics;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends AbstractWindow{

    private JLabel username = new JLabel();

    private JButton commandMode = new JButton();
    private JButton visualMode = new JButton();
    private JButton tableMode = new JButton();

    private Client client;

    public MainWindow(Client cli, Rectangle rect) {
        super("Main window");

        client = cli;

        this.setBounds(rect);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));

        setLocal();

        commandMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommandMode commandMode = new CommandMode(client, getMe(), getBounds());
                setVisible(false);
            }
        });

        visualMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualMode visualMode = new VisualMode(client, getMe(), getBounds());
                setVisible(false);
            }
        });

        tableMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableWindow tableWindow = new TableWindow(client, getMe(), getBounds());
                setVisible(false);
            }
        });

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 3));

        container.add(Box.createGlue());

        container.add(Box.createGlue());

        container.add(super.getLangBox());

        container.add(Box.createGlue());

        Box box = Box.createVerticalBox();
        username.setText(client.getUser().getUsername());
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(username);
        commandMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        commandMode.setSize(new Dimension(500, 40));
        box.add(commandMode);
        visualMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualMode.setSize(new Dimension(500, 40));
        box.add(visualMode);
        tableMode.setAlignmentX(Component.CENTER_ALIGNMENT);
        tableMode.setSize(new Dimension(500, 40));
        box.add(tableMode);
        container.add(box);

        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());

        this.setVisible(true);
    }

    @Override
    protected void setLocal() {
        tableMode.setText(locals.getProperty("table_mode", "Local error"));
        visualMode.setText(locals.getProperty("visual_mode", "Local error"));
        commandMode.setText(locals.getProperty("command_mode", "Local error"));
    }

    private MainWindow getMe(){
        return this;
    }
}

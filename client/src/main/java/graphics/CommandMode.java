package graphics;

import client.Client;

import messages.AnswerMsg;
import messages.CommandMsg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class CommandMode extends AbstractWindow{

    private JLabel infoLabel = new JLabel();
    private JButton returnButton = new JButton();
    private JButton sendButton = new JButton();
    private JTextArea answerEarea = new JTextArea(15, 10);
    private JTextField commandField = new JTextField("", 10);

    Client client;

    MainWindow mainWindow;

    protected CommandMode(Client cli, MainWindow mw, Rectangle rec) {
        super("Command mode");

        client = cli;
        mainWindow = mw;

        this.setBounds(rec);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.setVisible(true);

        setLocal();

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(true);
                mainWindow.setLocal();
                close();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!commandField.getText().trim().equals("")){
                    String[] userCommand = {"", ""};
                    userCommand = (commandField.getText().trim() + " ").split(" ", 2);
                    Serializable obj = null;
                    if (userCommand[0].equals("add")){

                    }
                    CommandMsg commandMsg = new CommandMsg(userCommand[0], userCommand[1], obj, client.getUser());
                    AnswerMsg answer = client.sendAndAnswer(commandMsg);
                    answerEarea.setText(answerEarea.getText() + answer.getAnswer());
                }
            }
        });

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 3));
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(super.getLangBox());
        container.add(Box.createGlue());
        Box box = Box.createVerticalBox();
        box.add(infoLabel);
        box.add(commandField);
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(sendButton);
        answerEarea.setEditable(false);
        box.add(new JScrollPane(answerEarea));
        returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(returnButton);
        container.add(box);
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());
        container.add(Box.createGlue());

        setVisible(true);
    }

    @Override
    protected void setLocal() {
        returnButton.setText(locals.getProperty("return_button", "local error"));
        sendButton.setText(locals.getProperty("send_command", "local error"));
    }
}

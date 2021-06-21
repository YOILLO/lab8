package graphics;

import client.Client;

import javax.swing.*;

public class CommandMode extends AbstractWindow{

    private JLabel infoLabel = new JLabel();
    private JButton returnButton = new JButton();
    private JButton sendButton = new JButton();
    private JTextArea answerEarea = new JTextArea(15, 10);
    private JTextField commandField = new JTextField("", 10);

    Client client;

    protected CommandMode(Client cli) {
        super("Command mode");

        client = cli;



    }

    @Override
    protected void setLocal() {

    }
}

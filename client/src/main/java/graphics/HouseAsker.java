package graphics;

import client.Client;

import data.*;
import messages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HouseAsker extends AbstractWindow{
    private JLabel houseNameLabel = new JLabel();
    private JTextField houseNameField = new JTextField();

    private JLabel houseYearLabel = new JLabel();
    private JTextField houseYearField = new JTextField();

    private JLabel numberOfFloorsLabel = new JLabel();
    private JTextField numberOfFloorsField = new JTextField();

    private JButton sendButton = new JButton();

    private Client client;

    protected HouseAsker(Client cli, String  command, String arg) {
        super("Asker");

        client = cli;

        this.setBounds(10, 10, 650, 650);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));

        houseNameField.setMaximumSize(new Dimension(800,30));
        houseYearField.setMaximumSize(new Dimension(800,30));
        numberOfFloorsField.setMaximumSize(new Dimension(800,30));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean canSand = true;

                String houseName = houseNameField.getText();
                if (houseName.trim().equals("")){
                    houseNameLabel.setForeground(Color.red);
                    houseName = null;
                    canSand = false;
                }else{
                    houseNameLabel.setForeground(Color.green);
                }

                long year = -1;
                try{
                    year = Long.parseLong(houseYearField.getText().trim());
                    houseYearLabel.setForeground(Color.green);
                }catch (NullPointerException|NumberFormatException exc){
                    canSand = false;
                    houseYearLabel.setForeground(Color.red);
                }

                long numberOfFloors = -1;
                try{
                    numberOfFloors = Long.parseLong(numberOfFloorsField.getText().trim());
                    numberOfFloorsLabel.setForeground(Color.green);
                }catch (NullPointerException|NumberFormatException exc){
                    canSand = false;
                    numberOfFloorsLabel.setForeground(Color.red);
                }
                if(canSand){
                    House house = new House(houseName, year, numberOfFloors);
                    CommandMsg commandMsg = new CommandMsg(command, arg, house, client.getUser());
                    AnswerMsg answer = client.sendAndAnswer(commandMsg);
                    close();
                }
            }
        });

        setLocal();

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 3));

        container.add(Box.createGlue());

        Box box = Box.createVerticalBox();

        box.add(houseNameLabel);
        box.add(houseNameField);

        box.add(houseYearLabel);
        box.add(houseYearField);

        box.add(numberOfFloorsLabel);
        box.add(numberOfFloorsField);

        box.add(sendButton);
        box.add(Box.createGlue());

        container.add(box);

        container.add(getLangBox());

        setVisible(true);
    }

    @Override
    protected void setLocal() {
        houseNameLabel.setText(locals.getProperty("house_name_label", "local error"));
        houseYearLabel.setText(locals.getProperty("house_year_label", "local error"));
        numberOfFloorsLabel.setText(locals.getProperty("number_of_floors_label", "local error"));
        sendButton.setText(locals.getProperty("send_button", "local error"));
    }
}

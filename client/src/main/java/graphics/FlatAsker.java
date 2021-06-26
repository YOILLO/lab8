package graphics;

import client.Client;

import data.*;
import messages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlatAsker extends AbstractWindow{

    private JLabel nameLabel = new JLabel();
    private JTextField nameField = new JTextField();

    private JLabel xLabel = new JLabel();
    private JTextField xField = new JTextField();

    private JLabel yLabel = new JLabel();
    private JTextField yField = new JTextField();

    private JLabel areaLabel = new JLabel();
    private JTextField areaField = new JTextField();

    private JLabel numberOfRoomsLabel = new JLabel();
    private JTextField numberOfRoomsField = new JTextField();

    private JLabel priceLabel = new JLabel();
    private JTextField priceField = new JTextField();

    private JLabel furnishLabel = new JLabel();
    private JTextField furnishField = new JTextField();

    private JLabel transportLabel = new JLabel();
    private JTextField transportField = new JTextField();

    private JLabel houseNameLabel = new JLabel();
    private JTextField houseNameField = new JTextField();

    private JLabel houseYearLabel = new JLabel();
    private JTextField houseYearField = new JTextField();

    private JLabel numberOfFloorsLabel = new JLabel();
    private JTextField numberOfFloorsField = new JTextField();

    private JButton sendButton = new JButton();

    private Client client;

    protected FlatAsker(Client cli, String  command, String arg) {
        super("Asker");

        client = cli;

        this.setBounds(10, 10, 650, 650);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));

        nameField.setMaximumSize(new Dimension(800,30));
        xField.setMaximumSize(new Dimension(800,30));
        yField.setMaximumSize(new Dimension(800,30));
        areaField.setMaximumSize(new Dimension(800,30));
        numberOfRoomsField.setMaximumSize(new Dimension(800,30));
        priceField.setMaximumSize(new Dimension(800,30));
        furnishField.setMaximumSize(new Dimension(800,30));
        transportField.setMaximumSize(new Dimension(800,30));
        houseNameField.setMaximumSize(new Dimension(800,30));
        houseYearField.setMaximumSize(new Dimension(800,30));
        numberOfFloorsField.setMaximumSize(new Dimension(800,30));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean canSand = true;
                String name = nameField.getText();
                if (name.trim().equals("")){
                    nameLabel.setForeground(Color.red);
                    name = null;
                    canSand = false;
                }else{
                    nameLabel.setForeground(Color.green);
                }
                float x = -1;
                try{
                    x = Float.parseFloat(xField.getText().trim());
                    xLabel.setForeground(Color.green);
                }catch(NullPointerException|NumberFormatException exc){
                    canSand = false;
                    xLabel.setForeground(Color.red);
                }

                float y = -1;
                try{
                    y = Float.parseFloat(xField.getText().trim());
                    yLabel.setForeground(Color.green);
                }catch(NullPointerException|NumberFormatException exc){
                    canSand = false;
                    yLabel.setForeground(Color.red);
                }

                int area = -1;
                try{
                    area = Integer.parseInt(areaField.getText().trim());
                    areaLabel.setForeground(Color.green);
                }catch(NullPointerException|NumberFormatException exc){
                    canSand = false;
                    areaLabel.setForeground(Color.red);
                }

                int numberOfRooms = -1;
                try{
                    numberOfRooms = Integer.parseInt(numberOfRoomsField.getText().trim());
                    numberOfRoomsLabel.setForeground(Color.green);
                }catch(NullPointerException|NumberFormatException exc){
                    canSand = false;
                    numberOfRoomsLabel.setForeground(Color.red);
                }

                float price = -1;
                try{
                    price = Float.parseFloat(priceField.getText().trim());
                    priceLabel.setForeground(Color.green);
                }catch(NullPointerException|NumberFormatException exc){
                    canSand = false;
                    priceLabel.setForeground(Color.red);
                }

                Furnish furnish = null;
                try{
                    furnish = Furnish.valueOf(furnishField.getText().trim());
                    furnishLabel.setForeground(Color.green);
                }catch (Exception exc){
                    canSand = false;
                    furnishLabel.setForeground(Color.red);
                }

                Transport transport = null;
                try{
                    transport = Transport.valueOf(transportField.getText().trim());
                    transportLabel.setForeground(Color.green);
                }catch (Exception exc){
                    canSand = false;
                    transportLabel.setForeground(Color.red);
                }

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
                    year = Long.getLong(houseYearField.getText().trim());
                    houseYearLabel.setForeground(Color.green);
                }catch (NullPointerException|NumberFormatException exc){
                    canSand = false;
                    houseYearLabel.setForeground(Color.red);
                }

                long numberOfFloors = -1;
                try{
                    numberOfFloors = Long.getLong(numberOfFloorsField.getText().trim());
                    numberOfFloorsLabel.setForeground(Color.green);
                }catch (NullPointerException|NumberFormatException exc){
                    canSand = false;
                    numberOfFloorsLabel.setForeground(Color.red);
                }
                if(canSand){
                    RowFlat rowFlat = new RowFlat(name, new Coordinates(x, y), area, numberOfRooms, price, furnish, transport,
                            new House(houseName, year, numberOfFloors));
                    CommandMsg commandMsg = new CommandMsg(command, arg, rowFlat, client.getUser());
                    AnswerMsg answer = client.sendAndAnswer(commandMsg);
                }
            }
        });

        setLocal();

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 3));

        container.add(Box.createGlue());

        Box box = Box.createVerticalBox();

        box.add(nameLabel);
        box.add(nameField);

        box.add(xLabel);
        box.add(xField);

        box.add(yLabel);
        box.add(yField);

        box.add(areaLabel);
        box.add(areaField);

        box.add(numberOfRoomsLabel);
        box.add(numberOfRoomsField);

        box.add(priceLabel);
        box.add(priceField);

        box.add(furnishLabel);
        box.add(furnishField);

        box.add(transportLabel);
        box.add(transportField);

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
        sendButton.setText(locals.getProperty("send_button", "local error"));
        nameLabel.setText(locals.getProperty("name_label", "local error"));
        xLabel.setText(locals.getProperty("x_label", "local error"));
        yLabel.setText(locals.getProperty("y_label", "local error"));
        areaLabel.setText(locals.getProperty("area_label", "local error"));
        numberOfRoomsLabel.setText(locals.getProperty("number_of_rooms", "local error"));
        priceLabel.setText(locals.getProperty("price_label", "local error"));
        furnishLabel.setText(locals.getProperty("furnish_label", "local error") + " "+ Furnish.nameList());
        transportLabel.setText(locals.getProperty("transport_label", "local error") + " "+ Transport.nameList());
        houseNameLabel.setText(locals.getProperty("house_name_label", "local error"));
        houseYearLabel.setText(locals.getProperty("house_year_label", "local error"));
        numberOfFloorsLabel.setText(locals.getProperty("number_of_floors_label", "local error"));
    }
}

package graphics;

import client.Client;

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

    protected FlatAsker(Client cli) {
        super("Asker");

        client = cli;

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

            }
        });

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
    }

    @Override
    protected void setLocal() {

    }
}

package graphics;

import client.Client;
import data.Flat;
import io.Console;
import main.Main;
import messages.CommandMsg;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TableWindow extends AbstractWindow{
    private JButton returnButtn = new JButton();
    private JButton deleteButtn = new JButton();
    private JButton addButtn = new JButton();
    private JTextField filterField = new JTextField();
    private JButton filterButton = new JButton();

    private JTable table = new JTable();

    Client client;

    MainWindow mainWindow;

    ArrayList<Flat> collection;
    ArrayList<Flat> oldCollection;

    String[] names;

    boolean isWork = true;

    boolean isChanged = true;

    TableRowSorter<TablePattern> sorter;

    protected TableWindow(Client cli, MainWindow mw, Rectangle rec) {
        super("Table window");

        client = cli;
        mainWindow = mw;

        setLocal();

        this.setBounds(rec);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.setVisible(true);

        Thread th = new Thread(()->{
            while (isWork) {
                synchronized (this) {
                    CommandMsg commandMsg = new CommandMsg("get_collection", "", null, client.getUser());
                    collection = (ArrayList<Flat>) client.sendAndAnswer(commandMsg).getObj();
                    if (!collection.equals(oldCollection) || isChanged) {
                        synchronized (this) {
                            isChanged = false;
                            TablePattern tablePattern = new TablePattern();
                            tablePattern.setTableData(collection);
                            tablePattern.setNames(names);
                            table.setModel(tablePattern);
                            sorter = new TableRowSorter<TablePattern>(tablePattern);
                            table.setRowSorter(sorter);
                            table.revalidate();

                            oldCollection = collection;
                        }
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Console.println("Errr");
                }
            }
        });

        returnButtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.setVisible(true);
                mainWindow.setLocal();
                mainWindow.setBounds(getBounds());
                close();
            }
        });

        addButtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlatAsker flatAsker = new FlatAsker(client, "add", "");
            }
        });

        deleteButtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                synchronized (this) {
                    int row = table.getSelectedRow();
                    System.out.println(table.getModel().getValueAt(row, 12));
                    String own = (String) table.getModel().getValueAt(row, 12);
                    if (own.trim().equals(client.getUser().getUsername())) {
                        int id = (int) table.getModel().getValueAt(row, 0);
                        CommandMsg commandMsg = new CommandMsg("remove_by_id", Integer.toString(id), null, client.getUser());
                        client.sendAndAnswer(commandMsg);
                    }
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = filterField.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter(text));
                        sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.parseInt(text)));
                    } catch (PatternSyntaxException | NumberFormatException pse) {
                        System.err.println("Bad regex pattern");
                    }
                }
            }
        });

        th.start();

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1));

        container.add(getLangBox());

        container.add(new JScrollPane(table));

        Box box = Box.createHorizontalBox();
        box.add(Box.createGlue());
        Box inBOx = Box.createVerticalBox();
        Box topBox = Box.createHorizontalBox();
        topBox.add(returnButtn);
        topBox.add(deleteButtn);
        topBox.add(addButtn);
        inBOx.add(topBox);
        Box downBox = Box.createHorizontalBox();
        downBox.add(filterButton);
        filterField.setMinimumSize(new Dimension(200, 35));
        filterField.setMaximumSize(new Dimension(300, 35));
        downBox.add(filterField);
        inBOx.add(downBox);
        box.add(inBOx);
        box.add(Box.createGlue());
        container.add(box);

    }

    @Override
    protected void setLocal() {
        returnButtn.setText(locals.getProperty("return_button", "local error"));

        addButtn.setText(locals.getProperty("add_button", "local error"));

        deleteButtn.setText(locals.getProperty("delete_button", "local error"));

        filterButton.setText(locals.getProperty("filter_button", "local error"));

        names = new String[]{
            locals.getProperty("id", "error"),
            locals.getProperty("name", "error"),
            locals.getProperty("coordinates", "error"),
            locals.getProperty("creation_date", "error"),
            locals.getProperty("area", "error"),
            locals.getProperty("number_of_rooms", "error"),
            locals.getProperty("price", "error"),
            locals.getProperty("furnish", "error"),
            locals.getProperty("transport", "error"),
            locals.getProperty("house_name", "error"),
            locals.getProperty("house_year", "error"),
            locals.getProperty("house_number_of_floors", "error"),
            locals.getProperty("owner", "error")
        };

        isChanged = true;

    }
}

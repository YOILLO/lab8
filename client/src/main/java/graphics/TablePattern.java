package graphics;

import data.Flat;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class TablePattern extends AbstractTableModel {
    ArrayList<Flat> tableData;
    String[] names;

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public String getColumnName(int col) {
        return names[col];
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Flat flat = tableData.get(rowIndex);
        switch (columnIndex){
            case 0:
                return flat.getId();
            case 1:
                return flat.getName();
            case 2:
                return flat.getCoordinates().toString();
            case 3:
                return flat.getCreationDate();
            case 4:
                return flat.getArea();
            case 5:
                return flat.getNumberOfRooms();
            case 6:
                return flat.getPrice();
            case 7:
                return flat.getFurnish().toString();
            case 8:
                return flat.getTransport().toString();
            case 9:
                return flat.getHouse().getName();
            case 10:
                return flat.getHouse().getYear();
            case 11:
                return flat.getHouse().getNumberOfFloors();
            case 12:
                return flat.getUser().getUsername();
        }
        return null;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return LocalDate.class;
            case 4:
                return Integer.class;
            case 5:
                return Integer.class;
            case 6:
                return Float.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
            case 10:
                return Long.class;
            case 11:
                return Long.class;
            case 12:
                return String.class;
        }
        return Object.class;
    }

    public void setTableData(ArrayList<Flat> tableData) {
        this.tableData = tableData;
    }
}

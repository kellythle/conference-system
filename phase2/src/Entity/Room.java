package Entity;

import java.util.ArrayList;

public class Room {
    private int number;
    private int capacity;
    private ArrayList<String> technologies;
    private boolean tables;

    public Room(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setTables(boolean tables) {
        this.tables = tables;
    }

    public boolean getTable() {
        return tables;
    }

    public void addTechnology(String technology) {
        technologies.add(technology);
    }

    public boolean containsTechnology(String technology) {
        return technologies.contains(technology);
    }
}

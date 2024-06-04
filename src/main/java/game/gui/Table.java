package game.gui;

import javafx.beans.property.SimpleStringProperty;

public class Table {

    private final SimpleStringProperty column1;
    private final SimpleStringProperty column2;
    private final SimpleStringProperty column3;
    private final SimpleStringProperty column4;
    private final SimpleStringProperty column5;
    private final SimpleStringProperty column6;


    public Table(String column1, String column2, String column3, String column4, String column5, String column6) {
        this.column1 = new SimpleStringProperty(column1);
        this.column2 = new SimpleStringProperty(column2);
        this.column3 = new SimpleStringProperty(column3);
        this.column4 = new SimpleStringProperty(column4);
        this.column5 = new SimpleStringProperty(column5);
        this.column6 = new SimpleStringProperty(column6);

    }

    public String getColumn1() {
        return column1.get();
    }

    public void setColumn1(String value) {
        column1.set(value);
    }

    public String getColumn2() {
        return column2.get();
    }

    public void setColumn2(String value) {
        column2.set(value);
    }

    public String getColumn3() {
        return column3.get();
    }

    public void setColumn3(String value) {
        column3.set(value);
    }

    public String getColumn4() {
        return column4.get();
    }

    public void setColumn4(String value) {
        column4.set(value);
    }

    public String getColumn5() {
        return column5.get();
    }

    public void setColumn5(String value) {
        column5.set(value);
    }

    public void setColumn6(String value) {
        column6.set(value);
    }

    public String getColumn6() {
        return column6.get();
    }
}

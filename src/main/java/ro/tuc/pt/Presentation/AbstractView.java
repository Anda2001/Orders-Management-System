package ro.tuc.pt.Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 *Abstract frame that receives as parameter the type of object whose parameters must be displayed in a table.
 * @param <T> the type of the object.
 */
public abstract class AbstractView<T> extends JFrame {
    private final Class<T> type;

    private final JTable objectsTable;
    private final JButton editButton;
    private final JButton insertButton;
    private final JButton deleteButton;

    Font myFont = new Font("Times New Roman", Font.PLAIN, 22);

    @SuppressWarnings("unchecked")
    public AbstractView(String title) {
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        setTitle(title);
        setSize(800, 600);
        setLocation(600, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBounds(20, 20, 600, 500);
        panel.setBackground(Color.magenta);
        panel.setForeground(Color.pink);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.pink);

        objectsTable = new JTable();
        objectsTable.setBounds(20, 20, 600, 500);
        objectsTable.setBackground(Color.white);
        objectsTable.setForeground(Color.black);
        objectsTable.setBorder(new LineBorder(Color.gray));
        objectsTable.setRowHeight(20);
        objectsTable.setFont(new Font( "Monospaced",Font.PLAIN,15));
        objectsTable.getTableHeader().setBackground(Color.magenta);
        objectsTable.getTableHeader().setForeground(Color.black);



        editButton = new JButton("EDIT");
        editButton.setFont(myFont);
        editButton.setBounds(650, 310, 100, 40);
        editButton.setBorder(new LineBorder(Color.gray, 5));
        editButton.setBackground(Color.magenta);
        editButton.setVerticalAlignment(JButton.CENTER);

        deleteButton = new JButton("DELETE");
        deleteButton.setFont(myFont);
        deleteButton.setBounds(650, 230, 100, 40);
        deleteButton.setBorder(new LineBorder(Color.gray, 5));
        deleteButton.setBackground(Color.magenta);
        deleteButton.setVerticalAlignment(JButton.CENTER);

        insertButton = new JButton("INSERT");
        insertButton.setFont(myFont);
        insertButton.setBounds(650, 150, 100, 40);
        insertButton.setBorder(new LineBorder(Color.gray, 5));
        insertButton.setBackground(Color.magenta);
        insertButton.setVerticalAlignment(JButton.CENTER);

        JScrollPane scrollPane = new JScrollPane(objectsTable);
        panel.add(scrollPane);
        container.add(panel);
        container.add(editButton);
        container.add(deleteButton);
        container.add(insertButton);

        setVisible(true);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void deleteListener(ActionListener listenForDelete) {
        deleteButton.addActionListener(listenForDelete);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


    /**
     *The method uses reflection to generate an array of strings that represent the names of the columns
     * and a matrix of objects that represent the values that must be inserted in the table.
     * @param objects a list of type T objects that must be inserted in the table.
     */
    public void setContentsTable(List<T> objects) {
        int nbFields = 0;
        for(Field ignored : type.getDeclaredFields()) {
            nbFields++;
        }
        String[] fields = new String[nbFields];
        for(int i=0;i<nbFields;i++) {
            fields[i] = type.getDeclaredFields()[i].getName();
        }
        Object[][] data = new Object[objects.size()][nbFields];
        for(int row =0; row<objects.size();row++) {
            for(int column=0;column<nbFields; column++) {
                try {
                    Field field= type.getDeclaredField(fields[column]);
                    field.setAccessible(true);
                    String value = field.get(objects.get(row)).toString();
                    data[row][column] = value;
                } catch (IllegalAccessException e) {
                    System.out.println("uwu");
                } catch (NoSuchFieldException e) {
                    System.out.println("owo");
                }
            }
        }

        DefaultTableModel tableModel = (DefaultTableModel) objectsTable.getModel();
        tableModel.setDataVector(data, fields);
        objectsTable.setModel(tableModel);
        objectsTable.getColumnModel().getColumn(0).setMinWidth(0);
        objectsTable.getColumnModel().getColumn(0).setMaxWidth(0);
    }



    public String[] getSelectedRow(){
        int columns = objectsTable.getColumnCount();
        int row = objectsTable.getSelectedRow();
        String[] value = new String[columns];
        for(int i=0;i<columns; i++) {
            value[i] = objectsTable.getModel().getValueAt(row, i).toString();
        }

        return value;
    }


}

package Client.Windows;

import Client.Client;
import Server.Classes.Extradition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ViewActualReaderExtraditionWindow extends JFrame{
    private static JLabel data;
    private static JTable tableDB;
    ArrayList<Extradition> extraditions = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return true;
        };
    };

    public ViewActualReaderExtraditionWindow(){
        super("Актуальные книговыдачи");
        setSize(800,600);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Актуальные книговыдачи");
        data.setBounds(0,0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        tmodel.addColumn("ID книговыдачи");
        tmodel.addColumn("Фамилия читателя");
        tmodel.addColumn("Имя читателя");
        tmodel.addColumn("Фамилия автора");
        tmodel.addColumn("Имя автора");
        tmodel.addColumn("Название");
        tmodel.addColumn("Дата выдачи");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(50, 60, 700, 400);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(50, 60, 700, 400);

        setLayout(null);

        add(scrollPane);
        add(data);
        try {
            Client.out.writeObject("GetActualReaderExtraditions");
            extraditions = (ArrayList<Extradition>) Client.in.readObject();
        } catch (IOException exs) {
            exs.printStackTrace();
        } catch (ClassNotFoundException exs) {
            exs.printStackTrace();
        }
        tmodel.setRowCount(0);
        for (Extradition a: extraditions) {
            tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurnameReader(), a.getNameReader(), a.getSurnameAuthor(), a.getNameAuthor(), a.getTitle(), a.getDate() });
        }
    }
}

package Client.Windows;

import Client.Client;
import Server.Classes.Extradition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ViewCountExtraditionBooksWindow extends JFrame {
    private static JLabel data;
    private static JTable tableDB;
    private static JButton diagram;

    ArrayList<Extradition> extraditions = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        ;
    };

    public ViewCountExtraditionBooksWindow() {
        super("Популярность книг");
        setSize(800, 600);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Популярность книг");
        data.setBounds(0, 0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        diagram = new JButton("Посмотреть диаграмму");
        diagram.setBounds(270, 500, 250, 30);
        diagram.setBackground(new Color(0, 128, 128));
        diagram.setForeground(Color.WHITE);
        diagram.setFont(new Font("Times New Roman", Font.BOLD, 17));

        tmodel.addColumn("Фамилия автора");
        tmodel.addColumn("Имя автора");
        tmodel.addColumn("Название книги");
        tmodel.addColumn("Общее количество выдач книги");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(60, 60, 600, 400);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(60, 60, 600, 400);

        setLayout(null);

        add(scrollPane);
        add(data);
        add(diagram);

        try {
            Client.out.writeObject("GetCountBooks");
            extraditions = (ArrayList<Extradition>) Client.in.readObject();
        } catch (IOException exs) {
            exs.printStackTrace();
        } catch (ClassNotFoundException exs) {
            exs.printStackTrace();
        }
        tmodel.setRowCount(0);
        for (Extradition a : extraditions) {
            tmodel.addRow(new Object[]{a.getSurnameAuthor(), a.getNameAuthor(), a.getTitle(), Integer.toString(a.getCount())});
        }
        diagram.addActionListener(new ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == diagram) {
                DiagramWindow diagramWindow = new DiagramWindow(tableDB);
            }
        }
    }
}

package Client.Windows;

import Client.Client;
import Server.Classes.PublishingHouse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class ViewPublishingHouseWindow extends JFrame{
    private static JLabel data;
    private static JTable tableDB;
    private static JButton delete;
    private static JButton add;
    private static JButton update;

    ArrayList<PublishingHouse> publishingHouses = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return true;
        };
    };

    public ViewPublishingHouseWindow(){
        super("Список издательств");
        setSize(800,600);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Список издательств");
        data.setBounds(0,0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        delete = new JButton("Удалить");
        delete.setBounds(670, 160, 100, 30);
        delete.setBackground(new Color(0, 128, 128));
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("Times New Roman", Font.BOLD, 17));
        delete.setEnabled(false);

        add = new JButton("Добавить");
        add.setBounds(670, 210, 120, 30);
        add.setBackground(new Color(0, 128, 128));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Times New Roman", Font.BOLD, 17));

        update = new JButton("Обновить список");
        update.setBounds(70, 430, 200, 30);
        update.setBackground(new Color(0, 128, 128));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Times New Roman", Font.BOLD, 17));

        tmodel.addColumn("ID издательства");
        tmodel.addColumn("Название");
        tmodel.addColumn("Город");
        tmodel.addColumn("Адрес");
        tmodel.addColumn("Телефон");
        tmodel.addColumn("e-mail");
        tmodel.addColumn("fax");
        tmodel.addColumn("website");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(60, 60, 600, 350);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(60, 60, 600, 350);

        setLayout(null);

        add(scrollPane);
        add(data);
        add(delete);
        add(add);
        add(update);

        add.addActionListener(new ViewPublishingHouseWindow.ButtonActionListener());
        delete.addActionListener(new ViewPublishingHouseWindow.ButtonActionListener());
        update.addActionListener(new ViewPublishingHouseWindow.ButtonActionListener());
        tableDB.addMouseListener(new ViewPublishingHouseWindow.MouseLis());
    }

    public class MouseLis implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(tableDB.isCursorSet()){
                delete.setEnabled(false);
            }
            else{
                System.out.println(tableDB.getSelectedRow());
                delete.setEnabled(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == add){
                AddPublishingHouseWindow addPublishingHouseWindow = new AddPublishingHouseWindow();
                addPublishingHouseWindow.setVisible(true);
                addPublishingHouseWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == update){
                try {
                    Client.out.writeObject("GetPublH");
                    publishingHouses = (ArrayList<PublishingHouse>) Client.in.readObject();
                } catch (IOException exs) {
                    exs.printStackTrace();
                } catch (ClassNotFoundException exs) {
                    exs.printStackTrace();
                }
                tmodel.setRowCount(0);
                for (PublishingHouse a: publishingHouses) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getTitle(), a.getCity(), a.getAdress(), a.getPhone(), a.getE_mail(), a.getFax(), a.getWebsite() });
                }
            }
            else if (e.getSource() == delete){
                delete.setEnabled(false);
                try {
                    String ID = tmodel.getValueAt(tableDB.getSelectedRow(), 0).toString();
                    String clientMessage = "DeletePublH,,," + ID;
                    try {
                        Client.out.writeObject(clientMessage);
                    } catch (IOException exs) {
                        exs.printStackTrace();
                    }
                }
                catch (Exception exception) {
                }
                delete.doClick();
            }
        }
    }
}

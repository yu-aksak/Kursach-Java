package Client.Windows;

import Client.Client;
import Server.Classes.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class ViewEmployeeWindow extends JFrame{
    private static JLabel data;
    private static JTable tableDB;

    private static JButton delete;
    private static JButton add;
    private static JButton update;

    ArrayList<Employee> employees = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return true;
        };
    };

    public ViewEmployeeWindow(){
        super("Данные сотрудников");
        setSize(800,600);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Список сотрудников");
        data.setBounds(0,0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        delete = new JButton("Удалить");
        delete.setBounds(10, 470, 100, 30);
        delete.setBackground(new Color(0, 128, 128));
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("Times New Roman", Font.BOLD, 17));
        delete.setEnabled(false);

        add = new JButton("Добавить");
        add.setBounds(220, 470, 120, 30);
        add.setBackground(new Color(0, 128, 128));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Times New Roman", Font.BOLD, 17));

        update = new JButton("Обновить список");
        update.setBounds(380, 470, 200, 30);
        update.setBackground(new Color(0, 128, 128));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Times New Roman", Font.BOLD, 17));

        tmodel.addColumn("ID");
        tmodel.addColumn("Фамилия");
        tmodel.addColumn("Имя");
        tmodel.addColumn("Отчество");
        tmodel.addColumn("Дата рождения");
        tmodel.addColumn("Адресс");
        tmodel.addColumn("Телефон");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(10, 60, 700, 400);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(10, 60, 700, 400);

        setLayout(null);

        add(scrollPane);
        add(data);
        add(delete);
        add(add);
        add(update);

        delete.addActionListener(new ViewEmployeeWindow.ButtonActionListener());
        add.addActionListener(new ViewEmployeeWindow.ButtonActionListener());
        update.addActionListener(new ViewEmployeeWindow.ButtonActionListener());

        tableDB.addMouseListener(new ViewEmployeeWindow.MouseLis());
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
            if(e.getSource() == add ){
                AddUserWindow addUserWindow = new AddUserWindow();
                addUserWindow.setVisible(true);
                addUserWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == update){
                try {
                    Client.out.writeObject("GetEmployees");
                    employees = (ArrayList<Employee>) Client.in.readObject();
                } catch (IOException exs) {
                    exs.printStackTrace();
                } catch (ClassNotFoundException exs) {
                    exs.printStackTrace();
                }
                tmodel.setRowCount(0);
                for (Employee a : employees) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getMidname(), a.getBirthdate(), a.getAdress(), a.getPhone()});
                }
            }
            else if (e.getSource() == delete){
                delete.setEnabled(false);
                try {
                    String ID = tmodel.getValueAt(tableDB.getSelectedRow(), 0).toString();
                    String clientMessage = "DeleteEmployee,,," + ID;
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

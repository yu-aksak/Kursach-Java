package Client.Windows;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewPersonalDataWindow <Enemy> extends JFrame{
    private static JLabel data;
    private static JLabel identity;
    private static JLabel identityText;
    private static JLabel surname;
    private static JLabel name;
    private static JLabel midname;
    private static JLabel birthdate;
    private static JLabel adress;
    private static JLabel phone;
    private static JTextArea surnameText;
    private static JTextArea nameText;
    private static JTextArea midnameText;
    private static JTextArea birthdateText;
    private static JTextArea adressText;
    private static JTextArea phoneText;
    private static JButton update;
    private String message;
    private int level = 0;

    public ViewPersonalDataWindow(){
        super("Просмотр/редактирование данных");
        setSize(600,500);
        getContentPane().setBackground(new Color(102, 205, 170));

        try {
            Client.out.writeObject("GetData");
            message = (String) Client.in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] messageParts = message.split(",,,");
        level = Integer.parseInt(messageParts[7]);
        data = new JLabel("Просмотр/редактирование данных");
        data.setBounds(0,0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        update = new JButton("Обновить");
        update.setBounds(200, 400, 150, 30);
        update.setBackground(new Color(0, 128, 128));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Times New Roman", Font.BOLD, 17));

        identity = new JLabel("Идентификатор:");
        identity.setBounds(20,60, 300, 30);
        identity.setFont(new Font("Times New Roman", Font.BOLD, 18));

        identityText = new JLabel(messageParts[0]);
        identityText.setBounds(170,60, 300, 30);
        identityText.setFont(new Font("Times New Roman", Font.BOLD, 18));

        surname = new JLabel("Фамилия");
        surname.setBounds(20,100, 140, 30);
        surname.setFont(new Font("Times New Roman", Font.BOLD, 18));

        name = new JLabel("Имя");
        name.setBounds(20,140, 140, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));

        midname = new JLabel("Отчество");
        midname.setBounds(20,180, 140, 30);
        midname.setFont(new Font("Times New Roman", Font.BOLD, 18));

        birthdate = new JLabel("Дата рождения");
        birthdate.setBounds(20,220, 140, 30);
        birthdate.setFont(new Font("Times New Roman", Font.BOLD, 18));

        adress = new JLabel("Адрес");
        adress.setBounds(20,260, 140, 30);
        adress.setFont(new Font("Times New Roman", Font.BOLD, 18));

        phone = new JLabel("Телефон");
        phone.setBounds(20,300, 140, 30);
        phone.setFont(new Font("Times New Roman", Font.BOLD, 18));

        surnameText = new JTextArea(messageParts[1]);
        surnameText.setBounds(170,100,200,30);
        surnameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        surnameText.setBackground(new Color(199, 252, 236));

        nameText = new JTextArea(messageParts[2]);
        nameText.setBounds(170,140,200,30);
        nameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameText.setBackground(new Color(199, 252, 236));

        midnameText = new JTextArea(messageParts[3]);
        midnameText.setBounds(170,180,200,30);
        midnameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        midnameText.setBackground(new Color(199, 252, 236));

        birthdateText = new JTextArea(messageParts[4]);
        birthdateText.setBounds(170,220,200,30);
        birthdateText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        birthdateText.setBackground(new Color(199, 252, 236));

        adressText = new JTextArea(messageParts[5]);
        adressText.setBounds(170,260,200,30);
        adressText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adressText.setBackground(new Color(199, 252, 236));

        phoneText = new JTextArea(messageParts[6]);
        phoneText.setBounds(170,300,200,30);
        phoneText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(update);
        add(identity);
        add(identityText);
        add(surname);
        add(surnameText);
        add(name);
        add(nameText);
        add(midname);
        add(midnameText);
        add(birthdate);
        add(birthdateText);
        add(adress);
        add(adressText);
        add(phone);
        add(phoneText);

        update.addActionListener(new ViewPersonalDataWindow.ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == update){

                String clientMessage = "UpdateUser,,," + identityText.getText() + ",,," + surnameText.getText() + ",,," +
                        nameText.getText() + ",,," + midnameText.getText() + ",,," + birthdateText.getText() + ",,," +
                        adressText.getText() + ",,," +phoneText.getText()+ ",,," + level;
                try {
                    Client.out.writeObject(clientMessage);
                    message = (String) Client.in.readObject();
                } catch (IOException exs) {
                    exs.printStackTrace();
                } catch (ClassNotFoundException exs) {
                    exs.printStackTrace();
                }
                if (message.equals("success")) {
                    ToMenu();
                }
            }
        }

        private void ToMenu() {
            if (level == 1){
                EmployeeWindow employeeWindow = new EmployeeWindow();
                employeeWindow.setVisible(true);
                employeeWindow.setLocationRelativeTo(null);
                dispose();
            }
            else if (level == 2){
                ReaderWindow readerWindow = new ReaderWindow();
                readerWindow.setVisible(true);
                readerWindow.setLocationRelativeTo(null);
                dispose();
            }
        }
    }



}

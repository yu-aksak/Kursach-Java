package Client.Windows;

import Client.CheckValid;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddUserWindow extends JFrame{
    private static JLabel data;
    private static JLabel surname;
    private static JLabel name;
    private static JLabel midname;
    private static JLabel birthdate;
    private static JComboBox day;
    private static JComboBox month;
    private static JComboBox year;
    private static JLabel adress;
    private static JLabel phone;
    private static JTextArea surnameText;
    private static JTextArea nameText;
    private static JTextArea midnameText;
    private static JTextArea adressText;
    private static JTextArea phoneText;
    private static JRadioButton employee;
    private static JRadioButton reader;
    private static ButtonGroup buttonGroup;
    private static JButton add;
    private static JLabel cautionLabel;
    private String message;


    static String[] box1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    static String[] box2 = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    static String[] box3 = {"1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};

    public AddUserWindow(){
        super("Добавление пользователя");
        setSize(600,500);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Добавление пользователя");
        data.setBounds(0,0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 450, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        add = new JButton("Добавить");
        add.setBounds(200, 400, 150, 30);
        add.setBackground(new Color(0, 128, 128));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Times New Roman", Font.BOLD, 17));

        surname = new JLabel("Фамилия");
        surname.setBounds(20,60, 140, 30);
        surname.setFont(new Font("Times New Roman", Font.BOLD, 18));

        name = new JLabel("Имя");
        name.setBounds(20,100, 140, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));

        midname = new JLabel("Отчество");
        midname.setBounds(20,140, 140, 30);
        midname.setFont(new Font("Times New Roman", Font.BOLD, 18));

        birthdate = new JLabel("Дата рождения");
        birthdate.setBounds(20,180, 140, 30);
        birthdate.setFont(new Font("Times New Roman", Font.BOLD, 18));

        day = new JComboBox(box1);
        month = new JComboBox(box2);
        year = new JComboBox(box3);

        day.setBounds(170,180,50,30);
        day.setBackground(new Color(199, 252, 236));
        month.setBounds(230,180,70,30);
        month.setBackground(new Color(199, 252, 236));
        year.setBounds(310,180,60,30);
        year.setBackground(new Color(199, 252, 236));


        adress = new JLabel("Адрес");
        adress.setBounds(20,220, 140, 30);
        adress.setFont(new Font("Times New Roman", Font.BOLD, 18));

        phone = new JLabel("Телефон");
        phone.setBounds(20,260, 140, 30);
        phone.setFont(new Font("Times New Roman", Font.BOLD, 18));


        employee = new JRadioButton("Сотрудник");
        employee.setBounds(100,300,100,30);
        employee.setFont(new Font("Times New Roman", Font.BOLD, 14));
        employee.setOpaque(false);

        reader = new JRadioButton("Читатель");
        reader.setBounds(300,300,100,30);
        reader.setFont(new Font("Times New Roman", Font.BOLD, 14));
        reader.setOpaque(false);

        buttonGroup = new ButtonGroup();

        surnameText = new JTextArea();
        surnameText.setBounds(170,60,200,30);
        surnameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        surnameText.setBackground(new Color(199, 252, 236));

        nameText = new JTextArea();
        nameText.setBounds(170,100,200,30);
        nameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameText.setBackground(new Color(199, 252, 236));

        midnameText = new JTextArea();
        midnameText.setBounds(170,140,200,30);
        midnameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        midnameText.setBackground(new Color(199, 252, 236));

        adressText = new JTextArea();
        adressText.setBounds(170,220,200,30);
        adressText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adressText.setBackground(new Color(199, 252, 236));

        phoneText = new JTextArea();
        phoneText.setBounds(170,260,200,30);
        phoneText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(add);
        add(cautionLabel);
        add(surname);
        add(surnameText);
        add(name);
        add(nameText);
        add(midname);
        add(midnameText);
        add(birthdate);
        buttonGroup.add(employee);
        buttonGroup.add(reader);
        add(employee);
        add(reader);
        add(day);
        add(month);
        add(year);
        add(adress);
        add(adressText);
        add(phone);
        add(phoneText);

        add.addActionListener(new AddUserWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == add){
                String surname = surnameText.getText();
                String name = nameText.getText();
                String midname = midnameText.getText();
                String adress = adressText.getText();
                String phone = phoneText.getText();
                String date = year.getSelectedItem() + "-" + month.getSelectedItem() + "-" + day.getSelectedItem();

                if(surname.equals("") || name.equals("") || midname.equals("") || phone.equals("") || adress.equals("")){
                    cautionLabel.setText("Неверный формат ввода");
                }
                else {
                    if (!CheckValid.checkString(surname)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в названии.");
                        return;
                    }
                    if (!CheckValid.checkString(name)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в жанре.");
                        return;
                    }
                    if (!CheckValid.checkString(midname)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в жанре.");
                        return;
                    }

                    String clientMessage = "";
                    if(employee.isSelected())
                        clientMessage = "AddEmployee,,,";
                    else if(reader.isSelected())
                        clientMessage = "AddReader,,,";
                    else {
                        cautionLabel.setText("Выберите сотрудник или читатель");
                        return;
                    }
                    clientMessage += surname + ",,," + name + ",,," + midname + ",,," + date + ",,," + adress + ",,," + phone;
                    try {
                        Client.out.writeObject(clientMessage);
                        message = (String) Client.in.readObject();
                    } catch (IOException exs) {
                        exs.printStackTrace();
                    } catch (ClassNotFoundException exs) {
                        exs.printStackTrace();
                    }

                    if (message.equals("success")){
                        dispose();
                    }
                    else
                        cautionLabel.setText("Такое издательство уже есть");
                }
            }
        }
    }
}

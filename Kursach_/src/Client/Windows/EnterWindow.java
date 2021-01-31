package Client.Windows;

import Client.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class EnterWindow extends JFrame {
    private static JLabel data;
    private static JRadioButton employee;
    private static JRadioButton reader;
    private static JRadioButton admin;
    private static ButtonGroup buttonGroup;
    private static JLabel log;
    private static JLabel pass;
    private static JTextArea login;
    private static JPasswordField password;
    private static JButton enter;
    private static JButton registration;
    private static JLabel cautionLabel;

    public String message;

    public EnterWindow() {
        super("Авторизация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Введите данные");
        data.setBounds(0, 0, 500, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        admin = new JRadioButton("Администратор");
        admin.setBounds(10, 55, 130, 30);
        admin.setFont(new Font("Times New Roman", Font.BOLD, 14));
        admin.setOpaque(false);

        employee = new JRadioButton("Сотрудник");
        employee.setBounds(170, 55, 100, 30);
        employee.setFont(new Font("Times New Roman", Font.BOLD, 14));
        employee.setOpaque(false);

        reader = new JRadioButton("Читатель");
        reader.setBounds(350, 55, 100, 30);
        reader.setFont(new Font("Times New Roman", Font.BOLD, 14));
        reader.setOpaque(false);

        buttonGroup = new ButtonGroup();

        log = new JLabel("Логин");
        log.setBounds(70, 90, 70, 50);
        log.setFont(new Font("Times New Roman", Font.BOLD, 20));

        pass = new JLabel("Пароль");
        pass.setBounds(70, 140, 90, 50);
        pass.setFont(new Font("Times New Roman", Font.BOLD, 20));

        login = new JTextArea();
        login.setBounds(150, 100, 200, 30);
        login.setFont(new Font("Times New Roman", Font.BOLD, 20));
        login.setBackground(new Color(199, 252, 236));

        password = new JPasswordField(8);
        password.setEchoChar('*');
        password.setBounds(150, 150, 200, 30);
        password.setBackground(new Color(199, 252, 236));
        password.setFont(new Font("Times New Roman", Font.BOLD, 20));

        enter = new JButton("Вход");
        enter.setBounds(210, 220, 90, 30);
        enter.setBackground(new Color(0, 128, 128));
        enter.setForeground(Color.WHITE);
        enter.setFont(new Font("Times New Roman", Font.BOLD, 17));

        registration = new JButton("Регистрация");
        registration.setBounds(180, 270, 150, 30);
        registration.setBackground(new Color(0, 128, 128));
        registration.setForeground(Color.WHITE);
        registration.setFont(new Font("Times New Roman", Font.BOLD, 17));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 300, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        setLayout(null);

        add(data);
        buttonGroup.add(employee);
        buttonGroup.add(reader);
        buttonGroup.add(admin);
        add(employee);
        add(reader);
        add(admin);
        add(log);
        add(pass);
        add(login);
        add(password);
        add(enter);
        add(registration);
        add(cautionLabel);

        enter.addActionListener(new ButtonActionListener());
        registration.addActionListener(new ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registration) {
                RegistrationWindow registrationWindow = new RegistrationWindow();
                registrationWindow.setVisible(true);
                registrationWindow.setLocationRelativeTo(null);
            } else if (e.getSource() == enter) {
                String loginText = login.getText();
                String passwordText = String.valueOf(password.getPassword());


                if (loginText.equals("") || passwordText.equals("")) {
                    cautionLabel.setText("Неверный формат ввода");
                } else {
                    if (!CheckValid.checkString(passwordText)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в пароле.");
                        return;
                    }
                    if (!CheckValid.checkString(loginText)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в логине.");
                        return;
                    }


                    String clientMessage = "CheckLogin,,," + loginText + ",,," + passwordText;
                    if (admin.isSelected()) {
                        try {
                            clientMessage += ",,,0";
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exs) {
                            exs.printStackTrace();
                        } catch (ClassNotFoundException exs) {
                            exs.printStackTrace();
                        }

                        if (message.equals("success")) {
                            AdminWindow adminWindow = new AdminWindow();
                            adminWindow.setVisible(true);
                            adminWindow.setLocationRelativeTo(null);
                            dispose();
                        } else if (message.equals("fail"))
                            cautionLabel.setText("Такого пользователя не существует!");
                    } else if (reader.isSelected()) {
                        clientMessage += ",,,2";
                        try {
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exs) {
                            exs.printStackTrace();
                        } catch (ClassNotFoundException exs) {
                            exs.printStackTrace();
                        }

                        if (message.equals("success")) {
                            ReaderWindow readerWindow = new ReaderWindow();
                            readerWindow.setVisible(true);
                            readerWindow.setLocationRelativeTo(null);
                            dispose();
                        } else if (message.equals("fail"))
                            cautionLabel.setText("Проверьте введенные данные!");
                    } else if (employee.isSelected()) {
                        clientMessage += ",,,1";
                        try {
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exs) {
                            exs.printStackTrace();
                        } catch (ClassNotFoundException exs) {
                            exs.printStackTrace();
                        }
                        if (message.equals("success")) {
                            EmployeeWindow employeeWindow = new EmployeeWindow();
                            employeeWindow.setVisible(true);
                            employeeWindow.setLocationRelativeTo(null);
                            dispose();
                        } else if (message.equals("fail"))
                            cautionLabel.setText("Такого пользователя не существует!");

                    } else
                        cautionLabel.setText("Выберите уровень доступа!");
                }
            }
        }
    }
}
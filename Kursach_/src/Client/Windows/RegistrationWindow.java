package Client.Windows;

import Client.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegistrationWindow extends JFrame {
    private static JLabel data;
    private static JRadioButton employee;
    private static JRadioButton reader;
    private static ButtonGroup buttonGroup;
    private static JLabel log;
    private static JLabel pass;
    private static JLabel id;
    private static JTextArea identity;
    private static JTextArea login;
    private static JPasswordField password;
    private static JButton registration;
    private static JLabel cautionLabel;

    public String message;

    public RegistrationWindow() {
        super("Регистрация");
        setSize(500, 400);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Введите данные");
        data.setBounds(0, 0, 500, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        employee = new JRadioButton("Сотрудник");
        employee.setBounds(100, 55, 100, 30);
        employee.setFont(new Font("Times New Roman", Font.BOLD, 14));
        employee.setOpaque(false);

        reader = new JRadioButton("Читатель");
        reader.setBounds(270, 55, 100, 30);
        reader.setFont(new Font("Times New Roman", Font.BOLD, 14));
        reader.setOpaque(false);

        buttonGroup = new ButtonGroup();

        id = new JLabel("Идентификатор");
        id.setBounds(20, 100, 140, 30);
        id.setFont(new Font("Times New Roman", Font.BOLD, 18));

        log = new JLabel("Логин");
        log.setBounds(20, 140, 70, 30);
        log.setFont(new Font("Times New Roman", Font.BOLD, 18));

        pass = new JLabel("Пароль");
        pass.setBounds(20, 180, 70, 30);
        pass.setFont(new Font("Times New Roman", Font.BOLD, 18));

        identity = new JTextArea();
        identity.setBounds(170, 100, 200, 30);
        identity.setFont(new Font("Times New Roman", Font.BOLD, 20));
        identity.setBackground(new Color(199, 252, 236));

        login = new JTextArea();
        login.setBounds(170, 140, 200, 30);
        login.setFont(new Font("Times New Roman", Font.BOLD, 20));
        login.setBackground(new Color(199, 252, 236));

        password = new JPasswordField();
        password.setEchoChar('*');
        password.setBounds(170, 180, 200, 30);
        password.setBackground(new Color(199, 252, 236));
        password.setFont(new Font("Times New Roman", Font.BOLD, 20));

        registration = new JButton("Регистрация");
        registration.setBounds(190, 270, 150, 30);
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
        add(employee);
        add(reader);
        add(id);
        add(log);
        add(pass);
        add(identity);
        add(login);
        add(password);
        add(registration);
        add(cautionLabel);

        registration.addActionListener(new ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registration) {
                int id;
                try {
                    id = Integer.parseInt(identity.getText());
                } catch (Exception exception) {
                    cautionLabel.setText("Идентификатор должен быть целым");
                    return;
                }

                String log = login.getText();
                String pass = String.valueOf(password.getPassword());

                if (log.equals("") || pass.equals("")) {
                    cautionLabel.setText("Неверный формат ввода");
                } else {
                    if (!CheckValid.checkString(pass)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в пароле.");
                        return;
                    }
                    if (!CheckValid.checkString(log)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в логине.");
                        return;
                    }
                    int level = 0;
                    String clientMessage = "CheckUserInDB,,," + id;
                    if (reader.isSelected()) {
                        try {
                            clientMessage += ",,,2";
                            level = 2;
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exs) {
                            exs.printStackTrace();
                        } catch (ClassNotFoundException exs) {
                            exs.printStackTrace();
                        }
                    } else if (employee.isSelected()) {
                        try {
                            clientMessage += ",,,1";
                            level = 1;
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exs) {
                            exs.printStackTrace();
                        } catch (ClassNotFoundException exs) {
                            exs.printStackTrace();
                        }
                    } else
                        cautionLabel.setText("Выберите уровень доступа!");

                    if(message.equals("success")){
                        clientMessage = "AddUser,,," + id + ",,," + log  + ",,," + pass + ",,," + level;
                        try {
                            Client.out.writeObject(clientMessage);
                            message = (String) Client.in.readObject();
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                    }
                    else if (message.equals("fail")){
                        cautionLabel.setText("Проверьте выбранный уровень доступа и идентификатор");
                    }
                    else if (message.equals("fail1")){
                        cautionLabel.setText("Этот идентификатор уже зарегистрирован");
                    }
                    if (message.equals("success"))
                        dispose();
                }
            }
        }
    }
}

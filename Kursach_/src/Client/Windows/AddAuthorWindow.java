package Client.Windows;

import Client.CheckValid;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddAuthorWindow extends JFrame {
    private static JLabel data;
    private static JLabel surname;
    private static JLabel name;
    private static JLabel year;
    private static JTextArea surnameText;
    private static JTextArea nameText;
    private static JTextArea yearText;
    private static JButton add;
    private static JLabel cautionLabel;
    private String message;

    public AddAuthorWindow(){
        super("Добавление автора");
        setSize(600,400);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Добавление автора");
        data.setBounds(0,0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));


        add = new JButton("Добавить");
        add.setBounds(200, 300, 150, 30);
        add.setBackground(new Color(0, 128, 128));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Times New Roman", Font.BOLD, 17));

        surname = new JLabel("Фамилия");
        surname.setBounds(20,60, 140, 30);
        surname.setFont(new Font("Times New Roman", Font.BOLD, 18));

        name = new JLabel("Имя");
        name.setBounds(20,110, 140, 30);
        name.setFont(new Font("Times New Roman", Font.BOLD, 18));

        year = new JLabel("Год рождения");
        year.setBounds(20,160, 140, 30);
        year.setFont(new Font("Times New Roman", Font.BOLD, 18));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 250, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        surnameText = new JTextArea();
        surnameText.setBounds(170,60,200,30);
        surnameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        surnameText.setBackground(new Color(199, 252, 236));

        nameText = new JTextArea();
        nameText.setBounds(170,110,200,30);
        nameText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nameText.setBackground(new Color(199, 252, 236));

        yearText = new JTextArea();
        yearText.setBounds(170,160,200,30);
        yearText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        yearText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(add);
        add(cautionLabel);
        add(surname);
        add(name);
        add(year);
        add(surnameText);
        add(nameText);
        add(yearText);

        add.addActionListener(new AddAuthorWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == add){
                String surname = surnameText.getText();
                String name = nameText.getText();
                int year = 0;
                try {
                    year = Integer.parseInt(yearText.getText());
                }
                catch (Exception exception){
                    cautionLabel.setText("В поле год рождения должно быть целое число");
                }

                if(surname.equals("") || name.equals("")){
                    cautionLabel.setText("Неверный формат ввода");
                }
                else {
                    if (!CheckValid.checkString(surname)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в фамилии.");
                        return;
                    }
                    if (!CheckValid.checkString(name)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в имени.");
                        return;
                    }

                    String clientMessage = "AddAuthor,,," + surname + ",,," + name + ",,," + year;
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
                        cautionLabel.setText("Такой автор уже есть");
                }
            }
        }
    }
}

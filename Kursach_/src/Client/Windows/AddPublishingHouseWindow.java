package Client.Windows;

import Client.CheckValid;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddPublishingHouseWindow extends JFrame {
    private static JLabel data;
    private static JLabel title;
    private static JLabel city;
    private static JLabel adress;
    private static JLabel phone;
    private static JLabel e_mail;
    private static JLabel fax;
    private static JLabel website;
    private static JTextArea titleText;
    private static JTextArea cityText;
    private static JTextArea adressText;
    private static JTextArea phoneText;
    private static JTextArea e_mailText;
    private static JTextArea faxText;
    private static JTextArea websiteText;
    private static JButton add;
    private static JLabel cautionLabel;
    private String message;

    public AddPublishingHouseWindow(){
        super("Добавление издательства");
        setSize(600,500);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Добавление издательства");
        data.setBounds(0,0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        add = new JButton("Добавить");
        add.setBounds(200, 400, 150, 30);
        add.setBackground(new Color(0, 128, 128));
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Times New Roman", Font.BOLD, 17));

        title = new JLabel("Название");
        title.setBounds(20,60, 140, 30);
        title.setFont(new Font("Times New Roman", Font.BOLD, 18));

        city = new JLabel("Город");
        city.setBounds(20,100, 140, 30);
        city.setFont(new Font("Times New Roman", Font.BOLD, 18));

        adress = new JLabel("Адрес");
        adress.setBounds(20,140, 140, 30);
        adress.setFont(new Font("Times New Roman", Font.BOLD, 18));

        phone = new JLabel("Телефон");
        phone.setBounds(20,180, 140, 30);
        phone.setFont(new Font("Times New Roman", Font.BOLD, 18));

        e_mail = new JLabel("e-mail");
        e_mail.setBounds(20,220, 140, 30);
        e_mail.setFont(new Font("Times New Roman", Font.BOLD, 18));

        fax = new JLabel("fax");
        fax.setBounds(20,260, 140, 30);
        fax.setFont(new Font("Times New Roman", Font.BOLD, 18));

        website = new JLabel("website");
        website.setBounds(20,300, 140, 30);
        website.setFont(new Font("Times New Roman", Font.BOLD, 18));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 450, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        titleText = new JTextArea();
        titleText.setBounds(170,60,200,30);
        titleText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleText.setBackground(new Color(199, 252, 236));

        cityText = new JTextArea();
        cityText.setBounds(170,100,200,30);
        cityText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        cityText.setBackground(new Color(199, 252, 236));

        adressText = new JTextArea();
        adressText.setBounds(170,140,200,30);
        adressText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        adressText.setBackground(new Color(199, 252, 236));

        phoneText = new JTextArea();
        phoneText.setBounds(170,180,200,30);
        phoneText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneText.setBackground(new Color(199, 252, 236));

        e_mailText = new JTextArea();
        e_mailText.setBounds(170,220,200,30);
        e_mailText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        e_mailText.setBackground(new Color(199, 252, 236));

        faxText = new JTextArea();
        faxText.setBounds(170,260,200,30);
        faxText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        faxText.setBackground(new Color(199, 252, 236));

        websiteText = new JTextArea();
        websiteText.setBounds(170,300,200,30);
        websiteText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        websiteText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(add);
        add(title);
        add(titleText);
        add(city);
        add(cityText);
        add(adress);
        add(adressText);
        add(phone);
        add(phoneText);
        add(e_mail);
        add(e_mailText);
        add(fax);
        add(faxText);
        add(website);
        add(websiteText);
        add(cautionLabel);

        add.addActionListener(new AddPublishingHouseWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == add){
                String title = titleText.getText();
                String city = cityText.getText();
                String adress = adressText.getText();
                String phone = phoneText.getText();
                String e_mail = e_mailText.getText();
                String fax = faxText.getText();
                String website = websiteText.getText();

                if(title.equals("") || city.equals("") || adress.equals("") || phone.equals("") || e_mail.equals("") || fax.equals("") || website.equals("")){
                    cautionLabel.setText("Неверный формат ввода");
                }
                else {
                    if (!CheckValid.checkString(title)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в названии.");
                        return;
                    }
                    if (!CheckValid.checkString(city)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в жанре.");
                        return;
                    }

                    String clientMessage = "AddPublH,,," + title + ",,," + city + ",,," + adress  + ",,," + phone  + ",,," + e_mail + ",,," + fax + ",,," + website;
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

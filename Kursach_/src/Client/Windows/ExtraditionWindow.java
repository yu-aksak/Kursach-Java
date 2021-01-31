package Client.Windows;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ExtraditionWindow extends JFrame {
    private static JLabel data;
    private static JButton extradite;
    private static JLabel readerID;
    private static JLabel bookID;
    private static JTextArea readerIDText;
    private static JTextArea bookIDText;
    private static JLabel cautionLabel;
    private String message;

    public ExtraditionWindow() {
        super("Выдача книги");
        setSize(600, 500);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Выдача книги");
        data.setBounds(0, 0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 300, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        extradite = new JButton("Выдать");
        extradite.setBounds(200, 400, 150, 30);
        extradite.setBackground(new Color(0, 128, 128));
        extradite.setForeground(Color.WHITE);
        extradite.setFont(new Font("Times New Roman", Font.BOLD, 17));

        readerID = new JLabel("Номер читательского билета");
        readerID.setBounds(20, 60, 300, 30);
        readerID.setFont(new Font("Times New Roman", Font.BOLD, 18));

        bookID = new JLabel("ID книги");
        bookID.setBounds(20, 140, 170, 30);
        bookID.setFont(new Font("Times New Roman", Font.BOLD, 18));

        readerIDText = new JTextArea();
        readerIDText.setBounds(20, 100, 200, 30);
        readerIDText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        readerIDText.setBackground(new Color(199, 252, 236));

        bookIDText = new JTextArea();
        bookIDText.setBounds(20, 180, 200, 30);
        bookIDText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        bookIDText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(extradite);
        add(readerID);
        add(readerIDText);
        add(cautionLabel);
        add(bookID);
        add(bookIDText);
        extradite.addActionListener(new ExtraditionWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == extradite) {
                int idReaderText = 0;
                int idBookText = 0;
                try {
                    idReaderText = Integer.parseInt(readerIDText.getText());
                    idBookText = Integer.parseInt(bookIDText.getText());
                } catch (Exception exception) {
                    cautionLabel.setText("Данные должны быть целочисленными!");
                }
                String clientMessage = "ExtraditeBook,,," + idReaderText + ",,," + idBookText;
                try {
                    Client.out.writeObject(clientMessage);
                    message = (String) Client.in.readObject();
                } catch (IOException exs) {
                    exs.printStackTrace();
                } catch (ClassNotFoundException exs) {
                    exs.printStackTrace();
                }
                if (message.equals("success")) {
                    dispose();
                } else if (message.equals("fail"))
                    cautionLabel.setText("Проверьте введенные данные");
            }
        }
    }
}



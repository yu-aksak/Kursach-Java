package Client.Windows;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AcceptWindow extends JFrame {
    private static JLabel data;
    private static JButton accept;
    private static JLabel readerID;
    private static JLabel bookID;
    private static JTextArea readerIDText;
    private static JTextArea bookIDText;
    private static JLabel cautionLabel;
    private String message;

    public AcceptWindow(){
        super("Принятие книги");
        setSize(600,500);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Принятие книги");
        data.setBounds(0,0, 600, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        accept = new JButton("Принять");
        accept.setBounds(200, 400, 150, 30);
        accept.setBackground(new Color(0, 128, 128));
        accept.setForeground(Color.WHITE);
        accept.setFont(new Font("Times New Roman", Font.BOLD, 17));

        readerID = new JLabel("Номер читательского билета");
        readerID.setBounds(20,60, 300, 30);
        readerID.setFont(new Font("Times New Roman", Font.BOLD, 18));

        bookID = new JLabel("ID книги");
        bookID.setBounds(20,140, 170, 30);
        bookID.setFont(new Font("Times New Roman", Font.BOLD, 18));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 300, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        readerIDText = new JTextArea();
        readerIDText.setBounds(20,100,200,30);
        readerIDText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        readerIDText.setBackground(new Color(199, 252, 236));

        bookIDText = new JTextArea();
        bookIDText.setBounds(20,180,200,30);
        bookIDText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        bookIDText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(accept);
        add(readerID);
        add(readerIDText);
        add(bookID);
        add(bookIDText);
        add(cautionLabel);
        accept.addActionListener(new AcceptWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == accept){
                int idReaderText = 0;
                int idBookText = 0;
                try {
                    idReaderText = Integer.parseInt(readerIDText.getText());
                    idBookText = Integer.parseInt(bookIDText.getText());
                }
                catch (Exception exception){
                    cautionLabel.setText("Данные должны быть целочисленными!");
                }

                String clientMessage = "AcceptBook,,," +  idReaderText + ",,," + idBookText;
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
                    cautionLabel.setText("Такой выдачи не существует!");
            }
        }
    }
}

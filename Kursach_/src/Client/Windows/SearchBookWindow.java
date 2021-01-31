package Client.Windows;

import Client.CheckValid;
import Client.Client;
import Server.Classes.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class SearchBookWindow extends JFrame{
    private static JLabel data;
    private static JTable tableDB;
    private static JTextArea searchText;
    private static JButton searchGenre;
    private static JButton searchTitle;
    private static JButton searchAuthor;
    private static JLabel cautionLabel;
    private String message;

    ArrayList<Book> books = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return true;
        };
    };

    public SearchBookWindow(){
        super("ПОИСК");
        setSize(800,650);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("ПОИСК");
        data.setBounds(0,0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        searchText = new JTextArea();
        searchText.setBounds(170, 400, 200, 30);
        searchText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        searchText.setBackground(new Color(199, 252, 236));


        searchGenre = new JButton("Искать по жанру");
        searchGenre.setBounds(10, 470, 250, 30);
        searchGenre.setBackground(new Color(0, 128, 128));
        searchGenre.setForeground(Color.WHITE);
        searchGenre.setFont(new Font("Times New Roman", Font.BOLD, 17));

        searchTitle = new JButton("Искать по названию");
        searchTitle.setBounds(270, 470, 250, 30);
        searchTitle.setBackground(new Color(0, 128, 128));
        searchTitle.setForeground(Color.WHITE);
        searchTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));

        searchAuthor = new JButton("Искать по автору");
        searchAuthor.setBounds(130, 510, 250, 30);
        searchAuthor.setBackground(new Color(0, 128, 128));
        searchAuthor.setForeground(Color.WHITE);
        searchAuthor.setFont(new Font("Times New Roman", Font.BOLD, 17));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 600, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        tmodel.addColumn("ID книги");
        tmodel.addColumn("Фамилия");
        tmodel.addColumn("Имя");
        tmodel.addColumn("Название");
        tmodel.addColumn("Жанр");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(60, 60, 600, 300);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(60, 60, 600, 300);

        setLayout(null);

        add(scrollPane);
        add(data);
        add(searchAuthor);
        add(searchText);
        add(searchGenre);
        add(searchTitle);
        add(cautionLabel);

        searchAuthor.addActionListener(new SearchBookWindow.ButtonActionListener());
        searchTitle.addActionListener(new SearchBookWindow.ButtonActionListener());
        searchGenre.addActionListener(new SearchBookWindow.ButtonActionListener());

    }

    public class MouseLis implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

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
            String text = searchText.getText();
            String clientMessage = "";
            if(text.equals("")){
                cautionLabel.setText("Неверный формат ввода");
            }
            else {
                if (!CheckValid.checkString(text)) {
                    cautionLabel.setText("Присутствуют недопустимые символы в названии.");
                    return;
                }
            }
            if (e.getSource() == searchAuthor) {
                 clientMessage = "SearchAuthor,,," + text;
            }
            else if (e.getSource() == searchGenre) {
                clientMessage = "SearchGenre,,," + text;
            }
            else if (e.getSource() == searchTitle) {
                clientMessage = "SearchTitle,,," + text;
            }
            try {
                Client.out.writeObject(clientMessage);
                books = (ArrayList<Book>) Client.in.readObject();
            } catch (IOException exs) {
                exs.printStackTrace();
            } catch (ClassNotFoundException exs) {
                exs.printStackTrace();
            }
            if(books.size() == 0){
                cautionLabel.setText("Проверьте введенные данные");
            }else {
                tmodel.setRowCount(0);
                for (Book a : books) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getTitle(), a.getGenre()});
                }
                cautionLabel.setText("");
            }
        }
    }
}
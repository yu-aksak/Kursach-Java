package Client.Windows;

import Client.Client;
import Server.Classes.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewBookReaderWindow extends JFrame{
    private static JLabel data;
    private static JTable tableDB;
    private static JButton sortGenre;
    private static JButton sortTitle;
    private static JButton sortAuthor;
    private static JButton update;

    ArrayList<Book> books = new ArrayList<>();

    DefaultTableModel tmodel = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){
            return true;
        };
    };

    public ViewBookReaderWindow(){
        super("СПИСОК КНИГ");
        setSize(800,600);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Список книг");
        data.setBounds(0,0, 800, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        sortGenre = new JButton("Сортировать по жанру");
        sortGenre.setBounds(10, 470, 250, 30);
        sortGenre.setBackground(new Color(0, 128, 128));
        sortGenre.setForeground(Color.WHITE);
        sortGenre.setFont(new Font("Times New Roman", Font.BOLD, 17));

        sortTitle = new JButton("Сортировать по названию");
        sortTitle.setBounds(270, 470, 250, 30);
        sortTitle.setBackground(new Color(0, 128, 128));
        sortTitle.setForeground(Color.WHITE);
        sortTitle.setFont(new Font("Times New Roman", Font.BOLD, 17));

        sortAuthor = new JButton("Сортировать по автору");
        sortAuthor.setBounds(10, 510, 250, 30);
        sortAuthor.setBackground(new Color(0, 128, 128));
        sortAuthor.setForeground(Color.WHITE);
        sortAuthor.setFont(new Font("Times New Roman", Font.BOLD, 17));

        update = new JButton("Обновить таблицу");
        update.setBounds(270, 510, 250, 30);
        update.setBackground(new Color(0, 128, 128));
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Times New Roman", Font.BOLD, 17));

        tmodel.addColumn("ID книги");
        tmodel.addColumn("Фамилия");
        tmodel.addColumn("Имя");
        tmodel.addColumn("Название");
        tmodel.addColumn("Жанр");

        tableDB = new JTable(tmodel);
        tableDB.setBounds(60, 60, 600, 400);
        tableDB.setFillsViewportHeight(true);
        tableDB.setBackground(new Color(199, 252, 236));

        JScrollPane scrollPane = new JScrollPane(tableDB);
        scrollPane.setBounds(60, 60, 600, 400);

        setLayout(null);

        add(scrollPane);
        add(data);
        add(sortTitle);
        add(sortGenre);
        add(sortAuthor);
        add(update);

        sortTitle.addActionListener(new ViewBookReaderWindow.ButtonActionListener());
        sortGenre.addActionListener(new ViewBookReaderWindow.ButtonActionListener());
        sortAuthor.addActionListener(new ViewBookReaderWindow.ButtonActionListener());
        update.addActionListener(new ViewBookReaderWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == sortAuthor){
                Collections.sort(books, new AuthorComparator());
                tmodel.setRowCount(0);
                for (Book a: books) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getTitle(), a.getGenre() });
                }
            }
            else if(e.getSource() == sortGenre){
                Collections.sort(books, new GenreComparator());
                tmodel.setRowCount(0);
                for (Book a: books) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getTitle(), a.getGenre() });
                }
            }
            else if(e.getSource() == sortTitle){
                Collections.sort(books, new TitleComparator());
                tmodel.setRowCount(0);
                for (Book a: books) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getTitle(), a.getGenre() });
                }
            }
            else if(e.getSource() == update){
                try {
                    Client.out.writeObject("GetBooks");
                    books = (ArrayList<Book>) Client.in.readObject();
                } catch (IOException exs) {
                    exs.printStackTrace();
                } catch (ClassNotFoundException exs) {
                    exs.printStackTrace();
                }
                tmodel.setRowCount(0);
                for (Book a: books) {
                    tmodel.addRow(new Object[]{Integer.toString(a.getId()), a.getSurname(), a.getName(), a.getTitle(), a.getGenre() });
                }
            }
        }
    }

    public class GenreComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.genre.compareTo(o2.genre);
        }
    }

    public class TitleComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.title.compareTo(o2.title);
        }
    }

    public class AuthorComparator implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.surname.compareTo(o2.surname);
        }
    }
}

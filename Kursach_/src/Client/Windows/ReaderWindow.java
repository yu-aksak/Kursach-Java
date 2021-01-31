package Client.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReaderWindow extends JFrame {
    private static JLabel data;
    private static JButton viewData;
    private static JButton books;
    private static JButton searchBook;
    private static JButton extradition;
    private static JButton statistic;

    public ReaderWindow(){
        super("МЕНЮ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,300);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("МЕНЮ");
        data.setBounds(0,0, 500, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        viewData = new JButton("Личные данные");
        viewData.setBounds(130, 60, 220, 30);
        viewData.setBackground(new Color(0, 128, 128));
        viewData.setForeground(Color.WHITE);
        viewData.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewData.setHorizontalAlignment(SwingConstants.CENTER);


        books = new JButton("Книги");
        books.setBounds(130, 100, 220, 30);
        books.setBackground(new Color(0, 128, 128));
        books.setForeground(Color.WHITE);
        books.setFont(new Font("Times New Roman", Font.BOLD, 17));
        books.setHorizontalAlignment(SwingConstants.CENTER);

        searchBook = new JButton("Поиск");
        searchBook.setBounds(130, 140, 220, 30);
        searchBook.setBackground(new Color(0, 128, 128));
        searchBook.setForeground(Color.WHITE);
        searchBook.setFont(new Font("Times New Roman", Font.BOLD, 17));
        searchBook.setHorizontalAlignment(SwingConstants.CENTER);

        extradition = new JButton("Актуальные выдачи");
        extradition.setBounds(130, 180, 220, 30);
        extradition.setBackground(new Color(0, 128, 128));
        extradition.setForeground(Color.WHITE);
        extradition.setFont(new Font("Times New Roman", Font.BOLD, 17));
        extradition.setHorizontalAlignment(SwingConstants.CENTER);

        statistic = new JButton("Статистика");
        statistic.setBounds(130, 220, 220, 30);
        statistic.setBackground(new Color(0, 128, 128));
        statistic.setForeground(Color.WHITE);
        statistic.setFont(new Font("Times New Roman", Font.BOLD, 17));
        statistic.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(null);
        add(data);
        add(viewData);
        add(books);
        add(searchBook);
        add(extradition);
        add(statistic);

        viewData.addActionListener(new ReaderWindow.ButtonActionListener());
        books.addActionListener(new ReaderWindow.ButtonActionListener());
        searchBook.addActionListener(new ReaderWindow.ButtonActionListener());
        extradition.addActionListener(new ReaderWindow.ButtonActionListener());
        statistic.addActionListener(new ReaderWindow.ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == viewData){
                ViewPersonalDataWindow viewPersonalDataWindow = new ViewPersonalDataWindow();
                viewPersonalDataWindow.setVisible(true);
                viewPersonalDataWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == books){
                ViewBookReaderWindow viewBookReaderWindow = new ViewBookReaderWindow();
                viewBookReaderWindow.setVisible(true);
                viewBookReaderWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == searchBook){
                SearchBookWindow searchBookWindow = new SearchBookWindow();
                searchBookWindow.setVisible(true);
                searchBookWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == statistic){
                ViewCountExtraditionBooksWindow viewCountExtraditionBooksWindow = new ViewCountExtraditionBooksWindow();
                viewCountExtraditionBooksWindow.setVisible(true);
                viewCountExtraditionBooksWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == extradition){
                ViewActualReaderExtraditionWindow viewActualReaderExtraditionWindow = new ViewActualReaderExtraditionWindow();
                viewActualReaderExtraditionWindow.setVisible(true);
                viewActualReaderExtraditionWindow.setLocationRelativeTo(null);
            }
        }
    }
}

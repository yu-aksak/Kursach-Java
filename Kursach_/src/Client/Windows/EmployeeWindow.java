package Client.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeWindow extends JFrame{
    private static JLabel data;
    private static JButton viewData;
    private static JButton readers;
    private static JButton books;
    private static JButton extradition;
    private static JButton accept;
    private static JButton actualExtraditionView;
    private static JButton viewAuthors;
    private static JButton viewPublH;

    public EmployeeWindow(){
        super("Меню сотрудника");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,460);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("МЕНЮ");
        data.setBounds(0,0, 500, 50);
        data.setForeground(Color.WHITE);
        data.setFont(new Font("Times New Roman", Font.BOLD, 20));
        data.setHorizontalAlignment(SwingConstants.CENTER);
        data.setOpaque(true);
        data.setBackground(new Color(0, 128, 128));

        viewData = new JButton("Личные данные");
        viewData.setBounds(110, 60, 260, 30);
        viewData.setBackground(new Color(0, 128, 128));
        viewData.setForeground(Color.WHITE);
        viewData.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewData.setHorizontalAlignment(SwingConstants.CENTER);


        readers = new JButton("Читатели");
        readers.setBounds(110, 100, 260, 30);
        readers.setBackground(new Color(0, 128, 128));
        readers.setForeground(Color.WHITE);
        readers.setFont(new Font("Times New Roman", Font.BOLD, 17));
        readers.setHorizontalAlignment(SwingConstants.CENTER);

        books = new JButton("Книги");
        books.setBounds(110, 140, 260, 30);
        books.setBackground(new Color(0, 128, 128));
        books.setForeground(Color.WHITE);
        books.setFont(new Font("Times New Roman", Font.BOLD, 17));
        books.setHorizontalAlignment(SwingConstants.CENTER);

        extradition = new JButton("Выдать книгу");
        extradition.setBounds(110, 180, 260, 30);
        extradition.setBackground(new Color(0, 128, 128));
        extradition.setForeground(Color.WHITE);
        extradition.setFont(new Font("Times New Roman", Font.BOLD, 17));
        extradition.setHorizontalAlignment(SwingConstants.CENTER);

        accept = new JButton("Вернуть книгу");
        accept.setBounds(110, 220, 260, 30);
        accept.setBackground(new Color(0, 128, 128));
        accept.setForeground(Color.WHITE);
        accept.setFont(new Font("Times New Roman", Font.BOLD, 17));
        accept.setHorizontalAlignment(SwingConstants.CENTER);

        actualExtraditionView = new JButton("Просмотр актуальных выдач");
        actualExtraditionView.setBounds(110, 260, 260, 30);
        actualExtraditionView.setBackground(new Color(0, 128, 128));
        actualExtraditionView.setForeground(Color.WHITE);
        actualExtraditionView.setFont(new Font("Times New Roman", Font.BOLD, 17));
        actualExtraditionView.setHorizontalAlignment(SwingConstants.CENTER);

        viewAuthors = new JButton("Просмотр авторов");
        viewAuthors.setBounds(110, 300, 260, 30);
        viewAuthors.setBackground(new Color(0, 128, 128));
        viewAuthors.setForeground(Color.WHITE);
        viewAuthors.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewAuthors.setHorizontalAlignment(SwingConstants.CENTER);

        viewPublH = new JButton("Просмотр издательств");
        viewPublH.setBounds(110, 340, 260, 30);
        viewPublH.setBackground(new Color(0, 128, 128));
        viewPublH.setForeground(Color.WHITE);
        viewPublH.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewPublH.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(null);
        add(data);
        add(viewData);
        add(readers);
        add(books);
        add(extradition);
        add(accept);
        add(actualExtraditionView);
        add(viewAuthors);
        add(viewPublH);

        viewData.addActionListener(new EmployeeWindow.ButtonActionListener());
        readers.addActionListener(new EmployeeWindow.ButtonActionListener());
        books.addActionListener(new EmployeeWindow.ButtonActionListener());
        extradition.addActionListener(new EmployeeWindow.ButtonActionListener());
        accept.addActionListener(new EmployeeWindow.ButtonActionListener());
        actualExtraditionView.addActionListener(new EmployeeWindow.ButtonActionListener());
        viewAuthors.addActionListener(new EmployeeWindow.ButtonActionListener());
        viewPublH.addActionListener(new EmployeeWindow.ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == viewData){
                ViewPersonalDataWindow viewPersonalDataWindow = new ViewPersonalDataWindow();
                viewPersonalDataWindow.setVisible(true);
                viewPersonalDataWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == readers){
                ViewReaderWindow viewReaderWindow = new ViewReaderWindow();
                viewReaderWindow.setVisible(true);
                viewReaderWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == books){
                ViewBookWindow viewBookWindow = new ViewBookWindow();
                viewBookWindow.setVisible(true);
                viewBookWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == extradition){
                ExtraditionWindow extraditionWindow = new ExtraditionWindow();
                extraditionWindow.setVisible(true);
                extraditionWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == accept){
                AcceptWindow acceptWindow = new AcceptWindow();
                acceptWindow.setVisible(true);
                acceptWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == actualExtraditionView){
                ViewActualExtraditionWindow viewActualExtraditionWindow = new ViewActualExtraditionWindow();
                viewActualExtraditionWindow.setVisible(true);
                viewActualExtraditionWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() ==viewAuthors){
                ViewAuthorsWindow viewAuthorsWindow = new ViewAuthorsWindow();
                viewAuthorsWindow.setVisible(true);
                viewAuthorsWindow.setLocationRelativeTo(null);
            }
            else if(e.getSource() == viewPublH){
                ViewPublishingHouseWindow viewPublishingHouseWindow = new ViewPublishingHouseWindow();
                viewPublishingHouseWindow.setVisible(true);
                viewPublishingHouseWindow.setLocationRelativeTo(null);
            }
        }
    }
}

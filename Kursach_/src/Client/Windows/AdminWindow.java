package Client.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminWindow extends JFrame {
    private static JLabel data;
    private static JButton employee;
    private static JButton reader;
    private static JButton book;
    private static JButton actualExtraditionView;
    private static JButton extraditionView;
    private static JButton viewAuthors;
    private static JButton viewPublH;
    private static JButton statistic;

    public AdminWindow(){
        super("МЕНЮ");
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

        employee = new JButton("Сотрудники");
        employee.setBounds(110, 60, 260, 30);
        employee.setBackground(new Color(0, 128, 128));
        employee.setForeground(Color.WHITE);
        employee.setFont(new Font("Times New Roman", Font.BOLD, 17));
        employee.setHorizontalAlignment(SwingConstants.CENTER);

        reader = new JButton("Читатели");
        reader.setBounds(110, 100, 260, 30);
        reader.setBackground(new Color(0, 128, 128));
        reader.setForeground(Color.WHITE);
        reader.setFont(new Font("Times New Roman", Font.BOLD, 17));
        reader.setHorizontalAlignment(SwingConstants.CENTER);

        book = new JButton("Книги");
        book.setBounds(110, 140, 260, 30);
        book.setBackground(new Color(0, 128, 128));
        book.setForeground(Color.WHITE);
        book.setFont(new Font("Times New Roman", Font.BOLD, 17));
        book.setHorizontalAlignment(SwingConstants.CENTER);

        actualExtraditionView = new JButton("Просмотр актуальных выдач");
        actualExtraditionView.setBounds(110, 180, 260, 30);
        actualExtraditionView.setBackground(new Color(0, 128, 128));
        actualExtraditionView.setForeground(Color.WHITE);
        actualExtraditionView.setFont(new Font("Times New Roman", Font.BOLD, 17));
        actualExtraditionView.setHorizontalAlignment(SwingConstants.CENTER);

        extraditionView = new JButton("Просмотр всех выдач");
        extraditionView.setBounds(110, 220, 260, 30);
        extraditionView.setBackground(new Color(0, 128, 128));
        extraditionView.setForeground(Color.WHITE);
        extraditionView.setFont(new Font("Times New Roman", Font.BOLD, 17));
        extraditionView.setHorizontalAlignment(SwingConstants.CENTER);

        viewAuthors = new JButton("Просмотр авторов");
        viewAuthors.setBounds(110, 260, 260, 30);
        viewAuthors.setBackground(new Color(0, 128, 128));
        viewAuthors.setForeground(Color.WHITE);
        viewAuthors.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewAuthors.setHorizontalAlignment(SwingConstants.CENTER);

        viewPublH = new JButton("Просмотр издательств");
        viewPublH.setBounds(110, 300, 260, 30);
        viewPublH.setBackground(new Color(0, 128, 128));
        viewPublH.setForeground(Color.WHITE);
        viewPublH.setFont(new Font("Times New Roman", Font.BOLD, 17));
        viewPublH.setHorizontalAlignment(SwingConstants.CENTER);

        statistic = new JButton("Статистика по выдачам");
        statistic.setBounds(110, 340, 260, 30);
        statistic.setBackground(new Color(0, 128, 128));
        statistic.setForeground(Color.WHITE);
        statistic.setFont(new Font("Times New Roman", Font.BOLD, 17));
        statistic.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(null);

        add(data);
        add(employee);
        add(reader);
        add(book);
        add(actualExtraditionView);
        add(extraditionView);
        add(viewAuthors);
        add(viewPublH);
        add(statistic);

        employee.addActionListener(new AdminWindow.ButtonActionListener());
        reader.addActionListener(new AdminWindow.ButtonActionListener());
        book.addActionListener(new AdminWindow.ButtonActionListener());
        actualExtraditionView.addActionListener(new AdminWindow.ButtonActionListener());
        extraditionView.addActionListener(new AdminWindow.ButtonActionListener());
        viewAuthors.addActionListener(new AdminWindow.ButtonActionListener());
        viewPublH.addActionListener(new AdminWindow.ButtonActionListener());
        statistic.addActionListener(new AdminWindow.ButtonActionListener());
    }

    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == employee){
                ViewEmployeeWindow viewEmployeeWindow = new ViewEmployeeWindow();
                viewEmployeeWindow.setVisible(true);
                viewEmployeeWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == reader){
                ViewReaderWindow viewReaderWindow = new ViewReaderWindow();
                viewReaderWindow.setVisible(true);
                viewReaderWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == book){
                ViewBookWindow viewBookWindow = new ViewBookWindow();
                viewBookWindow.setVisible(true);
                viewBookWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == actualExtraditionView){
                ViewActualExtraditionWindow viewActualExtraditionWindow = new ViewActualExtraditionWindow();
                viewActualExtraditionWindow.setVisible(true);
                viewActualExtraditionWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == extraditionView){
                ViewExtraditionWindow viewExtraditionWindow = new ViewExtraditionWindow();
                viewExtraditionWindow.setVisible(true);
                viewExtraditionWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == viewAuthors){
                ViewAuthorsWindow viewAuthorsWindow = new ViewAuthorsWindow();
                viewAuthorsWindow.setVisible(true);
                viewAuthorsWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == viewPublH){
                ViewPublishingHouseWindow viewPublishingHouseWindow = new ViewPublishingHouseWindow();
                viewPublishingHouseWindow.setVisible(true);
                viewPublishingHouseWindow.setLocationRelativeTo(null);
            }
            else if (e.getSource() == statistic){
                ViewCountExtraditionReadersWindow viewCountExtraditionReadersWindow = new ViewCountExtraditionReadersWindow();
                viewCountExtraditionReadersWindow.setVisible(true);
                viewCountExtraditionReadersWindow.setLocationRelativeTo(null);
            }
        }
    }
}

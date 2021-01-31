package Client.Windows;

import Client.CheckValid;
import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddBookWindow extends JFrame {
    private static JLabel data;
    private static JLabel title;
    private static JLabel genre;
    private static JLabel series;
    private static JLabel year;
    private static JLabel pages;
    private static JLabel blinding;
    private static JLabel idAuthor;
    private static JLabel idPublH;
    private static JTextArea titleText;
    private static JTextArea genreText;
    private static JTextArea seriesText;
    private static JTextArea yearText;
    private static JTextArea pagesText;
    private static JTextArea blindingText;
    private static JTextArea idAuthorText;
    private static JTextArea idPublHText;
    private static JLabel cautionLabel;
    private String message;
    private static JButton add;

    public AddBookWindow(){
        super("Добавление книги");
        setSize(600,500);
        getContentPane().setBackground(new Color(102, 205, 170));

        data = new JLabel("Добавление книги");
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

        genre = new JLabel("Жанр");
        genre.setBounds(20,100, 140, 30);
        genre.setFont(new Font("Times New Roman", Font.BOLD, 18));

        series = new JLabel("Серия");
        series.setBounds(20,140, 140, 30);
        series.setFont(new Font("Times New Roman", Font.BOLD, 18));

        year = new JLabel("Год написания");
        year.setBounds(20,180, 140, 30);
        year.setFont(new Font("Times New Roman", Font.BOLD, 18));

        pages = new JLabel("Кол-во страниц");
        pages.setBounds(20,220, 140, 30);
        pages.setFont(new Font("Times New Roman", Font.BOLD, 18));

        blinding = new JLabel("Переплет");
        blinding.setBounds(20,260, 140, 30);
        blinding.setFont(new Font("Times New Roman", Font.BOLD, 18));

        idAuthor = new JLabel("ID автора");
        idAuthor.setBounds(20,300, 140, 30);
        idAuthor.setFont(new Font("Times New Roman", Font.BOLD, 18));

        idPublH = new JLabel("ID издания");
        idPublH.setBounds(20,340, 140, 30);
        idPublH.setFont(new Font("Times New Roman", Font.BOLD, 18));

        cautionLabel = new JLabel();
        cautionLabel.setBounds(10, 450, 380, 50);
        cautionLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        titleText = new JTextArea();
        titleText.setBounds(170,60,200,30);
        titleText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleText.setBackground(new Color(199, 252, 236));

        genreText = new JTextArea();
        genreText.setBounds(170,100,200,30);
        genreText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genreText.setBackground(new Color(199, 252, 236));

        seriesText = new JTextArea();
        seriesText.setBounds(170,140,200,30);
        seriesText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        seriesText.setBackground(new Color(199, 252, 236));

        yearText = new JTextArea();
        yearText.setBounds(170,180,200,30);
        yearText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        yearText.setBackground(new Color(199, 252, 236));

        pagesText = new JTextArea();
        pagesText.setBounds(170,220,200,30);
        pagesText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pagesText.setBackground(new Color(199, 252, 236));

        blindingText = new JTextArea();
        blindingText.setBounds(170,260,200,30);
        blindingText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        blindingText.setBackground(new Color(199, 252, 236));

        idAuthorText = new JTextArea();
        idAuthorText.setBounds(170,300,200,30);
        idAuthorText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idAuthorText.setBackground(new Color(199, 252, 236));

        idPublHText = new JTextArea();
        idPublHText.setBounds(170,340,200,30);
        idPublHText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idPublHText.setBackground(new Color(199, 252, 236));

        setLayout(null);

        add(data);
        add(add);
        add(title);
        add(titleText);
        add(genre);
        add(genreText);
        add(series);
        add(seriesText);
        add(year);
        add(yearText);
        add(pages);
        add(pagesText);
        add(blinding);
        add(blindingText);
        add(idAuthor);
        add(idAuthorText);
        add(idPublH);
        add(idPublHText);
        add(cautionLabel);

        add.addActionListener(new AddBookWindow.ButtonActionListener());
    }


    public class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == add){
                String title = titleText.getText();
                String genre = genreText.getText();
                String series = seriesText.getText();
                String binding = blindingText.getText();
                int year = 0, pages = 0, idAuthor = 0, idPublH = 0;
                try {
                    year = Integer.parseInt(yearText.getText());
                    pages = Integer.parseInt(pagesText.getText());
                    idAuthor = Integer.parseInt(idAuthorText.getText());
                    idPublH = Integer.parseInt(idPublHText.getText());

                }
                catch (Exception exception){
                    cautionLabel.setText("В поле год, страницы, ID должны быть целое числа");
                }

                if(title.equals("") || genre.equals("") || series.equals("") || binding.equals("")){
                    cautionLabel.setText("Неверный формат ввода");
                }
                else {
                    if (!CheckValid.checkString(title)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в названии.");
                        return;
                    }
                    if (!CheckValid.checkString(genre)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в жанре.");
                        return;
                    }
                    if (!CheckValid.checkString(series)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в серии.");
                        return;
                    }
                    if (!CheckValid.checkString(binding)) {
                        cautionLabel.setText("Присутствуют недопустимые символы в переплете.");
                        return;
                    }

                    String clientMessage = "AddBook,,," + title + ",,," + genre + ",,," + series  + ",,," + year  + ",,," + pages + ",,," + binding + ",,," + idPublH + ",,," + idAuthor;
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
                    else if (message.equals("fail"))
                        cautionLabel.setText("Такая книга уже есть");
                    else if (message.equals("fail1"))
                        cautionLabel.setText("Неправильно введен идентификатор издательства");
                    else if (message.equals("fail2"))
                        cautionLabel.setText("Неправильно введен идентификатор автора");
                }
            }
        }
    }
}

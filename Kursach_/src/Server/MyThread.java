package Server;

import Server.Classes.*;
import Server.Database.*;
import Server.Database.Database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MyThread extends Thread {
    private InetAddress addr;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String clientMessage;
    private int counter;
    private Database mdbc;
    private Statement stmt;
    private UserDB userDB;
    private BookDB bookDB;
    private ExtraditionDB extraditionDB;
    private AuthorDB authorDB;
    private PublishingHouseDB publishingHouseDB;
    private ReaderDB readerDB;
    private EmployeeDB employeeDB;
    private Reader reader;
    private Employee employee;

    public MyThread(Socket s, int counter) throws IOException, SQLException, ClassNotFoundException {
        this.counter = counter;
        this.out = new ObjectOutputStream(s.getOutputStream());
        this.in = new ObjectInputStream(s.getInputStream());
        this.addr = s.getInetAddress();
        this.mdbc = new Database();

        Connection conn = this.mdbc.getMyConnection();

        try {
            this.stmt = conn.createStatement();
            this.userDB = new UserDB(this.stmt, this.mdbc);
            this.bookDB = new BookDB(this.stmt, this.mdbc);
            this.extraditionDB = new ExtraditionDB(this.stmt, this.mdbc);
            this.authorDB = new AuthorDB(this.stmt, this.mdbc);
            this.publishingHouseDB = new PublishingHouseDB(this.stmt, this.mdbc);
            this.readerDB = new ReaderDB(this.stmt, this.mdbc);
            this.employeeDB = new EmployeeDB(this.stmt, this.mdbc);
        } catch (SQLException e5) {
            System.out.println(e5);
        }
    }

    @Override
    public void run() {
        String messageToClient = "";
        String str;
        String ThreadStop = "";
        try {
            System.out.println("Сервер ожидает действий от клиента");

            while (!ThreadStop.equals("Exit")) {
                str = (String) this.in.readObject();

                String[] messageParts = str.split(",,,");
                switch (messageParts[0]) {
                    case "CheckLogin":
                        String UserLogin = messageParts[1];
                        String UserPassword = messageParts[2];
                        int UserLevel = Integer.parseInt(messageParts[3]);
                        messageToClient = this.userDB.CheckLogin(UserLogin, UserPassword, UserLevel);
                        this.writeObj(messageToClient);
                        if(messageToClient.equals("success") && UserLevel == 1){
                            employee = this.userDB.findEmployee(UserLogin, UserPassword, UserLevel);
                        }
                        else if(messageToClient.equals("success") && UserLevel == 2){
                            reader = this.userDB.findReader(UserLogin, UserPassword, UserLevel);
                        }
                        break;
                    case "CheckUserInDB":
                        int UserID1 = Integer.parseInt(messageParts[1]);
                        int UserLevel1 = Integer.parseInt(messageParts[2]);
                        messageToClient = this.userDB.CheckUserInDB(UserID1, UserLevel1);
                        this.writeObj(messageToClient);
                        break;
                    case "AddUser":
                        int UserID2 = Integer.parseInt(messageParts[1]);
                        String UserLogin2 = messageParts[2];
                        String UserPassword2 = messageParts[3];
                        int UserLevel2 = Integer.parseInt(messageParts[4]);
                        messageToClient = this.userDB.AddUser(UserID2, UserLogin2, UserPassword2, UserLevel2);
                        this.writeObj(messageToClient);
                        break;
                    case "GetData":
                        if (reader != null)
                             messageToClient = reader.getId() + ",,," + reader.getSurname() + ",,," + reader.getName() + ",,," + reader.getMidname()
                                    + ",,," + reader.getBirthdate() + ",,," + reader.getAdress() + ",,," + reader.getPhone() + ",,,2";
                        else if (employee != null)
                            messageToClient = employee.getId() + ",,," + employee.getSurname() + ",,," + employee.getName() + ",,," + employee.getMidname()
                                + ",,," + employee.getBirthdate() + ",,," + employee.getAdress() + ",,," + employee.getPhone() + ",,,1";

                        this.writeObj(messageToClient);
                        break;
                    case "UpdateUser":
                        int id = Integer.parseInt(messageParts[1]);
                        String surname = messageParts[2];
                        String name = messageParts[3];
                        String midname = messageParts[4];
                        String birthdate = messageParts[5];
                        String adress = messageParts[6];
                        String phone = messageParts[7];
                        int level = Integer.parseInt(messageParts[8]);
                        messageToClient = this.userDB.UpdateUser(id, surname, name, midname, birthdate, adress, phone, level);
                        this.writeObj(messageToClient);
                        if(messageToClient.equals("success") && level == 1){
                            employee.setSurname(surname);
                            employee.setName(name);
                            employee.setMidname(midname);
                            employee.setBirthdate(birthdate);
                            employee.setAdress(adress);
                            employee.setPhone(phone);
                        }
                        else if(messageToClient.equals("success") && level == 2){
                            reader.setSurname(surname);
                            reader.setName(name);
                            reader.setMidname(midname);
                            reader.setBirthdate(birthdate);
                            reader.setAdress(adress);
                            reader.setPhone(phone);
                        }
                        break;
                    case "GetBooks":
                        ArrayList<Book> books;
                        books = this.bookDB.SelectBooks();
                        out.writeObject(books);
                        break;
                    case "GetAuthors":
                        ArrayList<Author> authors;
                        authors = this.authorDB.SelectAuthors();
                        out.writeObject(authors);
                        break;
                    case "GetPublH":
                        ArrayList<PublishingHouse> publishingHouses;
                        publishingHouses = this.publishingHouseDB.SelectPublH();
                        out.writeObject(publishingHouses);
                        break;
                    case "GetEmployees":
                        ArrayList<Employee> employees;
                        employees = this.employeeDB.SelectEmployees();
                        out.writeObject(employees);
                        break;
                    case "GetReaders":
                        ArrayList<Reader> readers;
                        readers = this.readerDB.SelectReaders();
                        out.writeObject(readers);
                        break;
                    case "GetActualExtraditions":
                        ArrayList<Extradition> actualExtraditions;
                        actualExtraditions = this.extraditionDB.SelectActualExtraditions();
                        out.writeObject(actualExtraditions);
                        break;
                    case "GetActualReaderExtraditions":
                        ArrayList<Extradition> actualReaderExtraditions;
                        actualExtraditions = this.extraditionDB.SelectActualReaderExtraditions(reader.id);
                        out.writeObject(actualExtraditions);
                        break;
                    case "GetExtraditions":
                        ArrayList<Extradition> extraditions;
                        extraditions = this.extraditionDB.SelectExtraditions();
                        out.writeObject(extraditions);
                        break;
                    case "GetCountExtraditions":
                        ArrayList<Extradition> countExtraditions;
                        countExtraditions = this.extraditionDB.SelectCountExtraditions();
                        out.writeObject(countExtraditions);
                        break;
                    case "GetCountBooks":
                        ArrayList<Extradition> countBooks;
                        countBooks = this.extraditionDB.SelectCountBooks();
                        out.writeObject(countBooks);
                        break;
                    case "AcceptBook":
                        int idReader = Integer.parseInt(messageParts[1]);
                        int idBook = Integer.parseInt(messageParts[2]);
                        messageToClient = this.extraditionDB.Accept(idReader, idBook);
                        this.writeObj(messageToClient);
                        break;
                    case "ExtraditeBook":
                        int idReader1 = Integer.parseInt(messageParts[1]);
                        int idBook1 = Integer.parseInt(messageParts[2]);
                        messageToClient = this.extraditionDB.Extradite(idReader1, idBook1, employee.getId());
                        this.writeObj(messageToClient);
                        break;
                    case "AddAuthor":
                        String surname1 = messageParts[1];
                        String name1 = messageParts[2];
                        int year1 = Integer.parseInt(messageParts[3]);
                        messageToClient = this.authorDB.AddAuthor(surname1, name1, year1);
                        this.writeObj(messageToClient);
                        break;
                    case "AddBook":
                        String title = messageParts[1];
                        String genre = messageParts[2];
                        String series = messageParts[3];
                        int year2 = Integer.parseInt(messageParts[4]);
                        int pages = Integer.parseInt(messageParts[5]);
                        String binding = messageParts[6];
                        int idPublH = Integer.parseInt(messageParts[7]);
                        int idAuthor = Integer.parseInt(messageParts[8]);

                        messageToClient = this.bookDB.AddBook(title, genre, series, year2, pages, binding, idPublH, idAuthor);
                        this.writeObj(messageToClient);
                        break;
                    case "AddPublH":
                        String title1 = messageParts[1];
                        String city = messageParts[2];
                        String adress1 = messageParts[3];
                        String phone1 = messageParts[4];
                        String e_mail = messageParts[5];
                        String fax = messageParts[6];
                        String website = messageParts[7];

                        messageToClient = this.publishingHouseDB.AddPublH(title1, city, adress1, phone1, e_mail, fax, website);
                        this.writeObj(messageToClient);
                        break;
                    case "AddEmployee":
                        String surname2 = messageParts[1];
                        String name2 = messageParts[2];
                        String midname2 = messageParts[3];
                        String birthdate2 = messageParts[4];
                        String adress2 = messageParts[5];
                        String phone2 = messageParts[6];

                        messageToClient = this.employeeDB.AddEmployee(surname2, name2, midname2, birthdate2, adress2, phone2);
                        this.writeObj(messageToClient);
                        break;
                    case "AddReader":
                        String surname3 = messageParts[1];
                        String name3 = messageParts[2];
                        String midname3 = messageParts[3];
                        String birthdate3 = messageParts[4];
                        String adress3 = messageParts[5];
                        String phone3 = messageParts[6];

                        messageToClient = this.readerDB.AddReader(surname3, name3, midname3, birthdate3, adress3, phone3);
                        this.writeObj(messageToClient);
                        break;
                    case "DeleteReader":
                        int idR = Integer.parseInt(messageParts[1]);
                        messageToClient = this.readerDB.DeleteReader(idR);
                        this.writeObj(messageToClient);
                        break;
                    case "DeleteEmployee":
                        int idE = Integer.parseInt(messageParts[1]);
                        messageToClient = this.employeeDB.DeleteEmployee(idE);
                        this.writeObj(messageToClient);
                        break;
                    case "DeletePublH":
                        int idP = Integer.parseInt(messageParts[1]);
                        messageToClient = this.publishingHouseDB.DeletePublH(idP);
                        this.writeObj(messageToClient);
                        break;
                    case "DeleteAuthor":
                        int idA = Integer.parseInt(messageParts[1]);
                        messageToClient = this.authorDB.DeleteAuthor(idA);
                        this.writeObj(messageToClient);
                        break;
                    case "DeleteBook":
                        int idB = Integer.parseInt(messageParts[1]);
                        messageToClient = this.bookDB.DeleteBook(idB);
                        this.writeObj(messageToClient);
                        break;
                    case "SearchAuthor":
                        String author = messageParts[1];
                        ArrayList<Book> booksA;
                        booksA = this.bookDB.SearchAuthor(author);
                        out.writeObject(booksA);
                        break;
                    case "SearchGenre":
                        String genre1 = messageParts[1];
                        ArrayList<Book> booksG;
                        booksG = this.bookDB.SearchGenre(genre1);
                        out.writeObject(booksG);
                        break;
                    case "SearchTitle":
                        String title2 = messageParts[1];
                        ArrayList<Book> booksT;
                        booksT = this.bookDB.SearchTitle(title2);
                        out.writeObject(booksT);
                        break;
                }
            }
        } catch (Exception var17) {
            System.err.println("Соединение закрыто");
        } finally {
            this.disconnect();
        }
    }

    public void writeObj(String str) {
        this.clientMessage = str;

        try {
            this.out.writeObject(this.clientMessage);
        } catch (IOException e3) {
            System.err.println("I/О thread error" + e3);
        }
    }

    private void disconnect() {
        try {
            if (this.out != null) {
                this.out.close();
            }
            if (this.in != null) {
                this.in.close();
            }
            System.out.println(this.addr.getHostName() + " Закрытие соединения " + this.counter + "-го клиента");
        } catch (IOException var5) {
            var5.printStackTrace();
        } finally {
            this.interrupt();
        }
    }
}

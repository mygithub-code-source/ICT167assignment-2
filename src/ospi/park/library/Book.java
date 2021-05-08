package ospi.park.library;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * This class defined all the fields for the Books. It deals with reading the
 * data from the file book.txt and setting \& getting value for each field.
 *
 * @title Ospi Park (Library)
 * @author Zain ul abideen (33921046)
 * File Name: Book.java Date: May 07, 2021 Assumptions: All the
 * inputs are supposed to be in same format as shown to the user while
 * prompting.
 * File configurations are as follows:
 *  isbn,id,title,authorFirstName,authorLastName,pages,date,borrower,bookType
                 
 */
public class Book {

    private String isbn;
    private String title;
    private String id;
    private String authorFirstName;
    private String authorLastName;
    private String pages;
    private String issueDate;
    private String borrowerName;
    private String bookType;

    /**
     *
     * @return type of book that is either Drama or Crime
     */
    public String getBookType() {
        return bookType;
    }

    /**
     *
     * @param bookType
     */
    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    /**
     *
     * @return first name of book author
     */
    public String getAuthorFirstName() {
        return authorFirstName;
    }

    /**
     *
     * @param authorFirstName
     */
    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    /**
     *
     * @return last name of book author
     */
    public String getAuthorLastName() {
        return authorLastName;
    }

    /**
     *
     * @param authorLastName
     */
    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    /**
     *
     * @return pages of a book
     */
    public String getPages() {
        return pages;
    }

    /**
     *
     * @param pages
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    /**
     *
     * @return ISBN of a book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return title of a book
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return id of a book
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return date for a book on which it was issued.
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     *
     * @param issueDate
     */
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     *
     * @return borrowerName
     */
    public String getBorrowerName() {
        return borrowerName;
    }

    /**
     *
     * @param borrowerName
     */
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    /**
     *
     * @return list containing books of crime category only
     */
    public ArrayList<Book> getCrimeBooksList() {
        return crimeBooksList;
    }

    /**
     *
     * @param crimeBooksList
     */
    public void setCrimeBooksList(ArrayList<Book> crimeBooksList) {
        this.crimeBooksList = crimeBooksList;
    }

    /**
     *
     * @return list containing books of drama category only
     */
    public ArrayList<Book> getDramaBooksList() {
        return dramaBooksList;
    }

    /**
     *
     * @param dramaBooksList
     */
    public void setDramaBooksList(ArrayList<Book> dramaBooksList) {
        this.dramaBooksList = dramaBooksList;
    }

    ArrayList<Book> crimeBooksList = new ArrayList();
    ArrayList<Book> dramaBooksList = new ArrayList();

    /**
     *
     * @param bookList
     * @return sorted Book List received as parameter.
     */
    public ArrayList sortBooksById(ArrayList<Book> bookList) {
        for (int i = 0; i < bookList.size(); i++) {
            for (int j = i + 1; j < bookList.size(); j++) {
                int indexi = Integer.valueOf(bookList.get(i).getId());
                int indexj = Integer.valueOf(bookList.get(j).getId());

                if (indexi > indexj) {
                    bookList.get(i).setId(String.valueOf(indexj));
                    bookList.get(j).setId(String.valueOf(indexi));
                }
            }
        }

        return bookList;
    }

    /**
     * No Args Constructor
     */
    public Book() {
    }

    /**
     *
     * @param isbn
     * @param title
     * @param id
     * @param authorFirstName
     * @param authorLastName
     * @param pages
     * @param issueDate
     * @param borrowerName
     * @param bookType
     */
    public Book(String isbn, String title, String id, String authorFirstName, String authorLastName, String pages, String issueDate, String borrowerName, String bookType) {
        this.isbn = isbn;
        this.title = title;
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.pages = pages;
        this.issueDate = issueDate;
        this.borrowerName = borrowerName;
        this.bookType = bookType;
    }
    

    /**
     *
     * @return a list containing the objects of Book.java where each object
     * stores the data for a book.
     */
    public ArrayList<Book> readFile() {
        ArrayList<Book> bookDetails = new ArrayList<>();
        try {
            File myObj = new File("book.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] lineSplit = line.split(",");
                if (lineSplit.length == 9) {
                    Book book = new Book();
                    //File configurations are as follows:
                    //isbn,id,title,authorFirstName,authorLastName,pages,date,borrower,bookType
                    book.setIsbn(lineSplit[0]);
                    book.setId(lineSplit[1]);
                    book.setTitle(lineSplit[2]);
                    book.setAuthorFirstName(lineSplit[3]);
                    book.setAuthorLastName(lineSplit[4]);
                    book.setPages(lineSplit[5]);
                    book.setIssueDate(lineSplit[6]);
                    book.setBorrowerName(lineSplit[7]);
                    book.setBookType(lineSplit[8]);

                    bookDetails.add(book);

                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
        return bookDetails;
    }

    /**
     * @param borrowDate
     * @return difference of the days from borrow date with current day.
     */
    public long calculateOverdueDays(String borrowDate) {
        SimpleDateFormat obj = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date borrow = obj.parse(borrowDate);
            String today = obj.format(new Date());
            Date currentDate = obj.parse(today);
            // calculate difference in millis
            long millis = Math.abs(currentDate.getTime() - borrow.getTime());
            // convert milliseconds to days
            long days = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
            return days;
        } catch (ParseException e) {
        }
        return 0;
    }
}

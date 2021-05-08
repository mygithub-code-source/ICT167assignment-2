package ospi.park.library;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This deals with displaying menu to the user and performing the required
 * tasks. This is the main class of the application
 *
 * @title Ospi Park (Library)
 * @author jamoo
 *
 * File Name: MainClass.java Date: May 07, 2021 Assumptions: All the inputs are
 * supposed to be in same format as shown to the user while prompting.
 *
 */
public class MainClass {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int choice = 0;
        Book book = new Book();
        ArrayList<Book> sortedList = null;
        ArrayList<Book> bookList = book.readFile();
        Scanner scan = new Scanner(System.in);
        do {
//            System.out.println("Enter Book Category\n 1- Drama \n 2- Crime");
//            int bookCategory = Integer.parseInt(scan.nextLine());
//            switch (bookCategory) {
//                case 1:
//                    for (Book book1 : bookList) {
//                        if (book1.getBookType().equals("drama")) {
//                            book.dramaBooksList.add(book1);
//                        }
//                    }
//                    break;
//                case 2:
//                    for (Book book1 : bookList) {
//                        if (book1.getBookType().equals("crime")) {
//                            book.crimeBooksList.add(book1);
//                        }
//                    }
//                    break;
//                default:
//                    System.err.println("Error Input! Please enter again");
//                    break;
//            }

            System.out.println("Enter your choice\n1. Quit (exit the program)\n"
                    + "2. Compute the overall fine from both the genre i.e. crime and drama, individually.\n"
                    + "3. Output the overall outstanding fine\n"
                    + "4. Find the top three books which led to the maximum revenue via late penalty\n"
                    + "5. Determine the number of books that are over-due for more than 10 days\n"
                    + "6. Display the most popular and least popular book based on the number of days the book was\n"
                    + "borrowed and left vacant in the library.\n"
                    + "7. Determine the last borrower of a book and their outstanding fine on the book, if any\n"
                    + "8. Search and get all details of the book from BookID.\n"
                    + "9. Search the book by their author first and/or last name\n"
                    + "10. Sort the books by their ID number\n"
                    + "11. Output the sorted list to a CSV file. ");
            choice = Integer.valueOf(scan.nextLine());

            if (choice <= 0 || choice > 11) {
                System.out.println("Error input!Please enter from the given choices!");
            } else {
                switch (choice) {
                    case 2:
                        int dramaFine = 0;
                        int crimeFine = 0;
                        Drama drama = new Drama();
                        Crime crime = new Crime();
                        for (Book book1 : bookList) {
                            if (book1.getBookType().equals("drama")) {
                                dramaFine += drama.calculateFineForABook(book1.getIssueDate());
                            } else if (book1.getBookType().equals("crime")) {
                                crimeFine += crime.calculateFineForABook(book1.getIssueDate());
                            }

                        }
                        System.out.println("Total Fine For Category Drama: $" + dramaFine);
                        System.out.println("Total Fine For Category Crime: $" + crimeFine);
                        break;
                    case 3:
                        int totalFine = 0;
                        Drama drama1 = new Drama();
                        Crime crime1 = new Crime();
                        for (Book book1 : bookList) {
                            if (book1.getBookType().equals("drama")) {
                                totalFine += drama1.calculateFineForABook(book1.getIssueDate());
                            } else if (book1.getBookType().equals("crime")) {
                                totalFine += crime1.calculateFineForABook(book1.getIssueDate());
                            }
                        }
                        System.out.println("Total Outstanding Fine Amount is: $" + totalFine);
                        break;
                    case 4:
                        //getting days for each book it is out of library for
                        ArrayList<Long> days = new ArrayList();
                        for (Book book2 : bookList) {
                            days.add(book2.calculateOverdueDays(book2.getIssueDate()));
                        }
                        //keeping an unsorted copy or the days list so that we may get the exact location of a book in list after sorting the array
                        ArrayList<Long> unsortedDays = new ArrayList<>(days);

                        Collections.sort(days, Collections.reverseOrder());//sorting the days to get in descending order. Top 3 will be on top 3 indexes 

                        int bookIndex1 = unsortedDays.indexOf(days.get(0));
                        int bookIndex2 = unsortedDays.indexOf(days.get(1));
                        int bookIndex3 = unsortedDays.indexOf(days.get(2));

                        String bookType1 = bookList.get(bookIndex1).getBookType();
                        String bookType2 = bookList.get(bookIndex2).getBookType();
                        String bookType3 = bookList.get(bookIndex3).getBookType();
                        /**
                         * This will calculate the fine for each book as
                         * numbered it uses conditional operator. Conditional
                         * operator works as follows: long result=condition ?
                         * action if condition is true : action if condition is
                         * false it will check the type of book with crime, if
                         * it is crime, it will calculate fine according to
                         * crime otherwise it will calculate fine according to
                         * drama
                         *
                         */
                        long fine1 = bookType1.equals("crime") ? new Crime().calculateFineForABook(bookList.get(bookIndex1).getIssueDate()) : new Drama().calculateFineForABook(bookList.get(bookIndex1).getIssueDate());
                        long fine2 = bookType2.equals("crime") ? new Crime().calculateFineForABook(bookList.get(bookIndex2).getIssueDate()) : new Drama().calculateFineForABook(bookList.get(bookIndex2).getIssueDate());
                        long fine3 = bookType3.equals("crime") ? new Crime().calculateFineForABook(bookList.get(bookIndex3).getIssueDate()) : new Drama().calculateFineForABook(bookList.get(bookIndex3).getIssueDate());

                        System.out.println("===========Top 3 Books with max revenue are ===========");
                        System.out.println("Book Title: " + bookList.get(bookIndex1).getTitle() + " Borrower: " + bookList.get(bookIndex1).getBorrowerName() + " Fine Amount: " + fine1 + " Total Days: " + days.get(0));
                        System.out.println("Book Title: " + bookList.get(bookIndex2).getTitle() + " Borrower: " + bookList.get(bookIndex2).getBorrowerName() + " Fine Amount: " + fine2 + " Total Days: " + days.get(1));
                        System.out.println("Book Title: " + bookList.get(bookIndex3).getTitle() + " Borrower: " + bookList.get(bookIndex3).getBorrowerName() + " Fine Amount: " + fine3 + " Total Days: " + days.get(2));
                        System.out.println("=======================================================");
                        System.out.println();//print a new line

                        break;

                    case 5:
                        System.out.println("=========Books that are over-due for more than 10 days======");
                        for (Book book3 : bookList) {
                            long totalDays = book3.calculateOverdueDays(book3.getIssueDate());
                            if (totalDays > 10) {
                                System.out.println("Book Title: " + book3.getTitle() + ", Borrower: " + book3.getBorrowerName() + ", Days Passed: " + totalDays);
                            }
                        }
                        System.out.println();
                        break;
                    case 6:
//getting days for each book it is out of library for
                        ArrayList<Long> daysBookIsOut = new ArrayList();
                        for (Book book2 : bookList) {
                            daysBookIsOut.add(book2.calculateOverdueDays(book2.getIssueDate()));
                        }
                        //keeping an unsorted copy or the days list so that we may get the exact location of a book in list after sorting the array
                        ArrayList<Long> unsortedDaysList = new ArrayList<>(daysBookIsOut);

                        Collections.sort(daysBookIsOut, Collections.reverseOrder());//sorting the days to get in descending order. Top 3 will be on top 3 indexes 

                        System.out.println("=========Most Popular Book=========");
                        int mostPopularBookIndex = unsortedDaysList.indexOf(daysBookIsOut.get(0));
                        System.out.println("Book Title: " + bookList.get(mostPopularBookIndex).getTitle() + " Total Days: " + daysBookIsOut.get(mostPopularBookIndex));

                        int leastPopularBookIndex = unsortedDaysList.indexOf(daysBookIsOut.get(daysBookIsOut.size() - 1));

                        System.out.println("=========Least Popular Book=========");
                        System.out.println("Book Title: " + bookList.get(leastPopularBookIndex).getTitle() + " Total Days: " + daysBookIsOut.get(daysBookIsOut.size() - 1));
                        System.out.println();
                        break;
                    case 7:
                        String borrowerName = "";
                        System.out.println("Enter Book Name: ");
                        String bookName = scan.nextLine();
                        for (Book book1 : bookList) {
                            if (book1.getTitle().trim().equals(bookName)) {//.trim() removes any spaces from the string
                                borrowerName = book1.getBorrowerName();

                            }
                        }
                        System.out.println("===============");

                        System.out.println("Book(s) borrowed by " + borrowerName + "");

                        for (Book book1 : bookList) {

                            if (book1.getBorrowerName().trim().equals(borrowerName)) {
                                if (book1.getBookType().equals("drama")) {
                                    long fine = new Drama().calculateFineForABook(book1.getIssueDate());
                                    System.out.println("Book Title: " + book1.getTitle());
                                    System.out.println("Fine Amount: " + fine);
                                } else if (book1.getBookType().equals("crime")) {

                                    long fine = new Crime().calculateFineForABook(book1.getIssueDate());
                                    System.out.println("===============");
                                    System.out.println("Book Title: " + book1.getTitle());
                                    System.out.println("Fine Amount: " + fine);

                                }

                            }
                        }
                        System.out.println("===============");

                        break;
                    case 8:
                        System.out.println("Enter Book Id to search data(1-8): ");
                        String bookId = scan.nextLine();
                        System.out.println("===============");

                        for (Book book2 : bookList) {
                            if (book2.getId().equals(bookId)) {
                                System.out.println("ISBN: " + book2.getIsbn());
                                System.out.println("Title: " + book2.getTitle());
                                System.out.println("Author: " + book2.getAuthorFirstName() + " " + book2.getAuthorLastName());
                                System.out.println("pages: " + book2.getPages());
                            }
                        }
                        System.out.println("===============");

                        break;
                    case 9:
                        System.out.println("Enter First/Last name of Author");
                        String author = scan.nextLine();
                        System.out.println("===============");

                        for (Book book1 : bookList) {
                            if (book1.getAuthorFirstName().trim().equals(author.trim()) || book1.getAuthorLastName().trim().equals(author.trim())) {
                                System.out.println("Title: " + book1.getTitle());
                                System.out.println("ISBN: " + book1.getIsbn());
                                System.out.println("Total Pages: " + book1.getPages());

                            }
                        }
                        System.out.println("===============");

                        break;
                    case 10:
                        sortedList = book.sortBooksById(bookList);
                        System.out.println("===============");

                        System.out.println("Sorted Books are ");
                        for (Book book1 : sortedList) {
                            System.out.println("ID: " + book1.getId());
                            System.out.println("ISBN: " + book1.getIsbn());
                            System.out.println("Title: " + book1.getTitle());
                            System.out.println("Author: " + book1.getAuthorFirstName());
                            System.out.println("Pages: " + book1.getPages());
                            System.out.println("Type: " + book1.getBookType());
                            System.out.println("");
                        }
                        System.out.println("===============");

                        break;
                    case 11:
                  try {
                        FileWriter csvWriter = new FileWriter("SortedBooks.csv");

                        csvWriter.write("ID,ISBN,Title,Author First Name,Author Last Name,Pages,Type");
                        csvWriter.write("\n");
                        for (Book book1 : sortedList) {
                            csvWriter.write(book1.getId() + "," + book1.getIsbn() + "," + book1.getTitle() + "," + book1.getAuthorFirstName() + "," + book1.getAuthorLastName() + "," + book1.getPages() + "," + book1.getBookType());
                            csvWriter.write("\n");
                        }

                        csvWriter.close();
                        System.out.println("Successfully wrote to the CSV file SortedBooks.csv.");
                    } catch (IOException e) {
                        System.out.println("File I/O error occurred.");
                        e.printStackTrace();
                    }
                    break;
                    default:
                        System.err.println("Invalid choice! Please input again");
                        break;
                }
            }
            //   System.out.println("Do you want to continue?");

        } while (choice != 1);
        System.out.println("Thank you for using this system");
    }

}

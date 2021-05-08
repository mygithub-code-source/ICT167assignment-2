
package ospi.park.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * It deals with the books of type Crime.
 *
 * @title Ospi Park (Library)
 * @author jamoo File Name: Crime.java Date: May 07, 2021 Assumptions: All the
 * inputs are supposed to be in same format as shown to the user while
 * prompting.
 *
 */
public class Crime extends Book {

    /**
     *
     * @param borrowDate
     * @return fine calculated for the time period from borrowDate for a book in
     * Crime category.
     */
    public long calculateFineForABook(String borrowDate) {

        SimpleDateFormat dateFormatObj = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date borrow = dateFormatObj.parse(borrowDate);
            
            String today = dateFormatObj.format(new Date());//getting curent day's date
            
            Date currentDate =dateFormatObj.parse(today);//parsing current day date to specific format
                        //checking difference in milli seconds

            long millis = Math.abs(currentDate.getTime() - borrow.getTime());

            // convert milliseconds to days
            long difference = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);

            if (difference <= 3) {
            } else if (difference > 3 && difference <= 8) {
                return 5 * difference;
            } else if (difference > 8 && difference <= 16) {
                return 10 * difference;
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
            System.err.println("Date Parse Exception occured");
        }
        return 0;
    }

}

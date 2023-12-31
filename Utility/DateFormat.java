package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The DateFormat class provides methods to convert between Date objects and
 * formatted date strings.
 */
public class DateFormat {
    Date date;
    String sdate;

    /**
     * Constructs a DateFormat object with the specified Date object.
     * The date is formatted using the pattern "dd/MM/yyyy HH:mm:ss".
     * 
     * @param d the Date object to be formatted
     */
    public DateFormat(Date d) {
        this.date = d;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.sdate = format.format(date);
    }

    /**
     * Constructs a DateFormat object with the specified formatted date string.
     * The string is parsed using the pattern "dd/MM/yyyy HH:mm:ss".
     * 
     * @param s the formatted date string to be parsed
     */
    public DateFormat(String s) {
        this.sdate = s;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            this.date = format.parse(s);
        } catch (Exception e) {
            System.out.println("Error in parsing date");
        }
    }

    /**
     * Returns the Date object associated with this DateFormat object.
     * 
     * @return the Date object
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the formatted date string associated with this DateFormat object.
     * 
     * @return the formatted date string
     */
    public String getSDate() {
        return this.sdate;
    }
}

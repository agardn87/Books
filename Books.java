package A00Books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Adam Gardner on 8/31/16.
 */
public class Books implements Comparable<Books>, Comparator<Books> {

    private String title, author;
    private int year;

    public Books(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public static List<Books> getList(String file) {
        String line;
        int counter = 0;
        Books books;
        List<Books> list = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            /*While a new line is available it checks to see if it follows the pattern of only two commas.
              If there are more than two then that book will be read in the err stream. If there are not line is put into
              a String array and then split apart by those two commas, then each cell is passed into the constructor
              creating a Books variable and adding each instance to the List of Books.*/

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.matches("^[\\s'&.A-Za-z0-9]+[,]+[\\s.'&A-Za-z0-9]+[,]\\d+")) {

                    System.err.println("Problem reading in \"" + line + "\"");
                } else{

                    String[] split = line.split("}");
                    //Had to parse the last cell to an int
                    int year = Integer.parseInt(split[2]);
                    books = new Books(split[0], null, year);
                    list.add(books);
                    counter++;
                }
            }
            bufferedReader.close();

            System.out.printf("Number of books read in: %s\n", counter);
            System.out.println();
            //Collections sorts the list in a natural order via the compareTo method
            Collections.sort(list);
            System.out.println("Sorted book list:\n");

            list.forEach(System.out::println);

            System.out.println();
            //Comparator sorts the list again via comapre method in reverse Order.
            list.sort(Comparator.reverseOrder());
            System.out.println("Reverse order:\n");

            list.forEach(System.out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return getTitle() + " by " + getAuthor() + " (" + getYear() + ")";
    }

    @Override
    public int compareTo(Books o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    @Override
    public int compare(Books o1, Books o2) {
        return o1.compareTo(o2);
    }
}

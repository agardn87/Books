package A00Books;

import static A00Books.Books.getList;

/**
 * Created by Adam Gardner on 8/31/16.
 */
public class BooksApp{

    public static void main(String[] args) {

        //You'll need to change the path of the csv file
        String file = "[file_path]";
        getList(file);
    }
}

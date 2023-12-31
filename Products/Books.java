package Products;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The Books class represents a book product in an e-commerce system.
 * It extends the Product class and adds additional properties specific to
 * books.
 */
public class Books extends Product {
    protected String author;
    protected String publisher;
    protected String[] genres;

    /**
     * Default constructor for the Books class.
     * It prompts the user to enter the author, publisher, and genres of the book.
     * It then sets the corresponding properties using the setter methods.
     */
    public Books() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the author of the book: ");
        String author = sc.nextLine();
        System.out.println("Enter the publisher of the book: ");
        String publisher = sc.nextLine();
        System.out.println("Enter the genres of the book: ");
        String[] genres = sc.nextLine().split(" ");
        ;
        setAuthor(author);
        setPublisher(publisher);
        setGenres(genres);
    }

    /**
     * Parameterized constructor for the Books class.
     * It takes in the name, description, price, stock quantity, image URL, author,
     * publisher, and genres of the book.
     * It sets the corresponding properties using the setter methods.
     *
     * @param name          the name of the book
     * @param description   the description of the book
     * @param price         the price of the book
     * @param stockQuantity the stock quantity of the book
     * @param imageUrl      the image URL of the book
     * @param author        the author of the book
     * @param publisher     the publisher of the book
     * @param genres        the genres of the book
     */
    public Books(int id, String name, String description, double price, int stockQuantity, String imageUrl,
            String author,
            String publisher, String[] genres) {
        super(id, name, description, price, stockQuantity, imageUrl);
        setAuthor(author);
        setPublisher(publisher);
        setGenres(genres);
    }

    /**
     * Overrides the toString method to provide a string representation of the Books
     * object.
     *
     * @return a string representation of the Books object
     */
    @Override
    public String toString() {
        return "Books , " + super.toString() + " , " + this.author + " , " + this.publisher + " , "
                + Arrays.toString(this.genres);
    }

    /**
     * Sets the author of the book.
     * Throws an IllegalArgumentException if the author is empty or null.
     *
     * @param author the author of the book
     * @throws IllegalArgumentException if the author is empty or null
     */
    protected void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        this.author = author;
    }

    /**
     * Sets the publisher of the book.
     * Throws an IllegalArgumentException if the publisher is empty or null.
     *
     * @param publisher the publisher of the book
     * @throws IllegalArgumentException if the publisher is empty or null
     */
    protected void setPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty())
            throw new IllegalArgumentException("Publisher cannot be empty");
        this.publisher = publisher;
    }

    /**
     * Sets the genres of the book.
     * Throws an IllegalArgumentException if the genres array is empty or null.
     *
     * @param genres the genres of the book
     * @throws IllegalArgumentException if the genres array is empty or null
     */
    protected void setGenres(String[] genres) {
        if (genres == null || genres.length == 0)
            throw new IllegalArgumentException("Genres cannot be empty");
        this.genres = genres;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    protected String getAuthor() {
        return this.author;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return the publisher of the book
     */
    protected String getPublisher() {
        return this.publisher;
    }

    /**
     * Gets the genres of the book.
     *
     * @return the genres of the book
     */
    protected String[] getGenres() {
        return this.genres;
    }

    public Books clone() {
        return new Books(this.id, this.name, this.description, this.price, this.stockQuantity, this.imageUrl,
                this.author, this.publisher, this.genres);
    }
}

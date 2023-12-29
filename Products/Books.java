package Products;

import java.util.Scanner;

public class Books extends Product {
    protected String author;
    protected String publisher;
    protected String[] genres;

    public Books() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the author of the book: ");
        String author = sc.nextLine();
        System.out.println("Enter the publisher of the book: ");
        String publisher = sc.nextLine();
        System.out.println("Enter the genres of the book: ");
        String[] genres = sc.nextLine().split(" ");
        sc.close();
        setAuthor(author);
        setPublisher(publisher);
        setGenres(genres);
    }

    public String toString() {
        return this.id + " , " + this.getName() + " , " + this.getDescription() + " , " + this.getPrice() + " , "
                + this.getStockQuantity() + " , " + this.getImageUrl() + " , " + this.author + " , " + this.publisher
                + " , " + String.join("//", this.genres);

    }

    protected void setAuthor(String author) {
        if (author == null || author.isEmpty())
            throw new IllegalArgumentException("Author cannot be empty");
        this.author = author;
    }

    protected void setPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty())
            throw new IllegalArgumentException("Publisher cannot be empty");
        this.publisher = publisher;
    }

    protected void setGenres(String[] genres) {
        if (genres == null || genres.length == 0)
            throw new IllegalArgumentException("Genres cannot be empty");
        this.genres = genres;
    }

    protected String getAuthor() {
        return this.author;
    }

    protected String getPublisher() {
        return this.publisher;
    }

    protected String[] getGenres() {
        return this.genres;
    }
}

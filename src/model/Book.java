/**
 * Die Klasse `Book` repräsentiert ein Buch mit seinen Attributen wie Buchnummer, Titel, Autor, Jahr und Genre.
 */
package model;

public class Book {
    private int bookNumber;
    private String title;
    private String author;
    private int year;
    private Genre genre;

    /**
     * Konstruktor, um eine neue Instanz eines Buchs zu erstellen.
     *
     * @param bookNumber Die eindeutige Kennung des Buchs.
     * @param title      Der Titel des Buchs.
     * @param author     Der Autor des Buchs.
     * @param year       Das Erscheinungsjahr des Buchs.
     * @param genre      Das Genre des Buchs.
     */
    public Book(int bookNumber, String title, String author, int year, Genre genre) {
        this.bookNumber = bookNumber;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public int getBookNumber() {
        return bookNumber;
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

    public Genre getGenre() {
        return genre;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Erstellt eine tiefe Kopie der Buch-Instanz.
     *
     * @return Eine geklonte Buch-Instanz.
     */
    public Book clone() {
        return new Book(bookNumber, title, author, year, genre.clone());
    }

    /**
     * Gibt eine formatierte Zeichenfolge zur Darstellung des Buchs zurück.
     *
     * @return Die formatierte Zeichenfolge, die das Buch repräsentiert.
     */
    @Override
    public String toString() {
        return String.format("%3s | %-20s | %-15s | %-10s | %s\n", bookNumber, title, author, year, genre);
    }
}

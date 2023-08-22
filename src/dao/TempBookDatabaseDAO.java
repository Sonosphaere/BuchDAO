package dao;

import model.Book;
import model.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse `TempBookDatabaseDAO` implementiert das `BookDAO`-Interface und stellt eine temporäre Datenbank für Bücher dar.
 */

public class TempBookDatabaseDAO implements BookDAO {

    private List<Book> bookList = new ArrayList<>();
    private List<Genre> genreList = new ArrayList<>();

    /**
     * Konstruktor, um die temporäre Datenbank mit Beispieldaten zu initialisieren.
     */

    public TempBookDatabaseDAO() {
        // Genres initialisieren
        Genre g1 = new Genre(1, "Krimi");
        Genre g2 = new Genre(2, "Thriller");
        Genre g3 = new Genre(3, "Roman");
        Genre g4 = new Genre(4, "Science Fiction");
        Genre g5 = new Genre(5, "Fantasy");
        Genre g6 = new Genre(6, "Biografie");
        Genre g7 = new Genre(7, "Horror");

        //Genres der genreList hinzufügen
        genreList.add(g1);
        genreList.add(g2);
        genreList.add(g3);
        genreList.add(g4);
        genreList.add(g5);
        genreList.add(g6);
        genreList.add(g7);

        //Bücher initialisiert und gleich der bookList zugefügt
        bookList.add(new Book(1, "Tage der Toten", "Don Wilson", 2010, g1));
        bookList.add(new Book(2, "Der süßeste Tod", "Heather Levy", 2023, g2));
        bookList.add(new Book(3, "Unterm Rad", "Hermann Hesse", 1906, g3));
        bookList.add(new Book(4, "Schöne Neue Welt", "Aldous Huxley", 1932, g4));
        bookList.add(new Book(5, "Rumo", "Walter Moers", 2003, g5));
        bookList.add(new Book(6, "Die Ärzte", "Markus Karg", 2008, g6));
        bookList.add(new Book(7, "Shining", "Stephen King", 1977, g7));

    }


    @Override
    public boolean insertBook(Book b) {
        //Prüft ob das Buch in der Liste vorhanden ist
        Book book = getBookByNumber(b.getBookNumber()) ;
        if (book != null) return false; // falls vorhanden, false

        //falls Buch NICHT vorhanden, Buch der Liste hinzufügen (als deep Copy => Clone)
        bookList.add(b.clone());
        return true;
    }

    @Override
    public Book getBookByNumber(int bnr) {
        // Suche das Buch in der Liste anhand der Buchnummer
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookNumber() == bnr) return bookList.get(i).clone(); // Gefundenen Film zurückgeben
            // (clonen =>  deep Copy)
        }
        return null; // Film nicht gefunden, Rückgabe von null
    }


    @Override
    public List<Book> getAllBooks() {
        // Eine deep Copy der Buchliste erstellen und zurückgeben
        List<Book> copyList = new ArrayList<>(bookList.size());
        for (int i = 0; i < bookList.size(); i++) {
            copyList.add(bookList.get(i).clone()); // Buch clonen und in die kopierte Liste hinzufügen
        }

        return copyList; // Rückgabe der kopierten Liste (Originalliste bleibt unverändert)


    }

    @Override
    public boolean updateBook(int bnr, Book b) {
        // Lösche das alte Buch (falls vorhanden) und füge den aktualisierten Film hinzu
        deleteBook(bnr);
        return insertBook(b);
    }

    @Override
    public boolean deleteBook(int bnr) {
        // Suche das Buch in der Liste anhand der Buchnummer und lösche ihn
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookNumber() == bnr) {
                bookList.remove(i);
                return true; // Buch erfolgreich gelöscht, Rückgabe von true

            }
        }
        return false;
    }

    @Override
    public int getHighestBookNumber() {
        // Finde die höchste Buchnummer in der Liste und gebe sie zurück
        int maxBookNumber = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookNumber() > maxBookNumber) maxBookNumber = bookList.get(i).getBookNumber();
        }
        return maxBookNumber;

    }

    @Override
    public boolean insertGenre(Genre g) {
        // Überprüfen, ob das Genre bereits in der Liste vorhanden ist
        Genre g2 = getGenreByNumber(g.getGenreNumber());
        if (g2 != null) return false; // Genre ist bereits vorhanden, Rückgabe von false
        genreList.add(g.clone()); // Genre in die Liste hinzufügen (clonen, um unabhängig zu sein)
        return true; // Erfolgreich eingefügt, Rückgabe von true

    }

    @Override
    public Genre getGenreByNumber(int gnr) {
        // Suche das Genre in der Liste anhand der Genrenummer
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreNumber() == gnr)
                return genreList.get(i).clone(); // Gefundenes Genre zurückgeben (clonen, um unabhängig zu sein)
        }
        return null; // Genre nicht gefunden, Rückgabe von null
    }


    @Override
    public Genre getGenreByName(String name) {
        // Suche das Genre in der Liste anhand des Namens
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreName().equals(name))
                return genreList.get(i).clone(); // Gefundenes Genre zurückgeben (clonen, um unabhängig zu sein)
        }
        return null; // Genre nicht gefunden, Rückgabe von null
    }


    @Override
    public List<Genre> getAllGenre() {
        // Eine tiefe Kopie der Genreliste erstellen und zurückgeben
        List<Genre> copyList = new ArrayList<>(genreList.size());
        for (int i = 0; i < genreList.size(); i++) {
            copyList.add(genreList.get(i).clone()); // Genre klonen und zur kopierten Liste hinzufügen
        }
        return copyList; // Rückgabe der kopierten Liste (Originalliste bleibt unverändert)

    }

    @Override
    public boolean updateGenre(int gnr, Genre g) {
        // Lösche das alte Genre (falls vorhanden) und füge das aktualisierte Genre hinzu
        deleteGenre(gnr);
        return insertGenre(g);

    }

    @Override
    public boolean deleteGenre(int gnr) {
        // Suche das Genre in der Liste anhand der Genrenummer und lösche es
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreNumber() == gnr) {
                genreList.remove(i);
                return true; // Genre erfolgreich gelöscht, Rückgabe von true
            }
        }
        return false; // Genre nicht gefunden, Rückgabe von false

    }

    @Override
    public int getHighestGenreNumber() {
        // Finde die höchste Genrenummer in der Liste und gebe sie zurück
        int maxGenrenummer = 0;
        for (int i = 0; i < genreList.size(); i++) {
            if (genreList.get(i).getGenreNumber() > maxGenrenummer) maxGenrenummer = genreList.get(i).getGenreNumber();
        }
        return maxGenrenummer;

    }
}

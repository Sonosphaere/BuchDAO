package dao;
import model.Book;
import model.Genre;

import java.util.List;

/**
 * Das `BookDAO`-Interface definiert Methoden, um auf Buch- und Genre-Informationen zuzugreifen.
 */
public interface BookDAO {

    /**
     * Fügt ein Buch der Datenbank hinzu.
     *
     * @param b Das Buch, das hinzugefügt werden soll.
     * @return `true`, wenn das Buch erfolgreich hinzugefügt wurde, ansonsten `false`.
     */
    boolean insertBook(Book b);

    /**
     * Ruft ein Buch anhand seiner Buchnummer ab.
     *
     * @param bnr Die Buchnummer des gesuchten Buchs.
     * @return Das gefundene Buch oder `null`, wenn kein Buch mit der angegebenen Buchnummer gefunden wurde.
     */
    Book getBookByNumber(int bnr);

    /**
     * Ruft eine Liste aller Bücher in der Datenbank ab.
     *
     * @return Eine Liste aller Bücher.
     */
    List<Book> getAllBooks();

    /**
     * Aktualisiert die Informationen eines Buchs in der Datenbank.
     *
     * @param bnr Die Buchnummer des zu aktualisierenden Buchs.
     * @param b   Die aktualisierten Buchinformationen.
     * @return `true`, wenn das Buch erfolgreich aktualisiert wurde, ansonsten `false`.
     */
    boolean updateBook(int bnr, Book b);

    /**
     * Löscht ein Buch aus der Datenbank.
     *
     * @param bnr Die Buchnummer des zu löschenden Buchs.
     * @return `true`, wenn das Buch erfolgreich gelöscht wurde, ansonsten `false`.
     */
    boolean deleteBook(int bnr);

    /**
     * Ruft die höchste Buchnummer in der Datenbank ab.
     *
     * @return Die höchste Buchnummer.
     */
    int getHighestBookNumber();

    /**
     * Fügt ein Genre der Datenbank hinzu.
     *
     * @param g Das Genre, das hinzugefügt werden soll.
     * @return `true`, wenn das Genre erfolgreich hinzugefügt wurde, ansonsten `false`.
     */
    boolean insertGenre(Genre g);

    /**
     * Ruft ein Genre anhand seiner Genrenummer ab.
     *
     * @param gnr Die Genrenummer des gesuchten Genres.
     * @return Das gefundene Genre oder `null`, wenn kein Genre mit der angegebenen Genrenummer gefunden wurde.
     */
    Genre getGenreByNumber(int gnr);

    /**
     * Ruft ein Genre anhand seines Namens ab.
     *
     * @param name Der Name des gesuchten Genres.
     * @return Das gefundene Genre oder `null`, wenn kein Genre mit dem angegebenen Namen gefunden wurde.
     */
    Genre getGenreByName(String name);

    /**
     * Ruft eine Liste aller Genres in der Datenbank ab.
     *
     * @return Eine Liste aller Genres.
     */
    List<Genre> getAllGenre();

    /**
     * Aktualisiert die Informationen eines Genres in der Datenbank.
     *
     * @param gnr Die Genrenummer des zu aktualisierenden Genres.
     * @param g   Die aktualisierten Genreinformationen.
     * @return `true`, wenn das Genre erfolgreich aktualisiert wurde, ansonsten `false`.
     */
    boolean updateGenre(int gnr, Genre g);

    /**
     * Löscht ein Genre aus der Datenbank.
     *
     * @param gnr Die Genrenummer des zu löschenden Genres.
     * @return `true`, wenn das Genre erfolgreich gelöscht wurde, ansonsten `false`.
     */
    boolean deleteGenre(int gnr);

    /**
     * Ruft die höchste Genrenummer in der Datenbank ab.
     *
     * @return Die höchste Genrenummer.
     */
    int getHighestGenreNumber();
}

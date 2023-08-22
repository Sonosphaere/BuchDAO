package model;

/**
 * Die Klasse `Genre` repräsentiert ein Genre mit seinen Attributen wie Genrenummer und Genrename.
 */
public class Genre {
    private int genreNumber;
    private String genreName;

    /**
     * Konstruktor, um eine neue Instanz eines Genres zu erstellen.
     *
     * @param genreNumber Die eindeutige Kennung des Genres.
     * @param genreName   Der Name des Genres.
     */
    public Genre(int genreNumber, String genreName) {
        this.genreNumber = genreNumber;
        this.genreName = genreName;
    }

    /**
     * Ruft die Genrenummer des Genres ab.
     *
     * @return Die Genrenummer.
     */
    public int getGenreNumber() {
        return genreNumber;
    }

    /**
     * Setzt die Genrenummer des Genres.
     *
     * @param genreNumber Die zu setzende Genrenummer.
     */
    public void setGenreNumber(int genreNumber) {
        this.genreNumber = genreNumber;
    }

    /**
     * Ruft den Namen des Genres ab.
     *
     * @return Der Genrename.
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Setzt den Namen des Genres.
     *
     * @param genreName Der zu setzende Genrename.
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    /**
     * Erstellt eine tiefe Kopie der Genre-Instanz.
     *
     * @return Eine geklonte Genre-Instanz.
     */
    public Genre clone() {
        return new Genre(genreNumber, genreName);
    }

    /**
     * Gibt den Genrename als Zeichenfolge zurück.
     *
     * @return Der Genrename als Zeichenfolge.
     */
    @Override
    public String toString() {
        return genreName;
    }
}

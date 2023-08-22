package controller;

import dao.BookDAO;
import dao.TempBookDatabaseDAO;
import model.Book;
import model.Genre;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final BookDAO bookDB = new TempBookDatabaseDAO();

    private static final Scanner eingabe = new Scanner(System.in);

    public static void main(String[] args) {
        Hauptschleife:
        while (true) {
            // Hauptmenü anzeigen
            System.out.println("Hauptmenu");
            System.out.println("=========");
            System.out.println("1 - Neues Buch anlegen");
            System.out.println("2 - Alle Bücher anzeigen");
            System.out.println("3 - Buch ändern");
            System.out.println("4 - Buch löschen");
            System.out.println("5 - Neues Genre anlegen");
            System.out.println("6 - Alle Genres Anzeigen");
            System.out.println("9 - Programm beenden");

            int auswahl = 0;
            try {
                // Benutzereingabe für die Menüauswahl lesen
                auswahl = eingabe.nextInt();
            } catch (InputMismatchException e) {
                // Bei ungültiger Eingabe (z. B. Buchstabe statt Zahl) eine Fehlermeldung ausgeben
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                eingabe.nextLine(); // Rest der Eingabezeile konsumieren
                continue; // Schleife erneut durchlaufen, um erneute Eingabe zu ermöglichen
            }

            // Menüoptionen verarbeiten
            switch (auswahl) {
                case 1:
                    createBook();
                    break;
                case 2:
                    showBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    createGenre();
                    break;
                case 6:
                    showGenre();
                    break;
                case 9:
                    // Programm beenden
                    break Hauptschleife;
                default:
                    System.out.println("Ungültige Eingabe, bitte nochmal versuchen");
            }
        }
        System.out.println("Vielen Dank und auf Wiedersehen");
    }


    private static void createBook() {
        int bookNumber = 0;
        Book book = null;
        while (true) {
            try {
                // Eingabe einer Buchnummer anfordern
                System.out.println("Buchnummer zuweisen lassen (0) oder");
                System.out.print("bitte eine Buchnummer eingeben: ");
                bookNumber = eingabe.nextInt();

                if (bookNumber == 0) {
                    // Automatische Zuweisung der höchsten Buchnummer + 1
                    bookNumber = bookDB.getHighestBookNumber() + 1;
                    System.out.println("Wir haben die Buchnummer " + bookNumber + " für Sie gewählt.");
                    break; // Schleife beenden, da gültige Eingabe erfolgt ist
                }
                // negativer Zahlenwert abfangen
                if (bookNumber < 0) {
                    System.out.println("Negative Buchnummern sind ungültig. Bitte geben Sie eine positive Zahl ein!");
                    continue; // rausspringen und Schleife wiederholen
                }
                // überprüfen ob Buchnummer schon angelegt wenn nicht Buchnummer vergeben
                book = bookDB.getBookByNumber(bookNumber);
                if (book != null) {
                    System.out.println("Die Nummer " + bookNumber + " ist schon vergeben für das Buch '" + book.getTitle() + "'");
                    continue;
                } else {
                    System.out.println("Deine Buchnummer lautet " + bookNumber);
                }

                // Gültige Eingabe erfolgt, Schleife beenden
                break;

            } catch (InputMismatchException e) {
                //Exception werfen bei ungültiger Eingabe(Buchstaben etc.)
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein!");
                eingabe.nextLine(); // Puffer leeren
            }
        }

        eingabe.nextLine(); // Rest der Eingabezeile konsumieren
        System.out.println();


        String title = "";
        while (title.isBlank()) {
            // Eingabe eines Titels anfordern und sicherstellen, dass der Titel nicht leer ist
            System.out.print("Bitte den Titel eingeben: ");
            title = eingabe.nextLine();
            if (title.isBlank()) {
                System.out.println("Dieser Titel ist ungültig");
            }
        }
        System.out.println("Das Buch heißt '" + title + "'");


        String author = "";
        while (author.isBlank()) {
            // Eingabe eines Titels anfordern und sicherstellen, dass der Titel nicht leer ist
            System.out.print("Bitte den Autor eingeben: ");
            author = eingabe.nextLine();

            if (author.isBlank()) {
                System.out.println("Dieser Autor ist ungültig");
            }
        }
        System.out.println("Der Autor des Buches '" + title + "' lautet " + author);
        System.out.println();


        String genreName;
        Genre genre = null;

// Eingabe des Genres anfordern
        System.out.println("Bitte Genre auswählen: ");

        List<Genre> genreList = bookDB.getAllGenre();
        int genreIndex = 0;

        while (true) {
            for (int i = 0; i < genreList.size(); i++) {
                System.out.println((i + 1) + ": " + genreList.get(i).getGenreName());
            }
            try {
                System.out.print("Ihre Wahl: ");
                genreIndex = eingabe.nextInt();
                eingabe.nextLine();

                if (genreIndex >= 1 && genreIndex <= genreList.size()) {
                    genre = genreList.get(genreIndex - 1);
                    System.out.println("Sie haben das Genre '" + genre.getGenreName() + "' gewählt");
                    break; // Schleife beenden, da gültige Eingabe erfolgt ist
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein!");
                }
            } catch (InputMismatchException e) {
                //Exception werfen bei ungültiger Eingabe(Buchstaben etc.)
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein!");
                eingabe.nextLine(); // Puffer leeren
            }
        }

        System.out.println();


        int year = 0;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        while (true) {
            try {
                // Eingabe des Erscheinungsjahr anfordern und sicherstellen, dass es im gültigen Bereich liegt
                System.out.print("Bitte Erscheinungsjahr eingeben: ");
                year = eingabe.nextInt();

                if (year < 0 || year > currentYear) {
                    System.out.println("Ungültiges Erscheinungsjahr!");
                    System.out.println("Bitte geben Sie ein Jahr zwischen 0 und " + currentYear + " ein.");
                    continue;
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein!");
                eingabe.nextLine();
            }
        }
        System.out.println("Eingegebenes Erscheinungsjahr: " + year);

        System.out.println();


        // Eingegebenes Buch in die Datenbank einfügen
        book = new Book(bookNumber, title, author, year, genre);
        if (bookDB.insertBook(book)) {
            System.out.println("Das Buch '" + book.getTitle() +
                    "' wurde der Datenbank hinzugefügt.");
        }


    }

    private static void updateBook() {
        while (true) {
            // Buchnummer für das zu aktualisierende Buch vom Benutzer einlesen
            System.out.print("Bitte die Buchnummer des zu aktualisierenden Buchs eingeben: ");
            int bookNumber = 0;
            try {
                bookNumber = eingabe.nextInt();
                eingabe.nextLine(); // Puffer leeren
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                eingabe.nextLine(); // Puffer leeren
                continue; // Schleife erneut durchlaufen
            }

            // Buch in der Datenbank suchen
            Book book = bookDB.getBookByNumber(bookNumber);

            if (book != null) {
                System.out.println("Aktuell gespeichertes Buch:");
                System.out.println(book);

                // Neue Werte für das Buch abfragen
                System.out.println("Bitte geben Sie die neuen Daten für das Buch ein:");

                // Eingabe des Titels
                System.out.print("Neuer Titel: ");
                String newTitle = eingabe.nextLine();

                // Eingabe des Autors
                System.out.print("Neuer Autor: ");
                String newAuthor = eingabe.nextLine();

                // Erscheinungsjahr (year) für das Buch aktualisieren
                int year = 0;
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                while (true) {
                    try {
                        // Eingabe des Erscheinungsjahrs anfordern und sicherstellen, dass es im gültigen Bereich liegt
                        System.out.print("Bitte neues Erscheinungsjahr eingeben: ");
                        year = eingabe.nextInt();
                        eingabe.nextLine(); // Puffer leeren

                        if (year < 0 || year > currentYear) {
                            System.out.println("Ungültiges Erscheinungsjahr!");
                            System.out.println("Bitte geben Sie ein Jahr zwischen 0 und " + currentYear + " ein.");
                            continue;
                        } else {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein!");
                        eingabe.nextLine(); // Puffer leeren
                    }
                }

                // Schleife für Genre-Auswahl
                Genre newGenre = null;
                while (true) {
                    // Verfügbare Genres anzeigen
                    System.out.println("Verfügbare Genres:");
                    List<Genre> genreList = bookDB.getAllGenre();
                    for (int i = 0; i < genreList.size(); i++) {
                        Genre genre = genreList.get(i);
                        System.out.println(genre.getGenreNumber() + ": " + genre.getGenreName());
                    }


                    int selectedGenreNumber = 0;
                    while (true) {
                        try {
                            System.out.print("Bitte wählen Sie ein Genre mit der entsprechenden Nummer: ");
                            selectedGenreNumber = eingabe.nextInt();
                            eingabe.nextLine(); // Puffer leeren
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ungültige Eingabe. Bitte eine gültige Nummer eingeben!");
                            eingabe.nextLine(); // Puffer leeren
                        }
                    }

                    // Das ausgewählte Genre anhand der Nummer finden
                    for (int i = 0; i < genreList.size(); i++) {
                        Genre genre = genreList.get(i);
                        if (genre.getGenreNumber() == selectedGenreNumber) {
                            newGenre = genre;
                            break;
                        }
                    }


                    if (newGenre == null) {
                        System.out.println("Ungültige Genre-Auswahl. Bitte eine gültige Nummer wählen.");
                        continue; // Schleife erneut durchlaufen, um gültige Auswahl zu treffen
                    }

                    // Das Buch aktualisieren
                    Book updatedBook = new Book(bookNumber, newTitle, newAuthor, year, newGenre);
                    if (bookDB.updateBook(bookNumber, updatedBook)) {
                        System.out.println("Buch erfolgreich aktualisiert:");
                        System.out.println(updatedBook);

                    } else {
                        System.out.println("Fehler beim Aktualisieren des Buchs.");
                    }

                    break; // Schleife für Genre-Auswahl beenden
                }
                break;

            } else {
                System.out.println("Buch mit der angegebenen Buchnummer nicht gefunden.");
            }
        }
    }
        private static void showBook () {
            // Alle Filme aus der Datenbank abrufen und anzeigen
            List<Book> bookList = bookDB.getAllBooks();
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                System.out.println(book);
            }
        }

        private static void deleteBook () {
            try {
                // Buchnummer für das zu löschende Buch vom Benutzer einlesen
                System.out.print("Bitte die zu löschende Buchnummer eingeben: ");
                int bookNumber = eingabe.nextInt();
                eingabe.nextLine(); // Puffer leeren
                Book book = bookDB.getBookByNumber(bookNumber);
                if (book != null) {
                    System.out.println("Achtung. Sie wollen das Buch '" + book.getTitle() + "' löschen.");
                    System.out.print("Wirklich löschen (j/n)? ");
                    char auswahl = eingabe.next().toUpperCase().charAt(0);
                    eingabe.nextLine();
                    if (auswahl == 'J') {
                        // Buch aus der Datenbank löschen
                        if (bookDB.deleteBook(bookNumber)) {
                            System.out.println("Buch '"
                                    + book.getTitle()
                                    + "' wurde gelöscht.");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                // Bei ungültiger Eingabe (z. B. Buchstabe statt Zahl) eine Fehlermeldung ausgeben
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                eingabe.nextLine(); // Rest der Eingabezeile konsumieren
            }
        }

        public static void createGenre () {
            //Genre erstellen
            int genreNumber;
            Genre genre = null;

            while (true) {
                try {
                    // Eingabe einer Genrenummer anfordern
                    System.out.println("Bitte eine Genrenummer eingeben: ");
                    System.out.println("Wenn Sie 0 eingeben wird automatisch die höchste Genrenummer gewählt!");
                    genreNumber = eingabe.nextInt();
                    eingabe.nextLine(); // Rest der Zeile konsumieren

                    // Überprüfen, ob der Benutzer 0 eingegeben hat, um die Genrenummer automatisch zuzuweisen
                    if (genreNumber == 0) {
                        // Die höchste vorhandene Genrenummer ermitteln und um 1 erhöhen
                        genreNumber = bookDB.getHighestGenreNumber() + 1;
                        System.out.println("Wir haben die Genrenummer " + genreNumber +
                                " für Sie gewählt");
                    }

                    // Prüfen, ob das Genre mit der eingegebenen Nummer bereits existiert
                    genre = bookDB.getGenreByNumber(genreNumber);

                    if (genre != null) {
                        // Eine bereits vorhandene Genrenummer wurde gewählt, und das zugehörige Genre wird angezeigt
                        System.out.println("Die Genrenummer ist bereits vergeben für das Genre '" + genre.getGenreName() + "'");
                    } else {
                        break; // Schleife beenden, da gültige Eingabe erfolgt ist
                    }
                } catch (InputMismatchException e) {
                    // Bei ungültiger Eingabe (z. B. Buchstabe statt Zahl) eine Fehlermeldung ausgeben
                    System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                    eingabe.nextLine(); // Rest der Eingabezeile konsumieren
                }
            }


            String genreName;
            while (true) {
                // Eingabe des Genrenamens
                System.out.print("Bitte geben Sie das Genre ein: ");
                genreName = eingabe.nextLine();

                // Überprüfen, ob das eingegebene Genre existiert, sonst nachfragen, ob es neu angelegt werden soll
                genre = bookDB.getGenreByName(genreName);

                if (genre == null) {
                    System.out.print("Unbekanntes Genre '" +
                            genreName +
                            "'. Soll dies neu angelegt werden (j/n)?");
                    char auswahl = eingabe.next().toUpperCase().charAt(0);
                    eingabe.nextLine();

                    if (auswahl == 'J') {
                        // Wenn ja, wird ein neues Genre mit der höchsten Genrenummer + 1 angelegt
                        genre = new Genre(bookDB.getHighestGenreNumber() + 1, genreName);

                        if (bookDB.insertGenre(genre)) {
                            System.out.println("Genre '"
                                    + genreName
                                    + "' wurde neu angelegt.");
                        }

                    } else {
                        break; // Schleife beenden, da gültige Eingabe erfolgt ist
                    }
                }
            }
        }

        private static void showGenre () {
            // Alle Filme aus der Datenbank abrufen und anzeigen
            List<Genre> genreList = bookDB.getAllGenre();
            for (int i = 0; i < genreList.size(); i++) {
                Genre genre = genreList.get(i);
                System.out.println(genre);
            }
        }


    }

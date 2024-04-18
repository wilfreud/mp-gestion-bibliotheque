package model.books;

/**
 * Represents an AudioBook, which is a specific type of Book.
 */
public class AudioBook extends Book {
    private long duration;
    private String audioFormat = "MP3";
    private String narrator;

    /**
     * Constructs an AudioBook with basic information.
     *
     * @param titre            The title of the audio book.
     * @param auteur           The author of the audio book.
     * @param anneePublication The publication year of the audio book.
     * @param ISBN             The ISBN (International Standard Book Number) of the audio book.
     */
    public AudioBook(String titre, String auteur, int anneePublication, String ISBN) {
        super(titre, auteur, anneePublication, ISBN);
    }

    /**
     * Constructs an AudioBook with detailed information.
     *
     * @param titre            The title of the audio book.
     * @param auteur           The author of the audio book.
     * @param anneePublication The publication year of the audio book.
     * @param ISBN             The ISBN (International Standard Book Number) of the audio book.
     * @param duration         The duration of the audio book in seconds.
     * @param audioFormat      The audio format of the audio book (e.g., MP3).
     * @param narrator         The narrator of the audio book.
     */
    public AudioBook(String titre, String auteur, int anneePublication, String ISBN,
                     long duration, String audioFormat, String narrator) {
        this(titre, auteur, anneePublication, ISBN);
        this.duration = duration;
        this.audioFormat = audioFormat;
        this.narrator = narrator;
    }

    /**
     * Retrieves the duration of the audio book.
     *
     * @return The duration of the audio book in seconds.
     */
    public long getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the audio book.
     *
     * @param duration The duration of the audio book in seconds.
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Retrieves the audio format of the audio book.
     *
     * @return The audio format of the audio book.
     */
    public String getAudioFormat() {
        return audioFormat;
    }

    /**
     * Sets the audio format of the audio book.
     *
     * @param audioFormat The audio format of the audio book.
     */
    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    /**
     * Retrieves the narrator of the audio book.
     *
     * @return The narrator of the audio book.
     */
    public String getNarrator() {
        return narrator;
    }

    /**
     * Sets the narrator of the audio book.
     *
     * @param narrator The narrator of the audio book.
     */
    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }
}

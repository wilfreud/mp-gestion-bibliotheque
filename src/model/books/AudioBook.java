package model.books;

public class AudioBook extends Book {
    private long duration;
    private String audioFormat = "MP3";
    private String narrator;

    public AudioBook(String titre, String auteur, int anneePublication, String ISBN) {
        super(titre, auteur, anneePublication, ISBN);
    }

    public AudioBook(String titre, String auteur, int anneePublication, String ISBN, long duration, String audioFormat, String narrator) {
        this(titre, auteur, anneePublication, ISBN);
        this.duration = duration;
        this.audioFormat = audioFormat;
        this.narrator = narrator;
    }
    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getAudioFormat() {
        return audioFormat;
    }

    public void setAudioFormat(String audioFormat) {
        this.audioFormat = audioFormat;
    }

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }
}

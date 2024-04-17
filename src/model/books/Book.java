package model.books;

abstract public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;

    public Book(String title, String author, int publicationYear, String ISBN){
        this.author = author;
        this.title = title;
        this.publicationYear = publicationYear;
        this.ISBN  = ISBN;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + title + '\'' +
                ", auteur='" + author + '\'' +
                ", anneePublication=" + publicationYear +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}

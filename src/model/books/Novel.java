package model.books;

import model.Utils.BookType;

public class Novel extends Book{
    private String subject;
    private final String category;
    public Novel(String title, String author, int publicationYear, String ISBN){
        super(title, author, publicationYear, ISBN);
        this.category = BookType.NOVEL.getKey();
    }
    public Novel(String title, String author, int publicationYear, String ISBN, String subject){
        this(title, author, publicationYear, ISBN);
        this.subject = subject;
    }

    public String getCategory() {
        return this.category;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}

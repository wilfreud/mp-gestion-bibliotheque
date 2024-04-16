package model.books;

public class Essay extends Book{
    private long wordCount;
    public Essay(String titre, String auteur, int anneePublication, String ISBN) {
        super(titre, auteur, anneePublication, ISBN);
    }

    public Essay(String titre, String auteur, int anneePublication, String ISBN, long wordsCount) {
        this(titre, auteur, anneePublication, ISBN);
        this.wordCount = wordsCount;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }
}

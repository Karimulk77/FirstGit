package com.cse.mist.bookstack;

/**
 * Created by User on 8/18/2017.
 */

public class Books {

    private String Bid;
    private String BookName;
    private String BookGenre;
    private String Writer;
    private String Type;


    public Books() {

    }

    public Books(String bid, String bookName, String bookGenre, String writer, String type) {
        this.Bid = bid;
        this.BookName = bookName;
        this.BookGenre = bookGenre;
        this.Writer = writer;
        this.Type = type;


    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookGenre() {
        return BookGenre;
    }

    public void setBookGenre(String bookGenre) {
        BookGenre = bookGenre;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }


}
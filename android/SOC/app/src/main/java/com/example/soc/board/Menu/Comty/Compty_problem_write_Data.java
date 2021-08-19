package com.example.soc.board.Menu.Comty;

import com.google.gson.annotations.SerializedName;

public class Compty_problem_write_Data {

    @SerializedName("title")
    private String title;
    @SerializedName("secondImageUrl")
    private String secondimageurl;
    @SerializedName("firstImageUrl")
    private String firstimageurl;
    @SerializedName("contents")
    private String contents;
    @SerializedName("boardId")
    private int boardid;
    public Compty_problem_write_Data(int boardid, String contents, String firstimageurl, String secondimageurl, String title) {
      this.boardid = boardid;
      this.contents = contents;
      this.firstimageurl = firstimageurl;
      this.secondimageurl = secondimageurl;
      this.title = title;
    }
    public Compty_problem_write_Data(int boardid, String contents, String secondimageurl, String title) {
        this.boardid = boardid;
        this.contents = contents;
        this.firstimageurl = firstimageurl;
        this.secondimageurl = secondimageurl;
        this.title = title;
    }
    public Compty_problem_write_Data(int boardid, String contents, String title) {
        this.boardid = boardid;
        this.contents = contents;
        this.firstimageurl = firstimageurl;
        this.secondimageurl = secondimageurl;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondimageurl() {
        return secondimageurl;
    }

    public void setSecondimageurl(String secondimageurl) {
        this.secondimageurl = secondimageurl;
    }

    public String getFirstimageurl() {
        return firstimageurl;
    }

    public void setFirstimageurl(String firstimageurl) {
        this.firstimageurl = firstimageurl;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

}



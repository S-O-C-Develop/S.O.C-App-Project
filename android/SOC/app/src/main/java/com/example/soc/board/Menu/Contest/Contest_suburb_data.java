package com.example.soc.board.Menu.Contest;

public class Contest_suburb_data {
    private String title;
    private String imageUrl;
    private String clickUrl;

    public Contest_suburb_data() {
        this.title = title;
        this.imageUrl = imageUrl;
        this.clickUrl = clickUrl;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getTitle() {
        return title;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getClickUrl(){
        return clickUrl;
    }
    public void setClickUrl(String clickUrl){
        this.clickUrl=clickUrl;
    }
}

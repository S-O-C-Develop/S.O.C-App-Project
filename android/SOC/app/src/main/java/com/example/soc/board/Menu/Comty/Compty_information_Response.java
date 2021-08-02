package com.example.soc.board.Menu.Comty;

public class Compty_information_Response {
    public String time;
    public String nickname;
    public String content;
    public String title;
    public String image1;
    public String image2;
    public String reply;

    public Compty_information_Response(String title, String time, String content, String nickname, String image1, String image2, String reply){
        this.title = title;
        this.time = time;
        this.content = content;
        this.nickname = nickname;
        this.image1 = image1;
        this.image2 = image2;
        this.reply = reply;

    }
    public String getNickname ()
    {
        return nickname;
    }
    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public void setImage1 (String image1)
    {
        this.image1= image1;
    }
    public String getImage1 ()
    {
        return image1;
    }

    public void setImage2 (String image2)
    {
        this.image2= image2;
    }
    public String getImage2 ()
    {
        return image2;
    }


    public String getTime ()
    {
        return time;
    }
    public void setTime (String time)
    {
        this.time = time;
    }

    public String getTitle ()
    {
        return title;
    }
    public void setTitle (String title) { this.title = title; }

    public String getContent ()
    {
        return content;
    }
    public void setContent (String content) { this.content = content; }

    public String getReply()
    {
        return reply;
    }
    public void setReply (String reply) { this.reply = reply; }

}


package com.example.soc.board.Menu.Comty;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Compty_problem_Board_Response {

    @SerializedName("result")
    private Result result;
    @SerializedName("message")
    private String message;
    @SerializedName("isSuccess")
    private boolean issuccess;
    @SerializedName("code")
    private int code;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(boolean issuccess) {
        this.issuccess = issuccess;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class Result {
        @SerializedName("totalPages")
        private int totalpages;
        @SerializedName("totalElements")
        private int totalelements;
        @SerializedName("sort")
        private Sort sort;
        @SerializedName("size")
        private int size;
        @SerializedName("numberOfElements")
        private int numberofelements;
        @SerializedName("number")
        private int number;
        @SerializedName("last")
        private boolean last;
        @SerializedName("first")
        private boolean first;
        @SerializedName("empty")
        private boolean empty;
        @SerializedName("content")
        private List<Content> content;

        public int getTotalpages() {
            return totalpages;
        }

        public void setTotalpages(int totalpages) {
            this.totalpages = totalpages;
        }

        public int getTotalelements() {
            return totalelements;
        }

        public void setTotalelements(int totalelements) {
            this.totalelements = totalelements;
        }

        public Sort getSort() {
            return sort;
        }

        public void setSort(Sort sort) {
            this.sort = sort;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumberofelements() {
            return numberofelements;
        }

        public void setNumberofelements(int numberofelements) {
            this.numberofelements = numberofelements;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean getLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public boolean getFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean getEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public List<Content> getContent() {
            return content;
        }

        public void setContent(List<Content> content) {
            this.content = content;
        }
    }

    public static class Sort {
        @SerializedName("unsorted")
        private boolean unsorted;
        @SerializedName("sorted")
        private boolean sorted;
        @SerializedName("empty")
        private boolean empty;

        public boolean getUnsorted() {
            return unsorted;
        }

        public void setUnsorted(boolean unsorted) {
            this.unsorted = unsorted;
        }

        public boolean getSorted() {
            return sorted;
        }

        public void setSorted(boolean sorted) {
            this.sorted = sorted;
        }

        public boolean getEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }
    }

    public static class Content {
        @SerializedName("title")
        private String title;
        @SerializedName("status")
        private String status;
        @SerializedName("reportCount")
        private int reportcount;
        @SerializedName("postId")
        private int postid;
        @SerializedName("contents")
        private String contents;
        @SerializedName("author")
        private String author;
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getReportcount() {
            return reportcount;
        }

        public void setReportcount(int reportcount) {
            this.reportcount = reportcount;
        }

        public int getPostid() {
            return postid;
        }

        public void setPostid(int postid) {
            this.postid = postid;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

}

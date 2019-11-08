package com.leon.myblog.enity;

public class Category {
    private Integer id;

    private String categoryname;

    private Integer categoryimageid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Integer getCategoryimageid() {
        return categoryimageid;
    }

    public void setCategoryimageid(Integer categoryimageid) {
        this.categoryimageid = categoryimageid;
    }
}
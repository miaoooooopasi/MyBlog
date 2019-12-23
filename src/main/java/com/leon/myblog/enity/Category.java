package com.leon.myblog.enity;

import io.swagger.annotations.ApiModelProperty;

public class Category {

    @ApiModelProperty(hidden = true)
    private Integer id;

    private String categoryname;

    private Integer categoryimageid;

    //手动添加一对一关系
    private Categoryimage categoryimage;

    public Categoryimage getCategoryimage() {
        return categoryimage;
    }

    public void setCategoryimage(Categoryimage categoryimage) {
        this.categoryimage = categoryimage;
    }



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
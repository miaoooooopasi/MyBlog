package com.leon.myblog.enity;

import java.util.List;

public class Article {
    private Integer id;

    private String title;

    private String summary;

    private String createtime;

    private String modifytime;

    private Integer clicknums;

    private Integer userid;

    private Integer imageid;

    private Integer categoryid;

    private String content;


    //手动添加一对一关系
    private Articleimage articleimage;

    public Articleimage getArticleimage() {
        return articleimage;
    }

    public void setArticleimage(Articleimage articleimage) {
        this.articleimage = articleimage;
    }


    // 多对多
    private List<Tag> tags;
    // 一对一分类
    private Category category;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getClicknums() {
        return clicknums;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setClicknums(Integer clicknums) {
        this.clicknums = clicknums;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String  toString(){
        return "Article[id:"+getId()+", title:"+getTitle()+"createtime:"+getCreatetime()+"summary:"+getSummary()+"]";
    }
}

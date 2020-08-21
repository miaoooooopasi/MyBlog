package com.leon.myblog.enity;

public class Matter {
    private Integer id;

    private String name;

    private String createtime;

    private String stoptime;

    private String level;

    private String degree;

    private String leveldesc;

    private String degreedesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLeveldesc() {
        return leveldesc;
    }

    public void setLeveldesc(String leveldesc) {
        this.leveldesc = leveldesc;
    }

    public String getDegreedesc() {
        return degreedesc;
    }

    public void setDegreedesc(String degreedesc) {
        this.degreedesc = degreedesc;
    }
}
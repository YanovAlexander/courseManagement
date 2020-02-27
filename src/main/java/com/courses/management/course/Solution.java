package com.courses.management.course;

import com.courses.management.common.BaseEntity;

public class Solution extends BaseEntity {
    private String text;
    private SolutionStatus status;
    private int mark;
    private Homework homeWork;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SolutionStatus getStatus() {
        return status;
    }

    public void setStatus(SolutionStatus status) {
        this.status = status;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Homework getHomework() {
        return homeWork;
    }

    public void setHomework(Homework homeWork) {
        this.homeWork = homeWork;
    }
}

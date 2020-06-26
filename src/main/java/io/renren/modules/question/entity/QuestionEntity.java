package io.renren.modules.question.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("question")
public class QuestionEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 问题表ID
     */
    private int qid;
    /**
     * 关联试卷报表pid
     */
    private int pid;
    /**
     * 问题描述（问题描述与选项间使用，：，分隔符）（包括选项 - - 选项间使用 ，；，分隔符）
     */
    private  String question;
    /**
     * 答案 ，（多选题使用,;,分隔符）
     */
    private String ansower;
    /**
     * 题目类型
     */
    private  String type;
    /**
     * 题目分数
     */
    private int score;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnsower() {
        return ansower;
    }

    public void setAnsower(String ansower) {
        this.ansower = ansower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


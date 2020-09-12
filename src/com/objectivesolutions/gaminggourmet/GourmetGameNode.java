package com.objectivesolutions.gaminggourmet;

public class GourmetGameNode {

    private String question;
    private String value;

    private GourmetGameNode parent;
    private GourmetGameNode right;
    private GourmetGameNode left;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GourmetGameNode getParent() {
        return parent;
    }

    public void setParent(GourmetGameNode parent) {
        this.parent = parent;
    }

    public GourmetGameNode getRight() {
        return right;
    }

    public void setRight(GourmetGameNode right) {
        this.right = right;
    }

    public GourmetGameNode getLeft() {
        return left;
    }

    public void setLeft(GourmetGameNode left) {
        this.left = left;
    }

    public GourmetGameNode(String string, String value) {
        this.question = string;
        this.value = value;
    }
}

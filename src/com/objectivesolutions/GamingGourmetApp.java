package com.objectivesolutions;

import com.objectivesolutions.gaminggourmet.GourmetGameTree;

public class GamingGourmetApp {

    public static void main(String[] args) {
        GourmetGameTree gourmetGameTree = new GourmetGameTree();
        gourmetGameTree.init();

        while (gourmetGameTree.getxButtonNotPressed()) {
            gourmetGameTree.makeInitialQuestion();
            gourmetGameTree.runQuestions(gourmetGameTree.getRoot(), gourmetGameTree.getRoot(), 0);
        }
    }
}

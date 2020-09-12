package com.objectivesolutions.gaminggourmet;

import static com.objectivesolutions.gaminggourmet.DialogConsts.*;
import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.showInputDialog;

public class GourmetGameTree {

    private GourmetGameNode root;

    private boolean xButtonNotPressed;

    public GourmetGameTree() { }

    public void init() {
        root = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);
        GourmetGameNode initialRight = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_LASANHA, DialogConsts.LASANHA);
        GourmetGameNode inivialLeft = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_BOLO_DE_CHOCOLATE, DialogConsts.BOLO_CHOCOLATE);

        root.setRight(initialRight);
        root.setLeft(inivialLeft);
        initialRight.setParent(root);
        inivialLeft.setParent(root);
        xButtonNotPressed = true;
    }

    public void runQuestions(GourmetGameNode current, GourmetGameNode parent, int answer) {
        if (current == null) {
            if (answer == YES_OPTION) {
                showSuccess(ACERTEI_DE_NOVO);
                return;
            }
            if (answer == NO_OPTION) {
                askForNewNodes(parent);
                return;
            }
        }

        answer = askQuestion(current.getQuestion());

        if (answer == YES_OPTION) {
            runQuestions(current.getRight(), current, answer);
        }
        if (answer == NO_OPTION) {
            runQuestions(current.getLeft(), current, answer);
        }
        if (answer == CLOSED_OPTION) {
            xButtonNotPressed = false;
        }
    }

    GourmetGameNode askForNewNodes(GourmetGameNode parent) {
        String newElement = getDesiredInput(QUAL_PRATO_PENSOU);
        GourmetGameNode newLeaf = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E + newElement + QUESTION_MARK, newElement);

        String newAdjective = getDesiredInput(newElement + E_____MAS + parent.getValue() + NAO_E);
        GourmetGameNode newGourmetGameNode = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E + newAdjective + QUESTION_MARK, newAdjective);

        addNodes(parent, newLeaf, newGourmetGameNode);
        return newLeaf;
    }

    void addNodes(GourmetGameNode parent, GourmetGameNode newLeaf, GourmetGameNode newGourmetGameNode) {
        GourmetGameNode bigParrent = parent.getParent();

        newGourmetGameNode.setParent(bigParrent);
        newGourmetGameNode.setRight(newLeaf);
        newGourmetGameNode.setLeft(parent);
        parent.setParent(newGourmetGameNode);
        newLeaf.setParent(newGourmetGameNode);

        boolean isRight = bigParrent.getRight().getValue().equals(parent.getValue());
        if (isRight) {
            bigParrent.setRight(newGourmetGameNode);
        } else {
            bigParrent.setLeft(newGourmetGameNode);
        }
    }

    void showSuccess(String message) {
        showMessageDialog(null, message);
    }

    int askQuestion(String question) {
        return showConfirmDialog(null, question, "", YES_NO_OPTION);
    }

    public void makeInitialQuestion() {
        showMessageDialog(null, PENSE_EM_UM_PRATO_QUE_GOSTA);
    }

    String getDesiredInput(String message) {
        return showInputDialog(message);
    }

    public GourmetGameNode getRoot() {
        return root;
    }

    public boolean getxButtonNotPressed() {
        return xButtonNotPressed;
    }
}

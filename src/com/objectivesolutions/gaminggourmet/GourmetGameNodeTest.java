package com.objectivesolutions.gaminggourmet;

import org.junit.Test;

import static com.objectivesolutions.gaminggourmet.DialogConsts.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GourmetGameNodeTest {

    @Test
    public void shouldSetQuestionAndValueCorrectly() {
        GourmetGameNode node = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);

        assertEquals(O_PRATO_QUE_VC_PENSOU_E_MASSA, node.getQuestion());
        assertEquals(MASSA, node.getValue());
    }

    @Test
    public void shouldBeNullAtRightAndLeftInitially() {
        GourmetGameNode node = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);

        assertNull(node.getRight());
        assertNull(node.getLeft());
    }

    @Test
    public void shouldBeNullAtParentInitially() {
        GourmetGameNode node = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);

        assertNull(node.getParent());
    }

    @Test
    public void shouldSetRightAndParentCorrently() {
        GourmetGameNode node = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);
        GourmetGameNode nextNode = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_LASANHA, LASANHA);

        node.setRight(nextNode);
        nextNode.setParent(node);

        assertEquals(nextNode.getValue(), node.getRight().getValue());
        assertEquals(nextNode.getParent().getValue(), node.getValue());
    }

    @Test
    public void shouldSetLeftAndParentCorrently() {
        GourmetGameNode node = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_MASSA, MASSA);
        GourmetGameNode nextNode = new GourmetGameNode(O_PRATO_QUE_VC_PENSOU_E_LASANHA, LASANHA);

        node.setLeft(nextNode);
        nextNode.setParent(node);

        assertEquals(nextNode.getValue(), node.getLeft().getValue());
        assertEquals(nextNode.getParent().getValue(), node.getValue());
    }


}

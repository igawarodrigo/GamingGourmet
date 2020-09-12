package com.objectivesolutions.gaminggourmet;


import org.junit.Test;

import static com.objectivesolutions.gaminggourmet.DialogConsts.*;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class GourmetGameTreeTest {

    private static final String PASTEL = "Pastel";
    private static final String FRITO = "frito";
    private static final String O_PRATO_QUE_VC_PENSOU_É_FRITO = "O prato que vc pensou é frito?";
    private static final String O_PRATO_QUE_VC_PENSOU_É_PASTEL = "O prato que vc pensou é Pastel?";
    private static final String O_PRATO_QUE_VC_PENSOU_É_LASANHA = "O prato que vc pensou é lasanha?";

    @Test
    public void shouldSetRootNodeToMassa() {
        GourmetGameTree gourmetGameTree = new GourmetGameTree();
        gourmetGameTree.init();

        assertEquals(O_PRATO_QUE_VC_PENSOU_E_MASSA, gourmetGameTree.getRoot().getQuestion());
        assertEquals(MASSA, gourmetGameTree.getRoot().getValue());
    }

    @Test
    public void shouldSetInitialRightToLasanha() {
        GourmetGameTree gourmetGameTree = new GourmetGameTree();
        gourmetGameTree.init();

        assertEquals(O_PRATO_QUE_VC_PENSOU_E_LASANHA, gourmetGameTree.getRoot().getRight().getQuestion());
        assertEquals(LASANHA, gourmetGameTree.getRoot().getRight().getValue());
    }

    @Test
    public void shouldSetInitialLeftToBolo() {
        GourmetGameTree gourmetGameTree = new GourmetGameTree();
        gourmetGameTree.init();

        assertEquals(O_PRATO_QUE_VC_PENSOU_E_BOLO_DE_CHOCOLATE, gourmetGameTree.getRoot().getLeft().getQuestion());
        assertEquals(BOLO_CHOCOLATE, gourmetGameTree.getRoot().getLeft().getValue());
    }

    @Test
    public void shouldShowSuccessForInitialStateMassaAndLasanha() {
        GourmetGameTree gourmetGameTree = mock(GourmetGameTree.class);

        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA))).thenReturn(YES_OPTION);
        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA))).thenReturn(YES_OPTION);

        doCallRealMethod().when(gourmetGameTree).init();
        doCallRealMethod().when(gourmetGameTree).getRoot();
        doCallRealMethod().when(gourmetGameTree).runQuestions(any(), any(), anyInt());
        doNothing().when(gourmetGameTree).showSuccess(anyString());

        gourmetGameTree.init();
        gourmetGameTree.runQuestions(gourmetGameTree.getRoot(), gourmetGameTree.getRoot(), YES_OPTION);

        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA));
        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA));
        verify(gourmetGameTree).showSuccess(eq(ACERTEI_DE_NOVO));
    }

    @Test
    public void shouldShowSuccessForInitialMassaAndBolo() {
        GourmetGameTree gourmetGameTree = mock(GourmetGameTree.class);

        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA))).thenReturn(NO_OPTION);
        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_BOLO_DE_CHOCOLATE))).thenReturn(YES_OPTION);

        doCallRealMethod().when(gourmetGameTree).init();
        doCallRealMethod().when(gourmetGameTree).getRoot();
        doCallRealMethod().when(gourmetGameTree).runQuestions(any(), any(), anyInt());
        doNothing().when(gourmetGameTree).showSuccess(anyString());

        gourmetGameTree.init();
        gourmetGameTree.runQuestions(gourmetGameTree.getRoot(), gourmetGameTree.getRoot(), YES_OPTION);

        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA));
        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_BOLO_DE_CHOCOLATE));
        verify(gourmetGameTree).showSuccess(eq(ACERTEI_DE_NOVO));
    }

    @Test
    public void shouldCallForNewFood() {
        GourmetGameTree gourmetGameTree = mock(GourmetGameTree.class);

        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA))).thenReturn(YES_OPTION);
        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA))).thenReturn(NO_OPTION);

        doCallRealMethod().when(gourmetGameTree).init();
        doCallRealMethod().when(gourmetGameTree).getRoot();
        doCallRealMethod().when(gourmetGameTree).runQuestions(any(), any(), anyInt());

        when(gourmetGameTree.askForNewNodes(any())).thenReturn(new GourmetGameNode("", ""));

        gourmetGameTree.init();
        gourmetGameTree.runQuestions(gourmetGameTree.getRoot(), gourmetGameTree.getRoot(), YES_OPTION);

        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA));
        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA));
        verify(gourmetGameTree).askForNewNodes(any());
    }

    @Test
    public void shouldAddNewFoodBeforeLasanha() {
        GourmetGameTree gourmetGameTree = mock(GourmetGameTree.class);

        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA))).thenReturn(YES_OPTION);
        when(gourmetGameTree.askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA))).thenReturn(NO_OPTION);
        when(gourmetGameTree.getDesiredInput(QUAL_PRATO_PENSOU)).thenReturn(PASTEL);
        when(gourmetGameTree.getDesiredInput(PASTEL + E_____MAS + LASANHA + NAO_E)).thenReturn(FRITO);

        doCallRealMethod().when(gourmetGameTree).init();
        doCallRealMethod().when(gourmetGameTree).getRoot();
        doCallRealMethod().when(gourmetGameTree).runQuestions(any(), any(), anyInt());
        doCallRealMethod().when(gourmetGameTree).askForNewNodes(any());
        doCallRealMethod().when(gourmetGameTree).addNodes(any(), any(), any());

        gourmetGameTree.init();
        gourmetGameTree.runQuestions(gourmetGameTree.getRoot(), gourmetGameTree.getRoot(), YES_OPTION);

        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_MASSA));
        verify(gourmetGameTree).askQuestion(eq(O_PRATO_QUE_VC_PENSOU_E_LASANHA));
        verify(gourmetGameTree).askForNewNodes(any());
        verify(gourmetGameTree).addNodes(any(), any(), any());

        assertEquals(O_PRATO_QUE_VC_PENSOU_E_MASSA, gourmetGameTree.getRoot().getQuestion());
        assertEquals(O_PRATO_QUE_VC_PENSOU_É_FRITO, gourmetGameTree.getRoot().getRight().getQuestion());
        assertEquals(O_PRATO_QUE_VC_PENSOU_É_PASTEL, gourmetGameTree.getRoot().getRight().getRight().getQuestion());
        assertEquals(O_PRATO_QUE_VC_PENSOU_É_LASANHA, gourmetGameTree.getRoot().getRight().getLeft().getQuestion());
    }


}
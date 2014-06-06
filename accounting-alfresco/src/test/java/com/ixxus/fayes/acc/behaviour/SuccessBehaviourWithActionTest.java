package com.ixxus.fayes.acc.behaviour;

import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SuccessBehaviourWithActionTest {

    private PolicyComponent policyComponent;
    private NodeService nodeService;
    private ActionService actionService;

    @Before
    public void setUp() throws Exception {
        // create dependencies
        nodeService = mock(NodeService.class);
        actionService = mock(ActionService.class);
        policyComponent = mock(PolicyComponent.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() throws Exception {
        // create instance of class to test
        SuccessBehaviourWithAction successBehaviourWithAction = new SuccessBehaviourWithAction();

        // set dependencies
        successBehaviourWithAction.setPolicyComponent(policyComponent);

        // call method under test
        successBehaviourWithAction.init();

        // verify the right methods were called
        verify(policyComponent).bindClassBehaviour(any(QName.class), any(QName.class), any(JavaBehaviour.class));
    }

    @Test
    public void testOnAddAspect() throws Exception {
        // create instance of class to test
        SuccessBehaviourWithAction successBehaviourWithAction = new SuccessBehaviourWithAction();

        // create dependencies
        Action action = mock(Action.class);
        when(actionService.createAction(anyString())).thenReturn(action);
        NodeRef nodeRef = new NodeRef("workspace://spacesStore/testNode");
        QName qName = QName.createQName("test");

        // set dependencies
        successBehaviourWithAction.setNodeService(nodeService);
        successBehaviourWithAction.setActionService(actionService);

        // call method under test
        successBehaviourWithAction.onAddAspect(nodeRef, qName);

        // verify method calls
        verify(nodeService).setProperty(any(NodeRef.class), any(QName.class), anyString());
        verify(actionService).executeAction(action, nodeRef, false, true);
    }
}
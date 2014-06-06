package com.ixxus.fayes.acc.action;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.attributes.AttributeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SuccessAttributeActionExecutorTest {

    private NodeService nodeService;
    private AttributeService attributeService;

    @Before
    public void setUp() throws Exception {
        // create dependencies
        nodeService = mock(NodeService.class);
        attributeService = mock(AttributeService.class);
    }

    @Test
    public void testExecuteImpl() throws Exception {
        // create instance of class to test
        SuccessAttributeActionExecutor successAttributeActionExecutor = new SuccessAttributeActionExecutor();

        // create dependencies
        Action action = mock(Action.class);
        NodeRef nodeRef = new NodeRef("workspace://spaceStore/testNode");

        // set dependencies
        successAttributeActionExecutor.setNodeService(nodeService);
        successAttributeActionExecutor.setAttributeService(attributeService);

        // call method under test
        successAttributeActionExecutor.executeImpl(action, nodeRef);

        // verify method calls
        verify(attributeService).setAttribute(any(Serializable.class), any(Serializable.class),any(Serializable.class),any(Serializable.class));
    }
}
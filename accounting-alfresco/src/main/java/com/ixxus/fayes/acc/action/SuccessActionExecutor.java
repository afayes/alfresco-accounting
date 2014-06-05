package com.ixxus.fayes.acc.action;

import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by afayes on 05/06/2014.
 */
public class SuccessActionExecutor extends ActionExecuterAbstractBase {
    public static final String NAME = "successAction";
    public static final String PARAM_TARGET_PROPERTY_NAME = "targetPropertyName";

    // Dependencies
    private NodeService nodeService;
    private Logger logger = Logger.getLogger(SuccessActionExecutor.class);

    /**
     * @see org.alfresco.repo.action.ParameterizedItemAbstractBase#addParameterDefinitions(java.util.List)
     */
    @Override
    protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        // todo
        // Add definitions for action parameters
        paramList.add(
                new ParameterDefinitionImpl(                       // Create a new parameter defintion to add to the list
                        PARAM_TARGET_PROPERTY_NAME,                // The name used to identify the parameter
                        DataTypeDefinition.QNAME,                  // The parameter value type
                        true,                                      // Indicates whether the parameter is mandatory
                        getParamDisplayLabel(PARAM_TARGET_PROPERTY_NAME)));      // The parameters display label
    }

    /**
     * @see org.alfresco.repo.action.executer.ActionExecuterAbstractBase#executeImpl(Action, NodeRef)
     */
    public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
        if (logger.isDebugEnabled()) logger.debug("Inside executeImpl");
        QName targetPropertyName = (QName) action.getParameterValue(PARAM_TARGET_PROPERTY_NAME);
        nodeService.setProperty(actionedUponNodeRef, targetPropertyName, "SUCCESS");
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
}

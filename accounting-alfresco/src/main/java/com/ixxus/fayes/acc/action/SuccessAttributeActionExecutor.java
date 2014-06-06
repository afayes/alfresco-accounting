package com.ixxus.fayes.acc.action;

import org.alfresco.repo.action.ParameterDefinitionImpl;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.attributes.AttributeService;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by afayes on 05/06/2014.
 */
public class SuccessAttributeActionExecutor extends ActionExecuterAbstractBase {
    public static final String NAME = "successAttributeAction";
    public static final String PARAM_PROPERTY_NAME = "propertyName";

    // Dependencies
    private NodeService nodeService;
    private AttributeService attributeService;
    private Logger logger = Logger.getLogger(SuccessAttributeActionExecutor.class);

    /**
     * @see org.alfresco.repo.action.ParameterizedItemAbstractBase#addParameterDefinitions(java.util.List)
     */
    @Override
    protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
        // Add definitions for action parameters
        paramList.add(
                new ParameterDefinitionImpl(                       // Create a new parameter defintion to add to the list
                        PARAM_PROPERTY_NAME,                // The name used to identify the parameter
                        DataTypeDefinition.QNAME,                  // The parameter value type
                        true,                                      // Indicates whether the parameter is mandatory
                        getParamDisplayLabel(PARAM_PROPERTY_NAME)));      // The parameters display label
    }

    /**
     * @see org.alfresco.repo.action.executer.ActionExecuterAbstractBase#executeImpl(org.alfresco.service.cmr.action.Action, org.alfresco.service.cmr.repository.NodeRef)
     */
    public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
        if (logger.isDebugEnabled()) logger.debug("Inside executeImpl");
        QName propertyName = (QName) action.getParameterValue(PARAM_PROPERTY_NAME);
        String value = (String) nodeService.getProperty(actionedUponNodeRef, propertyName);
        attributeService.setAttribute(value, "dev", "accounting", "status");
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
}

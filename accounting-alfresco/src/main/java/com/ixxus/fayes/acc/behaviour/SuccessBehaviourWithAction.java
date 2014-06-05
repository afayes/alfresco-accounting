package com.ixxus.fayes.acc.behaviour;

import com.ixxus.fayes.acc.action.SuccessActionExecutor;
import com.ixxus.fayes.acc.action.SuccessAttributeActionExecutor;
import com.ixxus.fayes.model.SomeCoRatingsModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

/**
 * Created by afayes on 04/06/2014.
 */
public class SuccessBehaviourWithAction implements NodeServicePolicies.OnAddAspectPolicy {
    // Dependencies
    private NodeService nodeService;
    private ActionService actionService;
    private PolicyComponent policyComponent;
    private Logger logger = Logger.getLogger(SuccessBehaviourWithAction.class);


    // Behaviours
    private Behaviour onAddAspect;

    public void init() {
        if (logger.isDebugEnabled()) logger.debug("Initializing rateable behaviors");
        System.out.println("inside behaviour init"); // todo use proper loggine for this
        // Create behaviours
        this.onAddAspect = new JavaBehaviour(this, "onAddAspect", Behaviour.NotificationFrequency.TRANSACTION_COMMIT);

        // Bind behaviours to node policies
        this.policyComponent.bindClassBehaviour(QName.createQName(NamespaceService.ALFRESCO_URI, "onAddAspect"), QName.createQName(SomeCoRatingsModel.NAMESPACE_ACCOUNTING_CONTENT_MODEL, SomeCoRatingsModel.ASPECT_ACC_VERIFIABLE), this.onAddAspect);
    }

    @Override
    public void onAddAspect(NodeRef nodeRef, QName qName) {
        if (logger.isDebugEnabled()) logger.debug("Inside onAddAspect");
        nodeService.setProperty(nodeRef, QName.createQName(SomeCoRatingsModel.NAMESPACE_ACCOUNTING_CONTENT_MODEL, SomeCoRatingsModel.PROP_STATUS), "SUCCESS");

        Action successAction = actionService.createAction(SuccessAttributeActionExecutor.NAME);
        successAction.setParameterValue(SuccessAttributeActionExecutor.PARAM_PROPERTY_NAME, QName.createQName(SomeCoRatingsModel.NAMESPACE_ACCOUNTING_CONTENT_MODEL, SomeCoRatingsModel.PROP_STATUS));
        actionService.executeAction(successAction, nodeRef, false, true);
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setPolicyComponent(PolicyComponent policyComponent) {
        this.policyComponent = policyComponent;
    }

    public void setActionService(ActionService actionService) {
        this.actionService = actionService;
    }
}

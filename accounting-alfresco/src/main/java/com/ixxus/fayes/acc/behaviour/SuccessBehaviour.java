package com.ixxus.fayes.acc.behaviour;

import com.ixxus.fayes.model.SomeCoRatingsModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;

/**
 * Created by afayes on 04/06/2014.
 */
public class SuccessBehaviour implements NodeServicePolicies.OnAddAspectPolicy {
    // Dependencies
    private NodeService nodeService;
    private PolicyComponent policyComponent;
    private Logger logger = Logger.getLogger(SuccessBehaviour.class);


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
    }

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public PolicyComponent getPolicyComponent() {
        return policyComponent;
    }

    public void setPolicyComponent(PolicyComponent policyComponent) {
        this.policyComponent = policyComponent;
    }
}

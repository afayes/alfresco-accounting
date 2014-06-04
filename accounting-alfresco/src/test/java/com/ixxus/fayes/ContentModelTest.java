package com.ixxus.fayes;

import org.junit.Test;

import com.ixxus.test.AlfrescoTestUtils;

public class ContentModelTest {

    @Test
    public void testBootstrapModel() throws Exception {
        AlfrescoTestUtils.testBootstrapModel("alfresco/module/accounting/model/content-model.xml");
    }
}
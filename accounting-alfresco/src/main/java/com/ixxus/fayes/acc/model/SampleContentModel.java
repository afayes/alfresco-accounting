package com.ixxus.fayes.acc.model;

import org.alfresco.service.namespace.QName;

/**
 * 
 * TODO: Change the name to something other than SampleContentModel
 *
 */
public class SampleContentModel {
    public final static String NAMESPACE_URI = "http://www.ixxus.co.uk/model/accounting/1.0";
    public final static String NAMESPACE_PREFIX = "acc";

    public static final class SampleAspect {
        public static final QName ASPECT = acc("sampleAspect");

        private SampleAspect() {
        }

        public static final class Prop {
            private Prop() {
            }
            
            public static final QName SAMPLE_ASPECT_PROPERTY = acc("sampleAspectProperty");
        }
    }

    public static final class SampleType {
    	public static final QName TYPE = acc("sampleType");

    	private SampleType() {
    	}

    	public static final class Prop {
            private Prop() {
            }
            
            public static final QName SAMPLE_TYPE_PROPERTY = acc("sampleTypeProperty");
        }
    }
    
    public static QName acc(final String qname) {
        return QName.createQName(NAMESPACE_URI, qname);
    }
}
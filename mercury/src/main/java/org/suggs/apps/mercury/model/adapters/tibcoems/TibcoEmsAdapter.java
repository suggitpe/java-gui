/*
 * TibcoEmsAdapter.java created on 10 Nov 2008 07:54:43 by suggitpe for project GUI - Mercury
 * 
 */
package org.suggs.apps.mercury.model.adapters.tibcoems;

import org.suggs.apps.mercury.model.adapters.support.AbstractMercuryAdapter;

/**
 * Main adapter class for the Tibco EMS middleware impl.
 * 
 * @author suggitpe
 * @version 1.0 10 Nov 2008
 */
public class TibcoEmsAdapter extends AbstractMercuryAdapter {

    public static final CONNECTION_TYPE TYPE = CONNECTION_TYPE.TIBCO_EMS;

    private static final String FRIENDLY_NAME = "Tibco Ems Adapter";

    /**
     * @see org.suggs.apps.mercury.model.adapters.IConnectionAdapter#getFriendlyName()
     */
    @Override
    public final String getFriendlyName() {
        return FRIENDLY_NAME;
    }

    /**
     * @see org.suggs.apps.mercury.model.adapters.IConnectionAdapter#getType()
     */
    @Override
    public final String getType() {
        return TYPE.toString();
    }

}

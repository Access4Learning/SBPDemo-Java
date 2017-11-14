/*
 * BaseSBPDemoSubscriber.java
 * Created: 14 Nov 2017
 *
 * Copyright 2017 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License. 
 */

package sbpdemo.subscriber;

import openadk.library.ElementDef;
import openadk.library.EventAction;
import openadk.library.SIFDataObject;
import openadk.library.Zone;
import systemic.sif.sbpframework.exception.InvalidKeyException;
import systemic.sif.sbpframework.subscriber.SBPBaseSubscriber;

/**
 * @author Joerg Huber
 *
 */
public abstract class BaseSBPDemoSubscriber extends SBPBaseSubscriber 
{
    public BaseSBPDemoSubscriber(String subscriberID, ElementDef dtd)
    {
        super(subscriberID, dtd);
    }

    /* (non-Javadoc)
     * @see systemic.sif.sbpframework.subscriber.SBPBaseSubscriber#onKeyError(openadk.library.SIFDataObject, openadk.library.Zone, boolean, openadk.library.EventAction, systemic.sif.sbpframework.exception.InvalidKeyException)
     */
    @Override
    public void onKeyError(SIFDataObject sifObject, Zone zone, boolean isEvent, EventAction eventAction, InvalidKeyException invalidKeyException)
    {
        // Just write the info to the log.
        logger.error("onKeyError called with error: "+invalidKeyException.getMessage()+".\nOffending Object:\n"+sifObject.toXML());
    }

    /* (non-Javadoc)
     * @see systemic.sif.sifcommon.subscriber.BaseSubscriber#finalise()
     */
    @Override
    public void finalise()
    {
        logger.debug("...in finalise for "+getId());
    }

}

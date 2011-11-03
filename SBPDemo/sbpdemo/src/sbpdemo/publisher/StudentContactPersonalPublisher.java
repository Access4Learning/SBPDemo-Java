/*
* Copyright 2010-2011 Systemic Pty Ltd
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software distributed under the License 
* is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
* or implied.
* See the License for the specific language governing permissions and limitations under the License.
*/
package sbpdemo.publisher;

import openadk.library.ADKException;
import openadk.library.Query;
import openadk.library.SIFException;
import openadk.library.Zone;
import openadk.library.student.StudentDTD;
import sbpdemo.publisher.iterator.XMLFileEventIterator;
import sbpdemo.publisher.iterator.XMLFileResponseIterator;
import systemic.sif.sbpframework.publisher.SBPBasePublisher;
import systemic.sif.sifcommon.publisher.SIFEventIterator;
import systemic.sif.sifcommon.publisher.SIFResponseIterator;

public class StudentContactPersonalPublisher extends SBPBasePublisher
{
	public StudentContactPersonalPublisher(String publisherID)
	{
		super(publisherID, StudentDTD.STUDENTCONTACTPERSONAL);
	}

	@Override
    public void finalise()
    {
	    logger.debug("...in finalise for "+getId() );
    }
	
	@Override
    public SIFResponseIterator getRequestedSIFObjects(Query query, Zone zone) throws ADKException, SIFException
    {
		logger.debug("getRequestedSIFObjects called for publisher: "+getId());
		return new XMLFileResponseIterator(this);
    }

	@Override
    public SIFEventIterator getSIFEvents() throws ADKException
    {
		logger.debug("getSIFEvents called for publisher: "+getId());
		return new XMLFileEventIterator(this);
    }
}

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

import java.util.List;

import openadk.library.ADKException;
import openadk.library.Query;
import openadk.library.SIFDataObject;
import openadk.library.SIFException;
import openadk.library.Zone;
import openadk.library.school.SchoolDTD;
import sbpdemo.publisher.iterator.XMLFileEventIterator;
import sbpdemo.publisher.iterator.XMLFileResponseIterator;
import systemic.sif.sbpframework.persist.model.SIFObjectKey;
import systemic.sif.sbpframework.publisher.SBPBasePublisher;
import systemic.sif.sifcommon.publisher.SIFEventIterator;
import systemic.sif.sifcommon.publisher.SIFResponseIterator;

public class SchoolInfoPublisher extends SBPBasePublisher
{
	public SchoolInfoPublisher(String publisherID)
	{
		super(publisherID, SchoolDTD.SCHOOLINFO);
	}

	@Override
    public void finalise()
    {
//	    logger.debug("Finalise for "+getId()+": Release Connection Pool." );
//        ConnectionManager.closeConnectionPools();
//	    logger.debug("Connection Pool for "+getId()+" released." );
	    logger.debug("...in finalise for "+getId() );
    }
	
	@Override
    public SIFResponseIterator getAllSIFObjects(Query query, Zone zone) throws ADKException, SIFException
    {
//		logger.debug("getRequestedSIFObjects called for publisher: "+getId());
//		return new SchoolInfoResponseIterator();
		logger.debug("getAllSIFObjects called for publisher: "+getId());
		return new XMLFileResponseIterator(this);
    }

	@Override
    public SIFDataObject getSIFObjectByPrimaryKey(List<SIFObjectKey> keys, Query query, Zone zone) throws ADKException, SIFException
    {
		logger.debug("getSIFObjectByPrimaryKey called for publisher: "+getId()+" with key values: "+keys);
		List<SIFDataObject> sifObjs = getObjectsFromFile();
		if ((sifObjs != null) && (sifObjs.size() > 0))
		{
			return sifObjs.get(0);
		}
	    return null;
    }

	@Override
    public SIFEventIterator getSIFEvents() throws ADKException
    {
//		logger.debug("getSIFEvents called for publisher: "+getId());
//		return new SchoolInfoEventIterator();
		logger.debug("getSIFEvents called for publisher: "+getId());
		return new XMLFileEventIterator(this);
    }
}

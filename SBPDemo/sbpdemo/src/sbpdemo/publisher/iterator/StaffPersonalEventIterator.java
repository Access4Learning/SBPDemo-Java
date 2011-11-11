/*
 * StaffPersonalEventIterator.java
 * Created: 11/11/2011
 *
 * Copyright 2011 Systemic Pty Ltd
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

package sbpdemo.publisher.iterator;

import java.util.ArrayList;

import openadk.library.ADK;
import openadk.library.EventAction;
import openadk.library.common.AUCodeSetsStaffStatusType;
import openadk.library.common.Name;
import openadk.library.common.NameType;
import openadk.library.common.PersonInfo;
import openadk.library.student.StaffPersonal;
import openadk.library.tools.mapping.ADKMappingException;
import openadk.library.tools.mapping.MappingsContext;
import systemic.sif.sifcommon.BaseInfo;
import systemic.sif.sifcommon.model.SIFEvent;
import systemic.sif.sifcommon.publisher.SIFEventIterator;

/**
 * @author Joerg Huber
 *
 */
public class StaffPersonalEventIterator implements SIFEventIterator
{
	private ArrayList<StaffPersonal> staff = new ArrayList<StaffPersonal>();
	private int currentPos = 0;

	public StaffPersonalEventIterator()
	{
		StaffPersonal sp = new StaffPersonal();
		sp.setRefId(ADK.makeGUID());
		sp.setLocalId("ez7b7j14");
		sp.setEmploymentStatus(AUCodeSetsStaffStatusType.ACTIVE);
		sp.setPersonInfo(new PersonInfo());
		sp.getPersonInfo().setName(new Name());
		sp.getPersonInfo().getName().setType(NameType.LEGAL);
		sp.getPersonInfo().getName().setGivenName("Peter");
		sp.getPersonInfo().getName().setFamilyName("Gill");
		
		staff.add(sp);
	}
		
	@Override
    public SIFEvent getNextEvent(BaseInfo baseInfo, MappingsContext mappingCtx) throws ADKMappingException
    {
	    if (hasNext())
	    {
	    	SIFEvent event = new SIFEvent(staff.get(currentPos), EventAction.CHANGE);
	    	currentPos++;
	    	return event;
	    }
	    else
	    {
	    	return null;
	    }
    }

	@Override
    public boolean hasNext()
    {
	    return currentPos < staff.size();
    }

	@Override
    public void releaseResources()
    {
    }

}

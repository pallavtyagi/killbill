/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.entitlement.engine.core;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.ning.billing.config.IEntitlementConfig;
import com.ning.billing.entitlement.engine.dao.IEntitlementDao;
import com.ning.billing.entitlement.events.IEntitlementEvent;
import com.ning.billing.entitlement.exceptions.EntitlementError;
import com.ning.billing.util.clock.IClock;

public class ApiEventProcessorMemoryMock extends ApiEventProcessorBase {


    @Inject
    public ApiEventProcessorMemoryMock(IClock clock, IEntitlementDao dao, IEntitlementConfig config) {
        super(clock, dao, config);
    }


    @Override
    protected boolean doProcessEvents(int sequenceId) {

        List<IEntitlementEvent> events =  dao.getEventsReady(apiProcessorId, sequenceId);
        if (events.size() == 0) {
            return false;
        }
        log.info(String.format("doProcessEvents : Got %d event(s)", events.size() ));
        for (IEntitlementEvent cur : events) {
            log.info(String.format("doProcessEvents :  (clock = %s) CALLING Engine with event %s", clock.getUTCNow(), cur));
            listener.processEventReady(cur);
            log.info(String.format("doProcessEvents : PROCESSED event %s", cur));
            nbProcessedEvents++;
        }
        dao.clearEventsReady(apiProcessorId, events);
        log.info(String.format("doProcessEvents : clearEvents"));
        return true;
    }


    @Override
    protected boolean doProcessEventsFromList(int sequenceId,
            Collection<IEntitlementEvent> events) {
        throw new EntitlementError("Method not implemented");
    }
}

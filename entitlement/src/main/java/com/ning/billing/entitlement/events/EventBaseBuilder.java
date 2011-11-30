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

package com.ning.billing.entitlement.events;

import java.util.UUID;

import org.joda.time.DateTime;

import com.ning.billing.entitlement.events.IEventLifecycle.IEventLifecycleState;

@SuppressWarnings("unchecked")
public class EventBaseBuilder<T extends EventBaseBuilder<T>> {

    private UUID uuid;
    private UUID subscriptionId;
    private DateTime requestedDate;
    private DateTime effectiveDate;
    private DateTime processedDate;

    private long activeVersion;
    private boolean isActive;
    private UUID processingOwner;
    private DateTime nextAvailableProcessingTime;
    private IEventLifecycleState processingState;


    public EventBaseBuilder() {
        this.uuid = UUID.randomUUID();
        this.isActive = true;
        this.processingState = IEventLifecycleState.AVAILABLE;
    }

    public EventBaseBuilder(EventBaseBuilder<?> copy) {
        this.uuid = copy.uuid;
        this.subscriptionId = copy.subscriptionId;
        this.requestedDate = copy.requestedDate;
        this.effectiveDate = copy.effectiveDate;
        this.processedDate = copy.processedDate;

        this.activeVersion = copy.activeVersion;
        this.isActive = copy.isActive;
        this.processingOwner = copy.processingOwner;
        this.nextAvailableProcessingTime = copy.nextAvailableProcessingTime;
        this.processingState = copy.processingState;
    }

    public T setUuid(UUID uuid) {
        this.uuid = uuid;
        return (T) this;
    }

    public T setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
        return (T) this;
    }

    public T setRequestedDate(DateTime requestedDate) {
        this.requestedDate = requestedDate;
        return (T) this;
    }

    public T setEffectiveDate(DateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
        return (T) this;
    }

    public T setProcessedDate(DateTime processedDate) {
        this.processedDate = processedDate;
        return (T) this;
    }

    public T setActiveVersion(long activeVersion) {
        this.activeVersion = activeVersion;
        return (T) this;
    }

    public T setActive(boolean isActive) {
        this.isActive = isActive;
        return (T) this;
    }

    public T setProcessingOwner(UUID processingOwner) {
        this.processingOwner = processingOwner;
        return (T) this;
    }

    public T setNextAvailableProcessingTime(DateTime nextAvailableProcessingTime) {
        this.nextAvailableProcessingTime = nextAvailableProcessingTime;
        return (T) this;
    }

    public T setProcessingState(IEventLifecycleState processingState) {
        this.processingState = processingState;
        return (T) this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public DateTime getRequestedDate() {
        return requestedDate;
    }

    public DateTime getEffectiveDate() {
        return effectiveDate;
    }

    public DateTime getProcessedDate() {
        return processedDate;
    }

    public long getActiveVersion() {
        return activeVersion;
    }

    public boolean isActive() {
        return isActive;
    }

    public UUID getProcessingOwner() {
        return processingOwner;
    }

    public DateTime getNextAvailableProcessingTime() {
        return nextAvailableProcessingTime;
    }

    public IEventLifecycleState getProcessingState() {
        return processingState;
    }
}

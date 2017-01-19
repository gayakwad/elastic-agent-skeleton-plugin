/*
 * Copyright 2016 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gayakwad.gocd.elasticagent.mesos;

import com.gayakwad.gocd.elasticagent.mesos.requests.CreateAgentRequest;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class MesosAgentInstances implements AgentInstances<MesosInstance> {

    private final ConcurrentHashMap<String, MesosInstance> instances = new ConcurrentHashMap<>();

    private boolean refreshed;
    public Clock clock = Clock.DEFAULT;

    @Override
    public MesosInstance create(CreateAgentRequest request, PluginSettings settings) throws Exception {
        // TODO: Implement me!
        throw new UnsupportedOperationException();
//        MesosInstance instance = MesosInstance.create(request, settings);
//        register(instance);
//        return instance;
    }

    @Override
    public void terminate(String agentId, PluginSettings settings) throws Exception {
        // TODO: Implement me!
        throw new UnsupportedOperationException();

//        MesosInstance instance = instances.get(agentId);
//        if (instance != null) {
//            instance.terminate(docker(settings));
//        } else {
//            LOG.warn("Requested to terminate an instance that does not exist " + agentId);
//        }
//        instances.remove(agentId);
    }

    @Override
    public void terminateUnregisteredInstances(PluginSettings settings, Agents agents) throws Exception {
        // TODO: Implement me!
        throw new UnsupportedOperationException();

//        MesosAgentInstances toTerminate = unregisteredAfterTimeout(settings, agents);
//        if (toTerminate.instances.isEmpty()) {
//            return;
//        }
//
//        LOG.warn("Terminating instances that did not register " + toTerminate.instances.keySet());
//        for (MesosInstance container : toTerminate.instances.values()) {
//            terminate(container.name(), settings);
//        }
    }

    @Override
    // TODO: Implement me!
    public Agents instancesCreatedAfterTimeout(PluginSettings settings, Agents agents) {
        ArrayList<Agent> oldAgents = new ArrayList<>();
        for (Agent agent : agents.agents()) {
            MesosInstance instance = instances.get(agent.elasticAgentId());
            if (instance == null) {
                continue;
            }

            if (clock.now().isAfter(instance.createdAt().plus(settings.getAutoRegisterPeriod()))) {
                oldAgents.add(agent);
            }
        }
        return new Agents(oldAgents);
    }

    @Override
    public void refreshAll(PluginRequest pluginRequest) throws Exception {
        // TODO: Implement me!
        throw new UnsupportedOperationException();

//        if (!refreshed) {
//            TODO: List all instances from the cloud provider and select the ones that are created by this plugin
//            TODO: Most cloud providers allow applying some sort of labels or tags to instances that you may find of use
//            List<InstanceInfo> instanceInfos = cloud.listInstances().filter(...)
//            for (Instance instanceInfo: instanceInfos) {
//                  register(MesosInstance.fromInstanceInfo(instanceInfo))
//            }
//            refreshed = true;
//        }
    }

    @Override
    public MesosInstance find(String agentId) {
        return instances.get(agentId);
    }

    // used by tests
    public boolean hasInstance(String agentId) {
        return instances.containsKey(agentId);
    }

    private void register(MesosInstance instance) {
        instances.put(instance.name(), instance);
    }

//    private MesosAgentInstances unregisteredAfterTimeout(PluginSettings settings, Agents knownAgents) throws Exception {
//        Period period = settings.getAutoRegisterPeriod();
//        MesosAgentInstances unregisteredContainers = new MesosAgentInstances();
//
//        for (String instanceName : instances.keySet()) {
//            if (knownAgents.containsAgentWithId(instanceName)) {
//                continue;
//            }
//
//            // TODO: Connect to the cloud provider to fetch information about this instance
//            InstanceInfo instanceInfo = connection.inspectInstance(instanceName);
//            DateTime dateTimeCreated = new DateTime(instanceInfo.created());
//
//            if (clock.now().isAfter(dateTimeCreated.plus(period))) {
//                unregisteredContainers.register(MesosInstance.fromInstanceInfo(instanceInfo));
//            }
//        }
//        return unregisteredContainers;
//    }
}

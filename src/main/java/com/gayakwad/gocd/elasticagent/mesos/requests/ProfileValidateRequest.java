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

package com.gayakwad.gocd.elasticagent.mesos.requests;

import com.gayakwad.gocd.elasticagent.mesos.RequestExecutor;
import com.gayakwad.gocd.elasticagent.mesos.executors.ProfileValidateRequestExecutor;
import com.google.gson.Gson;

import java.util.Map;

public class ProfileValidateRequest {

    private static final Gson GSON = new Gson();
    private Map<String, String> properties;

    public ProfileValidateRequest(Map<String, String> properties) {
        this.properties = properties;
    }


    public Map<String, String> getProperties() {
        return properties;
    }

    public static ProfileValidateRequest fromJSON(String json) {
        return new ProfileValidateRequest(GSON.fromJson(json, Map.class));
    }

    public RequestExecutor executor() {
        return new ProfileValidateRequestExecutor(this);
    }
}

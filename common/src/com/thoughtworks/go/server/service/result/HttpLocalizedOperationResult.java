/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.server.service.result;

import com.thoughtworks.go.i18n.LocalizedMessage;
import com.thoughtworks.go.i18n.Localizer;
import com.thoughtworks.go.i18n.Localizable;
import com.thoughtworks.go.serverhealth.HealthStateType;
import org.apache.commons.httpclient.HttpStatus;

/**
 * @understands localized operation result for http
 * We suck at this
 */
public class HttpLocalizedOperationResult implements LocalizedOperationResult {
    private Localizable message;
    private HealthStateType healthStateType;
    private int httpCode = 200;

    public static LocalizedOperationResult badRequest(String messageKey){
        LocalizedOperationResult result = successfulResult();
        result.badRequest(LocalizedMessage.string(messageKey));
        return result;
    }

    public static LocalizedOperationResult successfulResult(){
        return new HttpLocalizedOperationResult();
    }

    public void setMessage(Localizable message) {
        this.message = message;
    }


    public boolean hasMessage() {
        return message != null;
    }

    public void notImplemented(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_NOT_IMPLEMENTED;
    }

    public void unauthorized(Localizable message, HealthStateType healthStateType) {
        this.message = message;
        this.healthStateType = healthStateType;
        httpCode = HttpStatus.SC_UNAUTHORIZED;
    }

    public void notFound(Localizable message, HealthStateType healthStateType) {
        this.message = message;
        this.healthStateType = healthStateType;
        httpCode = HttpStatus.SC_NOT_FOUND;
    }

    public void conflict(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_CONFLICT;
    }
    
    public void internalServerError(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    public void badRequest(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_BAD_REQUEST;
    }

    public void notAcceptable(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_NOT_ACCEPTABLE;
    }

    public boolean isSuccessful() {
        return 200 <= httpCode && httpCode < 300; // I hate java
    }

    public void connectionError(Localizable message) {
        this.message = message;
        httpCode = HttpStatus.SC_BAD_REQUEST;
    }

    public int httpCode() {
        return httpCode;
    }

    public String message(Localizer localizer) {
        if (message == null) return null;
        return message.localize(localizer);
    }

    /**
     * Used only in tests
     */
    public Localizable localizable() {
        return message;
    }
}

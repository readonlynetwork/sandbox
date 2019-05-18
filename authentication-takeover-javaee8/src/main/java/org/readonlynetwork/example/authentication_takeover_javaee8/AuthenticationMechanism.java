/*
 * Copyright 2019 readonlynetwork.org
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
package org.readonlynetwork.example.authentication_takeover_javaee8;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authentication takeover with Java EE Security API 1.0
 *
 * @author readonlynetwork.org
 */
@RememberMe(
		cookieHttpOnly = true,//kick ass of JavaScript
		cookieMaxAgeSeconds = 3600,//max seconds
		cookieSecureOnly = false//FIXME: should be true if HTTPS is available
)
@ApplicationScoped
public class AuthenticationMechanism implements HttpAuthenticationMechanism {

	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
			HttpMessageContext httpMessageContext) throws AuthenticationException {
		
		//get data from request
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
        //only for protected pages
	    if(httpMessageContext.isProtected()) {
	    	//FIXME: use database and hashed password
	    	//simple user
		    if(Objects.equals("test", username) && Objects.equals("test1", password)) {
		    	return httpMessageContext.notifyContainerAboutLogin(UserAndRole.getValidationResult(username));
	    	}
		    
		    //admin
		    if(Objects.equals("admin", username) && Objects.equals("test2", password)){
		    	return httpMessageContext.notifyContainerAboutLogin(UserAndRole.getValidationResult(username));
		    }
		    
		    //invalid login
		    return httpMessageContext.forward("/login");
		}
		
	    //regular pages
		return httpMessageContext.doNothing();
	}

}

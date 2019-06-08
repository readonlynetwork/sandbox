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

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.AutoApplySession;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Authentication takeover with Java EE Security API 1.0
 *
 * @author readonlynetwork.org
 */
@RequestScoped
@AutoApplySession
public class AuthenticationMechanism implements HttpAuthenticationMechanism {
	public static final String LOGIN_PAGE = "/WEB-INF/login.jsp";
	public static final String ERROR_PAGE = "/WEB-INF/error.jsp";
	
	@Inject
	private IdentityStoreHandler identityStoreHandler;

	public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response,
			HttpMessageContext httpMessageContext) throws AuthenticationException {
		
		//get data from request
		String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
        //only for protected pages
	    if(httpMessageContext.isProtected()) {
	    	
	    	if(username != null && password != null) {
		    	CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(username, password));
		    	//successful login
		    	if(result.getStatus() == Status.VALID) {
		    		return httpMessageContext.notifyContainerAboutLogin(result);
		    	}
	    	}else {
	    		return httpMessageContext.forward(LOGIN_PAGE);
	    	}
		    
	    	//invalid login data
	    	return httpMessageContext.forward(ERROR_PAGE);
		}
		
	    //regular pages
		return httpMessageContext.doNothing();
	}
}

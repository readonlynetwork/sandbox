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
package org.readonlynetwork.example.jsf_login;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSF Bean for handle login
 * 
 * @author readonlynetwork.org
 *
 */
@Named
@RequestScoped
public class LoginBean {
	@Inject
	private SecurityContext securityContext;

	@Inject
	private FacesContext facesContext;  
	   
	private String username;
	private String password;
	
	/**
	 * Try login
	 */
	public void login() {
		Credential credential = new UsernamePasswordCredential(this.username, this.password);
	      
		AuthenticationStatus status = securityContext.authenticate(
				(HttpServletRequest)this.facesContext.getExternalContext().getRequest(),
				(HttpServletResponse)this.facesContext.getExternalContext().getResponse(),
				AuthenticationParameters.withParams().credential(credential)
			);
  
		if (status.equals(AuthenticationStatus.SEND_CONTINUE)) {
			facesContext.responseComplete();
		} else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
			this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Authentication failed", ""));
		}   
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return "";//no need to return with password
	}
	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}

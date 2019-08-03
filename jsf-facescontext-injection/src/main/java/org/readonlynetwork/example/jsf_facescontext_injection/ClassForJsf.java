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
package org.readonlynetwork.example.jsf_facescontext_injection;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Named and RequestScoped class
 * 
 * @author readonlynetwork.org
 */
@Model
public class ClassForJsf {
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;

	/**
	 * Click event
	 */
	public void click() {
		//not identified
		this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Your ip is", request.getRemoteAddr()));
	}
}

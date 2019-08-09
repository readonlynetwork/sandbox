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
package org.readonlynetwork.example.jsf_flow_subfolder;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.readonlynetwork.example.jsf_flow_subfolder.config.FlowNameConfig;

/**
 * Bean for flow
 * 
 * @author readonlynetwork.org
 */
@Named
@FlowScoped(FlowNameConfig.NAME)
public class FlowNameBean implements Serializable {
	private static final int MIN_NAME_LENGTH = 3;
	private static final int MIN_MESSAGE_LENGTH = 15;

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesContext facesContext;
	
	private String name;
	private String message;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Validate the name
	 * 
	 * @return null or next page
	 */
	public String validateName() {
		if(this.name == null || this.name.trim().length() < MIN_NAME_LENGTH) {
			this.facesContext.addMessage("name",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Invalid name",
							"Minimum lenght of name is " + MIN_NAME_LENGTH));
			return null;//stay here
		}
		return FlowNameConfig.STEP2;
	}
	
	/**
	 * Validate the message
	 * 
	 * @return null or next page
	 */
	public String validateMessage() {
		if(this.message == null || this.message.trim().length() < MIN_MESSAGE_LENGTH) {
			this.facesContext.addMessage("message",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Invalid message",
							"Minimum lenght of message is " + MIN_MESSAGE_LENGTH));
			return null;//stay here
		}
		return FlowNameConfig.STEP3;
	}
	
	/**
	 * @return exit from flow
	 */
	public String getExit() {
		//try prevent to return back e.g.: refresh
		return "/home?faces-redirect=true";
	}
	
}

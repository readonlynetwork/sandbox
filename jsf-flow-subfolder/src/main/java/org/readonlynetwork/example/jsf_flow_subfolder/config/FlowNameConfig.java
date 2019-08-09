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
package org.readonlynetwork.example.jsf_flow_subfolder.config;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

/**
 * Configure flow without XML
 * 
 * @author readonlynetwork.org
 */
public class FlowNameConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//the files in flowName folder should start with flowName
	public static final String NAME = "flowName";
	public static final String STEP2 = NAME + "-step2";
	public static final String STEP3 = NAME + "-step3";
	public static final String EXIT = "exit";
	
	public static final String SUBFOLDER = "/subfolder";
	
	private static final String START_PATH = SUBFOLDER + "/" + NAME + "/" + NAME + ".xhtml";
	private static final String STEP2_PATH = SUBFOLDER + "/" + NAME + "/" + STEP2 + ".xhtml";
	private static final String STEP3_PATH = SUBFOLDER + "/" + NAME + "/" + STEP3 + ".xhtml";
	private static final String EXIT_CMD = "${flowNameBean.exit}";
	
	@Produces
	@FlowDefinition
	public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
		
		flowBuilder.id("", NAME);
		//start the flow
		flowBuilder.viewNode(NAME, START_PATH).markAsStartNode();
		
		//define where are the other steps
		flowBuilder.viewNode(STEP2, STEP2_PATH);
		flowBuilder.viewNode(STEP3, STEP3_PATH);
		
		//exit from flow
		flowBuilder.returnNode(EXIT).fromOutcome(EXIT_CMD);
		
		return flowBuilder.getFlow();
	}
}

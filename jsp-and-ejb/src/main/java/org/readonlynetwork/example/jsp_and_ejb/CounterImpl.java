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
package org.readonlynetwork.example.jsp_and_ejb;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Counter EJB implementation
 *
 * @author readonlynetwork.org
 */
@Startup
@Singleton(name = "counter")
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class CounterImpl implements CounterLocal{
	private int number;
	
	@PostConstruct
	private void init() {
		this.number = 10;
	}

	@Override
	public synchronized int getNumber() {
		return this.number++;
	}

}

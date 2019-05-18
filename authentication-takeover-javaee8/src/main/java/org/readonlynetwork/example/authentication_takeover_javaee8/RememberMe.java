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

import java.util.Set;

import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;

/**
 * RememberMe Feature
 *
 * @author readonlynetwork.org
 */
public class RememberMe implements RememberMeIdentityStore {

	@Override
	public CredentialValidationResult validate(RememberMeCredential credential) {
		return UserAndRole.getValidationResult(getNameByToken(credential.getToken()));
	}

	@Override
	public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
		//Just an example
		return createTokenByName(callerPrincipal.getName());
	}

	@Override
	public void removeLoginToken(String token) {
		//TODO: remove from database
	}
	
	
	private String getNameByToken(String token) {
		//TODO: load from database
		return token;
	}

	private String createTokenByName(String name) {
		//FIXME: WARNING! need hashed secret
		//TODO: create token in database
		return name;
	}
}

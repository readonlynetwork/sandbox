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

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

/**
 * Built in users
 *
 * @author readonlynetwork.org
 */
public class BuiltInUsers implements IdentityStore {
	
	/**
	 * Check Username and Password
	 * 
	 * @param usernamePasswordCredential
	 * @return
	 */
	public CredentialValidationResult validate(UsernamePasswordCredential uPwCredential) {
        
		boolean valid = false;
		Set<String> roles = new HashSet<>();
		
		//FIXME Warning: use hashed password
		
		switch (uPwCredential.getCaller()) {
			case "admin":
					if(Objects.equals("test1", uPwCredential.getPasswordAsString())) {
						roles.add(LoginRole.ADMIN.getRoleName());
						roles.add(LoginRole.OTHER.getRoleName());
						valid = true;
					}
				break;
				
			case "user":
				if(Objects.equals("test1", uPwCredential.getPasswordAsString())) {
					roles.add(LoginRole.USER.getRoleName());
					roles.add(LoginRole.OTHER.getRoleName());
					valid = true;
				}
			break;
			
			case "other":
				if(Objects.equals("test1", uPwCredential.getPasswordAsString())) {
					roles.add(LoginRole.OTHER.getRoleName());
					valid = true;
				}
			break;
	
			default:
				valid = false;
				break;
		}

		//let user login
		if(valid) {
	        return new CredentialValidationResult(uPwCredential.getCaller(), roles);
		}
		
		//invalid
		return CredentialValidationResult.INVALID_RESULT;
    }
}

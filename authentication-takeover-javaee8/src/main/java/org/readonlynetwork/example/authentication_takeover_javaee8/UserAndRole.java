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

import java.util.Arrays;
import java.util.HashSet;

import javax.security.enterprise.identitystore.CredentialValidationResult;

/**
 * Find role by username
 *
 * @author readonlynetwork.org
 */
public class UserAndRole {
	public static CredentialValidationResult getValidationResult(String name) {
		//TODO: get data from database
		switch (name) {
			case "admin":
				return new CredentialValidationResult(
    					name,
    					new HashSet<>(Arrays.asList(LoginRole.ADMIN.getRoleName(), LoginRole.OTHER.getRoleName()))
    					);
				
			case "test":
				return new CredentialValidationResult(
    					name,
    					new HashSet<>(Arrays.asList(LoginRole.USER.getRoleName(), LoginRole.OTHER.getRoleName()))
    					);
				
			case "other":
				return new CredentialValidationResult(
    					name,
    					new HashSet<>(Arrays.asList(LoginRole.OTHER.getRoleName()))
    					);
	
			default:
				return CredentialValidationResult.INVALID_RESULT;
		}
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 
Copyright 2019 readonlynetwork.org
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
-->
<html>
<head>
<meta charset="UTF-8">
<title>Authentication Takeover in Java EE 8</title>
</head>
<body>
	<% if(request.getUserPrincipal() == null){ %>
		<h1>Login Error Page</h1>
		<p>:(</p>
		<p><a href="secret">Go back</a></p>
	<% }else{ %>
		<h1>Access Error Page</h1>
		<p>:(</p>
		<p><a href="home">Go home</a></p>
		<p><a href="logout">Log out</a></p>
	<% } %>
</body>
</html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="counter" class="org.readonlynetwork.example.jsp_and_ejb.JspCounter" scope="application"></jsp:useBean>
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
<title>JSP and EJB</title>
</head>
<body>
	<h1>JSP and EJB</h1>
	<p>Counter: <%= counter.getNumber() %></p>
</body>
</html>
<%@page import="com.travellist.travel.object.UserBO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	UserBO userBO = (UserBO)request.getAttribute("userBO");
%>
	
	<div class="irs_left">
		<div class="irs_lnb">
			<ul class="depth1">
				<li>
					<span class="menu_text"> <%=userBO.getScreenId()%> 님 환영합니다. </span>
				</li>
				
				<li>
					<span class="menu_text"> travelList </span>
					<ul class="depth2">
						<li><a href="${pageContext.request.contextPath}/travel/Loggin.action?id=<%=userBO.getId()%>" > + 목록 </a> </li>
					</ul>
				</li>
				
				<li>
					<span class="menu_text"> twitter </span>
					<ul class="depth2">
						<li><a href="${pageContext.request.contextPath}/travel/DoTwitter.action?id=<%=userBO.getId()%>" > + 트위터 보기 </a> </li>
					</ul>
				</li>
				
			</ul>
		</div>
	</div>
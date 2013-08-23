<%@page import="com.travellist.travel.object.UserBO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	UserBO userBO = (UserBO) request.getAttribute("userBO");
%>

<script type="text/javascript">
	jQuery( document ).ready( function(){
		document.logginInfo.submit();
	});

</script>

<div class="irs_right">
	<div class="tblwrap">
		<form name="logginInfo" id="logginInfo" method="POST" action='${pageContext.request.contextPath}/travel/Loggin.action' >
			<input type="hidden" name="id" value="<%=userBO.getId()%>">
		</form>
	</div>
</div>
					

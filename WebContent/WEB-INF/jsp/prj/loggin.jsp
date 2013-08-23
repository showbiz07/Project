<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="ko">
<head>
	<title>Travel Info Web</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/irs.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/anytime.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui-lightness/jquery-ui-1.9.2.custom.css"/>
	<script type="text/javascript" type="text/javascript" src="/Project/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" type="text/javascript" src="/Project/js/jquery-ui-1.9.2.custom.js"></script>
	<script type="text/javascript" type="text/javascript" src="/Project/js/anytime.js"></script>
</head>
	 <body oncontextmenu="return false">
		<div class="irs_wrap">
				<jsp:include page="/WEB-INF/jsp/common/prj_gnb.jsp"></jsp:include>
			
				<div class="irs_content">
					<jsp:include page="/WEB-INF/jsp/common/prj_left.jsp"></jsp:include>
					<jsp:include page="/WEB-INF/jsp/prj/include/inc_loggin.jsp"></jsp:include>
				</div>
		</div>
	</body>
</html>
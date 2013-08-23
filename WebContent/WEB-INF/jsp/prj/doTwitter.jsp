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
	
	<script type="text/javascript" src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=4c2b0daa-68e4-32e0-a2d4-af2331df4f0f"></script>
	<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=17b717e7581a9a5c03a4e80728e3a7a5252dc361" charset="utf-8"></script>
	
</head>
	 <body oncontextmenu="return false">
		<div class="irs_wrap">
				<jsp:include page="/WEB-INF/jsp/common/prj_gnb.jsp"></jsp:include>
			
				<div class="irs_content">
					<jsp:include page="/WEB-INF/jsp/common/prj_left.jsp"></jsp:include>
					<jsp:include page="/WEB-INF/jsp/prj/include/inc_doTwitter.jsp"></jsp:include>
				</div>
		</div>
	</body>
</html>
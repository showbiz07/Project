<%@page import="com.travellist.travel.object.UserBO"%>
<%@page import="com.travellist.travel.object.TravelInfoBO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	@SuppressWarnings("unchecked") 
	List<TravelInfoBO> travelList = (List<TravelInfoBO>) request.getAttribute("travelList") ;
	TravelInfoBO travelInfoBO = (TravelInfoBO) request.getAttribute("travelInfoBO");
	UserBO userBO = (UserBO)request.getAttribute("userBO");
%>
<style type="text/css">
#inputContentName{
           display:none;
} 
</style>

<script type="text/javascript">
	function reqInsertTravel(){
		if(jQuery('#sw').val() === "hide"){
			jQuery('#inputContentName').css('display', 'block');
			jQuery('#sw').val("show");
		}
		else{
			jQuery('#inputContentName').css('display', 'none');
			jQuery('#sw').val("hide");
		}
	}

	function doInsertTravel(){
		if(jQuery('contentName').val() === "" )
			alert("제목을 입력해주세요");
		else
			document.insertTravel.submit();
	}

	function doShowTravel(cnt) {
		jQuery('#travelNo').val( jQuery('#travelNo'+cnt).val() );
		jQuery('#show_contentName').val( jQuery('#show_contentName'+cnt).val() );
		document.showTravel.submit();
	}
</script>

<input type="hidden" id="sw" value="hide">

<div class="irs_right">

	<div class="tblwrap">
		<form name="showTravel" id="showTravel" method="POST" action='${pageContext.request.contextPath}/travel/ShowTravel.action'>
			<input type="hidden" id="show_id" name="show_id" value="<%=travelInfoBO.getId() %>">
			<input type="hidden" id="travelNo" name="travelNo" value="">
			<input type="hidden" id="show_contentName" name="show_contentName" value="">
			
			<table class="tb2">
				<colgroup>
					<col width="20">
					<col width="300">
				</colgroup>
<%
				int	cnt=0;
				for(TravelInfoBO travelInfo : travelList){
%>
					<tr onclick="javascript:doShowTravel(<%=cnt%>)">
						<th scope="row">
							여행 : 	
						</th>
						<td>
							<%=travelInfo.getTravelName() %>
							<input type="hidden" id="travelNo<%=cnt%>" name="travelNo<%=cnt%>" value="<%=travelInfo.getTravelNo() %>">
							<input type="hidden" id="show_contentName<%=cnt%>" name="show_contentName<%=cnt%>" value="<%=travelInfo.getTravelName() %>">
						</td>
					</tr>
<%
					cnt++;
				}
%>			
				<tr>
					<td>
						<span class="btn_Center">
							<a href="javascript:reqInsertTravel();" class="btnB_gray"> <span> 새 여행 등록 열기 </span></a>
						</span>		
					</td>
				</tr>
			</table>
		</form>
			
		<div id="inputContentName">
			<div class="tblwrap">
				<form name="insertTravel" id="insertTravel" method="POST" action='${pageContext.request.contextPath}/travel/DoInsertTravel.action'>
					<input type="hidden" id="id" name="id" value="<%=travelInfoBO.getId() %>">
					<table class="tb2">
						<colgroup>
							<col width="150">
							<col width="110">
						</colgroup>				
						<tr>
							<th scope="row"> 제목 </th> 
	
							<td>
								<input type="text" size="100" id="contentName" name="contentName" value="">
							
							</td>
						</tr>
						<tr>
							<td></td>
	
							<td>
								<span class="btn_Center">
									<a href="javascript:doInsertTravel();" class="btnB_gray"> <span> 등록 </span></a>
								</span>		
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
					

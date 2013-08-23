<%@page import="twitter4j.Status"%>
<%@page import="java.util.List"%>
<%@page import="com.travellist.travel.object.UserBO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
	UserBO userBO = (UserBO)request.getAttribute("userBO");
	@SuppressWarnings("unchecked") 
	List<Status> status = (List<Status>)request.getAttribute("status");
%>

<script>
  var userID = jQuery("#userID").val();
  
  new TWTR.Widget({
    version: 2,
    type: 'profile',
    rpp: 3,
    interval: 6000,
    width: 208,
    height: 300,
    theme: {
      shell: {
        background: '#c2c1c9',
        color: '#ffffff'
      },
      tweets: {
        background: '#ffffff',
        color: '#0a090a',
       links: '#5399cf'
      }
    },
    features: {
      scrollbar: false,
      loop: false,
      live: false,
      hashtags: true,
      timestamp: true,
      avatars: false,
      behavior: 'all'
    }
  }).render().setUser(userID).start();
 </script>

<div class="irs_right">
	<input type="hidden" id="userID" name="userID" value="<%=userBO.getScreenId()%>">
<%

	for(Status temp : status){
%>


<%=temp.getId() %><br>
<p><%=temp.getText() %></p>
<p><%=temp.getCreatedAt().toString()%></p>

<br>
<br>
<%		
	}
%>

</div>
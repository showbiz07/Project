<%@page import="com.travellist.travel.object.UserBO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.w3c.dom.ls.LSException"%>
<%@page import="com.travellist.travel.object.NodeBO"%>
<%@page import="com.travellist.travel.object.TravelInfoBO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%
	DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	TravelInfoBO travelInfoBO = (TravelInfoBO)request.getAttribute("travelInfoBO");
	
	@SuppressWarnings("unchecked") 
	List<NodeBO> nodeList = (List<NodeBO>)request.getAttribute("nodeList");
	
	UserBO userBO = (UserBO)request.getAttribute("userBO");
	
	if(nodeList==null) nodeList = new ArrayList<NodeBO>();
	if(travelInfoBO==null) travelInfoBO = new TravelInfoBO();
	int lastNodeIndex = nodeList.size() - 1 ;
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

/* 맵 우클릭 팝업창 */
#menu1{
           position:absolute;
           display:none;
           width:220px;
           height:100px;
           background-color:#FFFFFF;
           border:8px solid #C1C1C1;
           z-index:2000;
           overflow-x:hidden; 
           overflow-y:auto; 
           *overflow-y:scroll; 
} 

#roadview{
		 	display:none;
}

</style>

<script type="text/javascript">

	
	
	var kmlLayer;
	var markers;
	var marker1;
	var marker2;
	// 맵 생성 및 초기화 작업
	function initialize() {
		map = new Tmap.Map({
			div : 'map_div',
			width : '100%',
			height : "600px",
			weight : "600px"
		});
		markers = new Tmap.Layer.Markers("MarkerLayer");
		map.addLayer(markers);

		map.events.on({
			"mouseup" : onRightClick
		});
		setLayers();
		init_StartMarker();

		map.addControl(new Tmap.Control.MousePosition());

	}
	// 시작 마커 등록
	function init_StartMarker(){
		var x = jQuery('#startX').val();
		var y = jQuery('#startY').val();
		if( x != null && y != null){
			var lonLat = new Tmap.LonLat(x, y);
	
			var size = new Tmap.Size(31, 35);
			var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
			var icon = new Tmap.Icon('${pageContext.request.contextPath}/img/ico_spot.png', size, offset);
	
			marker1 = new Tmap.Markers(lonLat, icon);
			markers.addMarker(marker1);
			map.addLayer(markers);
			map.setCenter(lonLat, 15);
	
			var addrStr = jQuery('#addressStr').val();
			jQuery('#startPlace').val( addrStr );
		}
	}
	window.onload = function() {
		initialize();
	};

	// 우 클릭 방지
	document.oncontextmenu = function() {
		return false;
	};

	// 우클릭 이벤트 등록
	function onRightClick(evt) {
		jQuery('#menu1').css('display', 'none');
		if (evt.button == 2 || evt.button == 3) {
			showMenu(evt);
		} else {
			closeMenu(evt);
		}
	}

	// 좌표 값으로 주소 얻어오는 메소드
	function loadGetAddressFromLonLat(lonLat){
		var loadGetAddressFromLonLatEvt = new Tmap.TData();
		loadGetAddressFromLonLatEvt.events.register("onComplete", loadGetAddressFromLonLatEvt, onCompleteLoadGetAddressFromLonLat);    
		loadGetAddressFromLonLatEvt.getAddressFromLonLat(lonLat);
	    
	}

	// 좌표 값으로 주소를 얻어오면 팝업 메뉴에 그 주소를 입력	
	function onCompleteLoadGetAddressFromLonLat(){
		jQuery('#addressStr').val(jQuery(this.responseXML).find("fullAddress").text());
	}

	// 우 클릭시 띄울 메뉴 
	function showMenu(evt) {
		var menu1 = document.getElementById("menu1");
		pixelX = evt.clientX + document.documentElement.scrollLeft ;
		pixelY = evt.clientY + document.documentElement.scrollTop ;
		
		menu1.style.left = pixelX + "px";
		menu1.style.top = pixelY + "px";
		menu1.style.display = 'block';

		pixelX -= 196;
		pixelY -= 92;


		var lonLat = map.getLonLatFromPixel(new Tmap.Pixel(pixelX,pixelY));
		
		jQuery('#lon').val(lonLat.lon);
		jQuery('#lat').val(lonLat.lat);

		loadGetAddressFromLonLat(lonLat);

		var htmlText = "";
		var addrStr = jQuery('#addressStr').val();
		
		htmlText += "<b>  " + addrStr + "  </b><br><br>";
		
		jQuery('#address').html(htmlText);
	}

	// 우클릭 이외의 이벤트가 들어오면 팝업 메뉴를 숨김
	function closeMenu(evt) {
		var menu1 = document.getElementById("menu1");
		menu1.style.display = 'none';
	}

	// 마커와 경로를 표시할 레이어 등록
	function setLayers() {
		//레이어 등록
		if (!map) {
			varmsg = "map객체가 초기화되기 전에 레이어가 등록되었습니다.";
			alert(msg);
			return;
		}
		var context = {
			getColor : function(feature) {
				var color = '#aaaaaa';
				if (feature.attributes.clazz && feature.attributes.clazz === 4) {
					color = '#ee0000';
				} else if (feature.cluster) {
					var onlyFour = true;
					for ( var i = 0; i < feature.cluster.length; i++) {
						if (onlyFour
								&& feature.cluster[i].attributes.clazz !== 4) {
							onlyFour = false;
						}
					}
					if (onlyFour === true) {
						color = '#ee0000';
					}
				}
				return color;
			}
		};
		var style = new Tmap.Style({
			pointRadius : 5,
			fillColor : "${getColor}",
			fillOpacity : 1,
			strokeColor : "#FF0000",
			strokeWidth : 2,
			strokeOpacity : 1,
			graphicZIndex : 1
		}, {
			context : context
		});
		var v_option = {
			renderers : [ 'Canvas', 'SVG', 'VML' ],
			styleMap : style
		};
		kmlLayer = new Tmap.Layer.Vector("kml", v_option);
		markers = new Tmap.Layer.Markers("MarkerLayer");
		map.addLayer(kmlLayer);
		map.addLayer(markers);
	}

	// 우클릭 메뉴창에 목적지 선택 메뉴를 선택했을 경우 처리
	function selectMenu() {
		jQuery('#menu1').css('display', 'none');

		if (marker2 != null) {
			marker2.destroy();
		}
		var lonLat = map.getLonLatFromPixel(new Tmap.Pixel(pixelX,pixelY));

		jQuery('#endX').val(lonLat.lon);
		jQuery('#endY').val(lonLat.lat);

		var size = new Tmap.Size(31, 35);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
		var icon = new Tmap.Icon(
				'${pageContext.request.contextPath}/img/ico_spot.png', size,
				offset);
		marker2 = new Tmap.Markers(lonLat, icon);
		markers.addMarker(marker2);
		map.addLayer(markers);
		

		var addrStr = jQuery('#addressStr').val();
		jQuery('#nodeName1').val(addrStr);
		jQuery('#endPlace').val( addrStr );

		if(jQuery('#startX').val() != "")
			getRouteData();
	}
	// POI 검색으로 생성된 마커 클릭시 처리
	function poiMarkerClick(lonLat){
		jQuery('#menu1').css('display', 'none');

		if (marker2 != null) {
			marker2.destroy();
		}

		jQuery('#endX').val(lonLat.lon);
		jQuery('#endY').val(lonLat.lat);

		var size = new Tmap.Size(31, 35);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
		var icon = new Tmap.Icon(
				'${pageContext.request.contextPath}/img/ico_spot.png', size,
				offset);
		marker2 = new Tmap.Markers(lonLat, icon);
		markers.addMarker(marker2);
		map.addLayer(markers);
		map.zoomToExtent(markers.getDataExtent());

		var addrStr = jQuery('#addressStr').val();
		jQuery('#nodeName1').val(addrStr);
		jQuery('#endPlace').val( addrStr );

		if(jQuery('#startX').val() != "")
			getRouteData();
	}

	// 마커 생성 메소드
	function addMarker(lon, lat)
	{
		var lonLat = new Tmap.LonLat(lon, lat);
		var size = new Tmap.Size(31, 35);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
		var icon = new Tmap.Icon('${pageContext.request.contextPath}/img/ico_spot.png', size,offset);
		marker = new Tmap.Markers(lonLat, icon);
		marker.events.register('click', marker,	onMarkerClick);
		markers.addMarker(marker);
		map.addLayer(markers);
	}

	// 경로 검색 메소드
	function getRouteData() {
		jQuery('#menu1').css('display', 'none');
		var startLon = jQuery('#startX').val();
		var startLat = jQuery('#startY').val();
		var endLon = jQuery('#endX').val();
		var endLat = jQuery('#endY').val();
		if (startLon == '' || startLat == '') {
			alert('출발 위치가 없습니다.');
		} else if (endLon == '' || endLat == '') {
			alert('도착 위치를 선택하세요');
		} else {
			markers.clearMarkers();
			var startLonLat = new Tmap.LonLat(startLon, startLat);
			var endLonLat = new Tmap.LonLat(endLon, endLat);
			addMarker(startLonLat.lon , startLonLat.lat);
			addMarker(endLonLat.lon , endLonLat.lat);
			var option = {
				version : "1",
				format : 'xml'
			};
			var getRouteDataEvt = new Tmap.TData();
			getRouteDataEvt.events.register("onComplete", getRouteDataEvt, onLoadSuccess);
			getRouteDataEvt.getRoutePlan(startLonLat, endLonLat, option);
		}
	}
	// 경로 반환 완료시 처리 메소드
	function onLoadSuccess() {
		if(kmlLayer != null) kmlLayer.destroyFeatures();
		
		var kmlForm = new Tmap.Format.KML().read(this.responseXML);
		for ( var i = 0; i <= kmlForm.length - 1; i++) {
			kmlLayer.addFeatures([ kmlForm[i] ]);
		}
	}

	// 위치 검색어 입력시 처리 
	function poiSearch(){
		var searchText =  jQuery('#POI').val()  ;
		var encodingSearchText = encodeURIComponent(searchText);
		var poiSearchEvt = new Tmap.TData();

		if (searchText != '') {
			var options = {		version : 1		} ;
			poiSearchEvt.events.register("onComplete", poiSearchEvt, onCompleteLoadGetPOIDataFromSearch);
			poiSearchEvt.getPOIDataFromSearch(encodingSearchText, options);
		} else {
			alert('검색할 POI를 입력해주세요.');
		}
	}
	// POI 검색 처리
	function onCompleteLoadGetPOIDataFromSearch(){

		markers.clearMarkers();
		var size = new Tmap.Size(22,30);
		var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
		if (jQuery(this.responseXML).find("searchPoiInfo").text() != '') {
			jQuery(this.responseXML).find("poi").each(
				function() {
						var name = jQuery(this).find("name").text();
						var upperAddrName = jQuery(this).find("upperAddrName").text();
						var middleAddrName = jQuery(this).find("middleAddrName").text();
						var lowerAddrName = jQuery(this).find("lowerAddrName").text();
						var upperBizName = jQuery(this).find("upperBizName").text();
						var middleBizName = jQuery(this).find("middleBizName").text();
						var lowerBizName = jQuery(this).find("lowerBizName").text();
						var detailBizName = jQuery(this).find("detailBizName").text();
						var coordX = jQuery(this).find("frontLon").text();
						var coordY = jQuery(this).find("frontLat").text();
						var icon = new Tmap.Icon('${pageContext.request.contextPath}/img/ico_cg1_b_1.png', size, offset);
						var label = new Tmap.Label("&nbsp;상호명 : "
								+ name + "<br/><br/>&nbsp;주소 : "
								+ upperAddrName + " " + middleAddrName
								+ "" + lowerAddrName
								+ "<br/><br/>&nbsp;구분 : "
								+ upperBizName + "&nbsp;&gt;&nbsp;"
								+ middleBizName + "&nbsp;&gt;&nbsp;"
								+ lowerBizName + "&nbsp;&gt;&nbsp;"
								+ detailBizName);
						var marker = new Tmap.Markers(new Tmap.LonLat(coordX, coordY), icon, label);
						marker.events.register('mouseover', marker,	onMarkerOver);
						marker.events.register('mouseout', marker,	onMarkerOut);
						marker.events.register('click', marker,	onMarkerClick);
						markers.addMarker(marker);
					});

		} else if (jQuery(this.responseXML).find("error").text() != '') {
			var errorMessage = jQuery(this.responseXML).find("error message").text();
			alert(errorMessage);
		} else {
			alert('검색결과가없습니다.');
			map.setCenter(new Tmap.LonLat(14134074.680985, 4517814.0870894), 15);
		}

		if (jQuery(this.responseXML).find("searchPoiInfo").text() != '') {
			map.zoomToExtent(markers.getDataExtent());
		} else {
			map.setCenter(new Tmap.LonLat(14134074.680985, 4517814.0870894), 15);
			markers.clearMarkers();
		}
	}
	function onMarkerClick(evt){
		var lonLat = evt.object.lonlat;
		poiMarkerClick(lonLat);
		map.setCenter(lonLat, 15);
        map.zoomTo(18);
	}

	function onMarkerOver(evt){
		this.popup.show();
	}
	function onMarkerOut(evt){
		this.popup.hide();
	}

	//pr_3857 인스탄스 생성.
	var pr_3857 = new Tmap.Projection("EPSG:3857");
	 
	//pr_4326 인스탄스 생성.
	var pr_4326 = new Tmap.Projection("EPSG:4326");
		
	function get4326LonLat(coordX, coordY){
	    return new Tmap.LonLat(coordX, coordY).transform(pr_3857, pr_4326);
	}
	function showRoadView(){
		jQuery('#menu1').css('display', 'none');
		jQuery('#roadview').css('display', 'block');
		var lon = jQuery('#lon').val();
		var lat = jQuery('#lat').val();
		var trlonlat=  get4326LonLat(lon,lat)  ;
		
		var p = new daum.maps.LatLng(trlonlat.lat, trlonlat.lon);	// WGS84 (lat , lon)
		var rc = new daum.maps.RoadviewClient();
		var rv = new daum.maps.Roadview(document.getElementById("roadview"));
		rc.getNearestPanoId(p, 50, function(panoid) {
			rv.setPanoId(panoid, p);
		});
	}
	function hideRoadView(){
		jQuery('#menu1').css('display', 'none');
		jQuery('#roadview').css('display', 'none');
	}

	function doInsertNode(){
		alert("등록되었습니다");
		document.doInsert.submit();
	}
	function showContent(sel) {
		if(document.getElementById('row'+sel).className == 'list') {
			document.getElementById('row'+sel).className = 'list selected';
			document.getElementById('row'+sel+'_detail').style.display='';
			document.getElementById('row'+sel+'_detail').className='selected_detail';
		}
		else {
			document.getElementById('row'+sel).className = 'list';
			document.getElementById('row'+sel+'_detail').style.display='none';
			document.getElementById('row'+sel+'_detail').className='';
		}
	}
		
</script>




<% if ( lastNodeIndex < 0 )  { %>
<input type="hidden" id="startX"/>
<input type="hidden" id="startY"/>
<% } else { %>
<input type="hidden" id="startX" value="<%=nodeList.get(lastNodeIndex).getEndX()%>"/>
<input type="hidden" id="startY" value="<%=nodeList.get(lastNodeIndex).getEndY()%>"/>
<% }  %>

<div class="irs_right">
	<form id="doInsert" name="doInsert" method="POST"  action='${pageContext.request.contextPath}/travel/DoInsertNode.action'>
	<input type="hidden" id="lon" name="lon"/>
	<input type="hidden" id="lat" name="lat" />
	<input type="hidden" id="endX" name="endX"/>
	<input type="hidden" id="endY" name="endY"/>
	<input type="hidden" id="pixelX" name="pixelX"/>
	<input type="hidden" id="pixelY" name="pixelY"/>
	<input type="hidden" id="addressStr" name="addressStr"/>
	<input type="hidden" id="id" name="id" value="<%=travelInfoBO.getId() %>"/>
	<input type="hidden" id="travelNo" name="travelNo" value="<%=travelInfoBO.getTravelNo()%>" />
	<input type="hidden" id="travelName" name="travelName" value="<%=travelInfoBO.getTravelName() %>"/>
						
	<table>
		<colgroup>
			<col width="600">
			<col width="5">
			<col width="450">
		</colgroup>
		<tr>
			<td>
				<div id="map_div"></div>
			</td>
			<td>
			</td>
	
			<td>
				<div class="tblwrap">
					
						<table class="tb2">
							<colgroup>
								<col width="70">
								<col width="110">
								<col width="10">
								<col width="110">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">일정</th>
									<td>	
										<input type="text" id="endDate" name="endDate"/>
    										<img id="createCalendar" src="${pageContext.request.contextPath}/img/calendar.png" alt="[calendar icon]"/>
									</td>
									<td></td>
									<td>
										<input type="button" value="등록" onclick="doInsertNode()">
									</td>	
								</tr>
	
								<tr>
									<th scope="row">목적지</th>
									<td colspan="3">
										<input type="text" name="nodeName1" id="nodeName1" value="" readonly="readonly" size="45">
									</td>
								</tr>
								
								<tr>
									<th scope="row">내용</th>
									<td colspan="3">
										<textarea id="content" name="content" rows="30" cols="52" style="resize:none;"></textarea>
									</td>
								</tr>
							</tbody>
						</table>
					
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table class="tb2">
					<colgroup>
						<col width="20">
						<col width="70">
						<col width="20">
						<col width="70">
						<col width="20">
						<col width="70">
						<col width="20">
						<col width="70">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">위치검색</th>
							<td>
								<input type="text" id="POI" onKeypress="javascript:if(event.keyCode==13){poiSearch(); return false;}" > 
							</td>
							<td>
								<input type="button" value="검색" onclick="poiSearch()">
							</td>
						</tr>
							
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	</form>
	<div id="roadview" style="width:600px;height:400px;">
	</div>
	
	<div class="nodeList">
		<table class="tb2">
			<colgroup>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"> nodeNo </th>
					<th scope="row"> 목적지 </th>
					<th scope="row"> 날짜 </th>
				</tr>
	<%			int cnt=0;			
				for(NodeBO temp : nodeList) {
	%>
					<tr id="row<%=cnt%>" class="list" onclick="showContent(<%=cnt%>)">
						<td> <%=cnt+1%></td>
						<td> <%=temp.getNodeName() %></td>
						<td><%=sdFormat.format(temp.getEndDate())%></td> 
					</tr>
					<tr id="row<%=cnt%>_detail" style="display: none;">
						<td></td>
						<td> <pre><%=temp.getContent() %></pre></td>
						<td></td>
					</tr>
	<%
					cnt++;
				}
	%>		
			</tbody>		
		</table>
	</div>
</div>
	
	
<div>
	<div id="menu1" style="text-decoration: none;">
		<br>
		<div id ="address">
			
		</div>
		
		<div>
			&nbsp;<a style="text-decoration: none;"
				href="javascript:selectMenu()"><font color="black">현재위치를 도착 위치로 지정</font></a>
		</div>

		<div>
			&nbsp;<a style="text-decoration: none;"
				href="javascript:showRoadView()"><font color="black">로드뷰 보기</font></a>
		</div>
				
		<div>
			&nbsp;<a style="text-decoration: none;"
				href="javascript:hideRoadView()"><font color="black">로드뷰 닫기</font></a>
		</div>		
	</div>
</div>
<script>
$('#createCalendar').click(
	      function(e) {
		        $('#endDate').AnyTime_noPicker().AnyTime_picker().focus();
	    	    e.preventDefault();
	      } );
</script>
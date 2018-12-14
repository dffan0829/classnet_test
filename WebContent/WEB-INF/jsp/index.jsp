<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>网站首页</title>
		<link href="<%=request.getContextPath()%>/css/index.css"
			rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="wrap">
			<!--头部定义开始-->
			<div id="header" class="main">
				<jsp:include page="/WEB-INF/jsp/pub/header.jsp"></jsp:include>
			</div>
			<!--头部定义结束-->
			<!--中部内容定义开始-->
			<div class="main">
				<!--右侧内容定义-->
				<div class="mainContent">
					<div class="r_navigation">
						&nbsp;<strong style="font-size: 14px;">头条新闻</strong>
					</div>
					<div id="column1">
						<div id="flashNews">
							<div style="width:300px;height: 230px;float:right;text-align:center">
									<script type=text/javascript>
										var focus_width=300;
										var focus_height=230;
										var swf_height = focus_height;
										var picPath='<f:message key="upload_http_path"/>/images/';
										var pics='';
										var links='';
										<c:forEach items="${newsBigImgList}" var="nb">
										pics+=picPath+'<c:out value="${nb.img}"/>|';
										links+="<c:url value="/news/see.do?id=${nb.id}"/>|";
										</c:forEach>
										pics=pics.substring(0,pics.length-1);
										links=links.substring(0,links.length-1);
										var flash_path='<c:url value="/images/s_flash.swf"/>';
										document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
										document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="'+flash_path+'"><param name="quality" value="high"><param name="bgcolor" value="#F0F0F0">');
										document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
										document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&borderwidth='+focus_width+'&borderheight='+focus_height+'">');
										document.write('<embed src="'+flash_path+'" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&borderwidth='+focus_width+'&borderheight='+focus_height+'" menu="false" bgcolor="#F0F0F0" quality="high" width="'+ focus_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');		
										document.write('</object>');
									</script>
								</div>
						</div>
						<div class="newsList">
							<ul class="listStyle1">
								<c:forEach items="${newsTJList}" var="newstj">
								<li><a href="<c:url value="/news/see.do?id=${newstj.id}"/>" target="_blank"><c:out value="${newstj.title}"/></a><f:formatDate value="${newstj.pubtime}" pattern="MM-dd"/></li>
								</c:forEach>
							</ul>
						</div>
						<div class="clearbox"></div>
					</div>
					<div class="c_spacing"></div>
					<div class="ADBanner">
						<img src="<f:message key="image_http_url"/>/images/ADBanner1.gif" />
					</div>
					<div class="c_spacing"></div>
					<div id="moralEduSpace" class="goldenrod">
						<dl>
							<dt>
								<em><a href="<c:url value="/clazz/"/>">课程中心</a></em>
								<ul class="colChildNav">
									<c:forEach items="${clazzMenuList}" var="clazzmenu">
									<li><a href="<c:url value="/clazz/search.do?pmId=${clazzmenu.id}"/>"><c:out value="${clazzmenu.name}"/></a></li>
									</c:forEach>
								</ul>
							</dt>
							<dd>
								<ul class="thumbListStlye2">
									<c:forEach items="${tjClazzList}" var="tjc">
									<li>
										<div class="pe_u_thumb">
											<a href="<c:url value="/clazz/view.do?id=${tjc.id}"/>" target="_blank"><img src="<f:message key="upload_http_path"/>/images/${tjc.img}" width="160" height="120" border="0"/></a>
										</div>
										<div class="pe_u_thumb_title">
											<a href="<c:url value="/clazz/view.do?id=${tjc.id}"/>" target="_blank"><c:out value="${tjc.name}"/></a>
											<br />
										</div>
									</li>
									</c:forEach>
								</ul>
								<ul class="listStyle1">
									<c:forEach items="${newClazzList}" var="newclazz">
									<li><a href="<c:url value="/clazz/view.do?id=${newclazz.id}"/>" target="_blank"><c:out value="${newclazz.name}"/></a><f:formatDate value="${newclazz.pubtime}" pattern="MM-dd"/></li>
									</c:forEach>
								</ul>
								<div class="clearbox"></div>
							</dd>
						</dl>
					</div>
					<div class="c_spacing"></div>
					<div id="moralEduSpace" class="lightGreen" style="margin-top:60px;">
						<dl>
							<dt>
								<em><a href="<c:url value="/source/"/>">资源下载</a></em>
								<ul class="colChildNav">
									<c:forEach items="${sourceMenuList}" var="sourceMenu">
									<li><a href="<c:url value="/source/menu.do?id=${sourceMenu.id}"/>"><c:out value="${sourceMenu.name}"/></a></li>
									</c:forEach>
								</ul>
							</dt>
							<dd>
								<ul class="listStyle1" style="float:left">
								<c:forEach items="${sourceList}" var="source" varStatus="vs">
								<li><a href="<f:message key="upload_http_path"/>/files/${source.filename}"><c:out value="${source.name}"/>[<c:out value="${source.fileSize}" />]</a>[<f:formatDate value="${source.pubtime}" pattern="MM-dd"/>]</li>
								<c:if test="${vs.index==7}">
								</ul><ul class="listStyle1">
								</c:if>
								</c:forEach>
								<div class="clearbox"></div>
							</dd>
						</dl>
					</div>
					<div class="c_spacing"></div>
					<div id="instructionalResearch" class="mediumTurquoise" style="margin-top:20px;">
						<dl>
							<dt>
								<em><a href="<c:url value="/topic/"/>">互动交流</a></em>
								<ul class="colChildNav">
								<c:forEach items="${topicMenuList}" var="topicMenu">
									<li><a href="<c:url value="/topic/menu.do?id=${topicMenu.id}"/>"><c:out value="${topicMenu.name}" /></a></li>
								</c:forEach>
								</ul>
							</dt>
							<dd>
								<div class="col2">
									<ul class="listStyle1">
	            						<c:forEach items="${topicList}" var="topic" varStatus="vs">
	            						<c:if test="${vs.index<8}">
	            						<li><a href="<c:url value="/topic/topic.do?id=${topic.id}"/>" target="_blank">华师奥运抬水版</a><f:formatDate value="${topic.pubtime}" pattern="MM-dd HH:mm"/></li>
	            						</c:if>
	            						</c:forEach>
						            </ul>
								</div>
								<div class="col1">
									<ul class="listStyle1">
	            						<c:forEach items="${topicList}" var="topic" varStatus="vs">
	            						<c:if test="${vs.index>7}">
	            						<li><a href="<c:url value="/topic/topic.do?id=${topic.id}"/>" target="_blank">华师奥运抬水版</a><f:formatDate value="${topic.pubtime}" pattern="MM-dd HH:mm"/></li>
	            						</c:if>
	            						</c:forEach>
						            </ul>
								</div>
							</dd>
						</dl>
					</div>
					<div class="c_spacing"></div>
				</div>
				<!--侧边栏-->
				<div id="sideBar">
					<jsp:include page="/WEB-INF/jsp/userbox.jsp"></jsp:include>
					<div class="left_box">
						<dl>
							<dt><em>学科资源</em></dt>
							<dd>
								<ul class="subjectList">
									<c:forEach items="${clazzMenuList}" var="clazzmenu">
									<li><a href="<c:url value="/clazz/search.do?pmId=${clazzmenu.id}"/>"><c:out value="${clazzmenu.name}"/></a></li>
									</c:forEach>
									<div class="clearbox"></div>
								</ul>
							</dd>
						</dl>
					</div>
				</div>
				<div class="clearbox"></div>
			</div>
			<!--中部内容定义结束-->
			<div class="clearbox"></div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
	</body>
</html>

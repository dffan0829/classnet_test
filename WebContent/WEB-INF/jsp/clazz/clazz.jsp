<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><c:out value="${clazzEntity.name}" />-课程中心</title>
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/article.css"
			rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="wrap">
			<!--头部定义开始-->
			<div id="header" class="main">
				<jsp:include page="/WEB-INF/jsp/pub/header.jsp"></jsp:include>
			</div>
			<!--头部定义结束-->
			<div id="center_all" class="main">
				<div id="main_bg">
					<div id="main_right">
						<div id="main_right_box">
							<!-- 网站位置导航信息开始 -->
							<div class="r_navigation">
								您现在的位置：
								<a href="<c:url value="/"/>"><f:message key="site_name"/></a>&gt;&gt;
								<a href="<c:url value="/clazz/"/>"><span class="current">课程中心</span></a>&gt;&gt;
								课程内容
							</div>
							<!-- 网站位置导航信息结束 -->
							<div class="c_spacing"></div>
							<!-- 主体内容开始 -->
          <div class="c_main_content_box">
            <div class="c_title_text" style="padding-top:10px;">
              <h2><c:out value="${clazzEntity.name}"/></h2>
              </div>
            <div class="c_title_author" style=""> 
            	<table width="100%" border="0">
            		<tr>
						<td align="center" width="20%">作者：</td>
				        <td align="left" width="30%"><c:out value="${clazzEntity.author}"/></td>
						<td align="center" width="50%" rowspan=5>
							<img src="<f:message key="image_http_url"/>/images/zizhan_xia.gif" style="cursor: pointer;" onclick="location='<f:message key="upload_http_path"/>/files/${clazzEntity.filename}'"/>
						</td>
				    </tr>
					<tr>
						<td align="center">作者单位：</td>
				        <td align="left"><c:out value="${clazzEntity.school}"/></td>
				    </tr>
					<tr>
						<td align="center">文件类型：</td>
				        <td align="left"><c:out value="${clazzEntity.fileType.name}"/></td>
				    </tr>
				    <tr>
						<td align="center">访问量：</td>
				        <td align="left"><c:out value="${clazzEntity.viewCount}"/></td>
				    </tr>
					<tr>
						<td align="center">发布时间：</td>
				        <td align="left"><f:formatDate value="${clazzEntity.pubtime}" pattern="yyyy年MM月dd日"/></td>
				    </tr>
            	</table>
            </div>
            <!-- 正文 -->
            <div class="c_content_text">
              <div class="c_content_overflow" id="fontzoom"> 
   				<p align="center">
   					<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="680" height="538" align="middle">
				        <param name="allowFullScreen" value="false" />
				        <param name="movie" value="<f:message key="upload_http_path"/>/files/${clazzEntity.flashFilename}" />
				        <param name="quality" value="high" />
				        <param name="bgcolor" value="#ffffff" /> 
						<param name="wmode" value="window" /> 
				        <embed src="<f:message key="upload_http_path"/>/files/${clazzEntity.flashFilename}" quality="high" bgcolor="#ffffff" width="680" height="538" align="middle" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" wmode="opaque" pluginspage="http://www.macromedia.com/go/getflashplayer" />
			        </object>
   				</p>
   	 		 </div>
            </div>
          </div>
          <!-- 主体内容结束 -->
						</div>
					</div>
					<div id="sideBar">
						<jsp:include page="/WEB-INF/jsp/userbox.jsp"></jsp:include>
						<div class="left_box">
							<dl>
								<dt><em>栏目列表</em></dt>
								<dd>
									<ul class="subjectList">
										<c:forEach items="${menuList}" var="menu">
										<li><a href="<c:url value="/clazz/search.do?pmId=${menu.id}"/>"><c:out value="${menu.name}" /></a></li>
										</c:forEach>
										<div class="clearbox"></div>
									</ul>
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
			<div class="clearbox"></div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><c:out value="${newsEntity.title}" />-新闻</title>
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
				<ul id="subnav">
					<c:forEach items="${menuList}" var="menu">
					<li><a href="<c:url value="/news/menu.do?id=${menu.id}"/>"><c:out value="${menu.name}" /></a></li>
					</c:forEach>
				</ul>
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
								<a href="<c:url value="/news/"/>"><span class="current">校园新闻</span></a>&gt;&gt;
								<a href="<c:url value="/news/menu.do?id=${newsEntity.newsMenu.id}"/>"><c:out value="${newsEntity.newsMenu.name}"/></a>&gt;&gt;
								新闻内容
							</div>
							<!-- 网站位置导航信息结束 -->
							<div class="c_spacing"></div>
							<!-- 主体内容开始 -->
          <div class="c_main_content_box">
            <div class="c_title_text" style="padding-top:10px;">
              <h2><c:out value="${newsEntity.title}"/></h2>
              </div>
            <div class="c_title_author"> 
            	<span>作者：<c:out value="${newsEntity.author}"/></span> 
            	<span>来源：<c:out value="${newsEntity.source}"/></span> 
            	<span>发布时间：<f:formatDate value="${newsEntity.pubtime}" pattern="yyyy年MM月dd日"/></span> 
            </div>
            <!-- 正文 -->
            <div class="c_content_text">
              <div class="c_content_overflow" id="fontzoom"> 
   				<p align="center">
   					<c:out value="${newsEntity.content}" escapeXml="false"/>
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
										<li><a href="<c:url value="/news/menu.do?id=${menu.id}"/>"><c:out value="${menu.name}" /></a></li>
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
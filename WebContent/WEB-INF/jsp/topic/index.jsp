<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>互动交流</title>
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
					<div id="sideBar">
						<jsp:include page="/WEB-INF/jsp/userbox.jsp"></jsp:include>
						<div class="left_box">
							<dl>
								<dt><em>栏目列表</em></dt>
								<dd>
									<ul class="subjectList">
										<c:forEach items="${menuList}" var="menu">
											<li><a href="<c:url value="/topic/menu.do?id=${menu.id}"/>"><c:out value="${menu.name}" /></a></li>
										</c:forEach>
										<div class="clearbox"></div>
									</ul>
								</dd>
							</dl>
						</div>
					</div>
					<div id="" style="float:right;width: 750px;">
						<div class="">
								<!-- 网站位置导航信息开始 -->
								<div class="r_navigation">
									您现在的位置：
									<a href="<c:url value="/"/>"><f:message key="site_name"/></a>&gt;&gt;
									<a href="<c:url value="/topic/"/>"><span class="current">互动交流</span></a>
								</div>
								<!-- 父栏目列表信息列表开始 -->
								<div class="classlist_box">
									<!-- 栏目循环列表开始 -->
									<c:forEach items="${menuList}" var="menu">
									<div class="classBox">
										<h4><a href="<c:url value="/topic/menu.do?id=${menu.id}"/>"><c:out value="${menu.name}" /></a></h4>
										<ul class="listStyle1">
											<c:forEach items="${menu.topicList}" var="topic">
											<li><a href="<c:url value="/topic/topic.do?id=${topic.id}"/>"><c:out value="${topic.title}"/></a>[发布人:<span style="color:#1177b9"><c:out value="${topic.userEntity.username}" /></span>][时间:<span style="color:#1177b9"><f:formatDate value="${topic.pubtime}" pattern="MM-dd"/></span>][回复数:<span style="color:#1177b9"><c:out value="${topic.replyNum}" default="0"/></span>]</li>
											</c:forEach>
										</ul>
										<a class="more" href="<c:url value="/topic/menu.do?id=${menu.id}"/>">点击这里查看更多内容&gt;&gt;</a>
									</div>
									</c:forEach>
									<!-- 父栏目列表信息列表结束 -->
								</div>
								<!-- 栏目循环列表结束 -->
							</div>
					</div>
					
				</div>
			</div>
			<div class="clearbox"></div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>

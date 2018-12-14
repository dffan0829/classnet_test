<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><c:out value="${menuEntity.name}" />-新闻</title>
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
					<li><a href="#"><c:out value="${menu.name}" /></a></li>
					</c:forEach>
				</ul>
			</div>
			<!--头部定义结束-->
			<div id="center_all" class="main">
				<div id="main_bg">
					<div id="main_right">
						<div id="main_right_box">
							 <!-- 右侧二列开始 -->
        <div id="r_main">
          <div class="r_content">
            <dl>
              <dt>热点文章</dt>
              <dd><ul>
           		<c:forEach items="${hotNewsList}" var="news">
				<li><a href="<c:url value="/news/see.do?id=${news.id}"/>"><c:out value="${news.title}"></c:out></a></li>
				</c:forEach>
          	  </ul></dd>
            </dl>
          </div>
          <div class="c_spacing"></div>
          <div class="r_content">
            <dl>
              <dt>推荐文章</dt>
              <dd><ul>
                <c:forEach items="${tjnews}" var="news">
				<li><a href="<c:url value="/clazz/view.do?id=${news.id}"/>"><c:out value="${news.title}"></c:out></a></li>
				</c:forEach>
               </ul></dd>
            </dl>
          </div>
        </div>
        <div class="c_main">
        <!-- 网站位置导航信息开始 -->
        <div class="r_navigation">
          您现在的位置：<a href="<c:url value="/"/>"><f:message key="site_name"/></a>&gt;&gt;
			<a href="<c:url value="/news/"/>"><span class="current">校园新闻</span></a>&gt;&gt;
          <a href="<c:url value="/news/menu.do?id=${menuEntity.id}"/>"><c:out value="${menuEntity.name}"/></a> 
         </div>
        <!-- 网站位置导航信息结束 -->
          <div class="c_spacing"></div>
          <div class="centerAD"><img src="<f:message key="image_http_url"/>/images/centerAD.jpg" /></div>
          <div class="c_spacing"> </div>
          <!-- 子栏目列表信息列表开始 -->
          <div class="childclasslist_box">
          <h3>学校快讯</h3>
          <ul class="listStyle1">
			<c:forEach items="${newsList}" var="news">
			<li><a href="<c:url value="/news/see.do?id=${news.id}"/>" target="_blank"><c:out value="${news.title}"/></a><f:formatDate value="${news.pubtime}" pattern="MM-dd"/></li>
			</c:forEach>
          </ul>
          </div>
          <!-- 子栏目列表信息列表结束 -->
        <div class="class_page"> <span id="pe100_page_内容信息列表_普通式" class="pagecss">
 		<jsp:include page="/WEB-INF/jsp/pub/page1.jsp"/>
 		</span></div>
          <div class="clearbox"> </div>
        </div>
        <!-- 右侧二列结束 -->
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
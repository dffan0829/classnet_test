<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>互动交流--<c:out value="${menuEntity.name}" /></title>
		<link href="<%=request.getContextPath()%>/css/default.css"
			rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/article.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
		/* 分页 */
.class_page_topic {
	clear: left;
	text-align: left;
	color:#4C9DCE;
}
.class_page_topic ul, .class_page_topic li {
	padding: 0px;
	margin: 0px 0px 0px 10px;
	text-align: left;
	list-style: none;
}
.class_page_topic a, .class_page_topic strong {
	display:inline-block;
	border:1px solid #D7E5F2;
	line-height:160%;
	text-decoration:none;
	padding:0 5px;
	color: #4C9DCE;
}
.class_page_topic strong {
	background:#5384AF;
	color:#fff;
}
		</style>
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
									<a href="<c:url value="/topic/"/>"><span class="current">互动交流</span></a>&gt;&gt;
									<a href="<c:url value="/topic/menu.do?id=${menuEntity.id}"/>"><c:out value="${menuEntity.name}"/></a>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #a8d7f1">
								<tr>
									<td align="left" width="70%" height="20">
										<div class="class_page_topic"><span class="pagecss">
								 		<jsp:include page="/WEB-INF/jsp/pub/page2.jsp"/>
								 		</span></div>
									</td>
									<td align="right" height="20">
										<input type="button" value="发帖" onclick="location='<c:url value="/master/topic.do?m=addTopic"/>';"/>
									</td>
								</tr>
								</table>
								<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8d7f1">
								<tr>
									<td width="60%" align="left" height="30" style="padding-left:6px;color:#ffffff;font-weight: bold;">标题</td>
									<td width="15%" align="center" style="color:#ffffff;font-weight: bold;">发帖人</td>
									<td width="10%" align="center" style="color:#ffffff;font-weight: bold;">回复</td>
									<td width="15%" align="center" style="color:#ffffff;font-weight: bold;">最后更新</td>
								</tr>
								<c:choose>
								<c:when test="${not empty topicList}">
								<c:forEach items="${topicList}" var="topic" varStatus="vs">
								<tr>
									<td align="left" height="40" bgcolor="${vs.index%2==1?'#e8e9f9':'#ffffff'}" style="padding-left:6px;"><a href="<c:url value="/topic/topic.do?id=${topic.id}"/>"><c:out value="${topic.title}" /></a></td>
									<td align="center" bgcolor="${vs.index%2==1?'#e8e9f9':'#ffffff'}"><c:out value="${topic.userEntity.username}" /><p><f:formatDate value="${topic.pubtime}" pattern="MM-dd HH:mm"/></p></td>
									<td align="center" bgcolor="${vs.index%2==1?'#e8e9f9':'#ffffff'}"><c:out value="${topic.replyNum}" default="0"/></td>
									<td align="center" bgcolor="${vs.index%2==1?'#e8e9f9':'#ffffff'}"><c:out value="${topic.editUser}" /><p><f:formatDate value="${topic.editTime}" pattern="MM-dd HH:mm"/></p></td>
								</tr>								
								</c:forEach>
								</c:when>
								<c:otherwise>
								<tr>
									<td colspan="4" bgcolor="#ffffff" height="30">暂无帖子</td>
								</tr>
								</c:otherwise>
								</c:choose>
								</table>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #a8d7f1">
								<tr>
									<td align="left" width="70%" height="20">
										<div class="class_page_topic"><span class="pagecss">
								 		<jsp:include page="/WEB-INF/jsp/pub/page2.jsp"/>
								 		</span></div>
									</td>
									<td align="right" height="20">
										<input type="button" value="发帖" onclick="location='<c:url value="/master/topic.do?m=addTopic"/>';"/>
									</td>
								</tr>
								</table>
							</div>
					</div>
					
				</div>
			</div>
			<div class="clearbox"></div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>

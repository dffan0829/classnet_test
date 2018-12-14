<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><c:out value="${topicEntity.title}" />--互动交流</title>
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
					<div id="" style="width: 100%;">
						<div class="">
								<!-- 网站位置导航信息开始 -->
								<div class="r_navigation">
									您现在的位置：
									<a href="<c:url value="/"/>"><f:message key="site_name"/></a>&gt;&gt;
									<a href="<c:url value="/topic/"/>"><span class="current">互动交流</span></a>&gt;&gt;
									<a href="<c:url value="/topic/menu.do?id=${topicEntity.menuEntity.id}"/>"><c:out value="${topicEntity.menuEntity.name}"/></a>
								</div>
								<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8d7f1" style="margin-top:8px;">
								<tr>
									<td align="left" height="30" style="color:#ffffff;font-weight: bold;padding-left:8px">
										<c:out value="${topicEntity.title}" />
									</td>
								</tr>
								</table>
								<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8d7f1" style="margin-top:8px;">
								<tr>
									<td width="20%" bgcolor="#e8e9f9" rowspan="3">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="left" valign="middle" style="padding-left:30px;">
												<img src="<f:message key="image_http_url"/>/images/photo.jpg" width="120" height="120"/>
											</td>
										</tr>
										<tr>
											<td style="padding-top:10px;;padding-left:30px;font-weight: bold;" align="left">
												<c:out value="${topicEntity.userEntity.username}" />
											</td>
										</tr>
										</table>
									</td>
									<td width="80%" height="24" align="left" bgcolor="#e8e9f9">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" height="24" style="padding-left:10px;">发表于：<f:formatDate value="${topicEntity.pubtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
										<td align="right" style="padding-right:10px;font-weight: bold;">楼主</td>
									</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="160" bgcolor="#e8e9f9" align="left" valign="top" style="padding:10px;">
										<c:out value="${topicEntity.detail}" escapeXml="false"/>
									</td>
								</tr>
								<tr>
									<td bgcolor="#e8e9f9" height="24">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" height="24" style="padding-left:10px;">回复次数：<c:out value="${topicEntity.replyNum}" default="0"/></td>	
										<td align="right" style="padding-right:10px;font-weight: bold;"><a href="#">TOP</a></td>
									</tr>
									</table>
									</td>
								</tr>
								</table>
								<c:if test="${not empty topicEntity.answerList}">
								<c:forEach items="${topicEntity.answerList}" var="answer" varStatus="vs">
								<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8d7f1" style="margin-top:8px;">
								<tr>
									<td width="20%" bgcolor="#ffffff" rowspan="3">
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="left" valign="middle" style="padding-left:30px;">
												<img src="<f:message key="image_http_url"/>/images/photo.jpg" width="120" height="120"/>
											</td>
										</tr>
										<tr>
											<td style="padding-top:10px;;padding-left:30px;font-weight: bold;" align="left">
												<c:out value="${answer.userEntity.username}" />
											</td>
										</tr>
										</table>
									</td>
									<td width="80%" height="24" align="left" bgcolor="#ffffff">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" height="24" style="padding-left:10px;">发表于：<f:formatDate value="${answer.pubtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
										<td align="right" style="padding-right:10px;font-weight: bold;">#<c:out value="${vs.index+1}"/>楼</td>
									</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="160" bgcolor="#ffffff" align="left" valign="top" style="padding:10px;">
										<c:choose>
											<c:when test="${answer.status==1}"><c:out value="${answer.content}" escapeXml="false"/></c:when>
											<c:otherwise><span style="color:red;background-color: yellow">该回复已经被管理员屏蔽</span></c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td bgcolor="#ffffff" height="24">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td align="left" height="24" style="padding-left:10px;">
											<c:if test="${not empty loginUser&&isAdmin}">
												<a href="javascript:pingbi(${answer.id})"><font style="color:red">屏蔽该回复</font></a>
											</c:if>
										</td>	
										<td align="right" style="padding-right:10px;font-weight: bold;"><a href="#">TOP</a></td>
									</tr>
									</table>
									</td>
								</tr>
								</table>
								</c:forEach>
								</c:if>
								<table width="100%" border="0" cellpadding="0" cellspacing="1" style="margin-top:8px;border:1px solid #a8d7f1">
								<tr>
									<td width="20%" valign="top" align="center">
										<table width="90%" border="0" cellpadding="0" cellspacing="0" style="border:1px solid #a8d7f1;margin-top:10px;margin-bottom:10px;">
										<tr><td height="30" align="left" style="border-bottom: 1px solid #a8d7f1">&nbsp;赞助商广告</td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">超经典全套JAVA EE教程,免费</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">Android 移动开发实战 免费体</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">学.Net3.5 框架 坚持经典传承</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">PHP暑期班 六月报名7.5折</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">来CSDN一个月的学习经验</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">一个项目涉及到的50个Sql语句</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">[百度分享]以太网卡TSO技术</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">ASP.NET报表高效开发--博计</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">一个项目涉及到的50个Sql语句</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">PHP暑期班 六月报名7.5折</a></td></tr>
										<tr><td align="left" height="25">&nbsp;<a href="">Android 移动开发实战 免</a></td></tr>
										</table>
									</td>
									<td width="80%" valign="top">
										<form action="<c:url value="/topic/reply.do"/>" method="post" style="margin:0" onsubmit="return check(this)">
										<table width="100%" border="0" cellpadding="0" cellspacing="0" style="margin-top:10px;">
										<tr><td align="left" height="30">
											<span style="font-weight: bold;">回复内容</span>
											<c:if test="${empty loginUser}">
											匿名用户不能发表回复！ 
											<a href="<c:url value="/acegilogin.jsp"/>" target="_blank"><font style="color:red">登录</font></a> | <a href="<c:url value="/reg.do?m=toreg"/>" target="_blank"><font style="color:red">注册</font></a>
											</c:if>
										</td></tr>
										<tr>
											<td align="left"><textarea name="content"></textarea></td>
										</tr>
										<tr>
											<td align="center" height="30">
												<input type="submit" value="提交回复" ${empty loginUser?'disabled=disabled':''} />
												<input type="hidden" name="topicId" value="${topicEntity.id}"/>
											</td>
										</tr>
										</table>
										</form>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
var oFCKeditor = new FCKeditor('content') ;
oFCKeditor.BasePath	= "${pageContext.request.contextPath}/fckeditor/";
oFCKeditor.Width="740";
oFCKeditor.Height="240";
oFCKeditor.Value="&nbsp;&nbsp;&nbsp;&nbsp;";
oFCKeditor.ToolbarSet="classnet";
oFCKeditor.Config["CustomConfigurationsPath"]="${pageContext.request.contextPath}/fckeditor/replyconfig.js?d="+new Date().getTime();
oFCKeditor.ReplaceTextarea();

function check(form){
	if(form.content.value==""){
		alert("回复内容不能为空");
		return false;
	}
	return true;
}
function pingbi(id){
	$.ajax({
		type:"get",
		url:"<c:url value="/pingbi.do"/>",
		data:"id="+id,
		success:function(msg){
			if(msg=="1"){
				alert("操作成功");
				location.href=location.href;
			}
			else{
				alert("操作失败");
			}
		}
	});
}
</script>

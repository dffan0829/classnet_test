<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>课程中心</title>
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
								<a href="<c:url value="/clazz/"/>"><span class="current">课程中心</span></a>
							</div>
							<!-- 网站位置导航信息结束 -->
							<div class="c_spacing">
							</div>
							<div class="r_ad" style="border:1px solid #A6C7CC;height: 78px;background:url(http://localhost:9080/classnet/images/leftboxContentBg.jpg)">
								<div style="padding-top:10px;text-align: center">
								学科一级目录:
								<select name="pmenuId" id="pmenuId" style="width:100px;" onchange="changemenu(this.value)">
									<option value="0">请选择</option>
									<c:forEach items="${menuList}" var="menu">
										<option value="${menu.id}" ${menu.id==param.pmId?'selected=selected':''}><c:out value="${menu.name}"/></option>
									</c:forEach>
								</select>
								&nbsp;
								学科二级目录:
								<select name="menuId" id="menuId" style="width:100px;">
									<option value="0">请选择</option>
									<c:if test="${not empty childMenuList}">
										<c:forEach items="${childMenuList}" var="cm">
											<option value="${cm.id}" ${cm.id==param.mId?'selected=selected':''}><c:out value="${cm.name}"/></option>
										</c:forEach>
									</c:if>
								</select>
								&nbsp;
								文件类型:
								<select name="ft" id="ft" style="width:100px;">
									<option value="0">请选择</option>
									<c:forEach items="${fileTypeList}" var="ft">
										<option value="${ft.id}" ${ft.id==param.ftId?'selected=selected':''}><c:out value="${ft.name}"/></option>
									</c:forEach>
								</select>
								</div>
								<div style="padding-top:10px;text-align: center">
									<input type="text" size="70" name="key" id="key" value="${key}" style="height: 20px;"/>
									<input type="button" value="搜索" style="width: 60px;" onclick="search()"/>
								</div>
							</div>
							<div class="c_main_one">
								<dl>
									<dt class="c_title">
										<div class="more"></div>
										搜索结果
									</dt>
									<dd class="c_content">
										<!-- 显示8张最新图片文章 -->
										<div class="a_photo_list">
											<c:forEach items="${clazzList}" var="clazz">
											<li>
												<div class="pe_u_thumb">
													<a href="<c:url value="/clazz/view.do?id=${clazz.id}"/>" target="_blank"><img src="<f:message key="upload_http_path"/>/images/${clazz.img}" width="160" height="120" border="0"></a>
												</div>
												<div class="pe_u_thumb_title">
													<a href="<c:url value="/clazz/view.do?id=${clazz.id}"/>" target="_blank"><c:out value="${clazz.name}"/></a>
													<br />
												</div>
											</li>
											</c:forEach>
											<div class="clearbox"></div>
										</div>
										<div class="class_page"><span class="pagecss">
								 		<jsp:include page="/WEB-INF/jsp/pub/page1.jsp"/>
								 		</span></div>
								        <div class="clearbox"> </div>
									</dd>
								</dl>
							</div>
							<!-- 最新图片文章结束 -->
						</div>
					</div>
					<div id="sideBar">
						<jsp:include page="/WEB-INF/jsp/userbox.jsp"></jsp:include>
						<div class="left_box">
							<dl>
								<dt>
									<em>栏目列表</em>
								</dt>
								<dd>
									<ul class="subjectList">
										<c:forEach items="${menuList}" var="menu">
										<li><a href="<c:url value="/clazz/search.do?pmId=${menu.id}"/>"><c:out value="${menu.name}"/></a></li>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript">
function changemenu(pid){
	$("#menuId").empty();
	$("#menuId").append('<option value="0">请选择</option>');
	$.ajax({
		type:"get",
		url:"<c:url value="/changeMenu.do"/>",
		data:"pid="+pid,
		success:function(obj){
			if(obj){
				var array = eval("("+obj+")");
				if(array){
					for(var i=0;i<array.length;i++){
						$("#menuId").append('<option value="'+array[i].id+'">'+array[i].name+'</option>');
					}
				}
			}
		}
	});
}
function search(){
	var url = '<c:url value="/clazz/search.do"/>';
	var pmId = $("#pmenuId").val();
	var mId = $("#menuId").val();
	var ftId = $("#ft").val();
	var key = $("#key").val();
	if(pmId==0&&mId==0&&ftId==0&&key==""){
		location = url;
		return ;
	}
	else{
		url += '?';
		if(mId!=0){
			url += '&mId='+mId;
		}
		if(pmId!=0){
			url += '&pmId='+pmId;
		}
		if(ftId!=0){
			url += '&ftId='+ftId;
		}
		if(key!=""){
			url += '&key='+key;
		}
		url = url.replace(/\?&/,"?");
		location = url;
	}
}
</script>
</html>

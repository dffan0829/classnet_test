<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新闻</title>
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
								<a href="<c:url value="/news"/>"><span class="current">校园新闻</span>
								</a>
							</div>
							<!-- 网站位置导航信息结束 -->
							<div class="c_spacing">
							</div>
							<div id="column1">
								<div style="width:300px;height: 240px;float:right;text-align:center">
									<script type=text/javascript>
										var focus_width=300;
										var focus_height=260;
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
								<div class="newsList">
									<h3>校园新闻</h3>
									<ul class="listStyle1">
										<c:forEach items="${newsTJList}" var="newstj">
										<li><a href="<c:url value="/news/see.do?id=${newstj.id}"/>" target="_blank"><c:out value="${newstj.title}"/></a><f:formatDate value="${newstj.pubtime}" pattern="MM-dd"/></li>
										</c:forEach>
									</ul>
								</div>
								<div class="clearbox"></div>
							</div>
							<div class="c_spacing"></div>
							<div class="clearbox"></div>
							<div class="c_spacing"></div>
							<div class="r_ad">
								<a href="#"><img src="<f:message key="image_http_url"/>/images/rightAD2.JPG" alt="动易网络" border="0"/></a>
							</div>
							<div class="childclass_main">
								<!-- 栏目循环列表开始 -->
								<c:forEach items="${menuList}" var="mm">
									<div class="childclass_main_box">
										<div class="childclass_title">
											<div class="more"><a href="<c:url value="/news/menu.do?id=${mm.id}"/>">[更多]</a></div>
											<a href="" target="_blank"><c:out value="${mm.name}" /></a>
										</div>
										<div class="childclass_content">
											<!-- <ul class="thumbListStlye1">
												<li>
													<div class="pe_u_thumb">
														<a href="<c:url value="/news/see.do?id=${mm.id}"/>" target="_blank"><img src="<f:message key="upload_http_path"/>/images/${mm.newsEntity.img}" width="95" border="0" height="65" /></a>
													</div> 
													<div class="pe_u_thumb_Intro">
														<h3><a href="<c:url value="/news/see.do?id=${mm.newsEntity.id}"/>" target="_blank"><c:out value="${mm.newsEntity.title}"/></a></h3>
														<div class="intro">
															<c:out value="${fn:substring(mm.newsEntity.content,0,30)}"></c:out>...
														</div>
													</div>
												</li>
												<div class="clearbox"></div>
											</ul>-->
											<ul class="listStyle1">
												<c:forEach items="${mm.newsList}" var="mmnews">
												<li><a href="<c:url value="/news/see.do?id=${mmnews.id}"/>" target="_blank"><c:out value="${mmnews.title}"/></a></li>
												<div class="clearbox"></div>
												</c:forEach>
											</ul>
											<div class="clearbox">
											</div>
										</div>
										<div class="childclass_bot">
										</div>
									</div>
								</c:forEach>

								<!-- 栏目循环列表结束 -->
								<div class="clearbox"></div>
							</div>
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


						<div class="left_box">
							<dl>
								<dt><em>最新文章</em></dt>
								<dd><ul>
								<c:forEach items="${newnewsList}" var="nn">
									<li><a href="<c:url value="/news/see.do?id=${nn.id}"/>"><c:out value="${nn.title}" /></a></li>
								</c:forEach>
								</ul></dd>
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
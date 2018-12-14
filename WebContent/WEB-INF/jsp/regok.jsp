<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
	<head>
	<title>注册成功</title>
	<link href="<%=request.getContextPath()%>/css/default.css"
		rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/prompts.css"
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
				<div class="u_management_bg">
					<div class="P_width">
							<div class="Showms">
								<dl class="top"></dl>
								<dl class="content">
									<dd class="content1">
										<div class="Pic">
											<img id="ImgRight" src="<f:message key="image_http_url"/>/images/P_Right.gif"
												style="border-width: 0px;" />
										</div>
										<div class="MS">
											<dl>
												<dt class="titWrong">
													成功信息
												</dt>
												<dd>
													<li>
														注册成功！
													</li>
												</dd>
											</dl>
										</div>
										<div class="clearbox">
										</div>
										<div class="BUT">
											<a id="LnkReturnUrl" href="<c:url value="/"/>">&lt;&lt;&nbsp;返回上一页</a>
										</div>
									</dd>
								</dl>
								<dl class="bottom"></dl>
								<dl class="Shadow"></dl>
							</div>
							<div class="clearbox"></div>
					</div>
				</div>
			</div>
			<jsp:include page="/WEB-INF/jsp/pub/footer.jsp"></jsp:include>
		</div>
	</body>
</html>

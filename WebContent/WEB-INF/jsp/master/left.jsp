<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp"%>
<div id="content_1">
<ul>
<li><a href="<c:url value="/master/" />"><img align="absmiddle" src="<f:message key="image_http_url"/>/images/article_add.gif" style="border: 0" />&nbsp;&nbsp;会员信息</a></li>
<li><a href="<c:url value="/master/homework.do?m=hw" />"><img align="absmiddle" src="<f:message key="image_http_url"/>/images/article_add.gif" style="border: 0" />&nbsp;&nbsp;提交作业</a></li>
<li><a href="<c:url value="/master/topic.do?m=addTopic" />"><img align="absmiddle" src="<f:message key="image_http_url"/>/images/article_add.gif" style="border: 0" />&nbsp;&nbsp;发布主题</a></li>
<li><a href="<c:url value="/master/topic.do?m=list" />"><img align="absmiddle" src="<f:message key="image_http_url"/>/images/article_add.gif" style="border: 0" />&nbsp;&nbsp;我的互动</a></li>
</ul>
</div>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/pub/include.jsp" %>
<script type="text/javascript">
document.write("<ul>");
var url = document.URL;
if(url.indexOf("newsMenu.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/news/newsMenu.do?m=list"/>">新闻目录管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/news/newsMenu.do?m=list"/>">新闻目录管理</a></li>');
}
if(url.indexOf("pubnews.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/news/pubnews.do?m=to"/>">发布新闻</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/news/pubnews.do?m=to"/>">发布新闻</a></li>');
}
if(url.indexOf("/news.do")!=-1){
	document.write('<li class="bj"><a href="<c:url value="/admin/news/news.do?m=list"/>">新闻管理</a></li>');
}
else{
	document.write('<li><a href="<c:url value="/admin/news/news.do?m=list"/>">新闻管理</a></li>');
}
document.write("</ul>");
</script>
<%@page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/pub/include.jsp"%>
<c:if test="${PageInfo.next||PageInfo.prev}">
<div style="padding-right:20px;">
		<span class="txt"> 
		    <c:if test="${PageInfo.prev}">
				<a href="<c:url value="${PageInfo.url}${PageInfo.page-1}"/>">上一页</a>
			</c:if> 
			<c:choose>
				<c:when test="${PageInfo.total_page<=5}">
					<c:set var="firstPage" value="1" scope="page" />
					<c:set var="endPage" value="${PageInfo.total_page}" scope="page" />
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${PageInfo.page<5}">
							<c:set var="firstPage" value="1" scope="page" />
							<c:set var="endPage" value="5" scope="page" />
						</c:when>
						<c:when test="${(PageInfo.total_page-PageInfo.page)>=0 && (PageInfo.total_page-PageInfo.page) <5}"> 
							<c:set var="firstPage" value="${PageInfo.total_page-4}" scope="page" />
							<c:set var="endPage" value="${PageInfo.total_page}" scope="page" />
						</c:when>
						
						<c:when test="${(PageInfo.total_page-PageInfo.page) >5}"> 
							<c:set var="firstPage" value="${(PageInfo.total_page-PageInfo.page)+1}" scope="page" />
							<c:set var="endPage" value="${PageInfo.total_page}" scope="page" />
						</c:when>
						
						
						<c:otherwise>
							<c:set var="firstPage" value="${PageInfo.page+1}" scope="page" />
							<c:set var="endPage" value="${PageInfo.page+5}" scope="page" />
						</c:otherwise>
					</c:choose>
				</c:otherwise> 
			</c:choose> 
			
			<c:if test="${firstPage>0}">
			<c:forEach begin="${firstPage}" end="${endPage}" var="i">
				<c:choose>
					<c:when test="${PageInfo.page == i}">
						<strong><c:out value="${i}" /></strong>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="<c:out value="${PageInfo.url}${i}"/>"><c:out
								value="${i}" /></a>&nbsp;
					</c:otherwise>
				</c:choose>
			</c:forEach> 
			</c:if>
			<c:if test="${PageInfo.next}">
				<a href="<c:url value="${PageInfo.url}${PageInfo.page+1}"/>">下一页</a>
				<br />
			</c:if> 
			</span>

</div>
</c:if>

 <!--  上一页 下一页 -->  

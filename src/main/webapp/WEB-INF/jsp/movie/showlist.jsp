<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/movie/form" />">New Movie</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>MovieName</th>
		<th>MovieLength</th>
		<th>MovieLang</th>
		<th>ReleaseDate</th>
		<th>AgeCertificate</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/movie/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.movieName}
				</td>
				<td>
					${each.movieLength}
				</td>
				<td>
					${each.movieLang}
				</td>
				<td>
					${each.releaseDate}
				</td>
				<td>
					${each.ageCertificate}
				</td>
				<td>
					<a href="<c:url value="/rest/movie/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/movie/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Movies yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>


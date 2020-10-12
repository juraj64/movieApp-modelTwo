<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/director/form" />">New Director</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>FirstName</th>
		<th>LastName</th>
		<th>DateOfBirth</th>
		<th>Nationality</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/director/${each.id}" />">${each.id}</a>
				</td>
				<td>
					${each.firstName}
				</td>
				<td>
					${each.lastName}
				</td>
				<td>
					${each.dateOfBirth}
				</td>
				<td>
					${each.nationality}
				</td>
				<td>
					<a href="<c:url value="/rest/director/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/director/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Directors yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>


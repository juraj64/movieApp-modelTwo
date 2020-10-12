<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<a href="<c:url value="/rest/actor/form" />">New Actor</a>
</div>
<div>
	<c:if test="${not empty result}">
	<table>
		<thead>
		<th>Id</th>
		<th>FirstName</th>
		<th>LastName</th>
		<th>DateOfBirth</th>
		<th/>
		<th/>
		</thead>
		<c:forEach items="${result}" var="each" >
			<tr>
				<td>
					<a href="<c:url value="/rest/actor/${each.id}" />">${each.id}</a>
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
					<a href="<c:url value="/rest/actor/${each.id}" />">Show</a>
				</td>
				<td>
					<c:url value="/rest/actor/${each.id}" var="action"/>
					<form:form action="${action}" method="DELETE">
						<input id="delete" type="submit" value="Delete"/>
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty result}"><p>There are no Actors yet.</p></c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>


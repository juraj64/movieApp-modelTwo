<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>New MovieRevenue</h2>
	<c:url value="/rest/movieRevenue" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">
	<div id="movieRevenue_domesticTakings">
		<label for="_domesticTakings">DomesticTakings:</label>
		<form:input cssStyle="width:300px" id="_domesticTakings" path="domesticTakings"/>
		<br/>
		<form:errors cssClass="errors" id="_domesticTakings" path="domesticTakings"/>
	</div>
	<div id="movieRevenue_internationalTakings">
		<label for="_internationalTakings">InternationalTakings:</label>
		<form:input cssStyle="width:300px" id="_internationalTakings" path="internationalTakings"/>
		<br/>
		<form:errors cssClass="errors" id="_internationalTakings" path="internationalTakings"/>
	</div>
	<div class="submit" id="movieRevenue_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
		<div id="movieRevenue_domesticTakings">
			<label for="_domesticTakings">DomesticTakings:</label>
			<div class="box" id="_domesticTakings">${result.domesticTakings}</div>
		</div>
		<br/>
		<div id="movieRevenue_internationalTakings">
			<label for="_internationalTakings">InternationalTakings:</label>
			<div class="box" id="_internationalTakings">${result.internationalTakings}</div>
		</div>
		<br/>
	</c:if>
	<c:if test="${empty result}">No MovieRevenue found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

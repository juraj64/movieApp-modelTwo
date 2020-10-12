<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<h2>New MovieActor</h2>
	<c:url value="/rest/movieActor" var="action"/>
	<form:form action="${action}" method="POST" modelAttribute="entity">

	<div id="movieActor_movieId">
       	<label for="_movie.id">Movie:</label>
       	<form:input cssStyle="width:300px" id="_movie.id" path="movie.id"/>
       	<br/>
       	<form:errors cssClass="errors" id="_movie.id" path="movie.id"/>
    </div>
    <div id="movieActor_actorId">
       	<label for="_actor.id">Actor:</label>
       	<form:input cssStyle="width:300px" id="_actor.id" path="actor.id"/>
       	<br/>
       	<form:errors cssClass="errors" id="_actor.id" path="actor.id"/>
    </div>

	<div class="submit" id="movieActor_submit">
		<input id="proceed" type="submit" value="Save"/>
	</div>
	</form:form>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>

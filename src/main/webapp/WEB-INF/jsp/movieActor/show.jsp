<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
	<c:if test="${not empty result}">
	<div id="movieActor_movieId">
    	<label for="_movie.id">Movie:</label>
    	<div class="box" id="_movie.id">${result.movie.id}</div>
    </div>
    <br/>
    <div id="movieActor_actorId">
       	<label for="_actor.id">Actor:</label>
    	<div class="box" id="_actor.id">${result.actor.id}</div>
    </div>
    <br/>
	</c:if>
	<c:if test="${empty result}">No MovieActor found with this id.</c:if>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>


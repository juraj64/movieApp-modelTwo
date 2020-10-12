<jsp:directive.include file="/WEB-INF/jsp/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/jsp/header.jsp"/>
<div>
<p>
<p><font size="5" color="saddlebrown">MovieApp-modelTwo</font></p>
</p>
<ul>
<li><a href="<c:url value="/rest/actor" />">Actors</a></li>
<li><a href="<c:url value="/rest/director" />">Directors</a></li>
<li><a href="<c:url value="/rest/movieActor" />">MovieActors</a></li>
<li><a href="<c:url value="/rest/movie" />">Movies</a></li>
<li><a href="<c:url value="/rest/movieRevenue" />">MovieRevenues</a></li>
</ul>
</div>
<jsp:directive.include file="/WEB-INF/jsp/footer.jsp"/>



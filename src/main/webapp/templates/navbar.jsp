<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:url var="linkCreateForm" value="add-student">
	<c:param name="form" value="create"></c:param>
</c:url>
<c:url var="linkHome" value="/" />
<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="${linkHome}">Navbar</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="${linkHome}">Home</a></li>
				<li class="nav-item"><a class="nav-link active" aria-current="page" href="${linkCreateForm}">Add Student</a></li>
			</ul>
		</div>
	</div>
</nav>
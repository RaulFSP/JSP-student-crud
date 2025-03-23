<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="templates/head.jsp">
	<jsp:param value="Student Form" name="title" />
</jsp:include>
<body>
	<header><jsp:include page="templates/navbar.jsp" />
	</header>
	<main class="d-flex flex-column justify-content-center">
		<section class="container ">
			<jsp:include page="templates/student-form.jsp" />
		</section>
		
	</main>
	<footer></footer>
	<jsp:include page="templates/scripts.jsp" />
	
</body>
</html>
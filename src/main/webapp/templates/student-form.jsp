<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<h2>${titleForm}</h2>
<form method="post" action="add-student" id="studentForm">

	<input type="hidden" name="_method" value="${valueForm}"> <input type="hidden" name="student-id" value="${id}">

	<div class="mb-3">
		<label for="student-fn" class="form-label">Fist Name</label> <input type="text" class="form-control" id="student-fn" name="student-fn"
			required="required" value="${name}">

	</div>
	<div class="mb-3">
		<label for="student-ln" class="form-label">Last Name</label> <input type="text" class="form-control" id="student-ln" name="student-ln"
			required="required" value="${lastname}">
	</div>
	<div class="mb-3">
		<label for="student-email" class="form-label">Email address</label> <input type="email" class="form-control" id="student-email"
			aria-describedby="emailHelp" name="student-email" required="required" value="${email}">
		<div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
	</div>

	<!-- Button trigger modal -->
	<button id="modalBtn" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">${btnValue}</button>
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Confirm Student</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">Confirm if you want to add this student.</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Confirm">
				</div>
			</div>
		</div>
	</div>
</form>
<c:if test="${not empty id}">
	<form method="post" action="add-student">
		<input type="hidden" name="_method" value="delete"> <input type="hidden" name="student-id" value="${id}">
		<button id="modalBtnDelete" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button>


		<!-- Modal -->


		<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModal" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title fs-5" id="exampleModalLabel">Confirm Student</h1>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">Confirm if you want to delete this student.</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>

						<input type="submit" class="btn btn-danger" value="Delete">
					</div>
				</div>
			</div>
		</div>
	</form>
</c:if>


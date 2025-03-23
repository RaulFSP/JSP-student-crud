package servlet;

import java.io.IOException;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import repository.StudentRepository;
import service.StudentService;

/**
 * Servlet implementation class StudentFormServlet
 */
@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	private StudentService service;

	@Inject
	private StudentRepository studentRepository;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String formType = request.getParameter("form");

		if (formType.equals("create")) {
			request.setAttribute("titleForm", "Student Form");
			request.setAttribute("valueForm", "post");
			request.setAttribute("btnValue", "Add Student");
			request.getRequestDispatcher("/add-student.jsp").forward(request, response);
		} else if (formType.equals("update")) {
			request.setAttribute("titleForm", "Student Update Form");
			request.setAttribute("valueForm", "put");
			request.setAttribute("btnValue", "Update Student");
			Long id = Long.parseLong(request.getParameter("student-id"));
			Optional<Student> student = studentRepository.findById(id);
			if (student.isPresent()) {
				request.setAttribute("id", student.get().getId());
				request.setAttribute("name", student.get().getFirstName());
				request.setAttribute("lastname", student.get().getLastName());
				request.setAttribute("email", student.get().getEmail());
			}
			request.getRequestDispatcher("/add-student.jsp").forward(request, response);
		} else if (formType.equals("delete")) {
			request.setAttribute("titleForm", "Delete Confirmation");
			Long id = Long.parseLong(request.getParameter("student-id"));
			Optional<Student> student = studentRepository.findById(id);
			if (student.isPresent()) {
				request.setAttribute("id", student.get().getId());
				request.setAttribute("name", student.get().getFirstName());
				request.setAttribute("lastname", student.get().getLastName());
				request.setAttribute("email", student.get().getEmail());
			}
			request.getRequestDispatcher("/add-student.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("_method");
		if (method.equals("post")) {
			postForm(request, response);
		} else if (method.equals("put")) {
			doPut(request, response);
		} else if (method.equals("delete")) {
			doDelete(request, response);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		putForm(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("student-id"));
		Optional<Student> student = studentRepository.findById(id);
		System.out.println("entou no doDelete");
		if (student.isPresent()) {
			studentRepository.delete(student.get().getId());
			resp.sendRedirect(req.getContextPath());
		}

	}

	private void postForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fn = request.getParameter("student-fn").trim();
		String ln = request.getParameter("student-ln").trim();
		String email = request.getParameter("student-email").trim();
		if (fn == null || fn.trim().isEmpty() || ln == null || ln.trim().isEmpty() || email == null
				|| email.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
		}
		try {
			Student student = new Student(fn, ln, email);
			service.save(student);

			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to save the Student!");
		}
	}

	private void putForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("student-id"));
		String fn = request.getParameter("student-fn").trim();
		String ln = request.getParameter("student-ln").trim();
		String email = request.getParameter("student-email").trim();
		if (fn == null || fn.trim().isEmpty() || ln == null || ln.trim().isEmpty() || email == null
				|| email.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
		}
		try {
			Student student = new Student(fn, ln, email);
			student.setId(id);
			service.update(student);

			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to save the Student!");
		}
	}
}

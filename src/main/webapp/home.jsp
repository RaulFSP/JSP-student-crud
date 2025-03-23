<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<jsp:include page="templates/head.jsp">
	<jsp:param value="Home" name="title" />
</jsp:include>
<body>
	<header><jsp:include page="templates/navbar.jsp" />
	</header>
	<br>

	<main class="d-flex flex-column justify-content-center gap-5">
		<div class="container  border border-primary-subtle rounded text-center">
			<p class="text-start text-break p-3 ">Cadastra-se nesta plataforma, ainda em teste, de estudos. Apenas 5 minutos para preencher o formulários,
				e então participar!</p>
		</div>

		<div class="container text-center">
			<button type="button" class="btn btn-primary" id="btnCadastrados">Cadastrados</button>
			<button type="button" class="btn btn-primary" id="btnSobre">Sobre</button>
		</div>
		<div class="container">
			<table id="tabela-cadastrados" class="table table-striped-columns caption-top">
				<caption>Students Present</caption>
				<thead>
					<tr class="text-center">
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="student" items="${students}">

						<c:url var="linkUpdateForm" value="add-student">
							<c:param name="form" value="update"></c:param>
							<c:param name="student-id" value="${student.id}"></c:param>
						</c:url>
						
						<tr>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.email}</td>
							<td class="text-center"><a href="${linkUpdateForm}" class="btn btn-outline-primary">Settings</a>
				
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="sobre" class="bg-light p-3" hidden="true">
				<h2 class="text-center">Nota Importante: Site em Desenvolvimento</h2>

				<p class="text-wrap text-monospace">Prezado(a) visitante,</p>

				<p class="text-wrap text-monospace">Este site está atualmente em fase de desenvolvimento e não está totalmente funcional. Trata-se de um
					projeto acadêmico/profissional que visa criar um sistema de CRUD (Create, Read, Update, Delete) para gerenciamento de alunos, utilizando a
					plataforma Apache Tomcat como servidor de aplicações.</p>

				<h3 class="text-monospace">Contexto do Projeto</h3>
				<p class="text-wrap text-monospace">O sistema tem como objetivo centralizar informações de estudantes em um ambiente web, permitindo operações
					básicas como:</p>

				<ul class="list-unstyled text-monospace">
					<li><strong>Cadastro:</strong> Inclusão de novos registros de alunos.</li>
					<li><strong>Listagem:</strong> Visualização de todos os alunos cadastrados.</li>
					<li><strong>Atualização:</strong> Edição de dados existentes.</li>
					<li><strong>Exclusão:</strong> Remoção de registros.</li>
				</ul>

				<h3 class="text-monospace">Arquitetura do Projeto</h3>
				<p class="text-wrap text-monospace">A arquitetura do projeto prioriza a integração entre Java Servlets (para lógica de negócios) e JavaServer
					Pages (JSP) (para interface), com o Tomcat como container para execução das aplicações.</p>

				<h3 class="text-monospace">Status Atual</h3>
				<p class="text-wrap text-monospace">Embora o esqueleto básico do sistema já esteja implementado, ainda há funcionalidades em desenvolvimento,
					como:</p>

				<ul class="list-unstyled text-monospace">
					<li><strong>Persistência de dados:</strong> Integração com banco de dados relacional por meio de ORM (ex: Hibernate).</li>
					<li><strong>Validação de formulários:</strong> Garantia de consistência nos dados inseridos.</li>
					<li><strong>Segurança:</strong> Implementação de autenticação e autorização de usuários.</li>
					<li><strong>Interface responsiva:</strong> Ajustes para compatibilidade com dispositivos móveis.</li>
				</ul>

				<h3 class="text-monospace">Por Que o Site Não Está Funcional?</h3>
				<p class="text-wrap text-monospace">Algumas funcionalidades podem apresentar comportamentos inesperados ou erros temporários devido a:</p>

				<ul class="list-unstyled text-monospace">
					<li><strong>Falta de conexão com banco de dados:</strong> Os dados ainda não são persistidos de forma permanente.</li>
					<li><strong>Testes em andamento:</strong> Validações e tratamentos de exceção estão sendo refinados.</li>
					<li><strong>Otimização de código:</strong> Melhorias na performance e segurança estão sendo implementadas.</li>
				</ul>
			</div>
		</div>
	</main>
	<footer></footer>



	
	

	<jsp:include page="templates/scripts.jsp" />
	<script type="text/javascript">
    const btn1 = document.getElementById('btnCadastrados');
    const btn2 = document.getElementById('btnSobre');
    const sobre = document.getElementById('sobre');
	const tabela = document.getElementById('tabela-cadastrados');
    btn1.addEventListener('click', () => {
        sobre.hidden = true;
    	if (tabela.hidden){
        	tabela.hidden = !tabela.hidden	
        }
    });
    btn2.addEventListener('click',()=>{
    	tabela.hidden = true;
    	if (sobre.hidden){
    		sobre.hidden = !sobre.hidden
    	}
    })
</script>
</body>
</html>
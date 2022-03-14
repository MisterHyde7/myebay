<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Utente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica utente</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">Modifica i campi di interesse</h6>
		
		
							<form method="post" action="ExecuteEditUtenteAdminServlet" class="row g-3" novalidate="novalidate">
								
								<input type="hidden" name="idUtente" value="${utenteDaModificare.id }">
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${utenteDaModificare.nome }" required>
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${utenteDaModificare.cognome }" required>
								</div>
							
								<div class="col-md-6">
									<label for="username" class="form-label">Username </label>
									<input type="text" class="form-control" name="username" id="username" placeholder="Inserire lo username" value="${utenteDaModificare.username }" required>
								</div>
								
								<div class="col-md-6">
									<label for="stato" class="form-label">Stato </label>
									<select class="form-select" aria-label="Default select example" id="stato" name="statoUtente">
									  <option selected>Stato utente</option>
									  <option value="attivo">ATTIVO</option>
									  <option value="disabilitato">DISABILITATO</option>
									</select>
								</div>
								
								<div class="col-md-6 form-check">
									<p>Ruoli:</p>
									<c:forEach items="${mappaRuoliSelezionati_attr}" var="ruoloEntry">
										<div class="form-check">
											  <input class="form-check-input" name="ruoloInput" type="checkbox" value="${ruoloEntry.key.id}" id="ruoloInput-${ruoloEntry.key.id}" >
											  <label class="form-check-label" for="ruoloInput-${ruoloEntry.key.id}" >
											    ${ruoloEntry.key.codice}
											  </label>
										</div>
								  	</c:forEach>
								</div>
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</body>
</html>
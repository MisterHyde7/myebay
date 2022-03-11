<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Annuncio</title>
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
				        <h5>Modifica annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">Modifica i campi di interesse</h6>
		
		
							<form method="post" action="ExecuteEditAnnuncioServlet" class="row g-3" novalidate="novalidate">
							
								<c:set var="annuncioInPagina" value="${annuncioDaModificare}"></c:set>
								
								<input type="hidden" name="idAnnuncio" value="${annuncioDaModificare.id }">
							
							
								<div class="col-md-6">
									<label for="testo" class="form-label">Testo annuncio <span class="text-danger">*</span></label>
									<input type="text" name="testo" id="testo" class="form-control" placeholder="Inserire il testo" value="${annuncioDaModificare.testoAnnuncio }" required>
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo <span class="text-danger">*</span></label>
									<input type="number" name="prezzo" id="prezzo" class="form-control" placeholder="Inserire il prezzo" value="${annuncioDaModificare.prezzo }" required>
								</div>
								
								<div class="col-md-6 form-check">
									<p>Categorie:</p>
									<c:forEach items="${mappaCategorieConSelezionati_attr}" var="categoriaEntry">
										<div class="form-check">
											  <input class="form-check-input" name="categoriaInput" type="checkbox" value="${categoriaEntry.key.id}" id="categoriaInput-${categoriaEntry.key.id}" ${categoriaEntry.value?'checked':'' }>
											  <label class="form-check-label" for="categoriaInput-${categoriaEntry.key.id}" >
											    ${categoriaEntry.key.codice}
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
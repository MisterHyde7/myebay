<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Utente</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	   		
	   		 <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					 ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
	    
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio utente</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${utente_show_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome:</dt>
							  <dd class="col-sm-9">${utente_show_attr.nome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${utente_show_attr.cognome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Username:</dt>
							  <dd class="col-sm-9">${utente_show_attr.username}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Creazione:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${utente_show_attr.dateCreated}" /></dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Stato:</dt>
							  <dd class="col-sm-9">${utente_show_attr.stato}</dd>
					    	</dl>
					    	
					    	<c:if test="${utente_show_attr.ruoli!=null}">
					    	 <p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Ruoli
							  </a>
							</p>
							<div class="collapse" id="collapseExample">
							  <div class="card card-body">
							  	<c:forEach items="${utente_show_attr.ruoli}" var="ruoloItem">
							  		<dl class="row">
								 		<dd class="col-sm-9">${ruoloItem.codice}</dd>
							   		</dl>
							  	</c:forEach>
							  </div>
							</div>
							</c:if>
					    	
					    	<a href="PrepareListUtenteServlet" class='btn btn-outline-secondary' style='width:80px'>
						        <i class='fa fa-chevron-left'></i> Back
						    </a>
					 
					    <!-- end card body -->
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
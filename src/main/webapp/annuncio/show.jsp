<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Annuncio</title>
	   
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
					        <h5>Visualizza dettaglio annuncio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Testo annuncio:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${show_annuncio_attr.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data Inserimento:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${show_annuncio_attr.dataInserimento}" /></dd>
					    	</dl>
					    	
					    	
					    	
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <c:if test="${userInfo.nome==null }">
						         <a href="PrepareLoginServlet?from=/ExecuteVisualizzaAnnuncioServlet&idAnnuncio=${show_annuncio_attr.id}" class='btn btn-outline-primary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Buy
						        </a>
					        </c:if>
					        <c:if test="${userInfo.nome!=null and show_annuncio_attr.aperto==true}">
						         <a href="ExecuteBuyAnnuncioServlet?idUtente=${userInfo.id}&idAnnuncio=${show_annuncio_attr.id}" class='btn btn-outline-primary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Buy
						        </a>
					        </c:if>
					    
					        <a href="PrepareListAnnuncioServlet?idUtente=${userInfo.id }" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
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
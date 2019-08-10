<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<title>BSong</title>
		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Nunito+Sans:700%7CNunito:300,600" rel="stylesheet"> 

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/bootstrap.min.css"/>

		<!-- Font Awesome Icon -->
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/templates/public/css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
		
		<!-- Header -->
		<header id="header">
			<%@include file="/templates/public/inc/header.jsp" %>
		</header>
		<!-- /Header -->
		
		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-8">
						<div class="row">
						<%
							if (request.getAttribute("resultSearch") != null) {
								@SuppressWarnings("unchecked")
								ArrayList<Song> listSongBySearch = (ArrayList<Song>) request.getAttribute("resultSearch");
								for (Song objSong: listSongBySearch) {
									String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objSong.getSongCat().getCatName()) + "-" + objSong.getSongCat().getCatId() + "-" + 1;
									String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
						%>	
							<!-- post -->
							<div class="col-md-12">
								<div class="post post-row">
									<a class="post-img" href="<%=songUrl%>"><img width="300px" height="180px" src="<%=request.getContextPath()%>/files/<%=objSong.getSongPicture()%>" alt=""></a>
									<div class="post-body">
										<div class="post-meta">
											<a class="post-category cat-2" href="<%=catUrl%>"><%=objSong.getSongCat().getCatName()%></a>
											<span class="post-date"><%=StringUtil.convertIntoDate(objSong.getSongDateCreate())%></span>
											<span class="post-date">View: <%=objSong.getSongCouter() %></span>
										</div>
										<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName()%></a></h3>
										<p><%=objSong.getSongPreview().substring(0, 60) %>...</p>
									</div>
								</div>
							</div>
							<!-- /post -->
						<%	}} %>
							<div class="clearfix visible-md visible-lg"></div>
						<%
						  	if(request.getAttribute("search") != null){
								String search = (String)request.getAttribute("search");
							
						  		int numberOfPage = (Integer)request.getAttribute("numberOfPages");
						  		int currentPage = (Integer)request.getAttribute("currentPage");
						  		if(numberOfPage > 0) {
					  	%>
					  		<div class="pagination-area">
					      	<p class="pages"><small>Page <%=currentPage %> of <%=numberOfPage %></small>
						    <ul class="pagination">
						     <li class="page-item <%if(currentPage == 1) out.print("disabled"); %>"><a href="<%=request.getContextPath()%>/searches/<%if(currentPage - 1 < 0) out.print(currentPage); else out.print(currentPage - 1);%>?search=<%=StringUtil.makeSlug(search)%>">&laquo;</a></li>
						     <%
						     	String active = "";
						    	for(int i = 1; i <= numberOfPage; i++){
						    		String searchUrl = request.getContextPath() + "/searches/"+ i + "?search=" + StringUtil.makeSlug(search);
						    		if(currentPage == i){
						    			active = "active";
						    		} else {
						    			active = "";
						    		}
						     %>
						     <li class="page-item <%=active%>"><a href="<%=searchUrl%>"><%=i %></a></li>
						     <%	} %>
						    <li class="page-item <%if(currentPage == numberOfPage) out.print("disabled"); %>"><a href="<%=request.getContextPath()%>/searches/<%if(currentPage + 1 > numberOfPage) out.print(numberOfPage); else out.print(currentPage + 1);%>?search=<%=StringUtil.makeSlug(search)%>">&raquo;</a></li>
						   </ul>
						  </div>
						  <% }} %>
						</div>
					</div>
					
					<div class="col-md-4">
						<%@include file="/templates/public/inc/right-bar.jsp" %>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /section -->

		<!-- Footer -->
		<footer id="footer">
			<%@include file="/templates/public/inc/footer.jsp" %>
		</footer>
		<!-- /Footer -->

		<!-- jQuery Plugins -->
		<script src="<%=request.getContextPath()%>/templates/public/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/main.js"></script>

	</body>
</html>

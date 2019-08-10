<%@page import="model.bean.Song"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Home Page</title>

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
			<%@ include file="/templates/public/inc/header.jsp"%>
		</header>
		<!-- /Header -->
		
		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
			<%
				if (request.getAttribute("listSongLatest") != null) {
					@SuppressWarnings("unchecked")
					ArrayList<Song> listOfLatestSong = (ArrayList<Song>) request.getAttribute("listSongLatest");
			%>	
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="section-title">
							<h2>Latest Songs</h2>
						</div>
					</div>
					<%
						int i = 1;
						for(Song objSong: listOfLatestSong) {
							String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objSong.getSongCat().getCatName()) + "-" + objSong.getSongCat().getCatId() + "-" + 1;
							String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
					%>
					<!-- post -->
					<div class="col-md-4">
						<div class="post">
							<a class="post-img" href="<%=songUrl%>"><img width="360px" height="216px" src="<%=request.getContextPath() %>/files/<%=objSong.getSongPicture() %>" alt=""></a>
							<div class="post-body">
								<div class="post-meta">
									<a class="post-category cat-<%=i %>" href="<%=catUrl%>"><%=objSong.getSongCat().getCatName() %></a>
									<span class="post-date"><%=StringUtil.convertIntoDate(objSong.getSongDateCreate())%></span>
								</div>
								<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName() %></a></h3>
							</div>
						</div>
					</div>
					<!-- /post -->
					<%
							if (i == 3) {
								i = 0;
					%>
					<div class="clearfix visible-md visible-lg"></div>
					<%		}
						i++;
						} %>
					
				</div>
				<!-- /row -->
				<%	} %>

				<!-- row -->
				<div class="row">
					<div class="col-md-8">
						<div class="row">
						<%
							if (request.getAttribute("mostPopSong") != null) {
								Song song = (Song)request.getAttribute("mostPopSong");
								String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(song.getSongCat().getCatName()) + "-" + song.getSongCat().getCatId() + "-" + 1;
								String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(song.getSongName()) + "-" + song.getSongId();
						%>
							<!-- post -->
							<div class="col-md-12">
								<div class="post post-thumb">
									<a class="post-img" href="<%=songUrl%>"><img width="750px" height="450px" src="<%=request.getContextPath()%>/files/<%=song.getSongPicture()%>" alt=""></a>
									<div class="post-body">
										<div class="post-meta">
											<a class="post-category cat-2" href="<%=catUrl%>"><%=song.getSongCat().getCatName()%></a>
											<span class="post-date"><%=StringUtil.convertIntoDate(song.getSongDateCreate())%></span>
											<span class="post-date">View: <%=song.getSongCouter()%></span>
										</div>
										<h3 class="post-title"><a href="<%=songUrl%>"><%=song.getSongName()%></a></h3>
									</div>
								</div>
							</div>
							<!-- /post -->
						<%	}  %>
						<%
							if(request.getAttribute("listSongMostPopFromCat") != null) {
								@SuppressWarnings("unchecked")
								ArrayList<Song> listSongMostPopFromCat = (ArrayList<Song>) request.getAttribute("listSongMostPopFromCat");
								int i = 1;
								for ( Song objSong: listSongMostPopFromCat){
									String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objSong.getSongCat().getCatName()) + "-" + objSong.getSongCat().getCatId() + "-" + 1;
									String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
						%>
							<!-- post -->
							<div class="col-md-6">
								<div class="post">
									<a class="post-img" href="<%=songUrl%>"><img width="360px" height="216px" src="<%=request.getContextPath()%>/files/<%=objSong.getSongPicture()%>" alt=""></a>
									<div class="post-body">
										<div class="post-meta">
											<a class="post-category cat-4" href="<%=catUrl%>"><%=objSong.getSongCat().getCatName()%></a>
											<span class="post-date"><%=StringUtil.convertIntoDate(objSong.getSongDateCreate())%></span>
											<span class="post-date">View: <%=objSong.getSongCouter()%></span>
										</div>
										<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName()%></a></h3>
									</div>
								</div>
							</div>
							<!-- /post -->
						<%
								if (i % 2 == 0) {
									i = 1;
						%>
							<div class="clearfix visible-md visible-lg"></div>
						<%
								}}}
						%>
							
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
		
		
		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-8">
					<%
						if (request.getAttribute("listMostPop") != null) {
							@SuppressWarnings("unchecked")
							ArrayList<Song> listMostPop = (ArrayList<Song>)request.getAttribute("listMostPop");
							if(listMostPop.size() > 0){
					%>
						<div class="row">
							<div class="col-md-12">
								<div class="section-title">
									<h2>Most Popular</h2>
								</div>
							</div>
							<%
								for(Song objSong: listMostPop){
									String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objSong.getSongCat().getCatName()) + "-" + objSong.getSongCat().getCatId() + "-" + 1;
									String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
							%>
							<!-- post -->
							<div class="col-md-12">
								<div class="post post-row">
									<a class="post-img" href="<%=songUrl%>"><img width="300px" height="180px" src="<%=request.getContextPath() %>/files/<%=objSong.getSongPicture() %>" alt=""></a>
									<div class="post-body">
										<div class="post-meta">
											<a class="post-category cat-2" href="<%=catUrl%>"><%=objSong.getSongCat().getCatName() %></a>
											<span class="post-date"><%=StringUtil.convertIntoDate(objSong.getSongDateCreate())%></span>
										</div>
										<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName() %></a></h3>
										<span class="post-date">View: <%=objSong.getSongCouter() %></span>
										<p><%=objSong.getSongPreview().substring(0, 60) %>...</p>
									</div>
								</div>
							</div>
							<!-- /post -->
							<%
								}
							%>
							
						</div>
						<%	}} %>
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

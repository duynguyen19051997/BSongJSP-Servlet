<%@page import="model.bean.Song"%>
<%@page import="model.dao.SongDao"%>
<%@page import="util.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
<%@page import="model.dao.CatDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<!-- Nav -->
			<div id="nav">
				<!-- Main Nav -->
				<div id="nav-fixed">
					<div class="container">
						<!-- logo -->
						<div class="nav-logo">
							<a href="<%=request.getContextPath() %>/homepage" class="logo"><img src="<%=request.getContextPath()%>/templates/public/img/bsonglogo.png" alt="BSong"></a>
						</div>
						<!-- /logo -->
						
						<% 
							CatDao catDao = new CatDao();
							ArrayList<Category> listOfCat = catDao.getItems();
							if (listOfCat.size() > 0) {
						%>
						<!-- nav -->
						<ul class="nav-menu nav navbar-nav">
						<%
							for(Category objCat: listOfCat){
								String link = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objCat.getCatName()) + "-" + objCat.getCatId() + "-" + 1;
						%>
							<li class="cat-1"><a href="<%=link%>"><%=objCat.getCatName() %></a></li>
							
						<%
							}
						%>
						</ul>
						<%
							}
						%>
						<!-- /nav -->

						<!-- search & aside toggle -->
						<div class="nav-btns">
							<button class="aside-btn"><i class="fa fa-bars"></i></button>
							<button class="search-btn"><i class="fa fa-search"></i></button>
							<form class="search-form" action="<%=request.getContextPath()%>/searches/1" method="get">
									<input class="search-input" type="text" value="<%if(request.getAttribute("search") != null) out.print(request.getAttribute("search"));%>" name="search" placeholder="Enter Your Search ...">
									<button type="submit" class="search-close"><i class="fa fa-search"></i></button>
									<!-- <button class="search-close"><i class="fa fa-times"></i></button> -->
							</form>
						</div>
						<!-- /search & aside toggle -->
					</div>
				</div>
				<!-- /Main Nav -->

				<!-- Aside Nav -->
				<div id="nav-aside">
					<!-- nav -->
					<div class="section-row">
					<%
						if (listOfCat.size() > 0) {
					%>
						<ul class="nav-aside-menu">
						<%
							for(Category objCat: listOfCat){
								String link = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objCat.getCatName()) + "-" + objCat.getCatId() + "-" + 1;
						%>
							<li><a href="<%=link%>"><%=objCat.getCatName() %></a></li>
						<%	} %>
						</ul>
					<%	} %>
					</div>
					<!-- /nav -->
					<%
						SongDao songDao = new SongDao();
						ArrayList<Song> listLatestSong = songDao.getItemsLatest();
						if (listLatestSong.size() > 0) {
					%>
					<!-- widget posts -->
					<div class="section-row">
						<h3>Latest Songs</h3>
					<%
						for(Song objSong: listLatestSong){
							String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
					%>
						<div class="post post-widget">
							<a class="post-img" href="<%=songUrl%>"><img width="90px" height="70px" src="<%=request.getContextPath()%>/files/<%=objSong.getSongPicture() %>" alt=""></a>
							<div class="post-body">
								<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName()%></a></h3>
							</div>
						</div>
					<%	} %>
					</div>
					<!-- /widget posts -->
					<%	}  %>

					<!-- social links -->
					<div class="section-row">
						<h3>Follow us</h3>
						<ul class="nav-aside-social">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
					</div>
					<!-- /social links -->

					<!-- aside nav close -->
					<button class="nav-aside-close"><i class="fa fa-times"></i></button>
					<!-- /aside nav close -->
				</div>
				<!-- Aside Nav -->
			</div>
			<!-- /Nav -->
<%@page import="model.bean.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<%
			if (request.getAttribute("songDetail") != null) {
				Song objSong = (Song) request.getAttribute("songDetail");
		%>
		<title><%=objSong.getSongName() %></title>
		<%	} else { %>
		<title>Detail</title>
		<%	} %>
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
		
		<!-- jQuery Plugins -->
		<script src="<%=request.getContextPath()%>/templates/public/js/jquery-2.1.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/main.js"></script>
		<script src="<%=request.getContextPath()%>/templates/public/js/jquery.validate.min.js"></script>

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
				<!-- row -->
				<div class="row">
					<!-- Post content -->
					<div class="col-md-8">
					<%
						if (request.getAttribute("songDetail") != null) {
							Song objSong = (Song) request.getAttribute("songDetail");
							String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objSong.getSongCat().getCatName()) + "-" + objSong.getSongCat().getCatId() + "-" + 1;
					%>	
						<div class="section-row sticky-container">
							<div class="main-post">
								<h3><%=objSong.getSongName()%></h3>
								<p>Category: <a href="<%=catUrl%>"><%=objSong.getSongCat().getCatName()%></a></p>
								<p>Date Submitted: <%=StringUtil.convertIntoDate(objSong.getSongDateCreate())%></p>
								<p>View: <%=objSong.getSongCouter()%></p>
								<p><%=objSong.getSongPreview()%></p>
								<figure class="figure-img">
									<img width="670px" height="402px" class="img-responsive" src="<%=request.getContextPath()%>/files/<%=objSong.getSongPicture()%>" alt="">
									<figcaption><%=objSong.getSongName()%></figcaption>
								</figure>
								<p><%=objSong.getSongDetail()%></p>
							</div>
							<div class="post-shares sticky-shares">
								<a href="#" class="share-facebook"><i class="fa fa-facebook"></i></a>
								<a href="#" class="share-twitter"><i class="fa fa-twitter"></i></a>
								<a href="#" class="share-google-plus"><i class="fa fa-google-plus"></i></a>
								<a href="#" class="share-pinterest"><i class="fa fa-pinterest"></i></a>
								<a href="#" class="share-linkedin"><i class="fa fa-linkedin"></i></a>
								<a href="#"><i class="fa fa-envelope"></i></a>
							</div>
						</div>
					<%	}	%>

						<!-- ad -->
						<div class="section-row text-center">
							<a href="#" style="display: inline-block;margin: auto;">
								<img class="img-responsive" src="<%=request.getContextPath()%>/templates/public/img/ad-2.jpg" alt="">
							</a>
						</div>
						<!-- ad -->
						
						<!-- comments -->
						<div id="comment-area" class="section-row">
						<%
							if (request.getAttribute("listsContact") != null) {
								@SuppressWarnings("unchecked")
								ArrayList<Contact> listComment = (ArrayList<Contact>) request.getAttribute("listsContact");
								if (listComment.size() > 0) {
						%>
							<div id="comment-total" class="section-title">
								<h2><%=listComment.size() %> Comments</h2>
							</div>
						<%		} %>
							<div id="comments-post" class="post-comments">
							<%
								for(Contact comment: listComment) {
							%>
								<!-- comment -->
								<div class="media">
									<div class="media-left">
										<img class="media-object" src="<%=request.getContextPath()%>/templates/public/img/avatar.png" alt="">
									</div>
									<div class="media-body">
										<div class="media-heading">
											<h4><%=comment.getName() %> (<%=comment.getEmail() %>)</h4>
											<span class="time"><%=StringUtil.convertIntoDate(comment.getDateCreate()) %></span>
										</div>
										<p><%=comment.getMessage() %></p>
									</div>
								</div>
								<!-- /comment -->
							<%	} %>
							</div>
							
						<%
							}
						%>
						</div>
						<!-- /comments -->

						<!-- reply -->
						<div class="section-row">
							<div class="section-title">
								<h2>Leave a reply</h2>
								<p>your email address will not be published. required fields are marked *</p>
							</div>
							<form id="comment-ajax" class="post-reply" action="" method="post">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<span>Name *</span>
											<input id="name" class="input" type="text" name="name">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<span>Email *</span>
											<input id="email" class="input" type="email" name="email">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<span>Website</span>
											<input id="website" class="input" type="text" name="website">
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<textarea id="message" class="input" name="message" placeholder="Message"></textarea>
										</div>
										<button type="submit" class="primary-button">Submit</button>
									</div>
								</div>
							</form>
						</div>
						<!-- /reply -->
						
						<script type="text/javascript">
							$(document).ready(function(){
				        		$('#comment-ajax').validate({
				        			rules:{
				        				name: {
											required: true
				        				},
						        		email: {
											required: true,
											email: true
				        				},
						        		website: {
											required: true,
											url: true
				        				},
				        				message: {
											required: true,
				        				}
				        			},
				        			messages:{
				        				name: {
											required: 'Insert Name, please!'
				        				},
						        		email: {
											required: 'Insert Email, please!',
											email: 'Email: true!'
				        				},
						        		website: {
											required: 'Insert Website, please!',
											url: 'URL: true!'
				        				},
				        				message: {
											required: 'Insert Message, please!',
				        				}
				        			},
				        			submitHandler: function(form){
			 							var name = $('#name').val();
			 							var email = $('#email').val();
			 							var website = $('#website').val();
			 							var message = $('#message').val();
			 							var id = <%=request.getParameter("id")%>;
			 							$.ajax({
			 								url: '<%=request.getContextPath()%>/comment',
			 								type: 'POST',
			 								cache: false,
			 								data: {
			 									aName: name, aEmail: email, aWebsite: website, aMessage: message, aId: id
			 								},
			 								success: function(data){
			 									$(function(){
			 										$('#comment-area').empty();
			 									});
			 									$('#comment-area').html(data);
			 									$('#name').val("");
					 							$('#email').val("");
					 							$('#website').val("");
					 							$('#message').val("");
			 								},
			 								error: function(){
			 									alert('Có lỗi xảy ra');
			 								},
			 							});
			 							return false;
			 						}
				        		});
				        	});
						</script>
						
					</div>
					<!-- /Post content -->

					<!-- aside -->
					<div class="col-md-4">
						<%@ include file="/templates/public/inc/right-bar.jsp"%>
					</div>
					<!-- /aside -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /section -->

		<!-- Footer -->
		<footer id="footer">
			<%@ include file="/templates/public/inc/footer.jsp"%>
		</footer>
		<!-- /Footer -->

	</body>
</html>

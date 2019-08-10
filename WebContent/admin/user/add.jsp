<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="name-title">
              <div class="alert alert-warning">
			    <strong>Thêm người dùng</strong>
			  </div>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
	                            <%
									String msgText = "";
	                            	String userName = "";
	                            	String fullName = "";
									if(request.getParameter("msg")!=null){
										int msg = Integer.parseInt(request.getParameter("msg"));
										if(msg==0){
											msgText = "Có lỗi trong quá trình thêm, vui lòng kiểm tra lại";
											userName = request.getParameter("username");
											fullName = request.getParameter("fullname");
										}
										if(msg==4){
											msgText = "Username đã tồn tại!";
											userName = request.getParameter("username");
											fullName = request.getParameter("fullname");
										}
									
								%>
									<div class="alert alert-danger">
						    			<p class="success"><strong><%=msgText %></strong><p>
									</div>
								<%} %>
								<%
                               		if(request.getAttribute("userName-error") != null){
                               	%>
                               	<p id="userName-error-by-java"><%=request.getAttribute("userName-error") %></p>
                               	<%	
                               		}
                               	%>
                               	<%
                               		if(request.getAttribute("password-error") != null){
                               	%>
                               	<p id="password-error-by-java"><%=request.getAttribute("password-error") %></p>
                               	<%	
                               		}
                               	%>
                               	<%
                               		if(request.getAttribute("fullName-error") != null){
                               	%>
                               	<p id="fullName-error-by-java"><%=request.getAttribute("fullName-error") %></p>
                               	<%	
                               		}
                               	%>
                                <form method="post" action="" id="form" class="form-class">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" value="<%=userName %>" name="username" class="form-control" />
                                        <label for="passname">Password</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                        <label for="fullname">Fullname</label>
                                        <input type="text" id="fullname" value="<%=fullName %>" name="fullname" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
                                <script type="text/javascript">
	                                $(document).ready(function(){
	                                	$('.form-class').validate({
	                        				rules:{
	                        					username:{
	                        						required:true
	                        					},
	                                			password:{
		                    						required:true
		                    					},
	                                			fullname:{
		                    						required:true
		                    					}
	                        				},
	                        				messages:{
	                        					username:{
	                        						required:'Vui lòng không để trống!'
	                        					},
	                                			password:{
		                    						required:'Vui lòng không để trống!'
		                    					},
	                                			fullname:{
		                    						required:'Vui lòng không để trống!'
		                    					}
	                        				},
	                        			});
	                        		});
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
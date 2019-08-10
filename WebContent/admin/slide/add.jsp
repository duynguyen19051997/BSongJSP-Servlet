<%@page import="model.dao.CatDao"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
           <div class="name-title">
              <div class="alert alert-warning">
			    <strong>Thêm Slide</strong>
			  </div>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
               		 <%
						String msgText = "";
						if(request.getParameter("msg")!=null){
							int msg = Integer.parseInt(request.getParameter("msg"));
							if(msg==0){
								msgText = "Có lỗi trong quá trình thêm, vui lòng kiểm tra lại !";
							}
					%>
					<div class="alert alert-danger">
					    <p class="success"><strong><%=msgText %></strong><p>
					</div>
					<%} %>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                            	<%
                               		if(request.getAttribute("error") != null){
                               	%>
                               	<p id="error-by-java"><%=request.getAttribute("error") %></p>
                               	<%	
                               		}
                               	%>
                                <form role="form" method="post" enctype="multipart/form-data" class="form" id="form" action="">
                                    <div class="form-group">
                                        <label for="number">Sort</label>
                                        <input type="number" id="number" value="" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="pictureslide" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
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
document.getElementById("slide").classList.add('active-menu');
$(document).ready(function(){
	$('#form').validate({
		rules:{
			name:{
				required:true
			},
			pictureslide:{
				required: true
			}
		},
		messages:{
			name:{
				required: 'Nhập càng cao thì vị trí càng cao!'
			},
			pictureslide:{
				required: 'Vui lòng chọn ảnh!'
			}
		},
	});
});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
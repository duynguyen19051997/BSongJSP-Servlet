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
			    <strong>Thêm Bài hát</strong>
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
                               		if(request.getAttribute("songName-error") != null){
                               	%>
                               	<p id="songName-error-by-java"><%=request.getAttribute("songName-error") %></p>
                               	<%	
                               		}
                               	%>
                               	 <%
                               		if(request.getAttribute("cat-error") != null){
                               	%>
                               	<p id="songCatName-error-by-java"><%=request.getAttribute("cat-error") %></p>
                               	<%	
                               		}
                               	%>
                               	<%
                               		if(request.getAttribute("preview-error") != null){
                               	%>
                               	<p id="songPreview-error-by-java"><%=request.getAttribute("preview-error") %></p>
                               	<%	
                               		}
                               	%>
                               	 <%
                               		if(request.getAttribute("detai-error") != null){
                               	%>
                               	<p id="songDetail-error-by-java"><%=request.getAttribute("detai-error") %></p>
                               	<%	
                               		}
                               	%>
                                <form role="form" method="post" enctype="multipart/form-data" class="form" id="form" action="">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục bài hát</label>
                                        <select id="category" name="category" class="form-control" required title="Vui lòng chọn Danh mục bài hát!">
	                                        <%
	                                        	CatDao catDao = new CatDao();
	                                        	ArrayList<Category> listCat = catDao.getItems();
	                                        	if(listCat.size() > 0){
	                                        		for(Category cat : listCat){
	                                        %>
	                                        <option value="<%=cat.getCatId()%>"><%=cat.getCatName() %></option>
	                                        <%
	                                        	}}
	                                        %>
	                                        <option value="" selected="selected">--Chọn danh mục bài hát--</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                    </div>
                                    <div class="form-group">
                                        <label for="preview">Mô tả</label>
                                        <textarea class="ckeditor" id="preview" class="form-control" rows="3" name="preview"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="ckeditor" id="detail" class="form-control" id="detail" rows="5" name="detail"></textarea>
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
    document.getElementById("song").classList.add('active-menu');
    $(document).ready(function(){
    	$('#form').validate({
    		ignore: [],
			rules:{
				name:{
					required:true
				},
				preview:{
					required: true
				},
				detail:{
					required: true
				}
			},
			messages:{
				name:{
					required:'Vui lòng không để trống!'
				},
				preview:{
					required:'Vui lòng không để trống!'
				},
				detail:{
					required:'Vui lòng không để trống!'
				}
			},
		});
	});
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
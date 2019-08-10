<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="name-title">
              <div class="alert alert-warning">
			    <strong>Sửa Danh mục</strong>
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
									if(request.getParameter("msg")!=null){
										int msg = Integer.parseInt(request.getParameter("msg"));
										if(msg==0){
											msgText = "Có lỗi trong quá trình thêm, vui lòng kiểm tra lại";
										}
									
								%>
									<div class="alert alert-danger">
						    			<p class="success"><strong><%=msgText %></strong><p>
									</div>
								<%} %>
								<%
                               		if(request.getAttribute("name-error") != null){
                               	%>
                               	<p id="name-error-by-java"><%=request.getAttribute("name-error") %></p>
                               	<%	
                               		}
                               	%>
                               	<%
                               		String catName = "";
                               		int sort = 500;
                               		if(request.getAttribute("objCat") != null){
                               			Category objCat = (Category)request.getAttribute("objCat");
                               			catName = objCat.getCatName();
                               			sort = objCat.getSort();
                               		}
                               	%>
                                <form role="form" method="post" enctype="multipart/form-data" class="form-class" id="form" action="">
                                    <div class="form-group">
                                        <label for="name">Tên Danh Mục</label>
                                        <input type="text" id="name" value="<%=catName %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="name">Sort</label>
                                        <input type="number" id="number" value="<%=sort %>" name="number" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <script type="text/javascript">
                                $(document).ready(function(){
                                	$('.form-class').validate({
                        				rules:{
                        					name:{
                        						required:true
                        					},
                        					number:{
                        						required:true
                        					}
                        				},
                        				messages:{
                        					name:{
                        						required:'Vui lòng không để trống!'
                        					},
                        					number:{
                        						required: 'Nhập càng cao thì vị trí càng cao!'
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
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
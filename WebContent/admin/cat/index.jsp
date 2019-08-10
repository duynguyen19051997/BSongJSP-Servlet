<%@page import="util.StringUtil"%>
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
              <div class="alert alert-info">
			    <strong>Quản lý Danh mục</strong>
			  </div>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" class="form-control input-sm" placeholder="Nhập tên bài hát" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>
							<%
								if (request.getParameter("msg") != null) {
									int msg = Integer.valueOf(request.getParameter("msg"));
									String msgText = StringUtil.returnMessage(request, msg);
									if(msg == 0){
							%>
								<div class="alert alert-danger">
								    <p class="success"><strong><%=msgText %></strong><p>
								</div>
							<%} else {%>
								<div class="alert alert-success">
								  	<p class="success"><strong><%=msgText %></strong><p>
								</div>
							<%}} %>
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th style="text-align: center">ID</th>
                                        <th style="text-align: center">Tên Danh mục</th>
                                        <th style="text-align: center">Vị trí hiển thị</th>
                                        <th width="160px" style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		if(request.getAttribute("listCat") != null){
                                			@SuppressWarnings("unchecked")
                                			ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("listCat");
                                			if(listCat.size() > 0){
                                				for(Category cat: listCat){
                                					String urlEdit = request.getContextPath() + "/admin/cat/edit?id=" + cat.getCatId();
                                					String urlDelete = request.getContextPath() + "/admin/cat/delete?id=" + cat.getCatId();
                                	%>
                                	<tr>
                                        <td class="center" style="text-align: center"><%=cat.getCatId()%></td>
                                        <td class="center" style="text-align: center"><%=cat.getCatName() %></td>
                                        <td class="center" style="text-align: center"><%=cat.getSort() %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a onclick = "return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=urlDelete %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%	}}} %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("category").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
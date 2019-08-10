<%@page import="model.bean.Slide"%>
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
			    <strong>Quản lý slide</strong>
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
                                    <a href="<%=request.getContextPath()%>/admin/slide/add" class="btn btn-success btn-md">Thêm</a>
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
                                        <th style="text-align: center">Tên Slide</th>
                                        <th style="text-align: center">Vị trí hiển thị</th>
                                        <th width="160px" style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		if(request.getAttribute("listSlide") != null){
                                			@SuppressWarnings("unchecked")
                                			ArrayList<Slide> listSlide = (ArrayList<Slide>)request.getAttribute("listSlide");
                                			if(listSlide.size() > 0){
                                				for(Slide slide: listSlide){
                                					String urlEdit = request.getContextPath() + "/admin/slide/edit?id=" + slide.getId();
                                					String urlDelete = request.getContextPath() + "/admin/slide/delete?id=" + slide.getId();
                                	%>
                                	<tr>
                                        <td style="text-align: center"><%=slide.getId()%></td>
                                        <td class="center" style="text-align: center">
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=slide.getImage()%>" alt="<%=slide.getImage()%>"/>
                                        </td>
                                        <td class="center" style="text-align: center"><%=slide.getSort() %></td>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a onclick = "return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=urlDelete %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%	}}else{ %>
                                	<tr>
                                		<td colspan="4" style="text-align: center">Không có dữ liệu</td>
                                	</tr>
                                	<%	}} %>
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
<script type="text/javascript">
document.getElementById("slide").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
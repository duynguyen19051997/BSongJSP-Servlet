<%@page import="util.DefineUtil"%>
<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
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
			    <strong>Quản lý Bài hát</strong>
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
                                    <a href="<%=request.getContextPath() %>/admin/song/add" class="btn btn-success btn-md">Thêm</a>
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
	                            int numberOfPages = (Integer)request.getAttribute("numberOfPages");
	                        	int currentPages = (Integer)request.getAttribute("currentPages");
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
                                        <th style="text-align: center">Tên bài hát</th>
                                        <th style="text-align: center">Danh mục</th>
                                        <th style="text-align: center">Lượt đọc</th>
                                        <th style="text-align: center">Hình ảnh</th>
                                        <th width="160px" style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		if(request.getAttribute("listSong") != null){
                                			@SuppressWarnings("unchecked")
                                			ArrayList<Song> listSong = (ArrayList<Song>)request.getAttribute("listSong");
                                			if(listSong.size() > 0){
                                				for(Song song : listSong){
                                					String urlEdit = request.getContextPath() + "/admin/song/edit?page=" + currentPages + "&id=" + song.getSongId();
                                					String urlDelete = request.getContextPath() + "/admin/song/delete?id=" + song.getSongId();
                                	%>
                                	<tr>
                                        <td class="center" style="text-align: center"><%=song.getSongId() %></td>
                                        <td class="center" style="text-align: center"><%=song.getSongName() %></td>
                                        <td class="center" style="text-align: center"><%=song.getSongCat().getCatName() %></td>
                                        <td class="center" style="text-align: center"><%=song.getSongCouter() %></td>
                                        <%
                                        	if(!song.getSongPicture().isEmpty()){
                                        %>
                                        <td class="center">
											<img width="200px" height="200px" src="<%=request.getContextPath() %>/files/<%=song.getSongPicture() %>" alt="<%=song.getSongPicture() %>"/>
                                        </td>
                                        <%}else{ %>
                                        <td class="center">
                                        	<img width="200px" height="200px" src="<%=request.getContextPath() %>/templates/public/images/images.png" width="177" height="213" alt="Không có hình ảnh"/>
                                        </td>
                                        <%} %>
                                        <td class="center">
                                            <a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a onclick = "return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=urlDelete %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%	}}} %>
                                </tbody>
                            </table>
                            
                            <%
                            	
                            	if(numberOfPages > 1){
	                            	if(request.getAttribute("listSong") != null){
	                            		@SuppressWarnings("unchecked")
	                            		ArrayList<Song> listSong = (ArrayList<Song>)request.getAttribute("listSong");
	                            		if(listSong.size() > 0){
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị <%=listSong.size() %> của <%=request.getAttribute("numberOfItems") %> bài hát </div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                            <li class="paginate_button previous <%if(currentPages == 1) out.print("disabled"); %>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%if(currentPages - 1 > 1) out.print(1); else out.print(currentPages - 1);%>">&laquo;</a></li>
                                            <%
                                            	String active = "";
                                            	for(int i = 1; i <= numberOfPages; i++){
                                            		if(currentPages == i){
                                            			active = "active";
                                            		}else{
                                            			active = "";
                                            		}
                                            %>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%=i%>"><%=i%></a></li>
											<%	} %>
                                            <li class="paginate_button next <%if(currentPages == numberOfPages) out.print("disabled"); %>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/song/index?page=<%if(currentPages + 1 > numberOfPages) out.print(numberOfPages); else out.print(currentPages + 1);%>">&raquo;</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <%	}}} %>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
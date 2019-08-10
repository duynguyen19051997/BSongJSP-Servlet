<%@page import="util.StringUtil"%>
<%@page import="model.bean.User"%>
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
			    <strong>Quản lý người dùng</strong>
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
                                	<%
                                        	User userLogin = (User) session.getAttribute("userInfor");
                                        	if("anhduy".equals(userLogin.getUserName())){
                                     %>
                                    <a href="<%=request.getContextPath() %>/admin/user/add" class="btn btn-success btn-md">Thêm</a>
                                	<%	} %>
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
									if(msg == 0 || msg == 4 || msg == 5 || msg == 6){
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
                                        <th style="text-align: center">Username</th>
                                        <th style="text-align: center">Fullname</th>
                                        <th width="160px" style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		int numberOfItem = (int) request.getAttribute("numberOfItem");
	                                	int numberOfPage = (int) request.getAttribute("numberOfPage");
	                                	int currentPage = (int) request.getAttribute("currentPage");
                                		if(request.getAttribute("listUser") != null){
                           					@SuppressWarnings("unchecked")
                                			ArrayList<User> listCt = (ArrayList<User>)request.getAttribute("listUser");
                                			if(listCt.size() > 0){
                                				for(User user: listCt){
                                					String urlDelete = request.getContextPath() + "/admin/user/delete?id=" + user.getId();
                                					String urlEdit = request.getContextPath() + "/admin/user/edit?page=" + currentPage + "&id=" + user.getId();
                                	%>
                                	<tr>
                                        <td class="center" style="text-align: center"><%=user.getId() %></td>
                                        <td class="center" style="text-align: center"><%=user.getUserName() %></td>
                                        <td class="center" style="text-align: center"><%=user.getFullName()%></td>
                                        <%
                                        	if("anhduy".equals(userLogin.getUserName())){
                                        %>
                                        <td class="center">
                                        	<a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <%
                                            	if(user.getId() != 12){
                                            %>
                                            <a onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=urlDelete %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        	<%	} %>
                                        </td>
                                        <%	}else{ %>
                                        <td class="center">
                                        	<%
                                        		if(user.getId() == userLogin.getId()){
                                        	%>
                                        	<a href="<%=urlEdit %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                        </td>
                                        <%	}} %>
                                    </tr>
                                	<%	}}}else{ %>
                                	<tr>
                                		 <td colspan="5" align="center">Chưa có người dùng nào!</td>
                                	</tr>
                                    <%	} %>
                                </tbody>
                            </table>
                            
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị trang <%=currentPage%> của <%=numberOfPage%></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<li class="paginate_button previous <%if(currentPage == 1) out.print("disabled");%>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%if(currentPage - 1 >= 1) out.print(currentPage - 1); else out.print(1);%>">&laquo;</a></li>
                                        	<%
	                                        	String active = "";
	                                    		for(int i = 1; i <= numberOfPage; i++){
	                                    			if(i == currentPage){
	                                    				active = "active";
	                                    			}else{
	                                    				active = "";
	                                    			}
                                        	%>
                                            <li class="paginate_button <%=active %>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%=i%>"><%=i %></a></li>
                                        	<%} %>
                                        	<li class="paginate_button next <%if(currentPage == numberOfPage) out.print("disabled");%>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/user/index?page=<%if(currentPage + 1 <= numberOfPage) out.print(currentPage + 1); else out.print(numberOfPage);%>">&raquo;</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("user").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
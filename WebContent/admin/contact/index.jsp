<%@page import="util.StringUtil"%>
<%@page import="model.bean.Contact"%>
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
			    <strong>Quản lý liên hệ</strong>
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
                                        <th style="text-align: center">Tên</th>
                                        <th style="text-align: center">Email</th>
                                        <th style="text-align: center">Website</th>
                                        <th style="text-align: center">Message</th>
                                        <th style="text-align: center">Song Name</th>
                                        <th style="text-align: center">Date Create</th>
                                        <th width="100px" style="text-align: center">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<%
                                		if(request.getAttribute("listCt") != null){
                                					@SuppressWarnings("unchecked")
       	                                			ArrayList<Contact> listCt = (ArrayList<Contact>)request.getAttribute("listCt");
       	                                			if(listCt.size() > 0){
       	                                				for(Contact contact: listCt){
       	                                					String urlDelete = request.getContextPath() + "/admin/contact/delete?id=" + contact.getId();
                                	%>
                                	<tr>
                                        <td class="center" style="text-align: center"><%=contact.getId() %></td>
                                        <td class="center"><%=contact.getName() %></td>
                                        <td class="center"><%=contact.getEmail()%></td>
                                        <td class="center"><%=contact.getWebsite() %></td>
                                        <td class="center"><%=contact.getMessage() %></td>
                                        <td class="center"><%=contact.getSongName() %></td>
                                        <td class="center"><%=StringUtil.convertIntoDate(contact.getDateCreate()) %></td>
                                        <td class="center">
                                            <a onclick="return confirm('Bạn có chắc chắn muốn xóa không?')" href="<%=urlDelete %>" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                	<%	}}}else{ %>
                                	<tr>
                                		 <td colspan="6" align="center">Chưa có liên hệ nào!</td>
                                	</tr>
                                    <%	} %>
                                </tbody>
                            </table>
                            <%
                            	int numberOfItem = (int)request.getAttribute("numberOfItem");
                            	int numberOfPage = (int)request.getAttribute("numberOfPage");
                            	int currentPage = (int)request.getAttribute("currentPage");
                            %>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Trang <%=currentPage%> của <%=numberOfPage%></div>
                                </div>
                                <div class="col-sm-6" style="text-align: right">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<li class="paginate_button previous <%if(currentPage == 1) out.print("disabled");%>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%if(currentPage - 1 >= 1) out.print(currentPage - 1); else out.print(1);%>">&laquo;</a></li>
                                        	<%
                                        		String active = "";
                                        		for(int i = 1; i <= numberOfPage; i++){
                                        			if(i == currentPage){
                                        				active = "active";
                                        			}else{
                                        				active = "";
                                        			}
                                        	%>
                                            <li class="paginate_button <%=active%>" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%=i%>"><%=i%></a></li>
                                            <%	} %>
                                            <li class="paginate_button next <%if(currentPage == numberOfPage) out.print("disabled");%>" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/contact/index?page=<%if(currentPage + 1 <= numberOfPage) out.print(currentPage + 1); else out.print(numberOfPage);%>">&raquo;</a></li>
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
    document.getElementById("contact").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
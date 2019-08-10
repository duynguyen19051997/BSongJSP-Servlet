<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
            	<div class="alert alert-danger">
				    <p class="success"><strong>TRANG QUẢN TRỊ VIÊN</strong><p>
				</div>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/cat/index" title="">Quản lý danh mục</a></p>
                        <%
                        	if(request.getAttribute("countCat") != null){
                        %>
                        <p class="text-muted">Có <%=request.getAttribute("countCat") %> danh mục</p>
                        <%	}else{ %>
                        <p class="text-muted">Không có dữ liệu</p>
                        <%	} %>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bell-o"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/song/index?page=<%=1%>" title="">Quản lý bài hát</a></p>
                        <%
                        	if(request.getAttribute("countSong") != null){
                        %>
                        <p class="text-muted">Có <%=request.getAttribute("countSong") %> bài hát</p>
                        <%	}else{ %>
                        <p class="text-muted">Không có dữ liệu</p>
                        <%	} %>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/user/index" title="">Quản lý người dùng</a></p>
                        <%
                        	if(request.getAttribute("countUser") != null){
                        %>
                        <p class="text-muted">Có <%=request.getAttribute("countUser") %> người dùng</p>
                        <%	}else{ %>
                        <p class="text-muted">Không có dữ liệu</p>
                        <%	} %>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-red set-icon">
                    <i class="fa fa-rocket"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/admin/contact/index" title="">Quản lý liên hệ</a></p>
                        <%
                        	if(request.getAttribute("countContact") != null){
                        %>
                        <p class="text-muted">Có <%=request.getAttribute("countContact") %> liên hệ</p>
                        <%	}else{ %>
                        <p class="text-muted">Không có dữ liệu</p>
                        <%	} %>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath() %>/homepage" title="">Trang chủ Public</a></p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<script type="text/javascript">
document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>
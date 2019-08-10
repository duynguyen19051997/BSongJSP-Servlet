<%@page import="util.StringUtil"%>
<%@page import="model.bean.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
						<%
							if (request.getAttribute("listsContact") != null) {
								@SuppressWarnings("unchecked")
								ArrayList<Contact> listComment = (ArrayList<Contact>) request.getAttribute("listsContact");
								if (listComment.size() > 0) {
						%>
							<div id="comment-total" class="section-title">
								<h2><%=listComment.size() %> Comments</h2>
							</div>
						<%		} %>
							<div id="comments-post" class="post-comments">
							<%
								for(Contact comment: listComment) {
							%>
								<!-- comment -->
								<div class="media">
									<div class="media-left">
										<img class="media-object" src="<%=request.getContextPath()%>/templates/public/img/avatar.png" alt="">
									</div>
									<div class="media-body">
										<div class="media-heading">
											<h4><%=comment.getName() %> (<%=comment.getEmail() %>)</h4>
											<span class="time"><%=StringUtil.convertIntoDate(comment.getDateCreate()) %></span>
										</div>
										<p><%=comment.getMessage() %></p>
									</div>
								</div>
								<!-- /comment -->
							<%	} %>
							</div>
							
						<%
							}
						%>
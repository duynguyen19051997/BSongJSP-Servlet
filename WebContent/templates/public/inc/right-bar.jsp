<%@page import="java.util.Random"%>
<%@page import="java.util.Comparator"%>
<%@page import="model.bean.Category"%>
<%@page import="model.dao.CatDao"%>
<%@page import="util.StringUtil"%>
<%@page import="model.bean.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.SongDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
						<!-- post widget -->
						<%
							CatDao catDaoRightBar = new CatDao();
							SongDao songDaoRightBar = new SongDao();
							ArrayList<Category> listCatRightBar = (ArrayList<Category>)catDaoRightBar.getItems();
							for(Category objCat: listCatRightBar){
								objCat.setTotalOfSong(songDaoRightBar.getTotalItemsById(objCat.getCatId()));
							}
							listCatRightBar.sort(new Comparator<Category>() {
								@Override
								public int compare(Category o1, Category o2) {
									if(o1.getTotalOfSong() == o2.getTotalOfSong()){
							            return 0;
							        }
							        return o1.getTotalOfSong() > o2.getTotalOfSong() ? -1 : 1;
								}
								
							});
						%>
						
						<!-- catagories -->
						<div class="aside-widget">
							<div class="section-title">
								<h2>Catagories</h2>
							</div>
							<div class="category-widget">
								<ul>
								<%
									Random random = new Random();
									for(Category objCat: listCatRightBar) {
										String catUrl = request.getContextPath() + "/categories/" + StringUtil.makeSlug(objCat.getCatName()) + "-" + objCat.getCatId() + "-" + 1;
										int i = random.nextInt(3) + 1;
								%>
									<li><a href="<%=catUrl %>" class="cat-<%=i%>"><%=objCat.getCatName() %><span><%=objCat.getTotalOfSong()%></span></a></li>
									
								<%	} %>
								</ul>
							</div>
						</div>
						<!-- /catagories -->
						
						<%
							ArrayList<Song> listMostPopSong = (ArrayList<Song>)songDaoRightBar.getItemsPop();
							if (listMostPopSong.size() > 0) {
						%>
						
						<div class="aside-widget">
							<div class="section-title">
								<h2>Most Popular</h2>
							</div>
							
						<%
							for(Song objSong: listMostPopSong){
								String songUrl = request.getContextPath() + "/details/" + StringUtil.makeSlug(objSong.getSongName()) + "-" + objSong.getSongId();
						%>
							
							<div class="post post-widget">
								<a class="post-img" href="<%=songUrl%>"><img width="90px" height="70px" src="<%=request.getContextPath()%>/files/<%=objSong.getSongPicture()%>" alt=""></a>
								<div class="post-body">
									<h3 class="post-title"><a href="<%=songUrl%>"><%=objSong.getSongName()%></a></h3>
									<p class="post-date">View: <%=objSong.getSongCouter() %></p>
								</div>
							</div>
						<%
							}
						%>
							
						</div>
						<!-- /post widget -->
					<%
							}
					%>
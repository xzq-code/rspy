<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<link rel="stylesheet" href="${basePath}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${basePath}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${basePath}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tree-closed {
	    height : 40px;
	}
	.tree-expanded {
	    height : auto;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="./main.jsp">海报管理</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>小花<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="login.jsp"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="查询">
          </form>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 
						<span class="badge" style="float:right">1</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="./user.jsp" style="color:red;"><i class="glyphicon glyphicon-user"></i>海报管理</a> 
							</li>
						</ul>
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">活动海报</h1>
		<form class="form-inline" role="form">
		  <div class="form-group has-feedback">
		    <div class="input-group">
		      <div class="input-group-addon">查询条件</div>
		      <!-- <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件"> -->
		      <select class="form-control has-success">
		      	<option>餐饮</option>
		      	<option>旅游</option>
		      	<option>零售</option>
		      </select>
		    </div>
		  </div>
		  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
		</form>
          <div class="row placeholders">
          	<!-- 循环打印 -->
		    <c:forEach items="${imgsList}" var="img" varStatus="st">
				<div class="col-xs-6 col-sm-3 placeholder">
	              <img alt="" src="/upload/${img}">
	            </div>
		    </c:forEach>   
          </div>
          <!--分页-->
          <div>
          	<table class="table  table-bordered">
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							<li><a href='AllImgServlet?start=${pre}'>上一页</a></li>
							<c:forEach var="i" begin="1" end="6">
								<c:url var="user" value="jspexam/UserListServlet">
							        <c:param name="start" value="${(i-1)*5}"/>
							    </c:url>
								<li  class=''><a href='/<c:out value="${user}"/>'>${i}</a></li>
							</c:forEach>
							<li><a href='UserListServlet?start=${next}'>下一页</a></li>
						</ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
          </div>
          
        </div>
      </div>
    </div>
    <script src="${basePath}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/holder/2.9.4/holder.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
                    // jquery对象的回调方法中的this关键字为DOM对象
                    // $(DOM) ==> JQuery
				    if ( $(this).find("ul") ) { // 3 li
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
        </script>
  </body>
</html>

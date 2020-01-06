<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 13437
  Date: 2019/12/23
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-1.8.3.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        if (${msg>=1}){
            alert("添加成功");
            location.href="${pageContext.request.contextPath}/con/show";
        }
        if (${msg1>=1}){
            alert("删除成功");
            location.href="${pageContext.request.contextPath}/con/show";
        }
        if (${msg2>=1}){
            alert("修改成功");
            location.href="${pageContext.request.contextPath}/con/show";
        }

    })
</script>
<body>

<form action="/con/show" method="post">
    姓名：<input type="text" name="name" value="${page.name}">
    生日：<input type="date" name="startBirth" value="<fmt:formatDate value='${page.startBirth}' pattern='yyyy-MM-dd'></fmt:formatDate>">-<input type="date" name="endBirth" value="<fmt:formatDate value='${page.endBirth}' pattern='yyyy-MM-dd'></fmt:formatDate>">
    <input type="hidden" name="currPage" value="1">
    部门：<select name="detepId">
          <option value="0">全部</option>
           <c:forEach var="detep" items="${deteps}">
               <option value="${detep.id}"<c:if test="${detep.id==page.detepId}">selected="selected"</c:if>>${detep.detepid}</option>
           </c:forEach>
</select>
    <input type="submit" value="收索">
</form>
<table cellpadding="10" cellspacing="0" border="1">
    <tr>
        <td>全选：<input type="checkbox" id="checkAll" onclick="CheckeAll()"></td>
        <td>编号</td>
        <td>部门编号</td>
        <td>工资</td>
        <td>生日</td>
        <td>姓名</td>
        <td>照片</td>
        <td>性别</td>
        <td><input type="button" value="添加" onclick="location.href='${pageContext.request.contextPath}/con/add1'" >&nbsp;<input type="button" id="bacthDel" value="批量删除" onclick="bacthDels()"></td>
    </tr>
        <c:forEach var="list1" items="${list}">
            <tr>
                <td><input type="checkbox" class="checkBox" value="${list1.id}"></td>
                <td>${list1.id}</td>
                <td>${list1.detep}</td>
                <td>${list1.salary}</td>
                <td><fmt:formatDate value='${list1.birth}' pattern='yyyy-MM-dd hh:mm:ss'></fmt:formatDate></td>
                <td>${list1.name}</td>
                <td><a href="/con/downImg?urlImg=${list1.imgurl}"><img src="${list1.imgurl}" alt="图片显示"></a></td>
                <td>${list1.sex}</td>
                <td><a href="javascript:location.href='${pageContext.request.contextPath}/con/del?id=${list1.id}'">删除</a>&nbsp;<a href="javascript:location.href='${pageContext.request.contextPath}/con/update1?id=${list1.id}'">修改</a></td>
            </tr>

    </c:forEach>
</table>
   <table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <td><a href="javascript:goPage(1)">首页</a></td>
        <td><a href="javascript:goPage(${pageInfo.pageNum-1})">上一页</a></td>
        <c:forEach  var="i" begin="1" end="${pageInfo.pages}">
            <td><a href="javascript:goPage(${i})">${i}</a></td>
        </c:forEach>
        <td><a href="javascript:goPage(${pageInfo.pageNum+1})">下一页</a></td>
        <td><a href="javascript:goPage(${pageInfo.pages})">尾页</a></td>
    </tr>
  </table>
</body>
<script type="text/javascript">
    function goPage(v) {
       if (v<1) {
           v=1;
       }
       if (v>${pageInfo.pages}){
           v=${pageInfo.pages};
       }
       $("form input:hidden").val(v);
       $("form").submit();
    }
    function CheckeAll() {
       var ischecked = $("#checkAll").prop("checked");
       if (ischecked){
           $(".checkBox").prop("checked",true);
       }else {
           $(".checkBox").prop("checked",false);
       }
    }
    function bacthDels() {
        var ids = new Array();
       $(".checkBox:checked").each(function () {
           ids.push($(this).val());
       });
       $.post("/con/batchDel",{"ids":ids.toString()},function (data) {
             alert("删除了"+data+"条数据");
             location.href="/con/show";
       },"text");
    }
</script>
</html>

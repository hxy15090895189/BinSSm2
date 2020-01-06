<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: 13437
  Date: 2019/12/22
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-1.8.3.min.js"></script>
    <script src="/js/jquery.form.js"></script>
</head>
<body>
<form action="/con/update2" method="post" id="form">
    <input type="hidden" name="imgurl" value="" id="hd">
    <input type="hidden" value="${user.id}" name="id">
    姓名：<input type="text" name="name" value="${user.name}"><br>
    性别：<input type="text" name="sex" value="${user.sex}"><br>
    生日：<input type="date" name="birth" value="<fmt:formatDate value='${user.birth}' pattern='yyyy-MM-dd'></fmt:formatDate>"><br>
    <img src="${user.imgurl}" alt="图片回显" id="img1"><br>
    上传修改的图片：<input type="file" name="file" onchange="upload1()" ><br>
    工资：<input type="text" name="salary" value="${user.salary}"><br>
    部门：<select name="detep">
    <option value="0">请选择</option>
    <c:forEach var="deteps" items="${deteps}">
        <option value="${deteps.id}" <c:if test="${user.detep==deteps.id}">selected="selected"</c:if>>${deteps.detepid}</option>
    </c:forEach>
</select><br>
    <input type="submit" value="添加">
    <input type="reset" value="重置">
</form>
</body>
<script>
    function upload1() {
        var img={
            url:"/con/upload",
            dataType:"text",
            success:function (imgUrl) {
                $("#img1").attr("src",imgUrl);
                $("#hd").val(imgUrl);
            }
        };
        $("#form").ajaxSubmit(img);
    }
</script>
</html>

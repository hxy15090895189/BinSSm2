<%--
  Created by IntelliJ IDEA.
  User: 13437
  Date: 2019/12/22
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-1.8.3.min.js"></script>
    <script src="/js/jquery.form.js"></script>
</head>
<body>
<form action="/con/add2" method="post" id="form">
    姓名：<input type="text" name="name"><br>
    性别：<input type="text" name="sex"><br>
    生日：<input type="date" name="birth"><br>
    工资：<input type="text" name="salary"><br>
    <input type="hidden" name="imgurl" value="" id="hd">
    部门：<select name="detep">
     <option value="0">请选择</option>
        <c:forEach var="deteps" items="${deteps}">
            <option value="${deteps.id}">${deteps.detepid}</option>
        </c:forEach>
            </select><br>
    照片上传：<input type="file" onchange="upload1()" name="file"><br>
    <img src="" alt="即使回显" width="100px" height="100px" id="img1"/><br>
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

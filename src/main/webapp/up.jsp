<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<!-- enctype 默认是 application/x-www-form-urlencoded -->
<form action="FileUpLoad" enctype="multipart/form-data" method="post" >

    普通表单：<input type="text" name="usename"> <br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2： <input type="file" name="file2"><br/>
    <input type="submit" value="提交"/>

</form>
</body>
</html>

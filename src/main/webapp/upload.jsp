<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传文件</title>
</head>
<body>
<form action="<%=basePath%>upload.do" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="tuzi" value="tuzi">
    上传文件：<input type="file" name="uploadfile">
    <input type="submit" value="上传">
</form>


<a href="<%=basePath%>download?fileName=1524304153223.csv">下载</a>

<%--播放--%>
<script type="text/javascript">
    function getVideo(id) {
        window.location.href = "${pageContext.request.contextPath }/Test/getVideo.do?id=" + id;
    }
</script>
<button id="btn" onclick="getVideo('ea48576f30be1669971699c09ad05c94');">播放</button>
<audio id="mp3" src="${path}" autoplay="true" controls="true">
</audio>


</body>
</html>
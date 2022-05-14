<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="http://127.0.0.1:8080/fileManager/upload" method="post" enctype="multipart/form-data">
        选择文件/图片:
        <input type="file" name="file1"><br/>
        <input type="file" name="file2"><br/>
        <input type="file" name="file3"><br/>
        <input type="submit" value="点击上传">

    </form>

</body>
</html>

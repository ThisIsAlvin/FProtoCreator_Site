<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#btn_add").click(function(){
                $.ajax("/user/add?json",{
                    type:"post",
                    data:$('#add').serialize(),
                    success:function(data){
                         window.location.href="/user/list";
                    },
                })
            });
            $("#btn_back").click(function(){
                window.location.href="/user/list";
            });
        })
    </script>
</head>
<body>
<h2>add</h2>
<%--<form id="add" action="/user/add?json" method="post">--%>
<form id="add">
   名字: <input type="text" name="name"/><br>
   性别: <input type="text" name="sex"/><br>
   住址: <input type="text" name="home"/><br>
   电话: <input type="text" name="tel"/><br>
   邮箱: <input type="text" name="email"/><br>
    <input id="btn_add" type="button" value="提交"/>
    <input id="btn_back" type="button" value="返回"/>
</form>
</body>
</html>

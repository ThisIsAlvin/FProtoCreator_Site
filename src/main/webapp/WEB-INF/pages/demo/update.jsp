<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#name").val("${user.name}");
            $("#sex").val("${user.sex}");
            $("#home").val("${user.home}");
            $("#tel").val("${user.tel}");
            $("#email").val("${user.email}");
            $("#oldname").val("${user.name}");

            $("#btn_update").click(function(){
                $.ajax("/user/update?json",{
                    type:"post",
                    data:$('#update').serialize(),
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
<h2>update</h2>
<%--<form id="add" action="/user/add?json" method="post">--%>
<form id="update">
    名字: <input type="text" name="name" id="name"/><br>
    性别: <input type="text" name="sex" id="sex"/><br>
    住址: <input type="text" name="home" id="home"/><br>
    电话: <input type="text" name="tel" id="tel"/><br>
    邮箱: <input type="text" name="email" id="email"/><br>
    <input type="hidden" name="oldName" id="oldname"/><br>
    <input id="btn_update" type="button" value="提交"/>
    <input id="btn_back" type="button" value="返回"/>
</form>
</body>
</html>

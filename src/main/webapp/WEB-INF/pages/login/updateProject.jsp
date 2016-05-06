<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>更改项目</title>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#id").val("${project_info.id}");
            $("#name").val("${project_info.name}");
            $("#version").val("${project_info.version}");

            $("#btn_update").click(function(){
                $.ajax("/login/project/update?json",{
                    type:"post",
                    data:$('#update').serialize(),
                    success:function(data){
                        if (data.result == "error") {
                            alert("错误：" + data.message);
                        }
                        window.location.href="/login/project/list";
                    },
                })
            });
            $("#btn_back").click(function(){
                window.location.href="/login/project/list";
            });
        })
    </script>
</head>
<body>
<h2>更改项目</h2>
    <form id="update">
        <input type="hidden" name="id" id="id"/>
        项目名称: <input type="text" name="name" id="name"/><br>
        项目版本: <input type="text" name="version" id="version"/><br>
        <input id="btn_update" type="button" value="提交"/>
        <input id="btn_back" type="button" value="返回"/>
    </form>
</body>
</html>
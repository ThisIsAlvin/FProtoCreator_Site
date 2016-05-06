<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>更改协议</title>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#id").val("${proto.id}");
            $("#project_id").val("${proto.project_id}");
            $("#name").val("${proto.name}");
            $("#cmd").val("${proto.cmd}");
            $("#namespace").val("${proto.namespace}");
            $("#describes").val("${proto.describes}");

            $("#btn_update").click(function(){
                $.ajax("/login/proto/update?json",{
                    type:"post",
                    data:$('#update').serialize(),
                    success:function(data){
                        if (data.result == "error") {
                            alert("错误：" + data.message);
                        }
                        window.location.href="/login/proto/list?project_id=${proto.project_id}";
                    },
                })
            });
            $("#btn_back").click(function(){
                window.location.href="/login/proto/list?project_id=${proto.project_id}";
            });
        })
    </script>
</head>
<body>
<h2>更改协议</h2>
    <form id="update">
        <input type="hidden" name="id" id="id"/>
        <input type="hidden" name="project_id" id="project_id"/>
        C  M  D: <input type="text" name="cmd" id="cmd"/><br>
        协议名称: <input type="text" name="name" id="name"/><br>
        命名空间: <input type="text" name="namespace" id="namespace"/><br>
        协议描述: <input type="text" name="describes" id="describes"/><br>
        <input id="btn_update" type="button" value="提交"/>
        <input id="btn_back" type="button" value="返回"/>
    </form>
</body>
</html>
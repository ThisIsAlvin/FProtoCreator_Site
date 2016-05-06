<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>更改字段</title>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

            $("#id").val("${proto_field.id}");
            $("#proto_id").val("${proto_field.proto_id}");
            $("#name").val("${proto_field.name}");
            $("#type").val("${proto_field.type}");
            $("#extend").val("${proto_field.extend}");
            $("#is_array").val("${proto_field.is_array}");
            $("#remarks").val("${proto_field.remarks}");

            $("#btn_update").click(function(){
                $.ajax("/login/proto_field/update?json",{
                    type:"post",
                    data:$('#update').serialize(),
                    success:function(data){
                        if (data.result == "error") {
                            alert("错误：" + data.message);
                        }
                        window.location.href="/login/proto_field/list?proto_id=${proto_field.proto_id}";
                    },
                })
            });
            $("#btn_back").click(function(){
                window.location.href="/login/proto_field/list?project_id=${proto_field.proto_id}";
            });
        })
    </script>
</head>
<body>
<h2>更改字段</h2>
    <form id="update">
        <input type="hidden" name="id" id="id"/>
        <input type="hidden" name="proto_id" id="proto_id"/>
        字段名称: <input type="text" name="name" id="name"/><br>
        字段类型 : <input type="text" name="type" id="type"/><br>
        字段关联: <input type="text" name="extend" id="extend"/><br>
        是否数组: <input type="text" name="is_array" id="is_array"/><br>
        字段注释: <input type="text" name="remarks" id="remarks"/><br>
        <input id="btn_update" type="button" value="提交"/>
        <input id="btn_back" type="button" value="返回"/>
    </form>
</body>
</html>
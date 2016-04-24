<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
        $("#mytable").append("<tr id='title'><th>姓名</th><th>性别</th><th>住址</th><th>电话</th><th>邮箱</th></tr>");
        var btn_add="<input type='button' id='btn_add' value='添加' />";
        $("#title").append(btn_add);

        $("#btn_add").click(function(){
            window.location.href="/user/add";
        });

        $.ajax("/user/list?json",{
            type:"post",
            success:function(data){

                $.each(data,function(i,item){
                    var content="<tr id=content><td>"+item.name+"</td><td>"+item.sex+"</td><td>"+item.home+"</td><td>"+item.tel
                            +"</td><td>"+item.email+"</td></tr>";
                    var btn_del="<input type='button' id='btn_del' value='删除'/>";
                    var btn_update="<input type='button' id='btn_update' value='修改'/>";
                    $("#title").after(content);
                    $("#content").append(btn_update);
                    $("#content").append(btn_del);

                    $("#btn_del").click(function(){
                        $.post("/user/del?json","name="+item.name,function(){
                            window.location.reload();
                        });
                    });

                    $("#btn_update").click(function(){
                        var user = item;
                        $("html").load("/user/update",user,function(){

                        });
                    });


                });




            }
        })


    });

    </script>
</head>
<body>
<h2>list</h2>
<form id="list">
    <table id="mytable" border="1">
    </table>
</form>
</body>
</html>

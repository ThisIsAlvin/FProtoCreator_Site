<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>字段</title>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">

            function getPage(pageIndex,proto_id) {
                $("#mytable").empty();

                $.ajax("/login/proto_field/list?json",{
                    type:"post",
                    data:{"pageIndex":pageIndex,"proto_id":proto_id},
                    success:function(data){

                        $("#mytable").append("<tr id='title'><th>字段编号</th><th>协议编号</th><th>字段名称</th><th>字段类型</th><th>字段关联</th><th>是否数组</th><th>注释</th></tr>");
                        var btn_add="<input type='button' id='btn_add' value='添加'/>";
                        $("#title").append(btn_add);
                        $("#btn_add").click(function(){
                            /*添加的按钮动作*/
                            window.location.href="/login/proto_field/insert?proto_id="+proto_id;
                        });


                        if(data.result=="success"){
                            for(var i = 0; i < data.protoFields.length; i++){
                                var content="<tr id='content'><td>"+data.protoFields[i].id+"</td><td>"+data.protoFields[i].proto_id+"</td><td>"+data.protoFields[i].name+"</td><td>"+data.protoFields[i].type+"</td><td>"+data.protoFields[i].extend+"</td><td>"+data.protoFields[i].is_array+"</td><td>"+data.protoFields[i].remarks+"</td></tr>";
                                var btn_del="<input type='button' id='btn_del' value='删除'/>";
                                var btn_update="<input type='button' id='btn_update' value='修改'/>";
                                $("#title").after(content);
                                $("#content").append(btn_update);
                                $("#content").append(btn_del);


                                $("#btn_del").click(data.protoFields[i],function(event){
                                    /*删除的动作*/
                                    $.post("/login/proto_field/delete?json",event.data,function(){
                                        window.location.reload();
                                    });
                                });

                                $("#btn_update").click(data.protoFields[i],function(event){
                                    /*更新的操作*/
                                    $("html").load("/login/proto_field/update",event.data,function(){

                                    });
                                });

                            }
                        }else{
                            alert("result:"+data.result+"\n message:"+data.message);
                        }
                    }
                });

            }
        $(document).ready(function(){

            $("#proto_id").val("${proto_field.proto_id}");
            var id = 1;

            var index = 0;
            var count = 0;
            var maxIndex = 0;
            $.post("/login/proto_field/count?json",{proto_id:${proto_field.proto_id}},function(data){
                if(data.result == "success") {
                    count = data.count;
                    maxIndex = count/2;
                }
            });

            $("#pre-page").click(function(){
                index--;
                if(index>-1) {
                    getPage(index,${proto_field.proto_id});
                }else {
                    index++;
                }
            });
            $("#next-page").click(function(){
                index++;
                if(index< maxIndex) {
                    getPage(index,${proto_field.proto_id});
                }else {
                    index--;
                }
            });

           getPage(index,${proto_field.proto_id});


        });
    </script>

</head>
<body>
<h2>字段</h2>
<form id="projects">
    <table id="mytable" border="1">

    </table>
    <input type="button" id="pre-page" value="上一页">
    <input type="button" id="next-page" value="下一页">
    <input type="hidden" id="proto_id" name="proto_id">
</form>
</body>
</html>
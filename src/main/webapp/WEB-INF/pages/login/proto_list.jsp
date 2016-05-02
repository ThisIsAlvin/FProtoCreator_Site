<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>项目详情</title>
    <script type="text/javascript" src="/source/jquery.js"></script>
    <script type="text/javascript">

            function getPage(pageIndex,project_id) {
                $("#mytable").empty();

                $.ajax("/login/proto/list?json",{
                    type:"post",
                    data:{"pageIndex":pageIndex,"project_id":project_id},
                    success:function(data){

                        $("#mytable").append("<tr id='title'><th>协议编号</th><th>项目编号</th><th>CMD</th><th>协议名称</th><th>命名空间</th><th>描述</th></tr>");
                        var btn_add="<input type='button' id='btn_add' value='添加'/>";
                        $("#title").append(btn_add);
                        $("#btn_add").click(function(){
                            /*添加的按钮动作*/
                            window.location.href="/login/proto/insert?project_id="+project_id;
                        });

                        if(data.result=="success"){
                            for(var i = 0; i < data.protos.length; i++){
                                var content="<tr id='content'><td>"+data.protos[i].id+"</td><td>"+data.protos[i].project_id+"</td><td>"+data.protos[i].cmd+"</td><td>"+data.protos[i].name+"</td><td>"+data.protos[i].namespace+"</td><td>"+data.protos[i].describes+"</td></tr>";
                                var btn_del="<input type='button' id='btn_del' value='删除'/>";
                                var btn_update="<input type='button' id='btn_update' value='修改'/>";
                                $("#title").after(content);
                                $("#content").append(btn_update);
                                $("#content").append(btn_del);


                                $("#btn_del").click(data.protos[i],function(event){
                                    /*删除的动作*/
                                    $.post("/login/proto/delete?json",event.data,function(){
                                        window.location.reload();
                                    });
                                });

                                $("#btn_update").click(data.protos[i],function(event){
                                    /*更新的操作*/
                                    $("html").load("/login/proto/update",event.data,function(){

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

            $("#project_id").val("${proto.project_id}");
            var id = 1;

            var index = 0;
            var count = 0;
            var maxIndex = 0;
            $.post("/login/proto/count?json",{project_id:${proto.project_id}},function(data){
                if(data.result == "success") {
                    count = data.count;
                    maxIndex = count/2;
                }
            });

            $("#pre-page").click(function(){
                index--;
                if(index>-1) {
                    getPage(index,${proto.project_id});
                }else {
                    index++;
                }
            });
            $("#next-page").click(function(){
                index++;
                if(index< maxIndex) {
                    getPage(index,${proto.project_id});
                }else {
                    index--;
                }
            });

           getPage(index,${proto.project_id});


        });
    </script>

</head>
<body>
<h2>项目详情</h2>
<form id="projects">
    <table id="mytable" border="1">

    </table>
    <input type="button" id="pre-page" value="上一页">
    <input type="button" id="next-page" value="下一页">
    <input type="hidden" id="project_id" name="project_id">
</form>
</body>
</html>
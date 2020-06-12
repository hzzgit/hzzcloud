<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>jfinal-layui-blog</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link href="/assets/css/manage.css" media="screen" rel="stylesheet" type="text/css"/>
    <script src="/assets/js/jquery-1.4.4.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/lib/layui/css/layui.css" media="all">
    <script src="/lib/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<h1>学生管理管理&nbsp;&nbsp;
    <a href="/student/add">新增${tableconment}</a>
</h1>
<div class="table_box">
    <table class="layui-hide" id="test" lay-filter="test"></table>

    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="addData">新增</button>

        </div>
    </script>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="view">详情</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</div>
</body>

<script>
    layui.use(['form', 'table'], function () {
        var table = layui.table;
        var form = layui.form;
        table.render({
            elem: '#test'
            , url: '/${tablename}/selectlist.action'
            , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , cols: [[
                <#list tableColumnList as tablecolumn>
                {field: '${tablecolumn.columnname}', title: '${tablecolumn.columncomment}', sort: true},
                </#list>
                {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 250}
            ]]
            , page: true
        });


        //监听行工具事件
        table.on('tool(test)', function (obj) {
            var data = obj.data;
            //console.log(obj)
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    deleteUser(data.id);
                    layer.close(index);
                });
                document.location="/student";
            } else if (obj.event === 'edit') {
                editUser(data.id);
            } else if (obj.event === 'view') {
                view(data.id);
            }
        });

        //头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'addData':
                    addUser();
                    break;
            }
            ;
        });
    });

    function view(userId) {
        document.location = '/student/view/' + userId;
    }

    //删除用户
    function deleteUser(userId) {
        $.ajax(
            {
                type: 'get',
                url: "/student/delete?id=" + userId,
                success: function (flag) {
                    var parse = JSON.parse(result);
                    if (parse.code == 0) {
                        layer.msg('删除成功！',
                            function () {
                                window.parent.location.reload();
                            });
                    } else {
                        layer.msg('删除失败！');
                    }
                }

            });
    }

    //新增用户
    function addUser() {
        var data=[1,2];
        var map={"da":"111","name":"小米"};
        data.push(map);
        data.forEach(p,function (index,element) {
            console(index);
            console(element);
        })
        document.location = '/student/add/';
    }

    //编辑用户
    function editUser(userId) {
        document.location = '/student/edit/' + userId;
    }
</script>

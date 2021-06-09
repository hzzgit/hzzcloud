layui.use(['form', 'table'], function () {
    var table = layui.table;
    var form = layui.form;
    table.render({
        elem: '#test'
        , url: '/vehicle/search'
        , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        , cols: [[
            {field: 'simNo', title: 'simNo', sort: true}
            , {field: 'plateNo', title: '车牌号'}
            , {field: 'plateNo', title: '车牌号'}
            , {field: 'createDate', title: '创建时间'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 250}
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
           // document.location="/student";
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
    document.location = '/index/index';
}
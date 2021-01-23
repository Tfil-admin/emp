layui.use(['layer', 'table', 'jquery', 'form'], function(){
    var layer = layui.layer //弹层
        ,table = layui.table //表格
        ,$ = layui.jquery
        ,form = layui.form;

    //执行一个 table 实例
    table.render({
        elem: '#empTable'
        ,totalRow: true
        ,id:'bookReload'
        ,height: 620
        ,page:true
        ,url: 'emp/findEmp' //数据接口
        ,title: '信息表'
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,cols: [[ //表头
            {field: 'empId', title: '编号', sort: true,  width:180}
            ,{field: 'empName', title: '姓名', width:180}
            ,{field: 'dept', title: '部门', width:180, templet: function(d){
                    return d.dept.deptName;
                }}
            ,{field: 'dept', title: '地址', width:180 ,templet: function(d){
                    return d.dept.location;
                }}
            ,{ title: '操作', width: 180,toolbar:'#barDemo'}
        ]]
        ,parseData:function(rs){//数据格式解析
            console.log("rs===="+rs.toString()+"---"+rs.data.total+"---"+rs.data.list+"---"+rs.msg)
            return {
                "code":rs.code,	//返回状态码200
                "msg":rs.msg,	//
                "count":rs.data.total,	//总条目
                "data":rs.data.list	//具体内容
            }
        }
        // ,response: {
        //     statusCode: 0 //重新规定成功的状态码为 200，table 组件默认为 0
        // }
        // ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
        //     return {
        //         "code": res.code, //解析接口状态
        //         "msg": res.msg, //解析提示文本
        //         "count": res.data.totalCount, //解析数据长度
        //         "data": res.data.data //解析数据列表
        //     };
        // }
    });
    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,data = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                //layer.msg('添加');
                showDept(null);
                showForm(); // 打开弹窗展示新增的表单
                break;
            case 'update':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else if(data.length > 1){
                    layer.msg('只能同时编辑一个');
                } else {
                    layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                }
                break;
            case 'delete':
                if(data.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.msg('删除');
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent === 'detail'){
            layer.msg('查看操作');
        } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
                //obj.del(); //删除对应行（tr）的DOM结构
                delById(data.empId);
                layer.close(index);
                //向服务端发送删除指令
            });
        } else if(layEvent === 'edit'){
            //layer.msg('编辑操作');
            showDept(data);
            getById(data);
        }
    });

    //监听提交
    form.on('submit(formDemo)', function(data){
        // layer.msg(JSON.stringify(data.field));
        saveEmp(data.field);
        // layer.closeAll();
        return false;
    });
    function getById(emp){
        // 展示
        showForm();
        // 2. 给表单赋值
        $("input[name='empId']").val(emp.empId);
        $("input[name='empName']").val(emp.empName);
        $("input[name='deptId']").val(emp.dept.deptId);
    }

    // 保存方法
    function saveEmp(emp){
        $.ajax({
            type:"post",
            url:"emp/saveEmp",
            data:{
                "empId":emp.empId, // 新增不需要bookId ， 修改需要bookId
                "empName":emp.empName,
                "dept.deptId":emp.deptId
            },
            dataType: "json",
            success:function(msg){
                table.reload('bookReload', {});
                layer.closeAll();
            }
        })
    }

    // 删除的方法
    function delById(empId){
        $.ajax({
            type:"post",
            url:"emp/delEmp",
            data:{
                "empId":empId
            },
            dataType:"json",
            success:function(msg){
                // 删除数据表格重新刷新
                table.reload('bookReload', {});
            }
        });
    }
    function showDept(qwe) {
        $.ajax({
            type:"post",
            url:"dept/findDept",
            dataType:"json",
            success:function(msg){
                $("#selectID option").remove()
                var option="<option id=‘option'>请选择</option>";
                let list=msg.data;
                if (qwe==null){
                    for (let i=0;i<list.length;i++){
                        option+="<option value='"+list[i].deptId+"'>"+list[i].deptName+"</option>"
                    }
                }else {
                    for (let i=0;i<list.length;i++){
                        let sdsd=list[i].deptId == qwe.dept.deptId?'selected="selected"':'';
                        option+="<option value='"+list[i].deptId+"' "+sdsd+" >"+list[i].deptName+"</option>"
                    }
                }
                $("#selectID").append(option);
                form.render();
            }
        });
    }

    function showForm() {
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['620px', '640px'], //宽高
            content: $("#bookFrom").show()
            ,btn: ['确定', '取消']
            ,yes: function(index, layero){
                $("#submitBtn").click();
            }
            ,btn2: function(index, layero){
                //按钮【按钮二】的回调
            }, end:function(){
                $("#bookFrom").hide();
                $("#resetBtn").click();
                layer.closeAll();
            }
        });
    }

});
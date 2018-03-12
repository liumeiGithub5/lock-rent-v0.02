$(function (){
    $("#jqGrid").jqGrid({
        url: '/rent/sys/user/getAllUser',
        datatype: "json",
        colModel: [			
			{ label: '管理员ID', name: 'userId', index: "lock_id", width: 45, key: true },
			{ label: '用户名', name: 'username', width: 75 },
			{ label: '手机', name: 'mobile', width: 100 },
            {
                label: '状态', name: 'status', width: 80, formatter: function (value, options, row) {
                if (value === 1) {
                    return '<span class="label label-primary">正常</span>';
                }
                if (value === 0) {
                    return '<span class="label label-success">禁用</span>';
                }
            }
            },
            { label: '创建时间', name: 'createTime', width: 100 }

        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
    el:'#rrapp',
	data:{
        user:{

        },
        q:{
            username:null,
        },
        username:'',
        password:'',
        mobile:'',
        showList: true,
        title:"锁管理",

	},
	methods: {
		query: function () {
            vm.reload();

		},

		add: function(){
		    vm.shouList=false;
            vm.title = "新增";
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "新增",
                area: ['550px', '270px'],
                shadeClose: false,
                content: jQuery("#addLayer"),
                btn: ['确定','取消'],
                btn1: function (index) {
                    var url = "/rent/sys/user/add";
                    var data ="username="+vm.username+"&password="+vm.password+"&phone="+vm.mobile;//
                    console.error(data);
                    $.ajax({
                        type: "POST",
                        url: url+"?"+data,
                        data: "",
                        dataType: "json",
                        success: function(result){
                            if(result.code == 0){
                                layer.close(index);
                                layer.alert('添加成功', function(index){
                                    location.reload();
                                });
                            }else{
                                layer.alert(result.msg);
                            }
                        }
                    });
                }
            });

		},

		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "/rent/sys/user/delete",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},

		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		},

        disabled:function(){
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }
            confirm('确定要禁用吗？', function(){
                $.ajax({
                    type: "POST",
                    url: "/rent/sys/user/markDisable",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
		},
        normal:function(){
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }
            confirm('确定要解禁吗？', function(){
                $.ajax({
                    type: "POST",
                    url: "/rent/sys/user/markNormal",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
		}

	 }
});
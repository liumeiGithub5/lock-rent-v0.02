$(function (){
    $("#jqGrid").jqGrid({
        url: '/rent/webapi/remark/queryRemark',
        datatype: "json",
        colModel: [			
			{ label: '编号', name: 'id', index: "id", width: 20, key: true },
			{ label: '手机', name: 'mobile', width: 45 },
            { label: '留言', name: 'remark', width: 150 },
            {
                label: '状态', name: 'rmStatus', width: 25, formatter: function (value, options, row) {
                if (value === 1) {
                    return '<span class="label label-primary">已处理</span>';
                }
                if (value === 0) {
                    return '<span class="label label-success">待处理</span>';
                }
            }
            },
            { label: '创建时间', name: 'createtime', width: 50 }

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

		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'mobile': vm.q.mobile},
                page:page
            }).trigger("reloadGrid");
		},

        handled:function(){
            var ids = getSelectedRows();
            if(ids == null){
                return ;
            }
            confirm('确定标标记吗？', function(){
                $.ajax({
                    type: "POST",
                    url: "/rent/webapi/remark/handleRemark",
                    data: JSON.stringify(ids),
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
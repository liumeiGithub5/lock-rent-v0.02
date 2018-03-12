$(function () {
    $("#jqGrid").jqGrid({
        url: '/rent/webapi/IC/queryNeedUp',
        datatype: "json",
        colModel: [			
			{ label: '卡编号', name: 'icId', width: 30, key: true },
			{ label: '锁编号', name: 'lockId', width: 60 },
			{ label: '卡名称', name: 'icName', width: 100 },
			{ label: '更新时间', name: 'updatetime', width: 80 },
            {
                label: '卡状态', name: 'icStatus', width: 80, formatter: function (value, options, row) {
                if (value === 1) {
                    return '<span class="label label-primary">待补办</span>';
                }
                if (value === 0) {
                    return '<span class="label label-success">正常</span>';
                }
            }
            }
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
		q:{
            phone: null
		},
		showList: true,
		title: null,
		config: {},
		icName:null,
        outNo:null
	},
	methods: {
		query: function () {
			vm.reload();
		},

		update: function(){
            vm.title = "补办房卡";
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            layer.open({
                type: 1,
                skin: 'layui-layer-molv',
                title: "补办房卡",
                area: ['750x', '500px'],
                shadeClose: false,
                content: jQuery("#updateLayer"),
                btn: ['确定','取消'],
                btn1: function (index) {
                    var url = "/rent/webapi/IC/icUpdate";
                    var data = "icId="+id+"&name="+vm.icName+"&outNo="+vm.outNo;
                    console.error(data);
                    $.ajax({
                        type: "POST",
                        url: url+"?"+data,
                        data: '',
                        dataType: "json",
                        success: function(result){
                            if(result.code == 0){
                                layer.close(index);
                                layer.alert('操作成功', function(index){
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

		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'phone': vm.q.phone},
                page:page
            }).trigger("reloadGrid");
		}


	}
});
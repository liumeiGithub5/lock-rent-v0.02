$(function () {
    new AjaxUpload('#upload', {
        action: '/rent/webapi/lock/uploadIdMacExcel',
        name: 'file',
        autoSubmit:true,
        responseType:"json",
        onSubmit:function(file, extension){
            // if(vm.config.type == null){
            //     alert("云存储配置未配置");
            //     return false;
            // }
            // if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))){
            //     alert('只支持jpg、png、gif格式的图片！');
            //     return false;
            // }
            if (!(extension && /^(xlsx|xls)$/.test(extension.toLowerCase()))){
                alert('只支持excel文件！');
                return false;
            }
        },
        onComplete : function(file, r){
            if(r.code == 0){
                alert("上传成功");
               // vm.reload();
            }else{
                alert(r.msg);
            }
        }
    });

   $("#jqGrid").jqGrid({
        url: '/rent/webapi/lock/getHistory',
        datatype: "json",
        colModel: [
            { label: '编号', name: 'id', width: 45, key: true },
            { label: '本批次上传数量', name: 'count', width: 75 },
            { label: '截止锁编号', name: 'numrange', width: 100 },
            { label: '上传者', name: 'operator', width: 100 },
            { label: '上传时间', name: 'uptime', width: 100 }

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
            operator:''
        },
        showList: true,
        title: null
    },
    methods: {
        query:function(){
            vm.reload();
        },
        download:{

        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'version': vm.q.version},
                page:page
            }).trigger("reloadGrid");
        }

    }
});
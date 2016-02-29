
var CurrDataMap = {};
var CurrentCheckApply = null;

var ParamApplyPageNum = 1;


function init() {
	initData(function() {
		initComponent();
		initListener();
		initFace();
		$("#cdtStatus").val(0);
		query();
	});
	
}


function initData(cb) {
	DROP["VC_RESP_APPLY_CHECK_STATUS"] = [{code:"",name:"&nbsp;"},{code:"0",name:"待开通",attributes:{color:"#aa7700"}},{code:"1",name:"已开通",attributes:{color:"#008800"}}];
	
	var statusselhtml = PU.getSelectOptionsHtml("VC_RESP_APPLY_CHECK_STATUS");
	$("#cdtStatus").html(statusselhtml);
	
	RS.ajax({url:"/res/comproom/getCompRoomDropList",ps:{addEmpty:true, addAttr:true},cb:function(result) {
		DROP["DV_COMP_ROOM_CODE"] = result;
		
		var comproomselhtml = PU.getSelectOptionsHtml("DV_COMP_ROOM_CODE");
		$("#cdtCompRoomId").html(comproomselhtml);
		
		if(CU.isFunction(cb))cb();
	}});
	
	
}
function initComponent() {
	$("#cdtApplyTime").datetimepicker({
		minView: "month",
		format: "yyyy-mm-dd", 
		language: "zh-CN",
		autoclose:true
	});
}
function initListener() {
	$("#cdtCompRoomId").bind("change", function(){query();});
	$("#cdtApplyTime").bind("change", function(){query();});
	$("#cdtStatus").bind("change", function(){query();});
	
	$("#btn_query").bind("click",function(){query();});
	$("#grid_pageSize").bind("change",function(){query();});
}
function initFace() {
}


/** 执行条件文本框回车查询 **/
function doCdtTFKeyUp(e) {
	if(e.keyCode === 13) query();
}



function query(pageNum){
	if(CU.isEmpty(pageNum)) pageNum = 1;
	
	$("#applyTabList").html("");
	$("#ul_pagination").remove();
	$("#pagination_box").html('<ul id="ul_pagination" class="pagination-sm"></ul>');
	delete CurrDataMap;
	CurrDataMap = {};
	
	var pageSize = $("#grid_pageSize").val();
	var compRoomId = $("#cdtCompRoomId").val();
	var applyTime = $("#cdtApplyTime").val();
	var status = $("#cdtStatus").val();
	var orders = " ID DESC ";
	
	var cdt = {pageNum:pageNum,pageSize:pageSize,orders:orders};
	if(!CU.isEmpty(compRoomId)) cdt.compRoomId = compRoomId;
	if(!CU.isEmpty(status)) cdt.status = status;
	if(!CU.isEmpty(applyTime)) {
		applyTime = applyTime.replace(/-/g, "");
		cdt.startApplyTime = applyTime + "000000";
		cdt.endApplyTime = applyTime + "235959";
	}
	
	RS.ajax({url:"/vs/mntrespapply/queryInfoPage",ps:cdt,cb:function(r) {
		if(!CU.isEmpty(r)){
			var data = r.data;
			for(var i=0; i<data.length; i++) {
				CurrDataMap["key_"+data[i].apply.id] = data[i];
			}
			
			ParamApplyPageNum = r.pageNum;
			$("#ul_pagination").twbsPagination({
		        totalPages: r.totalPages?r.totalPages:1,
		        visiblePages: 7,
		        startPage: r.pageNum,
		        first:"首页",
		        prev:"上一页",
		        next:"下一页",
		        last:"尾页",
		        onPageClick: function (event, page) {
		        	query(page);
		        }
		    });
			$('#applyTabList-tmpl').tmpl(r).appendTo("#applyTabList");
			
			for(var i=0;i<data.length;i++){
				var link = $("#btn_openup_"+data[i].apply.id);
				if(!CU.isEmpty(link)) {
					//respType 1=svn	2=git
//					if(data[i].apply.respType==1) {
//						link.bind("click", function() {
//							var applyId = this.id.substring(this.id.lastIndexOf("_")+1);
//							openUpSvn(applyId);
//						});
//					}else {
						var url = data[i].apply.respUrl;
						if(CU.isEmpty(url)) url = "";
						link.editable({
							display:false,
							value:url,
							validate: function(value) {
					           if($.trim(value) == "") return "请输入版本库URL.";
					        },
							url: function (pps) {
								return openUpGit(pps.pk, pps.value);
							}
						});
//					}
				}
				
				$("#btn_source_"+data[i].apply.id+"_"+data[i].apply.sourceType+"_"+data[i].apply.sourceId).bind("click", function() {
					var applyId = this.id.substring(0,this.id.lastIndexOf("_"));
					applyId = applyId.substring(0,applyId.lastIndexOf("_"));
					applyId = applyId.substring(applyId.lastIndexOf("_")+1);
					var sid = this.id.substring(this.id.lastIndexOf("_")+1);
					var stype = this.id.substring(0, this.id.lastIndexOf("_"));
					stype = stype.substring(stype.lastIndexOf("_")+1);
					showSourceView(applyId,stype, sid);
				});
			}
		}
	}});
}



function openUpSvn(applyId) {
	CC.showMsg({msg:"您确定要开通当前SVN版本库吗?",option:2,callback:function(r) {
		if(r != "ok") return ;
		RS.ajax({url:"/vs/mntrespapply/openUpSvn",ps:{applyId:applyId},cb:function() {
			query(ParamApplyPageNum);
		}});
	}});
}
function openUpGit(applyId, respUrl) {
	return PU.submitEditable({url:"/vs/mntrespapply/openUpGit",ps:{applyId:applyId, respUrl:respUrl},cb:function() {
		query(ParamApplyPageNum);
	}});
}


function replaceDesc(desc) {
	if(CU.isEmpty(desc)) return "";
	return (desc+"").replace(/\n/g, "<br>");
}

//sourceType 1=product, 2=project
function showSourceView(applyId,sourceType, sourceId) {
	var record = CurrDataMap["key_"+applyId];
	var mntCode = record.merchent.mntCode;
	var mntName = record.merchent.mntName;
	var bean = {};
	bean.mntCode = mntCode;
	bean.mntName = mntName;
	if(sourceType == 1){
		RS.ajax({url:"/dev/product/queryProductById",ps:{id:sourceId},cb:function(result) {
			bean.productCode = result.code;
			bean.productName =  result.name;
			bean.productRemark = result.remark;
			$("#mntProductRepositoryTable").html("");
			$("#div_productRepositoryTitle").html("产品[<font color='blue'>"+result.name+"</font>]申请来源");
			$("#mntProductRepositoryTable-tmpl").tmpl(bean).appendTo("#mntProductRepositoryTable");
			$("#div_mntProductRepository").modal('show');
		}});
	}else if(sourceType == 2){
		RS.ajax({url:"/dev/project/queryProjectById",ps:{id:sourceId},cb:function(result) {
			bean.projectCode = result.project.code;
			bean.projectName =  result.project.name;
			bean.projectRemark = result.project.remark;
			bean.productName = result.product.name;
			bean.productCode = result.product.code;
			
			$("#mntProjectRepositoryTable").html("");
			$("#div_projectRepositoryTitle").html("工程[<font color='blue'>"+result.project.name+"</font>]申请来源");
			$("#mntProjectRepositoryTable-tmpl").tmpl(bean).appendTo("#mntProjectRepositoryTable");
			$("#div_mntProjectRepository").modal('show');
		}});
	}
}


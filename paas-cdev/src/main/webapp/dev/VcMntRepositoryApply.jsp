<%@ page contentType="text/html; charset=utf-8"%>

<%
String ContextPath = request.getContextPath();
%>

<jsp:include page="/layout/jsp/head.jsp"></jsp:include>



<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="filter-block pull-left">
				<div class="form-group pull-left">
					<div class="form-group pull-left">
						&nbsp;&nbsp;&nbsp;机房:
					</div>
					<div class="form-group pull-left">
						<select type="text" name="cdtCompRoomId" id="cdtCompRoomId" class="form-control" style="width:160px;"></select>
					</div>
					<div class="form-group pull-left">
						&nbsp;申请日期:
					</div>
					<div class="form-group pull-left">
						<input type="text" name="cdtApplyTime" id="cdtApplyTime" class="form-control" style="width:160px;" >
					</div>
					<div class="form-group pull-left">
						&nbsp;申请状态:
					</div>
					<div class="form-group pull-left">
						<select type="text" name="cdtStatus" id="cdtStatus" class="form-control" style="width:160px;"></select>
					</div>
				</div>
				<button class="btn btn-primary pull-left" id="btn_query"><i class="fa fa-search fa-lg"></i> 查询</button>
			</div>
		</div>
	</div>
</div>

<!-- 正文 -->

<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th class="text-center">申请单号</th>
								<th class="text-center">申请租户</th>
								<th class="text-center">所在机房</th>
								<th class="text-center">版本库类型</th>
								<th class="text-center">申请时间</th>
								<th class="text-center">申请状态</th>
								<th class="text-left">版本库URL</th>
								<th class="text-center">开通</th>
							</tr>
						</thead>
						<tbody id="applyTabList">
							
						</tbody>
					</table>
				</div>
				<div class="row-fluid">
					<div class="col-lg-6">
						<label>
							每页
								<select name="selPageSize"  class="pagination" id="grid_pageSize" >
									<option value="10">10</option>
									<option value="15">15</option>
									<option value="20" selected>20</option>
									<option value="25">25</option>
									<option value="30">30</option>
									<option value="40">40</option>
									<option value="50">50</option>
								</select>
							条记录
						</label>
					</div>
					<div class="col-lg-6">
						<div class="pagination pull-right" id="pagination_box">
							<ul id="ul_pagination" class="pagination-sm"></ul>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<!-- 产品来源弹出框（Modal） -->
<div class="modal fade" id="div_mntProductRepository" tabindex="-1" role="dialog" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="div_productRepositoryTitle">
              	 
            </h4>
         </div>
         <div class="modal-body">
            <table class="table">
		
				<tbody id="mntProductRepositoryTable">
					
				</tbody>
			</table>
         </div>
      </div>
	</div>
</div>

<!-- 工程来源弹出框（Modal） -->
<div class="modal fade" id="div_mntProjectRepository" tabindex="-1" role="dialog" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="div_projectRepositoryTitle">
              	 
            </h4>
         </div>
         <div class="modal-body">
            <table class="table">
		
				<tbody id="mntProjectRepositoryTable">
					
				</tbody>
			</table>
         </div>
      </div>
	</div>
</div>


<script id="applyTabList-tmpl" type="text/x-jquery-tmpl">
	{{each(i,row) data}}
		<tr>
			<td class="text-center">{{= row.apply.code}}</td>
			<td class="text-center">[{{= row.merchent.mntCode}}] {{= row.merchent.mntName}}</td>
			<td class="text-center">{{= PU.getDropValue("DV_COMP_ROOM_CODE",row.apply.compRoomId,false)}}</td>
			<td class="text-center">{{= PU.getDropValue("V_PC_PRODUCT_RESP_CODE_TYPE",row.apply.respType,false)}} - {{= PU.getDropValue("V_VC_MNT_REPOSITORY_APPLY_CONT_TYPE",row.apply.contType,false)}}</td>		
			<td class="text-center">{{= CU.toStringDateTime(row.apply.applyTime)}}</td>
			<td class="text-center">{{html PU.getDropValue("VC_RESP_APPLY_CHECK_STATUS",row.apply.status,true)}}</td>
			<td class="text-left">{{= row.apply.respUrl}}</td>
			<td class="text-center">
				{{if row.apply.respType==1}}
					{{if row.apply.status==0}}
						<a id="btn_openup_{{= row.apply.id}}" href="###" class="table-link" title="SVN版本库开通" data-type="text" data-pk="{{= row.apply.id}}" data-title="SVN版本库开通" class="editable">
							<span class="fa-stack">
								<i class="fa fa-square fa-stack-2x"></i>
								<i class="fa fa-gavel fa-stack-1x fa-inverse"></i>
							</span>
						</a>
					{{/if}}
				{{else}}
					<a id="btn_openup_{{= row.apply.id}}" href="###" class="table-link" title="GIT版本库开通" data-type="text" data-pk="{{= row.apply.id}}" data-title="GIT版本库开通" class="editable">
						<span class="fa-stack">
							<i class="fa fa-square fa-stack-2x"></i>
							<i class="fa fa-gavel fa-stack-1x fa-inverse"></i>
						</span>
					</a>
				{{/if}}
				<a id="btn_source_{{= row.apply.id}}_{{= row.apply.sourceType}}_{{= row.apply.sourceId}}" href="###" class="table-link" title="申请来源">
					<span class="fa-stack">
						<i class="fa fa-square fa-stack-2x"></i>
						<i class="fa fa-location-arrow fa-stack-1x fa-inverse"></i>
					</span>
				</a>
			</td>
		</tr>
	{{/each}}
</script>

<script id="mntProductRepositoryTable-tmpl" type="text/x-jquery-tmpl">
			<tr>
				<td class="text-right" width="200">租户代码：</td>
				<td>{{= mntCode}}</td>
			</tr>
			<tr>
				<td class="text-right">租户名称：</td>
				<td>{{= mntName}}</td>
			</tr>
			<tr>
				<td class="text-right">产品代码：</td>
				<td>{{= productCode}}</td>
			</tr>
			<tr>
				<td class="text-right">产品名称：</td>
				<td>{{= productName}}</td>
			</tr>
			<tr>
				<td class="text-right">产品描述：</td>
				<td>{{= replaceDesc(productRemark)}}</td>
			</tr>
</script>

<script id="mntProjectRepositoryTable-tmpl" type="text/x-jquery-tmpl">
			<tr>
				<td class="text-right" width="200">租户代码：</td>
				<td>{{= mntCode}}</td>
			</tr>
			<tr>
				<td class="text-right">租户名称：</td>
				<td>{{= mntName}}</td>
			</tr>
			<tr>
				<td class="text-right">所属产品代码：</td>
				<td>{{= productCode}}</td>
			</tr>
			<tr>
				<td class="text-right">所属产品名称：</td>
				<td>{{= productName}}</td>
			</tr>
			<tr>
				<td class="text-right">工程代码：</td>
				<td>{{= projectCode}}</td>
			</tr>
			<tr>
				<td class="text-right">工程名称：</td>
				<td>{{= projectName}}</td>
			</tr>
			<tr>
				<td class="text-right">工程描述：</td>
				<td>{{= replaceDesc(projectRemark)}}</td>
			</tr>
</script>



<jsp:include page="/layout/jsp/footer.jsp"></jsp:include>

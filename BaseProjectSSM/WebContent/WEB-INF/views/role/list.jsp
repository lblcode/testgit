<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
        </div>
        <div class="wu-toolbar-search">
            <label>角色名：</label><input id="search-name" class="wu-text" style="width:100px">
            <a href="#"  id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<style>
.selected{
	background:red;
}
</style>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">角色名:</td>
                <td><input type="text" name="name" class="wu-text  easyui-validatebox"  data-options="required:true,missingMessage:'请填写角色名'" /></td>
            </tr>
            <tr>
                <td align="right">角色备注:</td>
                <td><textarea id="edit-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<!--修改窗口-->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">角色名:</td>
                <td><input type="text"  id="edit-name"  name="name" class="wu-text  easyui-validatebox"  data-options="required:true,missingMessage:'请填写角色名'" /></td>
            </tr>
           <tr>
                <td align="right">角色备注:</td>
                <td><textarea id="edit-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- 选择权限弹窗 -->
<div id="select-icon-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:800px; height:550px;padding:10px;">
     <table id="icons-table" cellspacing="8px">
      
     </table>
</div>
<!-- End of easyui-dialog -->
<script type="text/javascript">
	/**
	* Name 添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		console.log(validate);
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warnnin");
			return;
		}
		var data = $('#add-form').serialize();
		$.ajax( {
			url:'../admin/role/add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type ==  'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	/**
	选择图标
	**/
	function selectIcon(){
		if($("#icons-table").children().length<=0){
			$.ajax( {
				url:'../admin/role/get_icons',
				dataType:'json',
				type:'post',
				success:function(data){
					if(data.type ==  'success'){
					    var icons = data.content;
						var table = ' ';
						/* console.log(icons); */
						for(var i=0;i<icons.length;i++){
							var tbody = '<td class="icon-td"><a onclick="selected(this)" href="javascript:void(0)"  class="'+icons[i]+'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>' ;
							if(i == 0){
								table += '<tr>'+tbody;
								continue;
							}
							if((i+1) %24 ==0){
								table +=tbody+'</tr><tr>';
								continue;
							}
							table += tbody;
						}
						table += '</tr>';
						$("#icons-table").append(table);
					}else{
						$.messager.alert('信息提示',data.msg,'warning');
					}
				}
		   });
	    }
		$('#select-icon-dialog').dialog({
			closed: false,
			modal:true,
            title: "选择icon信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function(){
                	var icon=$(".selected a").attr('class');
                	$("#add-icon").val(icon);
                	$("#edit-icon").val(icon);
                	$('#select-icon-dialog').dialog('close');   
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#select-icon-dialog').dialog('close');                    
                }
            }]
        });
		
	}
	
	function selected(e){
		$(".icon-td").removeClass('selected');
		$(e).parent("td").addClass('selected');
	}
	
	/**
	*  修改记录
	*/
	function edit(){	
		var validate = $("#edit-form").form("validate");
		console.log(validate);
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据!","warnnin");
			return;
		}
		var data = $("#edit-form").serialize();
		$.ajax( {
			url:'../admin/role/edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if(data.type ==  'success'){
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					$('#data-datagrid').datagrid('reload');
				}else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax( {
					url:'../admin/role/delete',
					dataType:'json',
					type:'post',
					data:{id:item.id},
					success:function(data){
						if(data.type ==  'success'){
							$.messager.alert('信息提示','删除成功！','info');
							$('#data-datagrid').datagrid('reload');
						}else{
							$.messager.alert('信息提示',data.msg,'warning');
						}
					}
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加角色",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#wu-dialog-2').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	*  打开修改窗口
	*/
	function openEdit(){
		//$('#edit-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		if(item == null || item.length == 0){
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');                    
                }
            }],
            onBeforeOpen:function(data){
            	$('#edit-id').val(item.id);
            	$('#edit-name').val(item.name);
            	$('#edit-remark').val(item.remark);
            }
        });
	}	
	
	
	
	/*搜索按钮监听  */
	$("#search-btn").click(function(){
		$('#data-datagrid').datagrid('reload',{
			name:$("#search-name").val()
		});
	});
	
	/**
	*  载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'../admin/role/list',	
		rownumbers:true,
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
	    treeField:'name',
		fit:true,
		columns:[[
			{ field:'chk',checkbox:true},
			{ field:'name',title:'角色名',width:100,sortable:true},
			{ field:'remark',title:'角色备注',width:180,sortable:true},
			{ field:'icon',title:'权限操作',width:100,formatter:function(value,index,row){
				var test = '<a class="icon-edit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>'
				return test + value;
			}}
		]]
	});
</script>
</script>
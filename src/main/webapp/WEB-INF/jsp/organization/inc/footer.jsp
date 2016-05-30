<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="pwdModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <form id="pwdForm" method="post" action="modifyPwd" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="oldPwd" class="col-sm-3 control-label">旧密码:</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="oldPwd" name="oldPwd" data-rule="required;length[6~];remote[check/oldPwd]" placeholder="请输入旧密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPwd" class="col-sm-3 control-label">新密码:</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" id="newPwd" name="newPwd" data-rule="新密码: required;length[6~]" placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="newPwd" class="col-sm-3 control-label">确认密码:</label>
                        <div class="col-sm-5">
                            <input type="password" class="form-control" data-rule="required;match(newPwd);" placeholder="请输入确认密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="save" onclick="modifyPwd()" class="btn btn-primary">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- Modal end -->

<footer >

    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<div class="submit">--%>
                    <%--<p class="text-center">武汉蓝色互动信息技术有限公司</p>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="container" >
        <div class="row">
            <div class="col-lg-12">
                <div class="submit">
                    <p class="text-center"><a href="javascript:void(0)" target="_blank">软范总后台管理系统</a></p>
                </div>
            </div>
        </div>
    </div>

</footer>


<!-- Metis Menu Plugin JavaScript -->
<script src="static/js/plugins/metisMenu/metisMenu.min.js"></script>

<script src="static/js/plugins/jquery.form.min.js"></script>


<script src="static/js/plugins/validator-0.7.3/jquery.validator.js"></script>

<script src="static/js/plugins/validator-0.7.3/local/zh_CN.js"></script>

<script src="static/js/plugins/notify-bootstap.min.js"></script>

<script src="static/js/plugins/jquery.uniform.min.js"></script>

<script src="static/js/plugins/boxer/jquery.fs.boxer.min.js"></script>

<!-- DataTables JavaScript -->
<script src="static/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="static/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- Custom Theme JavaScript -->
<script src="static/js/sb-admin-2.js"></script>

<script src="ueditor1_4_3/ueditor.config.js"></script>
<script src="ueditor1_4_3/ueditor.all.js"></script>
<script src="ueditor1_4_3/lang/zh-cn/zh-cn.js"></script>

<script src="static/js/global.js"></script>
<script src="static/js/jscolor.js"></script>
<script src="static/js/jscolor.min.js"></script>

<!-- datepicker -->
<script src="static/datepicker/js/bootstrap-datetimepicker.js"></script>
<script src="static/datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
    $(function () {
        $("#mediator").click(function () {
            showModal();
        });
    });

    function modifyPwd() {
        if(!$('#pwdForm').isValid()){
            return false;
        };
        $("#pwdForm").ajaxSubmit({
            dataType: "json",
            success: function (result) {
                if (result.status == '0') {
                    alert(result.msg)
                    location.reload();
                } else {
                    $sixmac.notify(result.msg, "error");
                }
            }
        })
    }

    function showModal() {
        $("#pwdModal").modal("show");
        $sixmac.clearForm($("#pwdModal"));
    }

</script>
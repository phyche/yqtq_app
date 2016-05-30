<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>权限审批</title>
    <%@ include file="inc/css.jsp" %>
</head>

<style>
    .textAling {
        text-align: center;
    }
</style>

<body>

<div id="posts" class="wrapper">
    <%@ include file="inc/nav.jsp" %>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="organization/join/index" class="btn btn-outline btn-primary btn-lg" role="button">加入申请</a>
                        <a href="organization/join/index" class="btn btn-outline btn-primary btn-lg" role="button">引荐申请</a>
                        <button type="button" onclick="organizationList.fn.appoint()">委任干事</button>
                        <button type="button" onclick="organizationList.fn.setinfo()">加入设置</button>

                        <form class="navbar-form navbar-right" role="search">
                            <div class="form-group">
                                <label>加入类型：</label>
                                <select id="typeList" style="width: 120px;" class="form-control">
                                    <option value="">选择加入</option>
                                    <option value="1">主动加入</option>
                                    <option value="2">被动邀请</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label>状态：</label>
                                <select id="checkList" style="width: 120px;" class="form-control">
                                    <option value="">选择状态</option>
                                    <option value="1">未处理</option>
                                    <option value="2">已批准</option>
                                    <option value="3">已忽略</option>
                                    <option value="4">已拉黑</option>
                                    <option value="5">等待对方验证</option>
                                    <option value="6">同意加入</option>
                                </select>
                            </div>
                        </form>
                    </div>

                    <div class="panel-body">

                        <div class="table-responsive">

                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                                <colgroup>
                                    <col class="gradeA even" style="width: 6%"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th><input type="checkbox" onclick="$sixmac.checkAll(this)" class="checkall"/></th>
                                    <th>头像</th>
                                    <th>名字</th>
                                    <th>性别</th>
                                    <th>年龄</th>
                                    <th>地区</th>
                                    <th>毕业院校</th>
                                    <th>事业状态</th>
                                    <th>行业领域</th>
                                    <th>职业</th>
                                    <th>专业</th>
                                    <th>擅长</th>
                                    <th>资历</th>
                                    <th>资源</th>
                                    <th>组织名称</th>
                                    <th>组织类型</th>
                                    <th>组织性质</th>
                                    <th>产品服务</th>
                                    <th>行业地位</th>
                                    <th>资质</th>
                                    <th>个人关键字</th>
                                    <th>发布</th>
                                    <th>加入类型</th>
                                    <th>验证信息</th>
                                    <th>状态</th>
                                    <th>申请时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <input id="mobile" class="form-control" placeholder="请输入会员手机号" name="mobile"  type="text">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick=" ">添加</button>
                </div>

                <div class="modal-body">
                    <form method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-12">
                            //干事标签
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" onclick="organizationList.fn.appointinfo()" class="btn btn-primary">委任</button>
                </div>

                <div class="modal-body">
                    <form method="post" class="form-horizontal" role="form">
                        <div class="form-group">
                            <div class="col-sm-12">
                                //干事标签
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

    <div class="modal fade" id="checkModal2" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <input type="radio" name="sex"  id="open"/>开放申请加入
                    <input type="radio" name="sex"  id="close"/>关闭申请加入
                </div>
                <div class="modal-body">
                    <form method="post" class="form-horizontal" role="form">
                        会员费<input type="text" id="feeId" />元
                    </form>
                </div>
                <div class="form-group">
                    <select id="industryList" style="width: 120px;" class="form-control">
                        <option value="">行业领域</option>
                        <option value="1">金融业</option>
                        <option value="2">服务业</option>
                        <option value="3">工业</option>
                        <option value="4">农业</option>
                    </select>
                </div>




                <div class="form-group">
                    <select id="areaList" style="width: 120px;" class="form-control">
                        <option value="">地区</option>
                        <option value="1">未处理</option>
                        <option value="2">已批准</option>
                        <option value="3">已忽略</option>
                        <option value="4">已拉黑</option>
                        <option value="5">等待对方验证</option>
                        <option value="6">同意加入</option>
                    </select>
                </div>





                <div class="form-group">
                    <select id="professionList" style="width: 120px;" class="form-control">
                        <option value="">职业</option>
                        <option value="1">管理类</option>
                        <option value="2">销售类</option>
                        <option value="3">事务类</option>
                        <option value="4">艺术类</option>

                    </select>
                </div>
                <div class="form-group">
                    <select id="genderList" style="width: 120px;" class="form-control">
                        <option value="">性别</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" onclick="organizationList.fn.setinfos()" class="btn btn-primary">确定设置</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</div>



<%@ include file="inc/footer.jsp" %>
</body>
</html>

<script type="text/javascript">

    var organizationList = {
        v: {
            id: "organizationList",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                organizationList.fn.dataTableInit();

                // 查询
                if ($("#typeList").val() != "" || $("#checkList").val() != ""){
                    organizationList.v.dTable.ajax.reload();
                };
            },
            dataTableInit: function () {
                organizationList.v.dTable = $sixmac.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ordering": false,
                    "ajax": {
                        "url": "backend/product/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": null},
                        {"data": "password"},
                        {"data": "type"},
                        {"data": "status"},
                        {"data": "createTime"},
                        {"data": ""},
                        {"data": "name"},
                        {"data": "merchant.nickName"},
                        {"data": "type"},
                        {"data": "status"},
                        {"data": "createTime"},
                        {"data": ""},
                        {"data": "name"},
                        {"data": "merchant.nickName"},
                        {"data": "type"},
                        {"data": "status"},
                        {"data": "createTime"},
                        {"data": ""},
                        {"data": "name"},
                        {"data": "merchant.nickName"},
                        {"data": "type"},
                        {"data": "status"},
                        {"data": "createTime"},
                        {"data": ""},
                        {"data": "status"},
                        {"data": "createTime"},
                        {"data": ""}
                    ],
                    "columnDefs": [
                        {
                            "data": null,
                            "defaultContent": /*"<button type='button' title='推荐到MALL' class='btn btn-info btn-circle editHot'>" +
                             "<i class='fa fa-recycle'></i>" +
                             "</button>" +
                             "&nbsp;&nbsp;" +*/
                            "<button type='button' value='批准' style='display: none' class='btn btn-primary btn-circle agree'>" +
                            "<i class='fa fa-check'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' value='拒绝' style='display: none' class='btn btn-primary btn-circle refuse'>" +
                            "<i class='fa fa-check'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' value='拉黑' style='display: none' class='btn btn-primary btn-circle black'>" +
                            "<i class='fa fa-check'></i>" +
                            "</button>"+
                            "&nbsp;&nbsp;" +
                            "<button type='button' value='移出黑名单' style='display: none' class='btn btn-primary btn-circle blackout'>" +
                            "<i class='fa fa-check'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        organizationList.v.list.push(data);

                        if (data.age == 1) {
                            $('td', row).eq(4).html("18-");
                        } else if (data.age == 2) {
                            $('td', row).eq(4).html("18+");
                        }else if (data.age == 3) {
                            $('td', row).eq(4).html("20+");
                        }else if (data.age == 4) {
                            $('td', row).eq(4).html("25+");
                        }else if (data.age == 5) {
                            $('td', row).eq(4).html("30+");
                        }else if (data.age == 6) {
                            $('td', row).eq(4).html("35+");
                        }else if (data.age == 7) {
                            $('td', row).eq(4).html("40+");
                        }else if (data.age == 8) {
                            $('td', row).eq(4).html("45+");
                        }else if (data.age == 9) {
                            $('td', row).eq(4).html("50+");
                        }else if (data.age == 10) {
                            $('td', row).eq(4).html("55+");
                        }else if (data.age == 11) {
                            $('td', row).eq(4).html("60+");
                        }else if (data.age == 12) {
                            $('td', row).eq(4).html("65+");
                        }else if (data.age == 13) {
                            $('td', row).eq(4).html("70+");
                        }else if (data.age == 14) {
                            $('td', row).eq(4).html("75+");
                        }else if (data.age == 15) {
                            $('td', row).eq(4).html("80+");
                        }else if (data.age == 16) {
                            $('td', row).eq(4).html("85+");
                        }else if (data.age == 17) {
                            $('td', row).eq(4).html("90+");
                        }else if (data.age == 18) {
                            $('td', row).eq(4).html("95+");
                        }else if (data.age == 19) {
                            $('td', row).eq(4).html("100+");
                        }

                        if (data.isCheck == 1) {
                            $('td', row).eq(24).html("待审核");
                        } else if (data.isCheck == 2) {
                            $('td', row).eq(24).html("审核通过");
                        } else if (data.isCheck == 3) {
                            $('td', row).eq(24).html("审核不通过");
                        }else if (data.isCheck == 4) {
                            $('td', row).eq(24).html("审核通过");
                        } else if (data.isCheck == 5) {
                            $('td', row).eq(24).html("审核不通过");
                        }else if (data.isCheck == 6) {
                            $('td', row).eq(24).html("审核通过");
                        }

                        if (data.isType == 1) {
                            $('td', row).eq(22).html("主动申请");
                        } else if (data.isType == 2) {
                        $('td', row).eq(22).html("被动邀请");
                        }
                    },
                    "rowCallback": function (row, data) {
                        var items = organizationList.v.list;

                        if (data.isCheck == 1) {
                            // 未审核时，显示审核按钮
                            $('td', row).last().find(".agree").css("display", '');
                            $('td', row).last().find(".refuse").css("display", '');
                            $('td', row).last().find(".black").css("display", '');
                        }else if (data.isCheck == 4) {
                            // 未审核时，显示审核按钮
                            $('td', row).last().find(".blackout").css("display", '');
                        }else {
                            $('td', row).last().html("/");
                        }




                        $('td', row).last().find(".agree").attr("href", 'backend/product/add?id=' + data.id);

                        $('td', row).last().find(".editHot").click(function () {
                            // 推荐到MALL or 取消推荐
                            if (data.isHot == 0) {
                                organizationList.fn.changeHot(data.id, 1);
                            } else {
                                organizationList.fn.changeHot(data.id, 0);
                            }
                        });

                        $('td', row).last().find(".delete").click(function () {
                            // 删除
                            organizationList.fn.delInfo(data.id);
                        });

                        $('td', row).last().find(".checkyes").click(function () {
                            // 审核为通过
                            organizationList.fn.changeCheck(data.id, 1);
                        });

                        $('td', row).last().find(".checkno").click(function () {
                            // 审核为不通过
                            organizationList.fn.checkNo(data.id);
                        });
                    },
                    "fnServerParams": function (aoData) {
                        aoData.age = ${age};
                        aoData.isCheck = $('#checkList option:selected').val();
                        aoData.isType = $('#typeList option:selected').val();
                    },
                    "fnDrawCallback": function (row) {
                        $sixmac.uiform();
                    }
                });

            },
            appoint:function () {
                $("#checkModal").modal("show");
            },
            appointinfo:function() {
                var flag = true;
                var mobile = $('#mobile').val();

                if (null == mobile || mobile.trim().length == 0) {
                    $sixmac.notify("请输入会员手机号", "error");
                    flag = false;
                    return;
                }

                if (flag) {
                    $sixmac.ajax("organization/join/add", {
                        "mobile": mobile
                    }, function (result) {
                        if (result == 1) {
                            $sixmac.notify("操作成功", "success");
                            $("#checkModal").modal("hide");
                        } else {
                            $sixmac.notify("操作失败", "error");
                        }
                    });
                }
            },
            setinfo:function () {
                //获取登录组织的id
                var id = ${organization.id};


                $("#checkModal1").modal("show");
            },
            setinfos:function() {
                var flag = true;
                var sex = document.getElementsByName("sex")
                for(var i=0;i<sex.length;i++) {
                    if (sex[i].checked == true) {
                        return true;
                    } else {
                        alert("请选择是否开放申请");
                        return false;
                    }
                }
                if (flag) {
                    $sixmac.ajax("organization/join/setinfo", {
                        "industry": $('#industryList option:selected').val(),
                        "area": $('#areaList option:selected').val(),
                        "profession": $('#professionList option:selected').val(),
                        "gender": $('#genderList option:selected').val()
                        "id":${organization.id};
                    }, function (result) {
                        if (result == 1) {
                            $sixmac.notify("操作成功", "success");
                            $("#checkModal").modal("hide");
                        } else {
                            $sixmac.notify("操作失败", "error");
                        }
                    });
                }
            }
        }
    }

    $(document).ready(function () {
    organizationList.fn.init();
    });
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>修改密码</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">修改密码</h3>
                </div>
                <div class="panel-body">
                    <form id="loginForm" role="form" action="login/check" method="post">
                        <fieldset>
                            <div class="container">
                                <input type="text" class="check_box" id="mobile" placeholder="请输入手机号码">
                                <div class="input_box">
                                    <input type="text" placeholder="请输入验证码" id="code" />
                                    <span><input type="button" id="codeBtn" onclick="settime(this)" value="获取验证码"/></span>
                                </div>
                                <div class="danger" id="dialog" style="color: red;">
                                    <span></span>
                                </div>
                                <div class="password_box">
                                    <input type="password" class="form_password" id="password" placeholder="输入新密码">
                                </div>
                                <div class="password_box">
                                    <input type="password" class="form_password" id="password1" placeholder="再输一次新密码">
                                </div>
                            </div>
                            <button type="button" id="submitButton" onclick="changeStyle()" class="btn btn-lg btn-success btn-block">确定修改</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">
    var countdown = 60;
    function settime(btn) {
        var mobile = $('#mobile').val();
        if (null == mobile || mobile == '') {
            dialog.style.display="block";
            dialog.innerHTML="请输入手机号码！";
            //alert('手机号不能为空');
        } else {
            // 发送验证码
            $.ajax({
                type: "post",
                url: "${contextPath}/moneymag/sendCode",
                data: {
                    mobile: mobile
                },
                success: function (result) {
                    if (result == "success") {
                    }
                }
            });
            jian(btn);
        }
    }
    function jian(btn) {
        var tg = document.getElementById("codeBtn");
        if (countdown == 0) {
            btn.removeAttribute("disabled");
            btn.value = "点击发送验证码";
            countdown = 60;
            btn.className = "btn_sendcode1";
            tg.className = "register1";
        } else {
            countdown--;
            btn.setAttribute("disabled", true);
            $("#codeBtn").attr("value",countdown + "秒后重新发送");
            btn.className = "btn_sendcode2";
            tg.className = "register2";
            setTimeout(function () {
                jian(btn)
            }, 1000)
        }
    }
    function changeStyle() {
        var flag = true;
        var flag2 = true;
        var mobile = $("#mobile").val();
        var code = $("#code").val();
        var dialog = document.getElementById("dialog");
        if(mobile == ""){
            dialog.style.display="block";
            dialog.innerHTML="请输入手机号码！";
            //alert("请输入手机号码");
            flag = false;
            return flag;
        }

        if(code ==""){
            dialog.style.display="block";
            dialog.innerHTML="请输入验证码";
            flag = false;
            return;
        }
        if(flag) {
            $.post("organization/index/confirmCode",{"mobile": $("#mobile").val(), "requestCode" : $("#code").val() },function(result) {
                if(result == "success") {
                    doSubmit();
                }
                else {
                    dialog.style.display="block";
                    dialog.innerHTML="验证码错误，请重新输入！"
                }
            });
        }
    }
    function doSubmit() {
        var flag = true;
        var password = $("#password").val();
        var password1 = $("#password1").val();
        if (null == password || password == "") {
            $sixmac.notify("请输入新密码", "error");
            flag = false;
            return;
        }
        if (null == password1 || password1 == "") {
            $sixmac.notify("请再输一次新密码", "error");
            flag = false;
            return;
        }
        if (password1 != password) {
            $sixmac.notify("两次密码输入不一致，请重新输入！", "error");
            flag = false;
            return;
        }
        if(flag) {
            window.location.href = "organization/index/findPassword?password=" + $("#password").val() + '&id=' + $("#");
        }

        return flag;
    }
</script>

</body>
</html>
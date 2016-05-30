<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>组织后台登录</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">登录账号</h3>
                </div>
                <div class="panel-body">
                    <form id="loginForm" role="form" action="organization/login/checkLogin" method="post">
                        <fieldset>
                            <div class="form-group  <c:if test="${empty error}">has_error</c:if>">
                                <label id="errorlable" class="control-label">${error}</label>
                                <input id="account" class="form-control" placeholder="请输入组织ID" name="account" value="${organizationID}" type="text"/>
                            </div>
                            <div class="form-group <#if error??>has-error</#if>">
                                <input id="password" class="form-control" placeholder="请输入密码" name="password" type="password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" value="1" id="checkboxInput" name="radio2">
                                <label for="checkboxInput"></label><span class="agree" style="font-size: 1.4rem">记住账号</span>
                                <span class="tips " style="width: 40%;position: relative;top: 0px;float: right;"><a href="organization/index/forgetIndex">忘记密码</a></span>
                            </div>

                            <button type="button" id="submitButton" onclick="validation()" class="btn btn-lg btn-success btn-block">登录</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">

    //回车触发登录
    $(document).keyup(function (event) {
        //点击回车触发
        if (event.keyCode == 13) {
            if ($("#account").val() == "") {
                $("#account").focus();
                return;
            }
            if ($("#password").val() == "") {
                $("#password").focus();
                return;
            }
            if ($("#textfield3").val() == "") {
                $("#textfield3").focus();
                return;
            }
            validation();
        }
    });

    function validation() {
        var flag = true;
        if ($("#account").val() == "") {
            $("#errorlable").html("请输入组织ID");
            $("#account").focus();
            flag = false;
            return flag;
        }
        if ($("#password").val() == "") {
            $("#errorlable").html("请输入密码");
            $("#password").focus();
            flag = false;
            return flag;
        }
        if(flag) {
            window.location.href = "organization/index/checkLogin?ID=" + $("#account").val() + '&password=' +$("#password").val();
        }
        $("#loginForm").submit();
    }

</script>

</html>

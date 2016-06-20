package com.sixmac.controller.api;

import cn.emay.channel.SmsSend;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.GirlServiceMessage;
import com.sixmac.entity.ServiceMessage;
import com.sixmac.entity.User;
import com.sixmac.service.ServiceMessageService;
import com.sixmac.service.UserService;
import com.sixmac.utils.CommonUtils;
import com.sixmac.utils.JsonUtil;
import com.sixmac.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Controller
@RequestMapping(value = "api/login")
public class LoginApi extends CommonController {

    @Autowired
    private ServiceMessageService serviceMessageService;

    private static Map<String, String> codeMap = new HashMap<String, String>();


    static {
        if (codeMap == null || codeMap.isEmpty()) {
            codeMap = new HashMap<String, String>();
        }
    }

    @Autowired
    private UserService userService;

    /**
     * 无法通过手机号获得密码
     *
     * @api {post} /api/login/checkLogin 登录检查
     * @apiName login.checkLogin
     * @apiGroup login
     * @apiParam {String} mobile 手机号  <必传 />
     * @apiParam {String} password 密码  <必传 />
     */
    @RequestMapping(value = "/checkLogin")
    public void checkLogin(HttpServletResponse response, String mobile, String password) {

        if (null == mobile || mobile == " " || password == null || password == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        if (userService.findByMobile(mobile) == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0015));
            return;
        }
        User user = userService.findByMobile(mobile);
        if (!user.getPassword().equals(password)) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0027));
            return;
        }

        if (user.getStatus() == 1) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0028));
        }

        WebUtil.printJson(response, new Result(true));
    }

    /**
     * 待定
     *
     * @api {post} /api/login/sendCode 发送验证码
     * @apiName login.sendCode
     * @apiGroup login
     * @apiParam {String} mobile 手机号  <必传 />
     *
     * @apiSuccess {Object}  codeMap
     * @apiSuccess {String} codeMap.mobile 发送验证码的手机号
     * @apiSuccess {String} codeMap.code 验证码
     */
    @RequestMapping(value = "/sendCode")
    public void sendCode(HttpServletResponse response, String mobile) {

        if ( mobile == null || mobile == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        try {
            // 生成验证码
            String code = CommonUtils.getCode(6);
            // 成功
            if (SmsSend.send(mobile, code)) {
                codeMap.put(mobile, code);

                Result obj = new Result(true).data(createMap("codeMap", codeMap));
                String result = JsonUtil.obj2ApiJson(obj);
                WebUtil.printApi(response, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成
     *
     * @api {post} /api/login/information 服务条款说明
     * @apiName login.information
     * @apiGroup login
     *
     * @apiSuccess {String} content 服务条款说明内容
     */
    @RequestMapping(value = "/information")
    public void information(HttpServletResponse response) {

        ServiceMessage serviceMessage = serviceMessageService.getById(1l);

        String description = serviceMessage.getContent();
        String others = "<html><head><style type='text/css'>body{overflow-x:hidden;margin:0;padding:0;background:#fff;color:#000;font-size:18px;font-family:Arial,'microsoft yahei',Verdana}body,div,fieldset,form,h1,h2,h3,h4,h5,h6,html,p,span{-webkit-text-size-adjust:none}h1,h2,h3,h4,h5,h6{font-weight:normal}applet,dd,div,dl,dt,h1,h2,h3,h4,h5,h6,html,iframe,img,object,p,span{margin:0;padding:0;border:0}img{margin:0;padding:0;border:0;vertical-align:top}li,ul{margin:0;padding:0;list-style:none outside none}input[type=text],select{margin:0;padding:0;border:0;background:0;text-indent:3px;font-size:14px;font-family:Arial,'microsoft yahei',Verdana;-webkit-appearance:none;-moz-appearance:none}.wrapper{box-sizing:border-box;padding:10px;width:100%}p{color:#666;line-height:1.6em}.wrapper img{width:auto!important;height:auto!important;max-width:100%}p,span,p span{font-size:18px!important}</head></style>";
        description = description.format("<body><div class='wrapper'>%s</div></body></html>", description);
        description = others + description;

        Result obj = new Result(true).data(createMap("content", description));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);

    }

    /**
     * 待定
     *
     * @api {post} /api/login/confirmCode 确认验证码
     * @apiName login.confirmCode
     * @apiGroup login
     * @apiParam {String} mobile 手机号  <必传 />
     * @apiParam {String} requestCode 验证码  <必传 />
     */
    @RequestMapping(value = "/confirmCode")
    public void confirmCode(HttpServletResponse response,
                            String mobile,
                            String requestCode) {

        if (null == mobile || mobile == " " || requestCode == null|| requestCode == " ") {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        String code = codeMap.get(mobile);

        if (StringUtils.isBlank(requestCode)) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0003));
            return;
        }

        if (!requestCode.equals(code)) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0004));
            return;
        }

        Result obj = new Result(true);
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }

    /**
     * 设置密码
     *
     * @api {post} /api/login/findPassword 设置密码
     * @apiName login.findPassword
     * @apiGroup login
     * @apiParam {String} mobile 手机号  <必传 />
     * @apiParam {String} password 登录密码  <必传 />
     * @apiParam {String} password2 确认密码  <必传 />
     */
    @RequestMapping(value = "/findPassword")
    public void findPassword(HttpServletResponse response, String password, String password2, String mobile) {

        if (null == password || password == "" || null == password2 || password2 == "" || null == mobile || mobile == "" ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }
        User user = userService.findByMobile(mobile);

        if (!password.equals(password2)) {
            WebUtil.printJson(response, new Result(true).data("登录密码与确认密码不一致，提示重新输入"));
        }else {

            if (user != null) {
                user.setPassword(password);
                userService.update(user);
            }else {
                user = new User();
                user.setMobile(mobile);
                user.setPassword(password);
                userService.create(user);
            }

            Result obj = new Result(true).data(createMap("password", password));
            String result = JsonUtil.obj2ApiJson(obj);
            WebUtil.printApi(response, result);
        }
    }

    /**
     * @api {post} /api/login/tLogin 第三方登录
     * @apiName login.tLogin
     * @apiGroup login
     * @apiParam {Integer} type 第三方类型，1=微信，2=QQ，3=新浪微博       <必传 />
     * @apiParam {String} openId 唯一标识       <必传 />
     * @apiParam {String} head 头像路径       <必传 />
     * @apiParam {String} nickname 昵称       <必传 />
     *
     * @apiSuccess {Object} userInfo 用户信息
     * @apiSuccess {Integer} userInfo.id 用户id
     * @apiSuccess {String} userInfo.mobile 手机号
     * @apiSuccess {String} userInfo.nickname 昵称
     * @apiSuccess {String} userInfo.avater 头像
     * @apiSuccess {Integer} userInfo.credibility 信誉度
     * @apiSuccess {String} userInfo.createTime 注册时间
     * @apiSuccess {Integer} userInfo.cityId 所在城市id
     */
    @RequestMapping(value = "/tLogin")
    public void tLogin(HttpServletResponse response,
                       Integer type,
                       String openId,
                       String head,
                       String nickname) {
        if (null == type || null == openId || openId == "" || null == head || head == "" || nickname == "" ||null == nickname ) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0002));
            return;
        }

        User users = userService.iTLogin(type, openId, head, nickname);

        if (null == users) {
            WebUtil.printApi(response, new Result(false).msg(ErrorCode.ERROR_CODE_0015));
            return;
        }

        Result obj = new Result(true).data(createMap("userInfo", users));
        String result = JsonUtil.obj2ApiJson(obj, "city", "password", "type");
        WebUtil.printApi(response, result);
    }
}

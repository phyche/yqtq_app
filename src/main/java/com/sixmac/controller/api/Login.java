package com.sixmac.controller.api;

import cn.emay.channel.SmsSend;
import com.sixmac.controller.common.CommonController;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.entity.User;
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
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Controller
@RequestMapping(value = "api/login")
public class Login extends CommonController {

    private static Map<String,String> codeMap = new HashMap<String,String>();


    static {
        if(codeMap == null || codeMap.isEmpty()) {
            codeMap =  new HashMap<String,String>();
        }
    }

    @Autowired
    private UserService userService;

    /**
     * 登录检查
     */
    @RequestMapping(value = "/checkLogin")
    public void checkLogin(HttpServletResponse response, String mobile, String password) {

        if (userService.findByMobile(mobile) == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0015));
            return;
        }

        if(password != userService.findByMobile(mobile).getPassword()){
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0027));
            return;
        }

        WebUtil.printJson(response, new Result(true));
    }

    /**
     * 发送验证码
     */
    @RequestMapping(value = "/sendCode")
    public void sendCode(HttpServletResponse response,String mobile) {

        try {
            // 生成验证码
            String code = CommonUtils.getCode(6);
            // 成功
            if (SmsSend.send(mobile, code)) {
                codeMap.put(mobile,code);

                Result obj = new Result(true).data(createMap("codeMap",codeMap));
                String result = JsonUtil.obj2ApiJson(obj);
                WebUtil.printApi(response, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 确认验证码
     */
    @RequestMapping(value = "/confirmCode")
    public void confirmCode(HttpServletResponse response,
                            String mobile,
                            String requestCode) {

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
     */
    @RequestMapping(value = "/findPassword")
    public void findPassword(HttpServletResponse response,String password, Integer userId) {

        User user = userService.getById(userId);

        if(user != null) {
            user.setPassword(password);
            userService.update(user);
        }

        Result obj = new Result(true).data(createMap("password",password));
        String result = JsonUtil.obj2ApiJson(obj);
        WebUtil.printApi(response, result);
    }
}

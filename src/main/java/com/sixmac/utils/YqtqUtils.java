package com.sixmac.utils;

import com.sixmac.core.Constant;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class YqtqUtils {
    /**
     * 获取指定天数的旧天数
     *
     * @return
     */
    public static Date getOldDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Constant.days * -1);

        return calendar.getTime();
    }
}

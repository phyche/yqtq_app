package com.sixmac.utils;

import com.sixmac.entity.vo.MessageVo;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class Compare implements Comparator<MessageVo> {
    @Override
    public int compare(MessageVo o1, MessageVo o2) {
        if (o1.getCreateDate() > o2.getCreateDate()) {
            return -1;
        } else {
            return 1;
        }
    }
}

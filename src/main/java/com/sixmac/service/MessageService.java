package com.sixmac.service;

import com.sixmac.entity.Message;
import com.sixmac.entity.MessageOrderBall;
import com.sixmac.entity.MessageWatching;
import com.sixmac.entity.PostComment;
import com.sixmac.entity.vo.MessageVo;
import com.sixmac.service.common.ICommonService;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
public interface MessageService extends ICommonService<Message> {

    public Message getByType(Integer type);

    // 好友约球消息
    public List<MessageVo> orderBall(HttpServletResponse response, Long userId) throws ParseException;

    // 处理消息
    public void doMessage(HttpServletResponse response, Long id, Integer status, Integer type);

    // 约看消息
    public List<MessageWatching> watching(HttpServletResponse response, Long userId);

    // 帖子消息
    public List<PostComment> post(HttpServletResponse response, Long userId);

    // 系统消息
    public List<MessageVo> system(HttpServletResponse response, Long userId);

    // 球队消息
    public List<MessageVo> team(HttpServletResponse response, Long userId);
}
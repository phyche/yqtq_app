package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.dao.*;
import com.sixmac.entity.*;
import com.sixmac.entity.vo.MessageVo;
import com.sixmac.service.MessageService;
import com.sixmac.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2016/6/2 0002 上午 10:42.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private MessageOrderBallDao messageOrderBallDao;

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Autowired
    private MessageJoinDao messageJoinDao;

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UserReserveDao userReserveDao;

    @Autowired
    private MessageAddDao messageAddDao;

    @Autowired
    private TeamMemberDao teamMemberDao;

    @Autowired
    private TeamRaceDao teamRaceDao;

    @Autowired
    private MessageWatchingDao messageWatchingDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private PostCommentDao postCommentDao;

    @Autowired
    private SystemMessageDao systemMessageDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private MessageTeamDao messageTeamDao;

    @Override
    public List<Message> findAll() {
        return messageDao.findAll();
    }

    @Override
    public Page<Message> find(int pageNum, int pageSize) {
        return messageDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<Message> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public Message getById(Long id) {
        return messageDao.findOne(id);
    }

    @Override
    public Message deleteById(Long id) {
        Message message = getById(id);
        messageDao.delete(message);
        return message;
    }

    @Override
    public Message create(Message message) {
        return messageDao.save(message);
    }

    @Override
    public Message update(Message message) {
        return messageDao.save(message);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Message getByType(Integer type) {
        return messageDao.getByType(type);
    }

    @Override
    public List<MessageVo> orderBall(HttpServletResponse response, Long userId) throws ParseException {

        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        List<MessageOrderBall> list1 = messageOrderBallDao.findByToUserId(userId);
        Reserve reserve = null;
        List<MessageRecord> messageRecordList = null;
        for (MessageOrderBall messageOrderBall : list1) {

            reserve = messageOrderBall.getReserve();
            messageRecordList = messageRecordDao.findByMessageId(messageOrderBall.getId(), 0);
            for (MessageRecord messageRecord : messageRecordList) {
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }
            messageVo = new MessageVo();
            messageVo.setId(messageOrderBall.getId());
            messageVo.setUserId(messageOrderBall.getUser().getId());
            messageVo.setNickname(messageOrderBall.getUser().getNickname());
            messageVo.setContent("您的好友" + "user" + "约您去踢球啦！");
            messageVo.setCreateDate(messageOrderBall.getCreateDate());
            messageVo.setStartTime(reserve.getStartTime());
            messageVo.setStadiumName(reserve.getStadium().getName());
            messageVo.setType(4);

            messageVo.setReserveType(reserve.getType());
            messageVo.setReserveId(reserve.getId());
            if (reserve.getType() == 0) {
                messageVo.setMatchType(reserve.getMatchType());
                messageVo.setJoinCount(reserve.getJoinCount());
                messageVo.setLackNum(reserve.getMatchType() * 2 - reserve.getJoinCount());
            }
            messageVoList.add(messageVo);

        }

        List<Reserve> reserveList = reserveDao.findByUserId(userId);
        List<UserReserve> list2 = new ArrayList<UserReserve>();
        for (Reserve reserve1 : reserveList) {
            for (UserReserve userReserve : userReserveDao.findByReserverId(reserve1.getId())) {

                reserve = userReserve.getReserve();
                messageRecordList = messageRecordDao.findByMessageId(userReserve.getId(), 1);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }
                messageVo = new MessageVo();
                messageVo.setUserId(userReserve.getUser().getId());
                messageVo.setNickname(userReserve.getUser().getNickname());
                messageVo.setContent("user" + "加入了您的约球");
                messageVo.setCreateDate(userReserve.getCreateDate());
                messageVo.setStartTime(reserve.getStartTime());
                messageVo.setStadiumName(reserve.getStadium().getName());
                messageVo.setType(5);

                messageVo.setReserveType(reserve.getType());
                messageVo.setReserveId(reserve.getId());
                if (reserve.getType() == 0) {
                    messageVo.setMatchType(reserve.getMatchType());
                    messageVo.setJoinCount(reserve.getJoinCount());
                    messageVo.setLackNum(reserve.getMatchType() * 2 - reserve.getJoinCount());
                }
                messageVoList.add(messageVo);
            }
        }

        List<UserReserve> list3 = userReserveDao.findByUserId(userId);
        for (UserReserve userReserve : list3) {

//            reserve = reserveService.getById(userReserve.getReserveId());
            reserve = userReserve.getReserve();
            messageRecordList = messageRecordDao.findByMessageId(userReserve.getId(), 2);
            for (MessageRecord messageRecord : messageRecordList){
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }
            if (reserve.getStatus() == 1 || reserve.getStatus() == 2) {
                messageVo = new MessageVo();
                messageVo.setContent(DateUtils.chinaDayOfWeekAndAM(DateUtils.longToDate(reserve.getStartTime(), "yyyy-MM-dd HH:mm:ss")) + "," + reserve.getStadium().getName() + "约球了");
                messageVo.setCreateDate(userReserve.getCreateDate());
                messageVo.setStartTime(reserve.getStartTime());
                messageVo.setStadiumName(reserve.getStadium().getName());
                messageVo.setType(6);
                messageVo.setStatus(reserve.getStatus());

                messageVo.setReserveType(reserve.getType());
                messageVo.setReserveId(reserve.getId());
                if (reserve.getType() == 0) {
                    messageVo.setMatchType(reserve.getMatchType());
                    messageVo.setJoinCount(reserve.getJoinCount());
                    messageVo.setLackNum(reserve.getMatchType() * 2 - reserve.getJoinCount());
                }
                messageVoList.add(messageVo);
            }
        }

        if (null != messageVoList && messageVoList.size() > 1) {
            Collections.sort(messageVoList, new Compare());
        }

        return messageVoList;
    }

    @Override
    @Transactional
    public void doMessage(HttpServletResponse response, Long id, Integer status, Integer type) {
        if (type == 1) {
            MessageOrderBall messageOrderBall = messageOrderBallDao.findOne(id);
            messageOrderBall.setStatus(status);
            messageOrderBallDao.save(messageOrderBall);

        } else if (type == 2) {
            MessageAdd messageAdd = messageAddDao.findOne(id);
            if (messageAdd != null) {
                messageAdd.setStatus(status);
            }
            messageAddDao.save(messageAdd);

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(messageAdd.getUser().getId());
            messageRecord.setStatus(1);
            messageRecord.setMessageId(messageAdd.getId());
            messageRecord.setType(6);
            messageRecordDao.save(messageRecord);
        } else if (type == 3) {
            MessageJoin messageJoin = messageJoinDao.findOne(id);
            messageJoin.setStatus(status);
            messageJoinDao.save(messageJoin);

            if (status == 1) {
                TeamMember teamMember = new TeamMember();
                teamMember.setUserId(messageJoin.getUser().getId());
                teamMember.setTeamId(messageJoin.getTeam().getId());
                teamMemberDao.save(teamMember);
            }

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(messageJoin.getTeam().getId());
            messageRecord.setStatus(1);
            messageRecord.setMessageId(messageJoin.getId());
            messageRecord.setType(14);
            messageRecordDao.save(messageRecord);

        } else if (type == 4) {
            TeamRace teamRace = teamRaceDao.findOne(id);
            teamRace.setStatus(status);
            if (status == 1) {
                teamRace.getVisitingTeam().setBattleNum(teamRace.getVisitingTeam().getBattleNum() + 1);
                teamRace.getHomeTeam().setDeclareNum(teamRace.getHomeTeam().getDeclareNum() + 1);
            }
            teamRaceDao.save(teamRace);

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(teamRace.getVisitingTeam().getLeaderUser().getId());
            messageRecord.setStatus(1);
            messageRecord.setMessageId(teamRace.getId());
            if (status == 1) {
                messageRecord.setType(11);
            } else if (status == 2) {
                messageRecord.setType(12);
            }
            messageRecordDao.save(messageRecord);
        }
    }

    @Override
    @Transactional
    public List<MessageWatching> watching(HttpServletResponse response, Long userId) {
        List<MessageWatching> list = messageWatchingDao.findByToUserId(userId);
        List<MessageRecord> messageRecordList = null;
        for (MessageWatching messageWatching : list) {
            messageRecordList = messageRecordDao.findByMessageId(messageWatching.getId(), 3);
            for (MessageRecord messageRecord : messageRecordList){
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }
            messageWatching.setContent("user" + "约您看球");
            if (messageWatching.getType() == 0) {
                messageWatching.setWatchingRace(new WatchingRace());
            } else if (messageWatching.getType() == 1) {
                messageWatching.setBigRace(new BigRace());
                messageWatching.getBigRace().setStadium(new Stadium());

            }
            if (messageWatching.getWatchingRace() != null && StringUtils.isNotBlank(messageWatching.getWatchingRace().getAvater())) {
                messageWatching.getWatchingRace().setAvater(messageWatching.getWatchingRace().getAvater());
            }
        }

        return list;
    }

    @Override
    public List<PostComment> post(HttpServletResponse response, Long userId) {
        List<PostComment> list = postCommentDao.findByToUserId(userId);
        Post post = null;
        List<MessageRecord> messageRecordList = null;
        for (PostComment postComment : list) {
            messageRecordList = messageRecordDao.findByMessageId(postComment.getId(), 4);
            for (MessageRecord messageRecord : messageRecordList){
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }

            post = postDao.findOne(postComment.getPostId());
            postComment.setPost(post);
            postComment.setTitle("user" + "评论了您的" + "post");
        }

        return list;
    }

    @Override
    @Transactional
    public List<MessageVo> system(HttpServletResponse response, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<SystemMessage> list1 = systemMessageDao.findByToUserId(userId);
        List<MessageAdd> list2 = messageAddDao.findByUserId(userId);
        List<MessageAdd> list3 = messageAddDao.findByToUserId(userId);
        List<MessageVo> list = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        List<MessageRecord> messageRecordList = null;
        for (SystemMessage systemMessage : list1) {
            messageVo = new MessageVo();
            messageRecordList = messageRecordDao.findByMessageId(systemMessage.getId(), 5);
            for (MessageRecord messageRecord : messageRecordList){
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }

            messageVo.setId(systemMessage.getId());
            messageVo.setContent(systemMessage.getTitle());
            messageVo.setCreateDate(systemMessage.getCreateDate());
            messageVo.setType(1);
            list.add(messageVo);
        }
        for (MessageAdd messageAdd : list2) {
            if (messageAdd.getUser().getId() == userId && messageAdd.getStatus() != 0) {
                messageRecordList = messageRecordDao.findByMessageId(messageAdd.getId(), 6);
                for (MessageRecord messageRecord: messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setId(messageAdd.getId());
                messageVo.setToUserId(messageAdd.getToUser().getId());
                messageVo.setNickname(messageAdd.getToUser().getNickname());
                messageVo.setCreateDate(messageAdd.getUpdateDate());
                messageVo.setType(2);
                if (messageAdd.getStatus() == 1) {
                    messageVo.setContent("user" + "同意了您的好友请求");

                } else if (messageAdd.getStatus() == 2) {
                    messageVo.setContent("user" + "拒绝了您的好友请求");
                }
                list.add(messageVo);

            }
        }
        for (MessageAdd messageAdd : list3) {
            if (messageAdd.getToUser().getId() == userId && messageAdd.getStatus() == 0) {
                messageRecordList = messageRecordDao.findByMessageId(messageAdd.getId(), 7);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setId(messageAdd.getId());
                messageVo.setUserId(messageAdd.getUser().getId());
                messageVo.setNickname(messageAdd.getUser().getNickname());
                messageVo.setCreateDate(messageAdd.getCreateDate());
                messageVo.setType(3);
                messageVo.setContent("user" + "添加您为好友");
                list.add(messageVo);
            }
        }

        if (null != list && list.size() > 1) {
            Collections.sort(list, new Compare());
        }

        return list;
    }

    @Override
    @Transactional
    public List<MessageVo> team(HttpServletResponse response, Long userId) {
        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        MessageVo messageVo = null;
        List<MessageTeam> list = messageTeamDao.findByToUserId(userId);
        List<MessageRecord> messageRecordList = null;
        for (MessageTeam messageTeam : list) {

            messageRecordList = messageRecordDao.findByMessageId(messageTeam.getId(), 8);
            for (MessageRecord messageRecord : messageRecordList) {
                messageRecord.setStatus(1);
                messageRecordDao.save(messageRecord);
            }


            messageVo = new MessageVo();
            messageVo.setId(messageTeam.getId());
            messageVo.setUserId(messageTeam.getTeam().getLeaderUser().getId());
            messageVo.setNickname(messageTeam.getTeam().getLeaderUser().getNickname());
            messageVo.setTeamId(messageTeam.getTeam().getId());
            messageVo.setTeamName(messageTeam.getTeam().getName());
            messageVo.setType(7);
            messageVo.setCreateDate(messageTeam.getCreateDate());
            messageVo.setContent("user" + "邀请您加入" + "team");
            messageVoList.add(messageVo);
        }

        Team team = teamDao.findListByLeaderId(userId);
        if (team == null) {
            WebUtil.printApi(response, new Result(true));
        } else {

            List<MessageTeam> list1 = messageTeamDao.findByTeam(team);
            for (MessageTeam messageTeam : list1) {

                messageRecordList = messageRecordDao.findByMessageId(messageTeam.getId(), 9);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setToUserId(messageTeam.getUser().getId());
                messageVo.setNickname(messageTeam.getUser().getNickname());
                messageVo.setStatus(messageTeam.getStatus());
                if (messageTeam.getStatus() == 1) {
                    messageVo.setContent("user" + "同意加入您的球队");
                } else if (messageTeam.getStatus() == 2) {
                    messageVo.setContent("user" + "拒绝加入您的球队");
                }
                messageVo.setType(8);
                messageVo.setCreateDate(messageTeam.getUpdateDate());
                messageVoList.add(messageVo);
            }


            List<MessageJoin> list2 = messageJoinDao.findByTeam(team);
            for (MessageJoin messageJoin : list2) {
                messageRecordList = messageRecordDao.findByMessageId(messageJoin.getId(), 10);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setId(messageJoin.getId());
                messageVo.setUserId(messageJoin.getUser().getId());
                messageVo.setNickname(messageJoin.getUser().getNickname());
                messageVo.setContent("user" + "申请加入您的球队");
                messageVo.setType(9);
                messageVo.setCreateDate(messageJoin.getCreateDate());
                messageVoList.add(messageVo);
            }

            List<MessageJoin> list3 = messageJoinDao.findByUserId(userId);
            for (MessageJoin messageJoin : list3) {
                messageRecordList = messageRecordDao.findByMessageId(messageJoin.getId(), 14);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setId(messageJoin.getId());
                messageVo.setUserId(messageJoin.getUser().getId());
                messageVo.setNickname(messageJoin.getUser().getNickname());
                messageVo.setTeamId(messageJoin.getTeam().getId());
                messageVo.setTeamName(messageJoin.getTeam().getName());
                if (messageJoin.getStatus() == 1) {
                    messageVo.setContent("team" + "同意您加入球队");
                } else if (messageJoin.getStatus() == 2) {
                    messageVo.setContent("team" + "拒绝您加入球队");
                }
                messageVo.setType(13);
                messageVo.setCreateDate(messageJoin.getCreateDate());
                messageVoList.add(messageVo);
            }

        }

        List<Team> teamList = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberDao.findByUserId(userId);
        User user = null;
        for (TeamMember teamMember : teamMemberList) {
            teamList.add(teamDao.findOne(teamMember.getTeamId()));
        }

        for (Team team1 : teamList) {
            List<TeamRace> teamRaceList1 = teamRaceDao.findHomeId(team1.getId());
            for (TeamRace teamRace : teamRaceList1) {
                if (teamRace.getStatus() == 1) {
                    messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 11);
                    for (MessageRecord messageRecord : messageRecordList){
                        messageRecord.setStatus(1);
                        messageRecordDao.save(messageRecord);
                    }

                    messageVo = new MessageVo();
                    messageVo.setTeamId(teamRace.getVisitingTeam().getId());
                    messageVo.setTeamName(teamRace.getVisitingTeam().getName());
                    messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                    messageVo.setContent("您的队伍和" + "visitingTeam" + "约战成功");
                    messageVo.setType(10);
                    messageVo.setCreateDate(teamRace.getUpdateDate());
                    messageVoList.add(messageVo);
                }
            }
            List<TeamRace> teamRaceList2 = teamRaceDao.findVisitingId(team.getId());
            for (TeamRace teamRace : teamRaceList2) {
                if (teamRace.getStatus() == 1) {
                    messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 11);
                    for (MessageRecord messageRecord : messageRecordList){
                        messageRecord.setStatus(1);
                        messageRecordDao.save(messageRecord);
                    }

                    messageVo = new MessageVo();
                    messageVo.setTeamId(teamRace.getHomeTeam().getId());
                    messageVo.setTeamName(teamRace.getHomeTeam().getName());
                    messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                    messageVo.setContent("您的队伍和" + "homeTeam" + "约战成功");
                    messageVo.setType(10);
                    messageVo.setCreateDate(teamRace.getUpdateDate());
                    messageVoList.add(messageVo);
                }
            }
        }

        List<TeamRace> teamRaceList1 = teamRaceDao.findHomeId(team.getId());
        for (TeamRace teamRace : teamRaceList1) {
            if (teamRace.getStatus() == 2) {
                messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 12);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setTeamId(teamRace.getVisitingTeam().getId());
                messageVo.setTeamName(teamRace.getVisitingTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                messageVo.setContent("visitingTeam" + "拒绝了和您约战");
                messageVo.setType(11);
                messageVo.setCreateDate(teamRace.getUpdateDate());
                messageVo.setStadiumName(teamRace.getAddress());
                messageVoList.add(messageVo);
            }
            if (teamRace.getStatus() == 1) {
                messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 11);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setTeamId(teamRace.getVisitingTeam().getId());
                messageVo.setTeamName(teamRace.getVisitingTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getVisitingTeam().getAvater());
                messageVo.setContent("您的队伍和" + "visitingTeam" + "约战成功");
                messageVo.setType(10);
                messageVo.setCreateDate(teamRace.getUpdateDate());
                messageVoList.add(messageVo);
            }
        }
        List<TeamRace> teamRaceList2 = teamRaceDao.findVisitingId(team.getId());
        for (TeamRace teamRace : teamRaceList2) {
            if (teamRace.getStatus() == 0) {

                messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 13);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setId(teamRace.getId());
                messageVo.setTeamId(teamRace.getHomeTeam().getId());
                messageVo.setTeamName(teamRace.getHomeTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                messageVo.setContent("homeTeam" + "约您对战");
                messageVo.setType(12);
                messageVo.setStartTime(teamRace.getStartTime());
                messageVo.setCreateDate(teamRace.getCreateDate());
                messageVoList.add(messageVo);
            }
            if (teamRace.getStatus() == 1) {
                messageRecordList = messageRecordDao.findByMessageId(teamRace.getId(), 11);
                for (MessageRecord messageRecord : messageRecordList){
                    messageRecord.setStatus(1);
                    messageRecordDao.save(messageRecord);
                }

                messageVo = new MessageVo();
                messageVo.setTeamId(teamRace.getHomeTeam().getId());
                messageVo.setTeamName(teamRace.getHomeTeam().getName());
                messageVo.setTeamAvater(ConfigUtil.getString("upload.url") + teamRace.getHomeTeam().getAvater());
                messageVo.setContent("您的队伍和" + "homeTeam" + "约战成功");
                messageVo.setType(10);
                messageVo.setCreateDate(teamRace.getUpdateDate());
                messageVoList.add(messageVo);
            }
        }

        if (null != messageVoList && messageVoList.size() > 1) {
            Collections.sort(messageVoList, new Compare());
        }

        return messageVoList;

    }
}
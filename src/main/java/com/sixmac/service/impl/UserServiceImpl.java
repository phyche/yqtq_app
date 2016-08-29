package com.sixmac.service.impl;

import com.sixmac.core.Constant;
import com.sixmac.core.ErrorCode;
import com.sixmac.core.bean.Result;
import com.sixmac.dao.*;
import com.sixmac.entity.*;
import com.sixmac.service.*;
import com.sixmac.utils.ConfigUtil;
import com.sixmac.utils.DateUtils;
import com.sixmac.utils.WebUtil;
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
 * Created by Administrator on 2016/5/17 0017 下午 4:52.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserOtherDao userOtherDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private VipLevelDao vipLevelDao;

    @Autowired
    private MessageAddDao messageAddDao;

    @Autowired
    private MessageRecordDao messageRecordDao;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostImageService postImageService;

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostService postService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private GirlCommentService girlCommentService;

    @Autowired
    private GirlImageService girlImageService;

    @Autowired
    private UserVipService userVipService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private VipLevelService vipLevelService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysVipService sysVipService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private GirlService girlService;

    @Autowired
    private GirlUserService girlUserService;

    @Autowired
    private SiteService siteService;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page<User> find(int pageNum, int pageSize) {
        return userDao.findAll(new PageRequest(pageNum - 1, pageSize, Sort.Direction.DESC, "id"));
    }

    @Override
    public Page<User> find(int pageNum) {
        return find(pageNum, Constant.PAGE_DEF_SZIE);
    }

    @Override
    public User getById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public User deleteById(Long id) {
        User user = getById(id);
        userDao.delete(user);
        return user;
    }

    @Override
    public User create(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    @Override
    public User iTLogin(Integer type, String openId, String head, String nickname) {
        UserOther userOther = userDao.iTLogin(openId, type);
        User user = null;
        // 如果第三方用户信息不存在，则执行注册操作
        if (null == userOther) {
            // 此处执行注册操作
            user = new User();
            user.setNickname(nickname);
            user.setAvater(head);
            user.setCityId(cityDao.findOne(1L).getCityId());
            user.setCredibility(0);
            user.setStatus(0);
            user.setCreateDate(new Date().getTime());

            userDao.save(user);

            // 注册完毕后，添加该用户的第三方信息
            UserOther others = new UserOther();
            others.setType(type);
            others.setOpenId(openId);
            others.setUser(user);

            userOtherDao.save(others);
        } else {
            user = userOther.getUser();
        }

        return user;
    }

    @Override
    public void changeIntegral(User userInfo) {

        List<VipLevel> levelList = vipLevelDao.findAll();
        for (VipLevel vipLevel : levelList) {
            if (userInfo.getExperience() < vipLevel.getExperience()) {
                userInfo.setVipNum(vipLevel.getLevel() - 1);
                userDao.save(userInfo);
            }
        }
    }

    @Override
    @Transactional
    public void addFriend(HttpServletResponse response, Long userId, String mobile) {
        User user = userDao.findByMobile(mobile);
        if (user == null) {
            WebUtil.printJson(response, new Result(false).msg(ErrorCode.ERROR_CODE_0003));
            return;
        }else {
            MessageAdd messageAdd = new MessageAdd();
            messageAdd.setStatus(0);
            messageAdd.setUser(userDao.findOne(userId));
            messageAdd.setToUser(user);
            messageAddDao.save(messageAdd);

            MessageRecord messageRecord = new MessageRecord();
            messageRecord.setUserId(user.getId());
            messageRecord.setStatus(0);
            messageRecord.setMessageId(messageAdd.getId());
            messageRecord.setType(7);
            messageRecordDao.save(messageRecord);

            WebUtil.printApi(response, new Result(true));
        }
    }

    @Override
    public Map<String, Object> homePage(HttpServletResponse response, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.getById(userId);
        if (StringUtils.isNotBlank(user.getAvater())) {
            user.setAvater(ConfigUtil.getString("upload.url") + user.getAvater());
        }

        //加入的球队
        List<Team> teamList = new ArrayList<Team>();
        List<TeamMember> teamMemberList = teamMemberService.findByUserId(userId);
        User user1 = null;
        Team team = null;
        for (TeamMember teamMember : teamMemberList) {

            user1 = userService.getById(teamMember.getUserId());
            team = teamService.getById(teamMember.getTeamId());
            if (teamService.findListByLeaderId(userId) != null) {

                if (teamMember.getTeamId() != teamService.findListByLeaderId(userId).getId()) {
                    if (StringUtils.isNotBlank(user1.getAvater())) {
                        user1.setAvater(ConfigUtil.getString("upload.url") + user1.getAvater());
                    }
                    teamList.add(team);
                }
            } else {
                if (StringUtils.isNotBlank(user1.getAvater())) {
                    user1.setAvater(ConfigUtil.getString("upload.url") + user1.getAvater());
                }
                teamList.add(team);
            }
            team.setCount(team.getList().size() + 1);
        }
        map.put("teamList", teamList);

        //自己创建的球队
        Team myTeam = null;
        if (teamService.findListByLeaderId(userId) != null) {
            myTeam = teamService.findListByLeaderId(userId);
            if (StringUtils.isNotBlank(myTeam.getAvater())) {
                myTeam.setAvater(ConfigUtil.getString("upload.url") + myTeam.getAvater());
            }
            myTeam.setCount(myTeam.getList().size() + 1);
        }
        map.put("myTeam", myTeam);

        UserVip userVip = userVipService.findByUserId(userId);
        if (userVip != null && user.getVipNum() != 0) {
            user.setEndDays((int) ((userVip.getEndDate() - System.currentTimeMillis())/1000/3600/24));
        }

        map.put("user", user);

        return map;
    }

    @Override
    public Map<String, Object> operation(HttpServletResponse response, Long userId, Integer num) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        Integer level = 0;
        String endDate = null;
        String status = null;
        UserVip userVip = userVipService.findByUserId(userId);
        Double price = 0.0;

        if (userVip == null || userVip.getEndDate() < System.currentTimeMillis()) {
            level = 0;
            //status = "您不是会员";
            //userVip.setStatus("您不是会员");
            if (num == 1) {

                //endDate = DateUtils.longToString(System.currentTimeMillis() + 1000 * 3600 * 365, "yyyy年-MM月-dd日 HH:mm:ss");
                price = sysVipService.getById(1l).getPrice();
            } else if (num == 2) {
                //endDate = DateUtils.longToString(System.currentTimeMillis() + 2 * 1000 * 3600 * 365, "yyyy年-MM月-dd日 HH:mm:ss");
                price = sysVipService.getById(1l).getPrice() * 2;
            } else if (num == 3) {
                //endDate = DateUtils.longToString(System.currentTimeMillis() + 3 * 1000 * 3600 * 365, "yyyy年-MM月-dd日 HH:mm:ss");
                price = sysVipService.getById(1l).getPrice() * 3;
            }


        } else {
            level = userService.getById(userId).getVipNum();
            status = DateUtils.longToString(userVip.getEndDate(), "yyyy年-MM月-dd日");
            //userVip.setStatus("会员到期时间" + DateUtils.longToString(userVip.getEndDate(),"yyyy年-MM月-dd日 HH:mm:ss"));
            if (num == 1) {
                /*endDate = DateUtils.longToString(userVip.getEndDate() + 1000 * 3600 * 365 * 24, "yyyy年-MM月-dd日 HH:mm:ss");
                Date secord = DateUtils.dateAddYear(DateUtils.longToDate(userVip.getEndDate(),"yyyy-MM-dd HH:mm:ss"),1);
                System.out.println("续费第二年日期:" + DateUtils.dateToStringWithFormat(secord,"yyyy-MM-dd HH:mm:ss"));*/

                price = sysVipService.getById(level.longValue()).getPrice() * vipLevelService.findBylevel(level).getPreferente();
            } else if (num == 2) {
                //endDate = DateUtils.longToString(userVip.getEndDate() + 2 * 1000 * 3600 * 365, "yyyy年-MM月-dd日 HH:mm:ss");
                price = sysVipService.getById(1l).getPrice() * vipLevelService.findBylevel(level).getPreferente() * 2;
            } else if (num == 3) {
                //endDate = DateUtils.longToString(userVip.getEndDate() + 3 * 1000 * 3600 * 365, "yyyy年-MM月-dd日 HH:mm:ss");
                price = sysVipService.getById(1l).getPrice() * vipLevelService.findBylevel(level).getPreferente() * 3;
            }
        }

        Integer experience = 0;
        Integer leftExperience = 0;
        if (level != 0) {
            VipLevel vipLevel = vipLevelService.findBylevel(level);
            experience = vipLevel.getExperience();
            leftExperience = vipLevelService.findBylevel(level + 1).getExperience() - vipLevel.getExperience();
        }else {
            leftExperience = vipLevelService.findBylevel(level + 1).getExperience();
        }

        map.put("level", level);
        //map.put("endDate", endDate);
        map.put("price", price);
        map.put("status", status);
        map.put("experience", experience);
        map.put("leftExperience", leftExperience);

        return map;
    }

}
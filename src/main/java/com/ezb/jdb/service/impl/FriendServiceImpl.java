package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.FriendDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IFriendService;
import com.ezb.jdb.tool.JdbGisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 通讯录
 * author : liufeng
 * create time:2015/8/12 10:50
 */
@Service
public class FriendServiceImpl implements IFriendService {

    @Resource
    private FriendDao friendDao;

    @Resource
    private UserDao userDao;

    public PageResult<Friend> queryFriend(PageResult<Friend> pageResult, String phone,
                                          String queryWords, Integer state) {

        return friendDao.queryFriend(pageResult, phone, queryWords, state);
    }

    public String confireFriend(Integer id) {
        return friendDao.confireFriend(id);
    }

    public String addFriend(String phone1, String phone2) {

        if (friendDao.queryBy2Phone(phone1, phone2) != null) {
            return ResponseState.FRIEND_ADDED;
        }

        User user1 = userDao.queryByPhone(phone1);
        User user2 = userDao.queryByPhone(phone2);

        if (user1 == null || user2 == null) {
            return ResponseState.INVALID_PHONE;
        }

        Friend friend = new Friend();
        friend.setUser(user1);
        friend.setFriend(user2);
        friend.setApplyDate(new Date());
        friend.setState(false);

        friend.setDistance(
                JdbGisUtil.getDistance(
                        user1.getAlumnus().getLat(),
                        user1.getAlumnus().getLng(),
                        user2.getAlumnus().getLat(),
                        user2.getAlumnus().getLng()
                )
        );

        friendDao.add(friend);
        return ResponseState.SUCCESS;
    }

    public String confireFriend(String phone1, String phone2) {
        Friend friend = friendDao.queryBy2Phone(phone1, phone2);
        if (null == friend) {
            return ResponseState.FRIEND_APPLY_FRIST;
        } else {
            friend.setState(true);
            friend.setConfireDate(new Date());
            friendDao.update(friend);
            return ResponseState.SUCCESS;
        }
    }

    public String releaseFriend(String phone1, String phone2) {
        Friend friend = friendDao.queryBy2Phone(phone1, phone2);
        if (null == friend) {
            return ResponseState.FRIEND_APPLY_FRIST;
        } else {
            if (friendDao.release(friend) > 0) {
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.FAIL;
            }
        }
    }
}

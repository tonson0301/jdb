package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.Constants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.controller.mobile.FriendController;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * author : liufeng
 * create time:2015/8/13 15:18
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class FriendControllerTest {

    @Resource
    private FriendController friendController;

    @Test
    @Rollback(false)
    public void queryFriend(){
        PageResult<Friend> pageResult = new PageResult<Friend>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryFriend(pageResult, "11111193", null, 1));
        log.info(friendController.queryFriend(pageResult, "11111193", null, 0));

        log.info(friendController.queryFriend(pageResult, "11111196",null, 1));
        log.info(friendController.queryFriend(pageResult, "11111196","realname64", 1));
        log.info(friendController.queryFriend(pageResult, "11111196","realname44", 1));
    }

    @Test
    @Rollback(false)
    public void confireFriend(){
        log.info(friendController.confireFriend(17));
    }

    @Test
    @Rollback(false)
    public void confirFriend2Phone(){
        log.info(friendController.confireFriend2Phone("11111143", "11111140"));
    }

    @Test
    @Rollback(false)
    public void queryUnFriend(){

        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryUnFriend(pageResult, "11111196", null, null));


        Alumnus alumnus = new Alumnus();
        alumnus.setRealName("realname1");
        alumnus.setSex(1);
        alumnus.setSchool("school1");
        alumnus.setDepartment("department1");
        alumnus.setGrade("grade1");
        log.info(friendController.queryUnFriend(pageResult, "11111196", alumnus, null));

        log.info(friendController.queryUnFriend(pageResult, "11111196", null, Constants.ORDERBY_USERNAME));
        log.info(friendController.queryUnFriend(pageResult, "11111196", null, Constants.ORDERBY_LOCATION));
    }

    @Test
    @Rollback(false)
    public void queryAllFriend(){

        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryUnFriend(pageResult, "11111196", null, null));


        Alumnus alumnus = new Alumnus();
        alumnus.setRealName("realname1");
        alumnus.setSex(1);
        alumnus.setSchool("school1");
        alumnus.setDepartment("department1");
        alumnus.setGrade("grade1");
        log.info(friendController.queryAllFriend(pageResult, "11111196", alumnus, null));

        log.info(friendController.queryAllFriend(pageResult, "11111196", null, Constants.ORDERBY_USERNAME));
        log.info(friendController.queryAllFriend(pageResult, "11111196", null, Constants.ORDERBY_LOCATION));
    }

    @Test
    @Rollback(false)
    public void addFriend(){
        String phone1 = "1111111";
        String phone2 = "1111112";
        log.info(friendController.addFriend(phone1, phone2));

        String phone3 = "2323232";
        String phone4 = "232323";
        log.info(friendController.addFriend(phone3, phone4));
    }

    @Test
    public void queryNearUsers(){
        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryNearUsers(pageResult, "11111112"));
    }

}

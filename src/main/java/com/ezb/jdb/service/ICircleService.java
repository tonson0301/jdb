package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.JoinUserCircle;

import javax.servlet.http.HttpServletRequest;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 16:05
 */
public interface ICircleService {


    String queryCircles(PageResult<Circle> pageResult, String phone, String queryWords);

    String queryMyCircles(PageResult<Circle> pageResult, String phone, String queryWords);

    String join(String phone, Integer id);

    PageResult<Circle> query(PageResult<Circle> pageResult, Integer id, String title, String realName, String state, String startTime, String endTime);

    String save(HttpServletRequest request,String phone, Circle circle);

    String offline(Integer id);

    String viewCircle(String phone,Integer id);

    Circle queryById(Integer id);

    String saveNickName(Integer id,String nickName);
}

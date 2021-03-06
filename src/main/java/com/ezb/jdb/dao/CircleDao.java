package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Circle;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 16:04
 */
@Repository
public class CircleDao extends BaseDao<Circle> {

    public PageResult<Circle> queryCircles(PageResult<Circle> pageResult, String queryWords) {
        String hql = "from Circle o where o.state=1";
        if (null != queryWords) {
            if (!StringUtils.isEmpty(queryWords.trim())) {
                hql += " and o.title like ''%{0}%'' ";
            }
        }
        hql += " order by o.createTime desc";

        return query(MessageFormat.format(hql, queryWords), pageResult);
    }

    public PageResult<Circle> queryMyCircles(PageResult<Circle> pageResult, String phone, String queryWords) {

        String appendHql = " from Circle c,User u " +
                "where u.id in elements(c.members) and u.username=''{0}''" +
                " and c.state=1";
        appendHql = MessageFormat.format(appendHql, phone, queryWords);
        String hql = "select c " + appendHql;
        String cql = "select count(*) " + appendHql;

        if (null != queryWords) {
            if (!StringUtils.isEmpty(queryWords.trim())) {
                hql += " and c.title like ''%{1}%'' ";
            }
        }
        hql += " order by c.createTime desc";
        return query(hql, cql, pageResult);
    }

    public PageResult<Circle> query(PageResult<Circle> pageResult, Integer id,
                                    String title, String realName, String state, String startTime, String endTime) {

        int index = 0;
        List<String> paramList = new ArrayList<String>();

        String hql = "from Circle o where 1=1";

        if (null != id) {
            hql += " and o.id=''{" + index++ + "}''";
            paramList.add(id + "");
        }

        if (!StringUtils.isEmpty(title)) {
            hql += " and o.title like ''%{" + index++ + "}%''";
            paramList.add(title);
        }

        if (!StringUtils.isEmpty(realName)) {
            hql += " and o.createUser.realName like ''%{" + index++ + "}%''";
            paramList.add(realName);
        }

        if (!StringUtils.isEmpty(state)) {
            hql += " and o.state=''{" + index++ + "}''";
            paramList.add(state);
        }

        if (!StringUtils.isEmpty(startTime)) {
            hql += " and o.createTime >= ''{" + index++ + "}''";
            paramList.add(startTime);
        }

        if (!StringUtils.isEmpty(endTime)) {
            hql += " and o.createTime <= ''{" + index++ + "}''";
            paramList.add(endTime);
        }

        hql += " order by o.createTime desc";

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }
}

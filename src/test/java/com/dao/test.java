package com.dao;

import com.example.Demo;
import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

public class test {


    @Test
    public void testSql(){

        Demo demo=new Demo();
        User user=new User();
        SqlSession sqlSession=demo.getSqlSession();
        System.out.println(demo.getTimeStringDay());
        user.setQq(2387020215L);
        user.setWeek("222");
        user.setPeriod_id("20190111");

        user.setQq(2387020215L);
        sqlSession.commit();
        List<User> list=  sqlSession.selectList("selectAll", user);

        for(User u:list){
            System.out.println(u.getQq()+" 次数："+u.getCount()+"\n请假时间："+u.getPeriod_id()+"-"+u.getWeek()+"");
        }
        int count=1;
         sqlSession.selectOne("selectByqqAndPeriod_id",user);
        System.out.println(sqlSession.selectOne("selectByqqAndPeriod_id",user));
        sqlSession.close();
    }

}

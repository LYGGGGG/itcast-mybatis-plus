package cn.itcast.mp.simple;

/*
@author YG
@create 2022/12/31   15:22
*/

import cn.itcast.mp.simple.mapper.UserMapper;
import cn.itcast.mp.simple.pojo.User;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatisPlus {

    @Test
    public void testUserList() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSessionFactory sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = mapper.findAll();
        List<User> users = mapper.selectList(null);
        users.forEach(user -> System.out.println(user));
    }
}

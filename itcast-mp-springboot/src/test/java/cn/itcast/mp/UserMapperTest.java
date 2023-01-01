package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
@author YG
@create 2022/12/31   21:29
*/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);
       /* Integer integer = userMapper.selectCount(wrapper);
        System.out.println("count = "+integer);*/
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(20);
        user.setEmail("test@itcast.cn");
        user.setName("曹操");
        user.setUserName("caocao");
        user.setPassword("123456");
        int result = userMapper.insert(user);
        System.out.println("受影响的行数为：" + result);
        System.out.println("id自动获取：" + user.getId());
    }

    @Test
    public void testDelete() {
        List<Long> list = Arrays.asList(8l, 9l, 10l);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "刘备");
        map.put("age", 23);
        userMapper.deleteByMap(map);
    }

    @Test
    public void testDeleteByWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "曹操");
        userMapper.delete(wrapper);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(23);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("age", 22);

        userMapper.update(user, wrapper);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(6L);
        user.setAge(22);
        int result = userMapper.updateById(user);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 1);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "test");
        IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + userIPage.getTotal());
        System.out.println("数据总页数：" + userIPage.getPages());
        System.out.println("当前页：" + userIPage.getCurrent());
        List<User> records = userIPage.getRecords();
        records.forEach(user -> System.out.println(user));

    }
}

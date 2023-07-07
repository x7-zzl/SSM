package Mybatis;

import mapper.BrandMapper;
import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Brand;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemoUsers {
    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
//        String resource = "mybatis-zzl.config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = MybatisUtil.sqlSession();

        //3. 执行sql
        //List<User> users = sqlSession.selectList("test.selectAll");
        //3.1 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println(users);


        User users1= userMapper.selectById(1);
        System.out.println(users1);


        User users2=userMapper.byUserPwd("1","1");
        System.out.println(users2);


//        userMapper.deleteById("6");

        //4. 释放资源
        sqlSession.close();
    }
}

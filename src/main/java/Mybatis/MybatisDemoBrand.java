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

public class MybatisDemoBrand {
    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. 执行sql
        //3.1 获取UserMapper接口的代理对象

        BrandMapper brandMapper1 = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper1.selectAll();
        System.out.println(brands);


        //集合输出显示
        List<Brand> brands2 = brandMapper1.selectCollections(0,"三只松鼠股份有限公司","三只松鼠");
        System.out.println(brands2);

        //增删改查
        Brand brand = new Brand(6,"1","2",3,"4",5);
        int i=brandMapper1.add(brand);
        System.out.println(i);
        sqlSession.commit();



        //4. 释放资源
        sqlSession.close();
    }
}

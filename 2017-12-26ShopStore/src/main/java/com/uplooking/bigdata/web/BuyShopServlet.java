package com.uplooking.bigdata.web;

import com.uplooking.bigdata.biz.UserBiz;
import com.uplooking.bigdata.vo.Province;
import com.uplooking.bigdata.vo.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ Title: 2017-12-26ShopStore
 * @ Package:com.uplooking.bigdata.web
 * @ description: (describe the file in one sentence)
 * @ This program is protected by copyright laws
 * @ author zhangjie
 * @ date 2017/12/28
 * @ version V1.0
 */
public class BuyShopServlet extends HttpServlet{

     private     UserBiz userBiz=new UserBiz();

    /**
     * 购买商品Servlet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        User user=(User) req.getSession().getAttribute("user");
//        String userName = user.getUsername();
//        System.out.println("name"+userName);
        try {
        String username= (String) req.getSession().getAttribute("username");
            //System.out.println("name"+username);
            Province province = userBiz.findProviceByusername(username);
           //System.out.println(province.getName());
            String provinceName = province.getName();
            //写给kafka 创建kafka的消息生产者的实例
            Properties properties=new Properties();
            properties.load(BuyShopServlet.class.getClassLoader().getResourceAsStream("producer.properties"));
            KafkaProducer<String,String> kafkaProducer=new KafkaProducer<String, String>(properties);
            //指定的topic如果存在则使用,如果不存在则创建
            ProducerRecord<String,String> producerRecord=new ProducerRecord<String, String>("spark",provinceName);
            kafkaProducer.send(producerRecord);
            System.out.println("-----------发送消息成功--------------");
            kafkaProducer.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
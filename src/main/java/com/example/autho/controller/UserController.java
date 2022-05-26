package com.example.autho.controller;

import com.example.autho.annotation.*;
import com.example.autho.aop.MeTransactional;
import com.example.autho.entity.Apple;
import com.example.autho.entity.User;
import com.example.autho.service.UserService;
import com.example.autho.utils.FruitInfoUtil;
import com.example.autho.utils.MapPerson;
import com.example.autho.utils.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
//@Slf4j
@RequestMapping("user")
public class UserController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
//    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("findById")
    @ResponseBody
    @MyLog(requestUrl = "111",value = "kkk")
    public Object findById() {
        User byId = userService.findById(6);
        return byId;
    }

    @KthLog("这是自定义日志")
    @RequestMapping("log")
    @ResponseBody
    public Object log() {
        log.info("enum变量"+ MapPerson.man2woman.getK());
        System.out.println(Person.valueOf(1));
        FruitInfoUtil.getFruitInfo(Apple.class);
        return userService.findById(7);
    }

    @MyLog(requestUrl = "/index请求")
    @RequestMapping(path = "mylog",method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return "index";
    }

    @RequestMapping("my")
    @ResponseBody
    @MeTransactional
    public Object my(Integer num) {
        int i = 1/num;
        return "";
    }

    @LogAnnotation(desc="我的注解")
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @HeaderChecker(headerNames = {"name"})
    @RequestMapping("head")
    @ResponseBody
    public Object checkHead() {
        return "0";
    }
}

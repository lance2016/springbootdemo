package com.lance.controller;

import com.lance.dao.SysUserRepository;
import com.lance.dao.TestRepository;
import com.lance.entity.Msg;
import com.lance.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lance on 2018/6/25.
 * 自定义controller
 */
@Controller
public class UserController {

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    TestRepository testRepository;
    @RequestMapping("/mmp")
    public String home(Model model){
        Msg msg = new Msg("mmp", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);

        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "index";
    }

    @RequestMapping("/query")
    public String test(){
        return "query";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @ResponseBody
    public TestEntity getUser(){
        TestEntity testEntity=new TestEntity();
        testEntity = testRepository.getByName("lance");
        System.out.println("跑出来了"+testEntity);
        return testEntity;
    }


    @RequestMapping(value = "getAllUsers",method = RequestMethod.POST)
    @ResponseBody
    public List<TestEntity> getAllUsers(){
        return testRepository.findAll();
    }


    @RequestMapping(value = "getAllUsersSortedAsc",method = RequestMethod.POST)
    @ResponseBody
    public List<TestEntity> getAllUsersSortedAsc(){
        Sort sort=new Sort(Sort.Direction.ASC,"name");
        return testRepository.findAll(sort);
    }

    @RequestMapping(value = "getAllUsersSortedDesc",method = RequestMethod.POST)
    @ResponseBody
    public List<TestEntity> getAllUsersSortedDesc(){
        Sort sort=new Sort(Sort.Direction.DESC,"name");
        return testRepository.findAll(sort);
    }

    @RequestMapping(value = "getFirst",method = RequestMethod.POST)
    @ResponseBody
    public List<TestEntity> getFirst(){
        Sort sort=new Sort(Sort.Direction.DESC,"name");
        return testRepository.findFirst4By(sort);
    }


    @RequestMapping(value = "getUserByNQ",method = RequestMethod.POST)
    @ResponseBody
    public TestEntity getUserByNamedQuery(@RequestParam String name)
    {
        return testRepository.getUserByNQ(name);
    }

    @RequestMapping(value = "getUserByQuery",method = RequestMethod.POST)
    @ResponseBody
    public TestEntity getUserByQuery(@RequestParam String name)
    {
        return testRepository.getUserByQuery(name);
    }

    @RequestMapping(value = "getUserByQuery2",method = RequestMethod.POST)
    @ResponseBody
    public TestEntity getUserByQuery2(@RequestParam String name)
    {
        return testRepository.getUserByQuery2(name);
    }


    @RequestMapping(value = "getUserByQueryUpdate",method = RequestMethod.POST)
    @ResponseBody
    public String getUserByQuery3(@RequestParam String name,@RequestParam String oldname)
    {
        System.out.println("________________"+name+" "+oldname);
        int n= testRepository.setName(name,oldname);
        return "{\"num\":\""+n+"\"}";
    }

    //分页
    @RequestMapping(value = "getUserByPage",method = RequestMethod.POST)
    @ResponseBody
    public Page<TestEntity> getUserByPage(@RequestParam int pagenum,@RequestParam int pagesize)
    {
        Page<TestEntity> testEntities = testRepository.findAll(new PageRequest(pagenum,pagesize));
        return testEntities;
    }

    //count 计算全部记录
    @RequestMapping(value = "count",method = RequestMethod.POST)
    @ResponseBody
    public String count(){
        long size=testRepository.count();
        return "{\"size\":\""+size+"\"}";
    }

    //保存用户
    @RequestMapping(value = "saveUser",method = RequestMethod.POST)
    @ResponseBody
    public TestEntity saveUser(@RequestParam String name,@RequestParam String nickname)
    {
       TestEntity testEntity=new TestEntity(name,nickname);
       return testRepository.save(testEntity);
    }

    //删除用户
    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public String  deleteUser(@RequestParam String name)
    {
        String msg="";
        int b=testRepository.deleteByName(name);
        msg="{\"msg\":\""+b+"\"}";
        System.out.println("msg is "+ msg);
        return msg;
    }

    //删除用户
    @RequestMapping(value = "deleteUserByNames",method = RequestMethod.POST)
    @ResponseBody
    public String  deleteUserByNames(@RequestParam List<String> names)
    {
        String msg="";
        int b=testRepository.deleteByNames(names);
        msg="{\"msg\":\""+b+"\"}";
        return msg;
    }

}

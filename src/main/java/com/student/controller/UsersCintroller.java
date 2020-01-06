package com.student.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.student.entity.Detep;
import com.student.entity.PageBean;
import com.student.entity.Users;
import com.student.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/con/")
public class UsersCintroller {
    @Autowired
    private UserService service;


    @RequestMapping("batchDel")
    @ResponseBody
    public int batchDel(Integer ids[]){
        int i =service.deleteBatch(ids);
        return i;
    }

    @RequestMapping("show")
    public ModelAndView showUser(ModelAndView mav, PageBean page, @RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "3") Integer size) {
        PageHelper.startPage(currPage, size);
        List<Users> list = service.selectPageAndLike(page);
        PageInfo<Users> pageInfo = new PageInfo<Users>(list);
        List<Detep> deteps = service.selectDtepAll();
        mav.addObject("list", list);
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("deteps", deteps);
        mav.addObject("page", page);
        System.out.println(page.getEndBirth());
        mav.setViewName("/jsp/shows");
        return mav;
    }

    @RequestMapping("add1")
    public String addUsers(Model model, HttpSession session) {
        List<Detep> deteps = service.selectDtepAll();
        model.addAttribute("deteps", deteps);
        return "/jsp/add";
    }

    @RequestMapping("add2")
    public ModelAndView addUsers2(Users users, ModelAndView mav) {
        int i = service.selectAddUsers(users);
        mav.addObject("msg", i);
        mav.setViewName("/jsp/shows");
        return mav;
    }

    @RequestMapping("del")
    public ModelAndView delUsers(Integer id, ModelAndView mav) {
        int i = service.delUsers(id);
        mav.addObject("msg1", i);
        mav.setViewName("/jsp/shows");
        return mav;
    }

    @RequestMapping("update1")
    public String update1(Model model, Integer id) {
        List<Detep> deteps = service.selectDtepAll();
        model.addAttribute("deteps", deteps);
        Users users = service.selectOneUsers(id);
        model.addAttribute("user", users);
        return "/jsp/update";
    }

    @RequestMapping("update2")
    public ModelAndView update2(Users users, ModelAndView mav) {
        int i = service.updateOne(users);
        mav.addObject("msg2", i);
        mav.setViewName("/jsp/shows");
        return mav;
    }

    @RequestMapping("upload")
    @ResponseBody
    public String imgUpload(@RequestParam(value = "file") MultipartFile file) {
        try {
            String path = "E:/image/";
            String filename = file.getOriginalFilename();
            filename = System.currentTimeMillis() + UUID.randomUUID().toString() + filename;
            File file1 = new File(path + filename);
            file.transferTo(file1);
            return "http://localhost:8088/"+filename;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @RequestMapping("downImg")
    public void downImg(String urlImg, HttpServletResponse response) {
        try {
            //1.找到图片的位置，构建一个输入流,读到程序中
            String path = urlImg;
            System.out.println(path);
            String imgName = path.substring(path.lastIndexOf("/")+1);
            System.out.println(imgName);
            FileInputStream is = new FileInputStream("E:\\image/"+imgName);
            //2.设置响应头
            response.setHeader("content-disposition", "attachment;filename="+imgName);
            //3.响应输出流，响应给浏览器
            ServletOutputStream os = response.getOutputStream();
            //输入流和输出流对接
            IOUtils.copy(is, os);

        } catch (Exception e) {
            e.printStackTrace();
            //重定向去首页
            try {
                response.sendRedirect("/");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }



}

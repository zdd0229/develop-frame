package com.z.plat.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @autho 孔垂云
 * @date 2017/7/6.
 */
@RequestMapping("/")
@Controller
public class SysIndexController {
    /**
     * 进入用户管理界面
     *
     * @return
     */
    /*@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, SysUserSearchVO sysUserSearchVO) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plat/index");// 跳转至Index页面
        return mv;
    }*/
}

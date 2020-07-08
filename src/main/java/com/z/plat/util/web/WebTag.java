package com.z.plat.util.web;

import com.z.plat.core.spring.SpringContextHolder;
import com.z.plat.sys.service.SysRoleService;
import com.z.plat.util.session.SessionUtil;
import com.z.plat.util.session.UserSession;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面标签，用于直接处理页面数据，展示使用
 *
 * @author 孔垂云
 * @date 2017-05-23
 */
public class WebTag {

    /**
     * 获取用户状态，系统管理-用户管理用到
     *
     * @param user_status
     * @return
     */
    public static String getUserStatus(Integer user_status) {
        if (user_status == 1)
            return "<span class=\"label label-success arrowed\">正常</span>";
        else if (user_status == 2)
            return "<span class=\"label label-warning arrowed\">已锁定</span>";
        else
            return "";
    }

    /**
     * 生成菜单
     *
     * @param request 请求
     * @return 菜单
     */
    public static String createMenu(HttpServletRequest request) {
        UserSession userSession = SessionUtil.getUserSession(request);
        SysRoleService sysRoleService = SpringContextHolder.getBean("sysRoleService");
        return sysRoleService.createMenuStr(userSession.getRoleId());
    }

}

package com.z.plat.sys.service;

import com.z.plat.sys.dao.SysResourceDao;
import com.z.plat.sys.dao.SysUserDao;
import com.z.plat.sys.model.SysUser;
import com.z.plat.sys.vo.SysUserSearchVO;
import com.z.plat.util.code.RandomCodeUtil;
import com.z.plat.util.encrypt.Md5SaltUtil;
import com.z.plat.util.model.ComboboxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysResourceDao sysResourceDao;

    //用户新增，判断用户是否存在 返回2 ：存在，返回1：操作成功
    public int add(SysUser sysUser){

        SysUser exist = sysUserDao.getByUsername(sysUser.getUsername());
        if(exist!=null){
            return 2;
        }else {
            String password="123456";
            String randomcode = RandomCodeUtil.createRandomCode(6);
            Md5SaltUtil md5SaltUtil=new Md5SaltUtil(randomcode);
            String md5Pass = md5SaltUtil.encode(password);
            sysUser.setPassword(md5Pass);
            sysUser.setRandomcode(randomcode);
            return sysUserDao.add(sysUser);
        }
    }

    public int update(SysUser sysUser) {
        return sysUserDao.update(sysUser);
    }

    public int delete(int id) {
        return sysUserDao.delete(id);
    }

    public SysUser get(int id) {
        return sysUserDao.get(id);
    }

    /**
     * 根据username获取用户
     *
     * @param username
     * @return
     */
    public SysUser getByUsername(String username) {
        return sysUserDao.getByUsername(username);
    }

    /**
     * 用户列表
     *
     * @param sysUserSearchVO
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        List<SysUser> list = sysUserDao.list(sysUserSearchVO);
        return list;
    }

    /**
     * 用户列表总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        return sysUserDao.count(sysUserSearchVO);
    }

    //修改密码
    public int updatePass(int id,String oldPass,String newPass){

        SysUser getUser=sysUserDao.get(id);

        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(getUser.getRandomcode());
        if(md5SaltUtil.isPasswordValid(getUser.getPassword(),oldPass)){
            String newRandomcode = RandomCodeUtil.createRandomCode(6);
            Md5SaltUtil md5SaltUtil12 = new Md5SaltUtil(newRandomcode);
            String md5Pass = md5SaltUtil12.encode(newPass);
            return sysUserDao.updatePass(getUser.getId(), md5Pass, newRandomcode);
        }else {
            return 2;
        }

    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    public int saveResetPass(int id) {
        int flag = 0;
        String password = "123456";
        String randomcode = RandomCodeUtil.createRandomCode(6);
        Md5SaltUtil md5SaltUtil = new Md5SaltUtil(randomcode);
        String md5Pass = md5SaltUtil.encode(password);
        flag = sysUserDao.updatePass(id, md5Pass, randomcode);
        return flag;
    }

    /**
     * 修改状态，锁定解锁用户时使用
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        return sysUserDao.updateStatus(id, status);
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        return sysUserDao.listAllUser();
    }

    /**
     * 用户列表
     *
     * @return
     */
    public List<SysUser> listAll() {
        List<SysUser> list = sysUserDao.listAll();
        return list;
    }
}

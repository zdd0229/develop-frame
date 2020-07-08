package com.z.plat.sys.dao;

import com.z.plat.core.dao.BaseDao;
import com.z.plat.sys.model.SysLog;
import com.z.plat.sys.vo.SysLogSearchVO;
import com.z.plat.util.page.PageUtil;
import com.z.plat.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统日志dao
 *
 * @author 孔垂云
 * @date 2017-06-13
 */
@Repository
public class SysLogDao extends BaseDao<SysLog, SysLogSearchVO> {

    public int add(SysLog sysLog) {
        String sql = "insert into t_sys_log(user_id,opera_date,opera_ip,module_name,opera_name,opera_url,opera_params)"
                + " values(:userId,now(),:operaIp,:moduleName,:operaName,:operaUrl,:operaParams)";
        return insert(sql, sysLog);
    }

    /**
     * 查询
     *
     * @return
     */
    public List<SysLog> list(SysLogSearchVO sysLogSearchVO) {
        String sql = "select l.*,u.realname realname  from t_sys_log l,t_sys_user u where l.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        sql = PageUtil.createMysqlPageSql(sql, sysLogSearchVO.getPageIndex());
        return list(sql, sysLogSearchVO);
    }

    /**
     * 查询所有，不分页
     *
     * @param sysLogSearchVO
     * @return
     */
    public List<SysLog> listAll(SysLogSearchVO sysLogSearchVO) {
        String sql = "select t.id,t.user_id,t.opera_date,t.operea_ip,t.module_name,t.opera_date,t.opera_url" +
                ",u.code user_code,u.realname realname  from t_sys_log t,t_sys_user u where t.user_id=u.id  ";
        sql += createSearchSql(sysLogSearchVO);
        sql += " order by opera_date desc";
        List<SysLog> list = list(sql, sysLogSearchVO);
        return list;
    }

    /**
     * 查询
     *
     * @param sysLogSearchVO
     * @return
     */
    public int count(SysLogSearchVO sysLogSearchVO) {
        String sql = "select count(*) from t_sys_log where 1=1 ";
        sql += createSearchSql(sysLogSearchVO);
        return count(sql, sysLogSearchVO);
    }

    private String createSearchSql(SysLogSearchVO sysLogSearchVO) {
        String sql = "";
        if (sysLogSearchVO.getUserId() != null) {
            sql += " and user_id=:userId";
        } else {
            sql += " and user_id=0";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getStartDate())) {
            sql += " and date(opera_date)>=:startDate";
        }
        if (StringUtil.isNotNullOrEmpty(sysLogSearchVO.getEndDate())) {
            sql += " and date(opera_date)<=:endDate";
        }
        return sql;
    }
}

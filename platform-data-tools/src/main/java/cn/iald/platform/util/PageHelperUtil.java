package cn.iald.platform.util;

import cn.iald.platform.base.dto.PageDTO;
import cn.iald.platform.constant.PageConstant;
import com.github.pagehelper.PageHelper;

/**
 * PageHelper工具类
 *
 * @author wangyc
 * @date 2021/06/10 12:25:09
 **/
public class PageHelperUtil {

    /**
     * 补充PageHelper分页信息
     *
     * @param pageDTO
     */
    public static void editPageHelper(PageDTO pageDTO) {
        if (pageDTO.getPageNum() == null) {
            pageDTO.setPageNum(PageConstant.PAGE_NUM);
        }
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(PageConstant.PAGE_SIZE);
        }
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
    }
}

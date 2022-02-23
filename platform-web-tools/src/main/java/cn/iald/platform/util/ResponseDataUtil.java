package cn.iald.platform.util;

import cn.hutool.core.collection.CollUtil;
import cn.iald.platform.base.vo.ResponseDataVO;
import cn.iald.platform.base.vo.ResultVO;
import cn.iald.platform.constant.ResponseConstant;
import cn.iald.platform.enums.SysCodeEnum;

import java.util.List;

/**
 * 返回请求工具类
 *
 * @author wangyc
 */
public class ResponseDataUtil {

    /**
     * 请求成功，返回list
     *
     * @param list 要返回的list
     * @return list大小及list数据
     */
    public static ResponseDataVO successList(List list) {
        if (CollUtil.isNotEmpty(list)) {
            return new ResponseDataVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(list.size(), list), "", "");
        }
        return new ResponseDataVO(SysCodeEnum.SUCCESS.getCode(), ResponseConstant.SUCCESS, new ResultVO(0, list), "", "");
    }
}

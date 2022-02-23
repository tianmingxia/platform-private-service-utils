package cn.iald.platform.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 执行一次任务DTO
 *
 * @author wangyc
 * @date 2021/07/22 10:27:40
 **/
@Data
public class XxlJobTriggerDTO implements Serializable {
    private static final long serialVersionUID = -2586042744876328163L;
    /**
     * 任务主键ID,必填
     */
    private Integer id;

    /**
     * 任务参数
     */
    private String executorParam;

    /**
     * 本次执行的机器地址，为空则从执行器获取
     */
    private String addressList;
}

package cn.iald.platform.controller;

import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.util.IdWorker;
import cn.iald.platform.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * id工具版本查看
 *
 * @author wangyc
 * @date 2020-11-06 16:28:00
 **/
@RestController
public class IdWorkerController {

    /**
     * 雪花算法信息查看
     *
     * @return
     */
    @GetMapping("/id/info")
    public ResponseVO info() {
        Map<String, Object> idWorkerMap = new HashMap<>(2);
        idWorkerMap.put("workId", IdWorker.getWorkId());
        idWorkerMap.put("dataCenterId", IdWorker.getDataCenterId());
        return ResponseUtil.success(idWorkerMap);
    }
}

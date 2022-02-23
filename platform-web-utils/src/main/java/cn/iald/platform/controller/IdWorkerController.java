package cn.iald.platform.controller;

import cn.iald.platform.utils.IdWorker;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: id工具版本查看
 * @author: wangyc
 * @create: 2020-11-06 16:28
 **/
@RestController
public class IdWorkerController {

	/**
	 * 雪花算法信息查看
	 *
	 * @return
	 */
	@GetMapping("/id/info")
	public ResponseVo info() {
		Map<String, Object> idWorkerMap = new HashMap<>(2);
		idWorkerMap.put("workId", IdWorker.getWorkId());
		idWorkerMap.put("dataCenterId", IdWorker.getDataCenterId());
		return ResponseUtils.success(idWorkerMap);
	}
}

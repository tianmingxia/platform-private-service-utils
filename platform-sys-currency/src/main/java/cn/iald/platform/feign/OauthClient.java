package cn.iald.platform.feign;

import cn.iald.platform.feign.vo.TokenVo;
import cn.iald.platform.vo.ResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * oauth 2.0客户端
 *
 * @author: wangyc
 * @create: 2020-12-16 08:59
 */
@FeignClient(name = "oauth")
public interface OauthClient {

	/**
	 * 获得token
	 *
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
	TokenVo token(@RequestParam Map<String, ?> queryParam);

	/**
	 * 提交用户信息
	 *
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/subUserInfo", method = RequestMethod.POST)
	ResponseVo subUserInfo(@RequestParam String userInfo);
}
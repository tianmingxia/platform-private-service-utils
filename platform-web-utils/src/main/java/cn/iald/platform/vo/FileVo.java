package cn.iald.platform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 文件返回Vo
 * @author: wangyc
 * @create: 2021-03-22 10:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVo {

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件地址
	 */
	private String fileUrl;
}

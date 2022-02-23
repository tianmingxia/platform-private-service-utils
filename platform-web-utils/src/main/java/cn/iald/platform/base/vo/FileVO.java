package cn.iald.platform.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件返回VO
 *
 * @author wangyc
 * @date 2021-06-05 14:53:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO implements Serializable {

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件地址
	 */
	private String fileUrl;
}

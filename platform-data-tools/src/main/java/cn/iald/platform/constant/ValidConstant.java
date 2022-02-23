package cn.iald.platform.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证提示语常量
 *
 * @author wangyc
 * @date 2021/06/03 17:31:04
 **/
public class ValidConstant {

    /**
     * 验证提示语
     */
    public static final Map<String, String> VALIDATION_ANNOTATION_DEFAULT_MESSAGES =
            new HashMap<String, String>(20) {
                {
                    put("Min", "validation.message.Min");
                    put("NotNull", "validation.message.NotNull");
                    put("NotEmpty", "validation.message.NotEmpty");
                }
            };
}

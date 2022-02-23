package cn.iald.platform.constant;

/**
 * 标志位常量
 *
 * @author wangyc
 * @date 2021-05-22 11:03:00
 **/
public class FlagConstant {

    /**
     * 通用是否标志-是
     */
    public static final Integer FLAG_YES = 1;

    /**
     * 通用是否标志-否
     */
    public static final Integer FLAG_NO = 0;

    /**
     * 获取数据粒度 1-指定类型的数据本身
     */
    public static final String CHILD_FLAG_SELF = "1";

    /**
     * 获取数据粒度 2-指定类型的数据及其直属子节点
     */
    public static final String CHILD_FLAG_FIRST = "2";

    /**
     * 获取数据粒度 3-指定类型的数据及其下辖所有节点
     */
    public static final String CHILD_FLAG_ALL = "3";
}

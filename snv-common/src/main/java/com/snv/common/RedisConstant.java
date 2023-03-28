package com.snv.common;

/**
 * @author Joword
 * @date: 2023/3/9 18:08
 * @version: 1.0
 * @description: redis persistence
 */
public interface RedisConstant {

    String USER_CACHE_NAME = "user_cache";

    String USER_CACHE_KEY_PREFIX = "user_";

    String ROLE_CACHE_NAME = "role_cache";

    String ROLE_CACHE_KEY_PREFIX = "role_";

    String DATA_CACHE_PREFIX = "data_";

    /**
     * 组持久数据
     */
    String GROUP_PREFIX = "group_";

    /**
     * 持久化数据接口
     */
    String DATA_PREFIX = "data";

    /**
     * 获取鉴权状态
     *
     * @param suffix 后缀
     *
     * @return key
     */
    static String getRedisRoleKey(String suffix) {
        return ROLE_CACHE_NAME + "::" + ROLE_CACHE_KEY_PREFIX + suffix;
    }

    /**
     * 数据缓存
     *
     * @param suffix 后缀
     *
     * @return key
     */
    static String getDataCachePrefixPrefix(String suffix) {
        return DATA_CACHE_PREFIX + suffix;
    }

    /**
     * 组持久化数据
     *
     * @param suffix 后缀
     *
     * @return key
     */
    static String getGroupPrefix(String suffix) {
        return GROUP_PREFIX + suffix;
    }

    /**
     * 持久化数据
     *
     * @param suffix 后缀
     *
     * @return key
     */
    static String getDataPrefix(String suffix) {
        return DATA_PREFIX + "::" + suffix;
    }

}

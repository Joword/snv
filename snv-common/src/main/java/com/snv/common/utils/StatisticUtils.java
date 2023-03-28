package com.snv.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joword
 * @date: 2023/3/7 17:32
 * @version: 1.0
 * @description: statistic utils
 */
public class StatisticUtils {

    public static int summarize(JSONObject arg) {
        List<Integer> lists = new ArrayList<>();
        for (String key : arg.keySet()) {
            if (arg.getInteger(key) != null) {
                lists.add(arg.getInteger(key));
            }
        }
        return lists.stream().mapToInt(x -> x).sum();
    }
}

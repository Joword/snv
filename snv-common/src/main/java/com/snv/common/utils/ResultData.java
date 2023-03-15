package com.snv.common.utils;

import com.snv.common.ResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Joword
 * @date: 2023/3/15 15:01
 * @version: 1.0
 * @description: return data structure
 */
@Getter
@Setter
@ToString
public class ResultData<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultData() {
    }

    public static <T> ResultData<T> successResult(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ResultCode.RESULT_SUCC);
        resultData.setMsg("success");
        resultData.setData(data);
        return resultData;
    }

    public static <S> ResultData<S> failResult(Integer code, String msg, S data) {
        ResultData<S> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

}

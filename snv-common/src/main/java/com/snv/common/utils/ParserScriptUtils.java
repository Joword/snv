package com.snv.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joword
 * @date: 2023/3/23 18:26
 * @version: 1.0
 * @description: to get a result from python
 */
@Component
public class ParserScriptUtils {

    /**
     * to calculate kernel density
     *
     * @param args input
     *
     * @return JSONObject
     */
    public static JSONObject getKernelDensity(List<String> args) throws IOException, InterruptedException {
        JSONObject jsonObject = new JSONObject();
        String[] args1 = new String[]{"python", "../resources/file/density.py", args.toString()};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(args1);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
            input.close();
            process.waitFor();
            return jsonObject;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            assert process != null;
            process.getInputStream().close();
            process.getOutputStream().close();
            process.getErrorStream().close();
            System.out.println("Java 调用python脚本结束");
        }
    }
}

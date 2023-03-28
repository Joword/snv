package com.joword.snv.web;

import com.joword.snv.UserService;
import com.joword.snv.model.UserInfoDo;
import com.snv.common.ResultCode;
import com.snv.common.utils.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * @author Joword
 * @date: 2023/3/15 14:58
 * @version: 1.0
 * @description: user information controller
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserService userService;

    public ResultData register() {
        try {
            UserInfoDo userDO = new UserInfoDo();

            return ResultData.successResult("success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultData.failResult(ResultCode.DATA_EXCEPTION, "error", null);
    }

    @Operation(
            description = "to test the email code",
            method = "POST",
            tags = {"UserInfoController"}
    )
    @GetMapping("/sendCode")
    public ResultData sendTestCode(@RequestParam("e") String email, HttpServletRequest request) {


        return ResultData.successResult("success");
    }

    @Operation(
            description = "login api",
            method = "POST",
            tags = {"UserInfoController"}
    )
    @GetMapping("/login")
    public ResultData login(HttpServletResponse response, @RequestParam("u") String userInfo) {

        return ResultData.successResult("success");
    }

    @Operation(
            description = "is login",
            method = "POST",
            tags = {"UserInfoController"}
    )
    @PostMapping("/checkLogin")
    public ResultData checkLogin(@CookieValue(name = "loginToken", required = false) String loginToken, HttpServletResponse response) {


        return ResultData.successResult("done");
    }

    @Operation(
            description = "logout",
            method = "POST",
            tags = {"UserInfoController"}
    )
    @GetMapping("/logout")
    public ResultData logout(@CookieValue(name = "loginToken", required = false) String loginToken, @CookieValue(name = "rootToken", required = false) String rootToken, HttpServletResponse response) throws Exception {

        return ResultData.successResult("success");
    }

}

package com.pxy.ggkt.vod.controller;

import com.pxy.ggkt.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-17 15:28
 **/
//@CrossOrigin
@RestController
@RequestMapping("/admin/vod/user")
public class UserLoginController {
    @PostMapping("login")
    public Result login(){
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    //info interface
    @GetMapping("info")
    public Result info(){
        HashMap<String, Object> map = new HashMap<>();
        //{"code":20000,"data":{"roles":["admin"],"introduction":"I am a super administrator","avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
        // "name":"Super Admin"}}
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map);
    }
}

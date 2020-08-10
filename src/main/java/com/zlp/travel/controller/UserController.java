package com.zlp.travel.controller;

import com.zlp.travel.entity.Result;
import com.zlp.travel.entity.User;
import com.zlp.travel.service.UserService;
import com.zlp.travel.util.CreateImageCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin // 允许跨域(前后端分离)
@Slf4j // 日志对象
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 生成验证码
     * @throws IOException
     */
    @GetMapping("/getImage")
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        // 获取验证码
        String securityCode = createImageCode.getCode();
        // 验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        // 生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }

    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public Result register(String code,String key, @RequestBody User user, HttpServletRequest request) {
        log.info("验证码" + code);
        log.info("用户信息" + user.toString());
        log.info("验证码编号"+key);
        Result result = new Result();
        String keyCode = (String)request.getServletContext().getAttribute(key);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                if(userService.findByUserName(user.getUsername()).size()==0){
                    Integer re = userService.register(user);
                    if(re == 1){
                        result.setMsg("注册成功！！");
                    }
                }else {
                    throw new RuntimeException("该用户已注册！！");
                }
            } else {
                throw new RuntimeException("验证码错误！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody User user,HttpServletRequest request){
      Result result = new Result();
      try{
          List<User> users = userService.login(user);
          if(users.size()==0){
              result.setMsg("用户名或密码错误!!").setState(false);
          }else {
            request.getServletContext().setAttribute(String.valueOf(users.get(0).getId()),users.get(0));
            result.setMsg("登陆成功!!").setUserId(String.valueOf(users.get(0).getId()));
          }
      }catch (Exception e){
          result.setMsg(e.getMessage()).setState(false);
      }
      return result;
    }
}

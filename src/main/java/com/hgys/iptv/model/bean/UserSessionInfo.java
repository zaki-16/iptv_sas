package com.hgys.iptv.model.bean;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * @ClassName UserSessionInfo
 * @Auther: wangz
 * @Date: 2019/5/17 17:32
 * @Description: TODO
 */
@Data
public class UserSessionInfo implements Serializable {
    private static final long serialVersionUID = -5237389443810617776L;


//    private Integer id;// 用户ID
    private String loginName;// 登录名 ==user.username
    private String realName;// 姓名
//    private String displayName;// 自定义展示姓名
    private String ip;// 用户IP
//    private String headImg; //头像
//    private User user;  //用户注册信息

//    private UserDetails userDetails;
//    Collection<? extends GrantedAuthority> authorities;//权限

}

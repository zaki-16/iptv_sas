package com.hgys.iptv.model.bean;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName UserSessionInfo
 * @Auther: wangz
 * @Date: 2019/5/17 17:32
 * @Description: TODO
 */
@Data
public class UserSessionInfo implements Serializable {
    private static final long serialVersionUID = -5237389443810617776L;


    private Integer id;// 用户ID
    private String loginName;// 登录名 ==user.username
    private String realName;// 姓名
    private String displayName;// 自定义展示姓名
    private String ip;// 用户IP
    private String headImg; //头像
    private User user;  //用户注册信息

    Collection<? extends GrantedAuthority> authorities;//权限

}

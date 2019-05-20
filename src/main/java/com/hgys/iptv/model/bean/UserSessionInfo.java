package com.hgys.iptv.model.bean;

import com.hgys.iptv.model.Role;
import com.hgys.iptv.model.User;
import lombok.Data;

import java.io.Serializable;
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


    private String id;// 用户ID
    private String loginName;// 登录名 ==user.username
    private String realName;// 姓名
    private String displayName;// 自定义展示姓名
    private String ip;// 用户IP
    private String headImg; //头像
    private User user;  //用户注册信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

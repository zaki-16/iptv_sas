package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Authority;
import com.hgys.iptv.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PersonalRole
 * @Auther: wangz
 * @Date: 2019/5/25 01:05
 * @Description: TODO
 */
@Data
public class PersonalRole {
    private Role role;
    private List<Authority> auths;
}

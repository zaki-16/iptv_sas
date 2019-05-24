package com.hgys.iptv.controller;

import com.hgys.iptv.model.dto.AuthorityDTO;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.AuthorityServiceImpl;
import com.hgys.iptv.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AuthorityController
 * @Auther: wangz
 * @Date: 2019/5/20 09:51
 * @Description: TODO
 */
@RestController
@RequestMapping("/auth")
@Api(value = "AuthorityController",tags = "菜单级别授权管理Api接口")
public class AuthorityController {

    @Autowired
    private AuthorityServiceImpl authorityService;

    @GetMapping("/findAllAuthority")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO findAllAuthority() {
        return authorityService.findAllAuthority();
    }

    @PostMapping("/addAuthority")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultVO addAuthority(@RequestBody AuthorityDTO authorityDTO) {
        return authorityService.addAuthority(authorityDTO);
    }

//    @GetMapping("/findByAuthorityName")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultVO findByAuthorityName(String name) {
//        return authorityService.findByName(name);
//    }

    @PostMapping("/updateAuthority")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO updateAuthority(@RequestBody AuthorityDTO authorityDTO) {
        return authorityService.updateAuthority(authorityDTO);
    }

    @DeleteMapping("/deleteAuthorityById")
    @ResponseStatus(HttpStatus.OK)
    public ResultVO deleteAuthorityById(Integer id) {
        return authorityService.deleteById(id);
    }


    @GetMapping("/bootAuthority")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "一键生成菜单权限",notes = "")
    public ResultVO bootAuthority() {
         authorityService.bootAuthority();
         return ResultVOUtil.success("一键生成菜单权限完美结束！");
    }


}

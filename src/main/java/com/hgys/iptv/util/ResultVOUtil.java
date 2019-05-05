package com.hgys.iptv.util;

import com.hgys.iptv.exception.BaseException;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;

/**
 * @author yangpeng
 */
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode("0");
        resultVO.setMsg("成功");
        return resultVO;
    }


    public static ResultVO success() {
        return success(null);
    }


    public static ResultVO<Void> error(String code, String msg) {
        ResultVO<Void> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


    public static ResultVO<String> error(BaseException e) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(e.getError().getCode());
        resultVO.setMsg(e.getError().getMessage());
        resultVO.setData(e.getExtMessage());
        return resultVO;
    }


    public static ResultVO<Void> error(ResultEnum resultEnum) {
        ResultVO<Void> resultVO = new ResultVO<>();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return resultVO;
    }
}

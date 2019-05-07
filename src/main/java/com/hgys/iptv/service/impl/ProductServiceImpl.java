package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.ProductRepository;
import com.hgys.iptv.service.ProductService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Auther: wangz
 * @Date: 2019/5/5 17:15
 * @Description:/
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    //必填字段
    private final String[] cols = {"name","price","status"};
    /**
     * cp 新增
     * @param prod
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(Product prod){
        //必填字段：产品名称，关联业务，价格，状态（启用、禁用）
        //系统返填字段：录入时间，修改时间，产品编码
        //校验名称是否已经存在
        Product byName = productRepository.findByName(prod.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName + "名称已经存在");
        }
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写!");
        prod.setInputTime(new Timestamp(System.currentTimeMillis()));//录入时间
        prod.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        prod.setCode(CodeUtil.getOnlyCode("SDS",5));//cp编码
        prod.setIsdelete(0);//删除状态-0：未删除
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * cp 修改
     * @param prod
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Product prod) {
        return ResultVOUtil.success(productRepository.save(prod));//jpa会调用isNew()方法判定对象是否已存在
    }

    /**
     * cp删除--逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    public ResultVO<?> logicDelete(Product prod){
        prod.setIsdelete(1);
        return this.update(prod);
    }

    /**
     * cp批量逻辑删除
     * @param prods
     */
    @Override
    public ResultVO<?> batchLogicDelete(List<Product> prods){
        if(prods!=null && prods.size()>0)
            for(Product prod:prods){
                logicDelete(prod);
            }
        return ResultVOUtil.error("1","请选择要删除的产品");
    }

    /**
     * cp单查询--根据id
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Product product = productRepository.findById(id).get();
        if(product!=null)
            return ResultVOUtil.success(product);
        return ResultVOUtil.error("1","所查询的产品不存在!");
    }

    /**
     * cp单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Product cp = productRepository.findByCode(code);
        if(cp!=null)
            return ResultVOUtil.success(cp);
        return ResultVOUtil.error("1","所查询的产品不存在!");
    }

    /**
     * cp列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Product> cps = productRepository.findAll();
        if(cps!=null&&cps.size()>0)
            return ResultVOUtil.success(cps);
        return ResultVOUtil.error("1","所查询的产品列表不存在!");
    }

}

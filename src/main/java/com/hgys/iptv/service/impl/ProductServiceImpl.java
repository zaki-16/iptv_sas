package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.assemlber.ProductControllerAssemlber;
import com.hgys.iptv.controller.vm.ProductControllerListVM;
import com.hgys.iptv.model.Product;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.BusinessRepository;
import com.hgys.iptv.repository.CpRepository;
import com.hgys.iptv.repository.ProductRepository;
import com.hgys.iptv.service.ProductService;
import com.hgys.iptv.util.CodeUtil;
import com.hgys.iptv.util.ResultVOUtil;
import com.hgys.iptv.util.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    ProductControllerAssemlber assemlber;
    /**
     * 新增
     * @param prod
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> save(Product prod){
        //校验名称是否已经存在
        Product byName = productRepository.findByName(prod.getName());
        if (null != byName){
            return ResultVOUtil.error("1",byName + "名称已经存在");
        }
        String[] cols = {prod.getName(),prod.getPrice().toString(),prod.getStatus().toString()};
        if(!Validator.validEmptyPass(cols))//必填字段不为空则插入
            return ResultVOUtil.error("1","有必填字段未填写！");
        prod.setInputTime(new Timestamp(System.currentTimeMillis()));//注册时间
        prod.setModifyTime(new Timestamp(System.currentTimeMillis()));//最后修改时间
        prod.setCode(CodeUtil.getOnlyCode("SDS",5));//cp编码
        prod.setIsdelete(0);//删除状态
        Product prod_add = productRepository.save(prod);
        if(prod_add !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");
    }

    /**
     * 修改
     * @param prod
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> update(Product prod) {
        Product prod_up = productRepository.save(prod);
        if(prod_up !=null)
            return ResultVOUtil.success(Boolean.TRUE);
        return ResultVOUtil.error("1","新增失败！");//jpa会调用isNew()方法判定对象是否已存在
    }

    /**
     * 逻辑删除，只更新对象的isdelete字段值 0：未删除 1：已删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> logicDelete(Integer id){
        try {
            productRepository.logicDelete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 批量逻辑删除
     * @param ids
     */
    @Override
    public ResultVO<?> batchLogicDelete(String ids){
        List<String>  idLists = Arrays.asList(StringUtils.split(ids, ","));
        for (String s : idLists)
            productRepository.logicDelete(Integer.parseInt(s));
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 单查询--根据id
     * @param id
     * @return
     */
    @Override
    public ResultVO<?> findById(Integer id) {
        Product prod = productRepository.findById(id).get();
        if(prod!=null)
            return ResultVOUtil.success(prod);
        return ResultVOUtil.error("1","所查询的cp不存在!");
    }

    /**
     * 单查询--根据code
     * @param code
     * @return
     */
    @Override
    public ResultVO<?> findByCode(String code) {
        Product prod = productRepository.findByCode(code);
        if(prod!=null)
            return ResultVOUtil.success(prod);
        return ResultVOUtil.error("1","所查询的产品不存在!");
    }

    /**
     * 列表查询
     * @return
     */
    @Override
    public ResultVO<?> findAll() {
        List<Product> prods = productRepository.findAll();
        if(prods!=null&&prods.size()>0)
            return ResultVOUtil.success(prods);
        return ResultVOUtil.error("1","所查询的产品列表不存在!");
    }

    @Override
    public Page<ProductControllerListVM> findByConditions(String name,String code, String status, Pageable pageable) {
        return productRepository.findAll(((root, query,builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(name)){
                Predicate condition = builder.equal(root.get("name").as(String.class), "%"+name+"%");
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(code)){
                Predicate condition = builder.equal(root.get("code").as(String.class), code);
                predicates.add(condition);
            }
            if (StringUtils.isNotBlank(status)){
                Predicate condition = builder.equal(root.get("status").as(String.class), status);
                predicates.add(condition);
            }
            Predicate condition = builder.equal(root.get("isdelete").as(String.class), 0);
            predicates.add(condition);
            if (!predicates.isEmpty()){
                return builder.and(predicates.toArray(new Predicate[0]));
            }
//            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            return builder.conjunction();
        }),pageable).map(assemlber::getListVM);
    }

}

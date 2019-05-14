package com.hgys.iptv.service.impl;

import com.hgys.iptv.model.*;
import com.hgys.iptv.model.bean.CpOrderCpExcelDTO;
import com.hgys.iptv.model.bean.OrderProductDimensionListDTO;
import com.hgys.iptv.model.qmodel.QCp;
import com.hgys.iptv.model.qmodel.QCpProduct;
import com.hgys.iptv.model.qmodel.QOrderProductWithSCD;
import com.hgys.iptv.model.qmodel.QProduct;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.OrderProductRepository;
import com.hgys.iptv.repository.OrderProductWithSCDRepository;
import com.hgys.iptv.repository.OrderQuantityWithCpRepository;
import com.hgys.iptv.repository.SettlementCombinatorialDimensionFromRepository;
import com.hgys.iptv.service.AccountSettlementService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.hgys.iptv.model.bean.OrderProductDimensionDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountSettlementServiceImpl implements AccountSettlementService {

    @Autowired
    private OrderQuantityWithCpRepository quantityWithCpRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderProductWithSCDRepository scdRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private SettlementCombinatorialDimensionFromRepository settlementCombinatorialDimensionFromRepository;


    /**
     * 新增分配结算
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO<?> addAccountSettlement() {
        return null;
    }

    @Override
    public List<?> excelExport(Integer type, String code) {
        //1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定比例结算
        if (type == 1){
            List<OrderQuantityWithCp> cpList = quantityWithCpRepository.findByMasterCode(code.trim());
            List<CpOrderCpExcelDTO> dtos = new ArrayList<>();
            for (OrderQuantityWithCp cp : cpList){
                CpOrderCpExcelDTO dto = new CpOrderCpExcelDTO();
                BeanUtils.copyProperties(cp,dto);
                dtos.add(dto);
            }
            return dtos;
        }else if (type == 3){

            OrderProduct byCode = orderProductRepository.findByCode(code);
            if (null != byCode){
                //查看是单维度还是多维度
                Integer mode = byCode.getMode();
                //查询所有的产品
                List<OrderProductWithSCD> byMasterCode = scdRepository.findByMasterCode(code.trim());
                //查询产品下所有的cp
                if (mode == 1){
                    QCpProduct qCpProduct = QCpProduct.cpProduct; //产品和cp关系表
                    QOrderProductWithSCD qScd = QOrderProductWithSCD.orderProductWithSCD;
                    QCp qCp = QCp.cp; //cp表
                    QProduct qProduct = QProduct.product; //产品表
                    List<OrderProductDimensionDTO> fetch = queryFactory.select(Projections.bean(
                            OrderProductDimensionDTO.class,
                            qCp.code.as("cpcode"),
                            qCp.name.as("cpname"),
                            qProduct.code.as("pcode"),
                            qProduct.name.as("pname")
                    )).from(qScd).innerJoin(qProduct).on(qScd.pcode.eq(qProduct.code))
                            .innerJoin(qCpProduct).on(qProduct.id.eq(qCpProduct.pid))
                            .innerJoin(qCp).on(qCpProduct.cpid.eq(qCp.id)).fetch();

                    List<OrderProductDimensionDTO> result = new ArrayList<>();
                    for (OrderProductDimensionDTO dto : fetch){
                        dto.setDimensionCode(byCode.getSdcode());
                        result.add(dto);
                    }
                    return result;
                }else {
                    QCpProduct qCpProduct = QCpProduct.cpProduct; //产品和cp关系表
                    QOrderProductWithSCD qScd = QOrderProductWithSCD.orderProductWithSCD;
                    QCp qCp = QCp.cp; //cp表
                    QProduct qProduct = QProduct.product; //产品表
                    List<OrderProductDimensionListDTO> fetch = queryFactory.select(Projections.bean(
                            OrderProductDimensionListDTO.class,
                            qCp.code.as("cpcode"),
                            qCp.name.as("cpname"),
                            qProduct.code.as("pcode"),
                            qProduct.name.as("pname")
                    )).from(qScd).innerJoin(qProduct).on(qScd.pcode.eq(qProduct.code))
                            .innerJoin(qCpProduct).on(qProduct.id.eq(qCpProduct.pid))
                            .innerJoin(qCp).on(qCpProduct.cpid.eq(qCp.id)).fetch();

                    List<OrderProductDimensionListDTO> result = new ArrayList<>();
                    //查询多维度下单维度
                    List<SettlementCombinatorialDimensionFrom> froms = settlementCombinatorialDimensionFromRepository.findByMasterCode(byCode.getScdcode().trim());
                    for (OrderProductDimensionListDTO dto : fetch){
                        dto.setDimensionACode(froms.get(0).getDim_code());
                        dto.setDimensionBCode(froms.get(1).getDim_code());
                        dto.setDimensionCCode(froms.get(2).getDim_code());
                        result.add(dto);
                    }
                    return result;
                }
            }else {
                return null;
            }
        }
        return null;
    }
}

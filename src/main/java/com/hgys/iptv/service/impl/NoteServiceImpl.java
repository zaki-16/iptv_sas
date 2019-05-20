package com.hgys.iptv.service.impl;

import com.hgys.iptv.controller.vm.NoteAddControllerVM;
import com.hgys.iptv.controller.vm.NoteQueryControllerVM;
import com.hgys.iptv.model.Note;
import com.hgys.iptv.model.enums.ResultEnum;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.repository.NoteRepository;
import com.hgys.iptv.service.NoteService;
import com.hgys.iptv.util.ResultVOUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * 新增
     * @param vm
     * @return
     */
    @Override
    public ResultVO<?> addNote(NoteAddControllerVM vm) {
        if (StringUtils.isBlank(vm.getContent())){
            return ResultVOUtil.error("1","备注模板内容不能为空");
        }else if (null == vm.getNote_type()){
            return ResultVOUtil.error("1","备注类型不能为空");
        }
        //获取当前用户信息，如果没有查询到UserId设置为0

        Note note = new Note();
        note.setContent(vm.getContent());
        note.setNote_type(vm.getNote_type());
        note.setCreate_time(new Timestamp(System.currentTimeMillis()));
        note.setUserId(0);//暂时设置为公共0

        try{
            noteRepository.save(note);
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }
        return ResultVOUtil.success(Boolean.TRUE);
    }

    /**
     * 通过Id批量删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO<?> batchLogicDelete(String ids) {
        try{
            List<String> idLists = Arrays.asList(StringUtils.split(ids, ","));
            for (String s : idLists){
                noteRepository.deleteById(Integer.parseInt(s));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.SYSTEM_INTERNAL_ERROR);
        }

        return ResultVOUtil.success(Boolean.TRUE);
    }

    @Override
    public List<NoteQueryControllerVM> findByConditions(String content, String userId, String noteType) {
        Specification querySpecifi = new Specification<Note>() {
            @Override
            public Predicate toPredicate(Root<Note> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotBlank(noteType)){
                    predicates.add(criteriaBuilder.equal(root.get("note_type"), Integer.parseInt(noteType)));
                }
                if(StringUtils.isNotBlank(userId)){
                    predicates.add(criteriaBuilder.equal(root.get("userId"), Integer.parseInt(userId)));
                }
                if(StringUtils.isNotBlank(content)){
                    predicates.add(criteriaBuilder.like(root.get("content"), "%"+content+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<NoteQueryControllerVM> list = new ArrayList();

        List<Note> all = noteRepository.findAll(querySpecifi);
        for (Note n : all){
            NoteQueryControllerVM vm = new NoteQueryControllerVM();
            BeanUtils.copyProperties(n,vm);
            list.add(vm);
        }
        return list;
    }

}

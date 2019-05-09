package com.hgys.iptv.service;

import com.hgys.iptv.controller.vm.NoteAddControllerVM;
import com.hgys.iptv.controller.vm.NoteQueryControllerVM;
import com.hgys.iptv.model.vo.ResultVO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface NoteService {

    ResultVO<?> addNote(NoteAddControllerVM vm);

    ResultVO<?> batchLogicDelete(String ids);

    List<NoteQueryControllerVM> findByConditions(String content, String userId, String noteType);
}

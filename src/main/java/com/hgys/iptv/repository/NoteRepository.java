package com.hgys.iptv.repository;

import com.hgys.iptv.model.Note;
import com.hgys.iptv.model.SettlementDimension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note,Object>, JpaSpecificationExecutor<Note> {
}

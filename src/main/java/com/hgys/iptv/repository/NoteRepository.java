package com.hgys.iptv.repository;

import com.hgys.iptv.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Integer> {
}

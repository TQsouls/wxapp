package com.wxapp.dao;

import com.wxapp.dbbean.TbTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbTagDao extends JpaRepository<TbTagEntity, Integer> {
}

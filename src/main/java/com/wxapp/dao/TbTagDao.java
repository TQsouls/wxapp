package com.wxapp.dao;

import com.wxapp.dbbean.TbTagEntity;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbTagDao extends JpaRepository<TbTagEntity, Integer> {
}

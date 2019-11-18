package com.tengol.spring.boot.schedule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * TbCronMapper
 *
 * @author dongrui
 * @date 2019/11/18 17:53
 */
@Mapper
@Repository
public interface TbCronMapper {
    @Select("select cron from tb_cron where cron_id=#{cronId} limit 1")
    String getCron(@Param("cronId") String cronId);
}

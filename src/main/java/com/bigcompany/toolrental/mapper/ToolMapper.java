package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.Tool;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ToolMapper {

    @Select("SELECT id, code, tool_type_id, brand_id, deleted, created_at, updated_at FROM tool")
    @Results(id = "ToolMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "code", column = "code"),
            @Result(property = "toolTypeId", column = "tool_type_id"),
            @Result(property = "brandId", column = "brand_id"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Tool> findAll();

    @Select("SELECT id, code, tool_type_id, brand_id, deleted, created_at, updated_at FROM tool WHERE code = #{code}")
    @ResultMap("ToolMap")
    Optional<Tool> findByCode(@Param("code") String toolCode);

    @Update("UPDATE tool SET deleted = TRUE WHERE id = #{id}")
    void delete(Long id);
}

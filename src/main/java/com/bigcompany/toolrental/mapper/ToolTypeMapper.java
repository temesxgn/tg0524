package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.Tool;
import com.bigcompany.toolrental.model.ToolType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ToolTypeMapper {

    @Select("SELECT id, name, deleted, created_at, updated_at FROM tool_type")
    @Results(id = "ToolTypeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<ToolType> findAll();

    @Select("SELECT id, code, tool_type_id, brand_id, created_at FROM tool WHERE code = #{code}")
    @ResultMap("ToolTypeMap")
    Optional<Tool> findByCode(@Param("code") String toolCode);

    @Update("UPDATE tool SET deleted = TRUE WHERE id = #{id}")
    void delete(Long id);
}

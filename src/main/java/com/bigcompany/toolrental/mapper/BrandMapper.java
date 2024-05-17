package com.bigcompany.toolrental.mapper;

import com.bigcompany.toolrental.model.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BrandMapper {

    @Select("SELECT id, name, deleted, created_at, updated_at FROM brand WHERE id = #{id}")
    @ResultMap("brandResult")
    Optional<Brand> findById(Long id);

    @Select("SELECT id, name, deleted, created_at, updated_at FROM brand")
    @Results(id = "brandResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    List<Brand> findAll();

    @Insert("INSERT INTO brand (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    Long insert(Brand brand);

    @Update("UPDATE brand SET name = #{name}, updated_at = NOW() WHERE id = #{id}")
    void update(Brand brand);

    @Update("UPDATE brand SET deleted = TRUE, updated_at = NOW() WHERE id = #{id}")
    void delete(Long id);

    @Update("UPDATE brand SET deleted = FALSE, updated_at = NOW() WHERE id = #{id}")
    void restore(Long id);
}

package oit.is.z2444.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, name FROM users WHERE id = #{id};")
  User selectById(int id);

  @Insert("INSERT INTO users (name) VALUES (#{name});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUsers(String name);

  @Select("SELECT id, name FROM users WHERE name = #{name};")
  User selectByName(String name);

  @Select("SELECT COUNT(id) FROM users;")
  int countById();

  @Select("SELECT id, name FROM users;")
  ArrayList<User> selectAllUsers();

}

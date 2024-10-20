package oit.is.z2444.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT id, userName FROM users WHERE id = #{id};")
  User selectById(int id);

  @Insert("INSERT INTO users (userName) VALUES (#{userName});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUsers(String userName);

  @Select("SELECT id, userName FROM users WHERE userName = #{userName};")
  User selectByUserName(String userName);

  @Select("SELECT COUNT(id) FROM users;")
  int countById();

  @Select("SELECT id, userName FROM users;")
  ArrayList<User> selectAllUsers();

}

package oit.is.z2444.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO matchInfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
  void insertMatchInfo(MatchInfo matchInfo);

  @Update("UPDATE matchInfo")
  void updateMatchInfo(MatchInfo matchInfo);

  @Select("SELECT * FROM matchInfo where user2 = #{user2}")
  MatchInfo seleMatchInfo(int user2);

  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchInfo where isActive = true")
  ArrayList<MatchInfo> selectMatchIsActive();

}

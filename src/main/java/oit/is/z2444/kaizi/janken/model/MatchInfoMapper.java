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

  @Update("UPDATE matchInfo SET isActive = false WHERE id = #{id}")
  void updateMatchInfoNonActive(MatchInfo matchInfo);

  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchInfo where user1 = #{user1} AND user2 = #{user2} AND isActive = true")
  MatchInfo selectMatchInfo(int user1, int user2);

  // すでにマッチがあるかどうかを確認
  @Select("SELECT EXISTS (SELECT 1 FROM matchInfo WHERE user1 = #{user1} AND user2 = #{user2})")
  Boolean isMatchInfo(int user1, int user2);

  @Select("SELECT id, user1, user2, user1Hand, isActive FROM matchInfo where isActive = true")
  ArrayList<MatchInfo> selectMatchIsActive();

}

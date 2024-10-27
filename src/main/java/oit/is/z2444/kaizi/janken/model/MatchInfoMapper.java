package oit.is.z2444.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Insert("INSERT INTO matchInfo (user1, user2) VALUES (#{user1}, #{user2})")
  void insertMatchInfo(MatchInfo matchInfo);

  @Update("UPDATE matchInfo")
  void updateMatchInfo(MatchInfo matchInfo);

}

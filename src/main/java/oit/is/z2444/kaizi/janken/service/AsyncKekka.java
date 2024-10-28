package oit.is.z2444.kaizi.janken.service;

import java.util.ArrayList;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z2444.kaizi.janken.model.Match;
import oit.is.z2444.kaizi.janken.model.MatchInfo;
import oit.is.z2444.kaizi.janken.model.MatchInfoMapper;
import oit.is.z2444.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {

  boolean dbUpdated = false;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Autowired
  MatchMapper matchMapper;

  @Autowired
  MatchInfoMapper MIMapper;

  @Transactional
  public Match syncMatchResult(int user1_id, int user2_id, String user2_hand) throws IOException {
    MatchInfo matchInfo = MIMapper.selectMatchInfo(user1_id, user2_id);
    Match matchResult = new Match(user1_id, user2_id, matchInfo.getUser1Hand(), user2_hand);
    matchMapper.insertMatch(matchResult);

    // matchInfoのisActiveをfalseに
    MIMapper.updateMatchInfoNonActive(matchInfo);

    this.dbUpdated = true;

    return matchResult;
  }

  public ArrayList<Match> syncShowResult() {
    return matchMapper.selectAllActiveMatch();
  }

  @Async
  public void asyncResult(SseEmitter emitter) {
    try {
      while (true) {// 無限ループ
        // DBが更新されていなければ0.5s休み
        if (false == dbUpdated) {
          TimeUnit.MILLISECONDS.sleep(500);
          continue;
        }
        // DBが更新されていれば更新後のリストを取得してsendし，1s休み，dbUpdatedをfalseにする
        ArrayList<Match> reslut = this.syncShowResult();
        emitter.send(reslut);

        TimeUnit.MILLISECONDS.sleep(1000);
        logger.info("done");
        dbUpdated = false;

        for (Match match : reslut) {
          matchMapper.updateMatchIsNonActive(match);
        }
      }
    } catch (Exception e) {
      // 例外の名前とメッセージだけ表示する
      logger.warn("Exception:" + e.getClass().getName() + ":" + e.getMessage());
    } finally {
      emitter.complete();
    }
    System.out.println("asyncResult complete");
  }

}

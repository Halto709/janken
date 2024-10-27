package oit.is.z2444.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2444.kaizi.janken.model.Janken;
import oit.is.z2444.kaizi.janken.model.Entry;
import oit.is.z2444.kaizi.janken.model.User;
import oit.is.z2444.kaizi.janken.model.UserMapper;
import oit.is.z2444.kaizi.janken.model.Match;
import oit.is.z2444.kaizi.janken.model.MatchInfo;
import oit.is.z2444.kaizi.janken.model.MatchInfoMapper;
import oit.is.z2444.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @Autowired
  private MatchInfoMapper MIMapper;

  @GetMapping("/janken")
  public String janken(Principal prin, ModelMap model) {
    /*
     * String loginUser = prin.getName();
     * this.entry.addUser(loginUser);
     * model.addAttribute("entry", this.entry);
     */
    ArrayList<User> users = userMapper.selectAllUsers();
    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("users", users);
    model.addAttribute("matches", matches);

    User user2 = userMapper.selectByName(prin.getName());
    // ログイン後にアクティブな試合を表示する用
    ArrayList<MatchInfo> activeMatches = MIMapper.selectMatchIsActive();
    model.addAttribute("activeMatches", activeMatches);
    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam Integer id, Principal prin, ModelMap model) {

    // 入室処理の実装（目標）
    String loginUser = prin.getName();

    User user1 = userMapper.selectByName(loginUser);
    User user2 = userMapper.selectById(id);

    model.addAttribute("user1", user1);
    model.addAttribute("user2", user2);

    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam int id, @RequestParam String hand, Principal prin, ModelMap model) {
    Janken janken = new Janken(hand);

    User user = userMapper.selectByName(prin.getName());

    Match match = new Match();
    match.setUser1(user.getId());
    match.setUser2(id);
    match.setUser1Hand(hand);
    match.setUser2Hand(janken.getEnemyHand());

    matchMapper.insertMatch(match);

    // それぞれの情報を格納
    model.addAttribute("janken", janken);
    model.addAttribute("opponent", userMapper.selectById(id));
    model.addAttribute("loginUser", prin.getName());
    model.addAttribute("opponent_id", id);

    return "match.html";
  }

  @GetMapping("/wait")
  public String wait(@RequestParam int id, @RequestParam String hand, Principal prin, ModelMap model) {

    // ユーザの情報を取得
    User user1 = userMapper.selectByName(prin.getName());
    User user2 = userMapper.selectById(id);

    MatchInfo matchInfo = new MatchInfo(user1.getId(), user2.getId(), hand);
    MIMapper.insertMatchInfo(matchInfo);

    // それぞれの情報を格納
    // model.addAttribute("janken", janken);
    model.addAttribute("user1", user1);
    model.addAttribute("user2", user2);
    model.addAttribute("matchInfo", matchInfo);

    // return "match.html";
    return "wait.html";
  }

  /**
   * POSTを受け付ける場合は@PostMappingを利用する
   *
   * @param playerName
   * @param model
   * @return
   */
  @PostMapping("/janken")
  public String janken(@RequestParam String playerName, ModelMap model) {
    model.addAttribute("playerName", playerName);
    return "janken.html";
  }

}

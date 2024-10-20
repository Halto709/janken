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
import oit.is.z2444.kaizi.janken.model.MatchMapper;

@Controller
public class JankenController {

  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

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

    return "janken.html";
  }

  @GetMapping("/match")
  public String sample23(@RequestParam Integer id, Principal prin, ModelMap model) {

    String loginUser = prin.getName();
    model.addAttribute("loginUser", loginUser);
    User opponent = userMapper.selectById(id);
    // matchMapper.insertMatch(id, userMapper.selectByUserName(loginUser).getId());
    model.addAttribute("opponent", opponent);
    model.addAttribute("opponent_id", id);

    return "match.html";
  }

  @GetMapping("/fight")
  public String jankengame(@RequestParam int id, @RequestParam String hand, Principal prin, ModelMap model) {
    Janken janken = new Janken(hand);

    User user = userMapper.selectByUserName(prin.getName());

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

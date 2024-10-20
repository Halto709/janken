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
    model.addAttribute("opponent", opponent);

    return "match.html";
  }

  @GetMapping("/match")
  public String jankengame(@RequestParam int id, @RequestParam String hand, ModelMap model) {
    Janken janken = new Janken(hand);

    // それぞれの情報を格納
    model.addAttribute("janken", janken);
    return "janken.html";
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

  /**
   * @param playerHand
   * @param model
   * @return
   */

}

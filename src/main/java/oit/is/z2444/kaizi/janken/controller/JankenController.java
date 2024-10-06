package oit.is.z2444.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z2444.kaizi.janken.model.Janken;

@Controller
public class JankenController {

  @GetMapping("/janken")
  public String janken() {
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
  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    Janken janken = new Janken(hand);

    // それぞれの情報を格納
    model.addAttribute("janken", janken);
    return "janken.html";
  }

}

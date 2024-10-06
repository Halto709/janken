package oit.is.z2444.kaizi.janken.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

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
    Random random = new Random();
    String playerHand = hand;
    String enemyHand = "";
    String jankenResult = "";

    // cpuの手を生成
    int enemy = random.nextInt(3);
    switch (enemy) {
      case 0:
        enemyHand = "Gu";
        break;
      case 1:
        enemyHand = "Choki";
        break;
      case 2:
        enemyHand = "Pa";
        break;
      default:
        break;
    }

    // 勝敗処理
    if (playerHand.equals(enemyHand)) {
      jankenResult = "Draw!";
    }
    if ((playerHand.equals("Gu") && enemyHand.equals("Choki"))
        || (playerHand.equals("Choki") && enemyHand.equals("Pa"))
        || (playerHand.equals("Pa") & enemyHand.equals("Gu"))) {
      jankenResult = "You Win!";
    }
    if ((playerHand.equals("Gu") && enemyHand.equals("Pa"))
        || (playerHand.equals("Choki") && enemyHand.equals("Gu"))
        || (playerHand.equals("Pa") && enemyHand.equals("Choki"))) {
      jankenResult = "You Lose...";
    }

    // それぞれの情報を格納
    model.addAttribute("playerHand", playerHand);
    model.addAttribute("enemyHand", enemyHand);
    model.addAttribute("jankenResult", jankenResult);
    return "janken.html";
  }

}

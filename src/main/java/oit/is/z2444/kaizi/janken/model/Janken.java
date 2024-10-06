package oit.is.z2444.kaizi.janken.model;

import java.util.Random;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class Janken {

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

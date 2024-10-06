package oit.is.z2444.kaizi.janken.model;

import java.util.Random;

public class Janken {
  String playerHand;
  String enemyHand;
  String jankenResult;

  public Janken(String hand) {
    this.playerHand = hand;
    makeEnemyhand();
    judgeResult();
  }

  private void makeEnemyhand() {
    Random random = new Random();
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
  }

  private void judgeResult() {
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
  }

  public void setPlayerHand(String playerHand) {
    this.playerHand = playerHand;
  }

  public void setEnemyHand(String enemyHand) {
    this.enemyHand = enemyHand;
  }

  public void setJankenResult(String jankenResult) {
    this.jankenResult = jankenResult;
  }

  public String getPlayerHand() {
    return playerHand;
  }

  public String getEnemyHand() {
    return enemyHand;
  }

  public String getJankenResult() {
    return jankenResult;
  }
}

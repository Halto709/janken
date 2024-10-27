package oit.is.z2444.kaizi.janken.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class AsyncKekka {
  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Async
  public void kekka(SseEmitter emitter, int matchinfo_id) throws IOException {
    try {
      while (true) {
        if (true) {

        } else {

        }
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
  }

}

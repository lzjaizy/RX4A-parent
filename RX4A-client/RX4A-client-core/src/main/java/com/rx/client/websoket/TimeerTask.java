package com.rx.client.websoket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class TimeerTask extends TimerTask {
  WSClient client;

  public TimeerTask(WSClient client) {
    this.client = client;
  }

  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

  @Override
  public void run() {
    try {
      if (client.isOpen()) {
        client.send("ping");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

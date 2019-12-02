package com.example.myapplication;

import android.content.Context;
import android.net.sip.SipManager;

public class SipManagerWrapper {

  public static SipManagerWrapper INSTANCE = new SipManagerWrapper();

  private SipManagerWrapper() {
  }

  public static SipManagerWrapper getInstance() {
    return INSTANCE;
  }

  public SipManager sipManager = null;

  public void init(Context context) {
    this.sipManager = SipManager.newInstance(context);
  }

}

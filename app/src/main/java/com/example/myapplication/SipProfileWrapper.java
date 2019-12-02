package com.example.myapplication;

import android.net.sip.SipProfile;

import java.text.ParseException;

public class SipProfileWrapper {

  private static final SipProfileWrapper INSTANCE = new SipProfileWrapper();

  public static SipProfileWrapper getInstance() {
    return INSTANCE;
  }

  public SipProfile sipProfile = null;

  public void init(String username, String domain, String password) {
    try {
      SipProfile.Builder builder = new SipProfile.Builder(username, domain);
      builder.setPassword(password);
      sipProfile = builder.build();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

}

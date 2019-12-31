package com.example.myapplication;

import android.net.sip.SipAudioCall;
import android.net.sip.SipProfile;
import android.util.Log;

public class SipCallListener extends SipAudioCall.Listener {

  @Override
  public void onReadyToCall(SipAudioCall call) {
    Log.i("SipCallListener", "onReadyToCall()");
  }

  @Override
  public void onCalling(SipAudioCall call) {
    Log.i("SipCallListener", "onCalling()");
  }

  @Override
  public void onRinging(SipAudioCall call, SipProfile caller) {
    Log.i("SipCallListener", "onRinging()");
  }

  @Override
  public void onRingingBack(SipAudioCall call) {
    Log.i("SipCallListener", "onRingingBack()");
  }

  @Override
  public void onCallEstablished(SipAudioCall call) {
    Log.i("SipCallListener", "onCallEstablished()");
    call.startAudio();
    call.setSpeakerMode(false);
  }

  @Override
  public void onCallEnded(SipAudioCall call) {
    Log.i("SipCallListener", "onCallEnded()");
  }

  @Override
  public void onCallBusy(SipAudioCall call) {
    Log.i("SipCallListener", "onCallBusy()");
  }

  @Override
  public void onCallHeld(SipAudioCall call) {
    Log.i("SipCallListener", "onCallHeld()");
  }

  @Override
  public void onError(SipAudioCall call, int errorCode, String errorMessage) {
    Log.i("SipCallListener", "onError(errorCode: " + errorCode + " errorMessage: " + errorMessage + ")");
  }

  @Override
  public void onChanged(SipAudioCall call) {
    Log.i("SipCallListener", "onChanged()");
  }
}

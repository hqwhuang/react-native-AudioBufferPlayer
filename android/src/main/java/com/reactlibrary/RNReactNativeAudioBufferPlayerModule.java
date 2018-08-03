
package com.reactlibrary;

import android.media.AudioTrack;
import android.media.AudioManager;
import android.media.AudioFormat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

public class RNReactNativeAudioBufferPlayerModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  private AudioTrack track;

  public RNReactNativeAudioBufferPlayerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  public String getName() {
    return "RNReactNativeAudioBufferPlayer";
  }

  @ReactMethod
  public void initialize(int hz, Promise promise){
    try{
      track = new AudioTrack(AudioManager.STREAM_MUSIC, hz, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, 3200, AudioTrack.MODE_STREAM);
      track.play();
      promise.resolve(true);
    } catch (Exception e){
      promise.reject("Initialize error.", e);
    }
  }

  @ReactMethod
  public void playAudioBuffer(ReadableMap bufferMap, int bufferSize, Promise promise){
    ReadableArray buffer = bufferMap.getArray("buffer");
    byte[] toWrite = new byte[bufferSize];
    for(int i = 0; i < bufferSize; i++)
    {
      toWrite[i] = (byte)(buffer.getInt(i));
    }
    try{
      track.write(toWrite, 0, bufferSize);
      promise.resolve(true);
    } catch (Exception e){
      promise.reject("Play audio buffer error.", e);
    }
  }

  @ReactMethod
  public void uninitialize(Promise promise){
    try{
      track.release();
      promise.resolve(true);
    } catch (Exception e){
      promise.reject("Uninitialize error.", e);
    }
  }
}
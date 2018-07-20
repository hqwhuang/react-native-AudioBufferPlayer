
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

//For debug
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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

  public void writeToFile(byte[] toWrite) throws IOException {
    File myFile = new File("/data/tmp.wav");
    try{
      output = new OutputStreamWriter(new FileOutputStream(myFile));
      output.write(toWrite);
    } catch(IOException ignored){}
  }

  @ReactMethod
  public void playAudioBuffer(ReadableArray buffer, int bufferSize, Promise promise){
    byte[] toWrite = new byte[bufferSize];
    for(int i = 0; i < bufferSize; i++)
    {
      toWrite[i] = (byte)buffer.getInt(i);
    }
    try{
      track.write(toWrite, 0, bufferSize);
      //For debug
      writeToFile(toWrite);

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
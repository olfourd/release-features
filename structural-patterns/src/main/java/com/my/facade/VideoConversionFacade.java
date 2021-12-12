package com.my.facade;

import com.my.facade.model.AudioMixer;
import com.my.facade.model.BitrateReader;
import com.my.facade.model.VideoFile;
import com.my.facade.model.codec.Codec;
import com.my.facade.model.codec.CodecFactory;
import com.my.facade.model.codec.MPEG4CompressionCodec;
import com.my.facade.model.codec.OggCompressionCodec;
import java.io.File;

public class VideoConversionFacade {

  public File convertVideo(String fileName, String format) {
    System.out.println("VideoConversionFacade: conversion started.");
    VideoFile file = new VideoFile(fileName);
    Codec sourceCodec = CodecFactory.extract(file);
    Codec destinationCodec;
    if (format.equals("mp4")) {
      destinationCodec = new OggCompressionCodec();
    } else {
      destinationCodec = new MPEG4CompressionCodec();
    }
    VideoFile buffer = BitrateReader.read(file, sourceCodec);
    VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
    File result = (new AudioMixer()).fix(intermediateResult);
    System.out.println("VideoConversionFacade: conversion completed.");
    return result;
  }
}

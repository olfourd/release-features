package com.my.proxy.model;

import com.my.proxy.model.Video;
import java.util.HashMap;

public interface ThirdPartyYouTubeLib {

  HashMap<String, Video> popularVideos();

  Video getVideo(String videoId);
}

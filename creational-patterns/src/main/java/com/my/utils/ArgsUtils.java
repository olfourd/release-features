package com.my.utils;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class ArgsUtils {

  public static String defineArg(String[] args, String argName) {
    return Arrays.stream(args)
        .filter(arg -> arg.contains(argName))
        .findFirst()
        .map(buttonArg -> StringUtils.substringAfter(buttonArg, "="))
        .orElseThrow(() -> new IllegalArgumentException("Error during defining argument: " + argName));
  }
}

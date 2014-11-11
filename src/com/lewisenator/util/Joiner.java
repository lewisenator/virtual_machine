package com.lewisenator.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kenneth on 11/10/14.
 */
public class Joiner {
  private String on = "";
  private String beginWith = "";
  private String endWith = "";

  private Joiner() {}

  public static Joiner on(String on) {
    Joiner result = new Joiner();
    result.on = on;
    return result;
  }

  public Joiner beginWith(String beginWith) {
    this.beginWith = beginWith;
    return this;
  }

  public Joiner endWith(String endWith) {
    this.endWith = endWith;
    return this;
  }

  public String join(int[] a) {
    List<Integer> b = new LinkedList<>();
    for (int i = 0; i < a.length; i++) b.add(a[i]);
    return join(b);
  }

  public String join(List<?> a) {
    return join(a.toArray());
  }

  public String join(Object[] a) {
    StringBuilder sb = new StringBuilder();
    sb.append(beginWith);
    if (a != null) {
      for (int i = 0; i < a.length; i++) {
        sb.append(a[i].toString());
        if (i < a.length - 1) sb.append(on);
      }
    }
    sb.append(endWith);
    return sb.toString();
  }
}

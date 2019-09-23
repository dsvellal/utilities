/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.samples.pojo;

import java.util.List;

public class CustomObject {
  private final List<String> stringList;

  public CustomObject(final List<String> stringList) {
    this.stringList = stringList;
  }

  public String getListAsString() {
    return stringList.toString();
  }

  public void removeAllStringFromList() {
    stringList.clear();
  }
}

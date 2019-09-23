/*
 * Copyright
 * @author: dsvellal
 *
 */
package com.sample.unit.testing.samples.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CustomObjectTest {
  @Test
  public void testGetListAsString() {
    final List<String> listOfString = getStringList();
    final CustomObject customObject = new CustomObject(getStringList());
    Assert.assertEquals(listOfString.toString(), customObject.getListAsString());
  }

  @Test
  public void testRemoveAllStringFromList() {
    final CustomObject customObject = new CustomObject(getStringList());
    customObject.removeAllStringFromList();
    Assert.assertEquals(Collections.emptyList().toString(), customObject.getListAsString());

  }

  private List<String> getStringList() {
    return new ArrayList<>(Arrays.asList("One"));
  }
}

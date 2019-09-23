/*
 * Copyright of Philips, 2019
 *
 * This file is subject to the terms and conditions defined by Philips,
 * visit https://www.ip.philips.com/licensing/ for more details.
 *
 */
package com.philips.elasticsearch.metrics.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.philips.elasticsearch.metrics.model.PropertiesPojo;
import com.philips.elasticsearch.metrics.model.PropertiesPojoBuilder;

/**
 * The Class PropertiesUtility.
 */
public class PropertiesUtility {

  /** The Constant DEFAULT_PATH. */
  private static final String DEFAULT_PATH = "properties/properties.txt";

  /** The properties. */
  private List<String> properties = new ArrayList<>();

  /**
   * Instantiates a new properties utility.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PropertiesUtility() throws IOException {
    this(DEFAULT_PATH);
  }

  /**
   * Instantiates a new properties utility.
   *
   * @param path the path
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PropertiesUtility(final String path) throws IOException {
    try {
      properties = Files.readAllLines(Paths.get(path));
    } catch (final IOException e) {
      e.printStackTrace(System.out);
      throw e;
    }
  }

  /**
   * Gets the properties pojo.
   *
   * @return the properties pojo
   */
  public PropertiesPojo getPropertiesPojo() {
    if (properties.isEmpty()) {
      throw new IllegalArgumentException("Unable to parse properties as it contains no values to parse");
    }

    PropertiesPojoBuilder builder = new PropertiesPojoBuilder();

    for (final String property : properties) {
      final String[] line = property.split("=");
      if (line.length != 2) {
        throw new IllegalArgumentException("Properties file does not have the right keys or value. Please check.");
      }

      final String key = line[0].trim();
      final String value = line[1].trim();

      switch (key) {
      case "host":
        builder = builder.setHost(value);
        break;
      case "port":
        builder = builder.setPort(Integer.parseInt(value));
        break;
      case "index":
        builder = builder.setIndex(value);
        break;
      case "type":
        builder = builder.setType(value);
        break;
      default:
        throw new IllegalArgumentException("Unable to process the specified key: " + key);
      }
    }

    return builder.build();

  }

}

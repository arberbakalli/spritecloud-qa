package com.spritecloud.model;

import java.util.Objects;

public class Resources implements Data {
  private int id;
  private String name;
  private int year;
  private String color;
  private String pantone_value;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }

  public String getColor() {
    return color;
  }

  public String getPantone_value() {
    return pantone_value;
  }

  @Override
  public String toString() {
    return "Resources(id="
        + id
        + ", name="
        + name
        + ", year="
        + year
        + ", color="
        + color
        + ", pantoneValue="
        + pantone_value
        + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Resources that = (Resources) o;
    return id == that.id
        && year == that.year
        && Objects.equals(name, that.name)
        && Objects.equals(color, that.color)
        && Objects.equals(pantone_value, that.pantone_value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, year, color, pantone_value);
  }
}

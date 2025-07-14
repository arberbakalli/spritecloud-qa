package com.spritecloud.model;

import java.util.List;

public class Pages<T extends Data> {
  private int page;
  private int per_page;
  private int total;
  private int total_pages;
  private List<T> data;
  private Support support;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPer_page() {
    return per_page;
  }

  public void setPer_page(int per_page) {
    this.per_page = per_page;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public Support getSupport() {
    return support;
  }

  public void setSupport(Support support) {
    this.support = support;
  }

  @Override
  public String toString() {
    return "Pages{"
        + "page="
        + page
        + ", per_page="
        + per_page
        + ", total="
        + total
        + ", total_pages="
        + total_pages
        + ", data="
        + data
        + ", support="
        + support
        + '}';
  }

  public class Support {
    private String url;
    private String text;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return "Support(url="
          + this.getUrl()
          + ", text="
          + this.getText()
          + ", pages="
          + Pages.this.page
          + ", data="
          + Pages.this.data
          + ")";
    }
  }
}

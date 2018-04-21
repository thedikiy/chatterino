package com.tdk.edu.chatterino.entity;

public class Message {

  private User author;

  private String content;

  public Message() {
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Message{" +
        "author=" + author +
        ", content='" + content + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Message message = (Message) o;

    return author.equals(message.author) && content.equals(message.content);
  }

  @Override
  public int hashCode() {
    int result = author.hashCode();
    result = 31 * result + content.hashCode();
    return result;
  }
}
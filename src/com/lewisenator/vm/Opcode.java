package com.lewisenator.vm;

/**
 * Created by kenneth on 11/10/14.
 */
public class Opcode {
  private String name;
  private int numArgs;

  public Opcode(String name, int numArgs) {
    this.name = name;
    this.numArgs = numArgs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumArgs() {
    return numArgs;
  }

  public void setNumArgs(int numArgs) {
    this.numArgs = numArgs;
  }
}

package com.lewisenator.vm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenneth on 11/9/14.
 */
public class ByteCode {
  public static final int HALT = 1; //exit the program
  public static final int IADD = 2;  //integer addition
  public static final int ISUB = 3;  //integer subtraction
  public static final int IMUL = 4;  //integer multiplication
  public static final int IDIV = 5;  //integer division
  public static final int JUMP = 6;  //jump.
  public static final int JUMPT = 7;  //jump if top of stack is true
  public static final int JUMPF = 8;  //jump if top of stack is false
  public static final int ICONST = 9;  //push integer constant onto the stack
  public static final int ILT = 10;  //integer less than
  public static final int ILTE = 11;  //integer less than or equal
  public static final int IEQ = 12;  //integer equal
  public static final int LOAD = 13;  //load local variable from stack frame
  public static final int HLOAD = 14;  //load variable from the heap
  public static final int STORE = 15;  //store top of stack into local variable
  public static final int HSTORE = 16;  //store top of stack into the heap
  public static final int POP = 17;  //pop and discard top of stack
  public static final int PRINT = 18;  //pop and print top of stack
  public static final int CALL = 19; // call a function with (address, numArgs)
  public static final int RET = 20;

  public static final Map<Integer, Opcode> OPCODE_MAP = new HashMap<>();
  static {
    OPCODE_MAP.put(HALT, new Opcode("HALT", 0));
    OPCODE_MAP.put(IADD, new Opcode("IADD", 0));
    OPCODE_MAP.put(ISUB, new Opcode("ISUB", 0));
    OPCODE_MAP.put(IMUL, new Opcode("IMUL", 0));
    OPCODE_MAP.put(IDIV, new Opcode("IDIV", 0));
    OPCODE_MAP.put(JUMP, new Opcode("JUMP", 1));
    OPCODE_MAP.put(JUMPT, new Opcode("JUMPT", 1));
    OPCODE_MAP.put(JUMPF, new Opcode("JUMPF", 1));
    OPCODE_MAP.put(ICONST, new Opcode("ICONST", 1));
    OPCODE_MAP.put(ILT, new Opcode("ILT", 0));
    OPCODE_MAP.put(ILTE, new Opcode("ILTE", 0));
    OPCODE_MAP.put(IEQ, new Opcode("IEQ", 0));
    OPCODE_MAP.put(LOAD, new Opcode("LOAD", 1));
    OPCODE_MAP.put(HLOAD, new Opcode("HLOAD", 1));
    OPCODE_MAP.put(STORE, new Opcode("STORE", 1));
    OPCODE_MAP.put(HSTORE, new Opcode("HSTORE", 1));
    OPCODE_MAP.put(POP, new Opcode("POP", 0));
    OPCODE_MAP.put(PRINT, new Opcode("PRINT", 0));
    OPCODE_MAP.put(CALL, new Opcode("CALL", 2));
    OPCODE_MAP.put(RET, new Opcode("RET", 0));
  }
}

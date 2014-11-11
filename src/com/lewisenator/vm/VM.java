package com.lewisenator.vm;

import com.lewisenator.util.Joiner;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Arrays;

import static com.lewisenator.vm.ByteCode.*;

/**
 * Created by kenneth on 11/10/14.
 */
public class VM {
  public static final int INITIAL_HEAP = 8;
  public static final int INITIAL_STACK = 128;

  public static final int FALSE = 0;
  public static final int TRUE = 1;

  private int ip; // instruction pointer
  private int sp = -1; // stack pointer
  private int fp = -1; // frame pointer

  private int[] code;
  private int[] stack;
  private int[] heap;

  public VM(int[] code, int main) {
    this.code = code;
    stack = new int[INITIAL_STACK];
    heap = new int[INITIAL_HEAP];
    ip = main;
  }

  public void execute() { execute(false); }
  public void execute(boolean debug) {
    int opcode = code[ip++];
    int a, b, address, offset, numArgs, returnVal;
    String dis = "", stackString = "";
    while (ip < code.length && opcode != HALT) {
      if (debug) dis = disassemble(opcode);
      switch (opcode) {
        case PRINT:
          a = stack[sp--];
          System.out.println(a);
          break;
        case POP:
          sp--;
          break;
        case IADD:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a + b;
          break;
        case ISUB:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a - b;
          break;
        case IMUL:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a * b;
          break;
        case IDIV:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a / b;
          break;
        case ILT:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a < b ? TRUE : FALSE;
          break;
        case ILTE:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a <= b ? TRUE : FALSE;
          break;
        case IEQ:
          b = stack[sp--]; // 2nd operand is at top of stack
          a = stack[sp--]; // 1st operand
          stack[++sp] = a == b ? TRUE : FALSE;
          break;
        case JUMP:
          ip = code[ip++];
          break;
        case JUMPT:
          a = stack[sp--];
          address = code[ip++];
          if (a == TRUE) ip = address;
          break;
        case JUMPF:
          a = stack[sp--];
          address = code[ip++];
          if (a != TRUE) ip = address;
          break;
        case ICONST:
          stack[++sp] = code[ip++];
          break;
        case LOAD: //load variable from stack frame (local var or function arg) to top of stack
          // offset = 1; //1st local
          // offset = 2; //2nd local
          // offset = -3; //last arg
          // offset = -4; //second to last arg
          offset = code[ip++];
          stack[++sp] = stack[fp + offset];
          break;
        case STORE:
          offset = code[ip++];
          stack[fp + offset] = stack[sp--];
          break;
        case HLOAD:
          address = code[ip++];
          stack[++sp] = heap[address];
          break;
        case HSTORE:
          address = code[ip++];
          heap[address] = stack[sp--];
          break;
        case CALL:
          address = code[ip++];
          numArgs = code[ip++];
          stack[++sp] = numArgs;
          stack[++sp] = fp;
          stack[++sp] = ip;
          fp = sp;
          ip = address;
          break;
        case RET:
          returnVal = stack[sp--];
          sp = fp;
          ip = stack[sp--];
          fp = stack[sp--];
          numArgs = stack[sp--];
          sp -= numArgs;
          stack[++sp] = returnVal;
          break;
        default:
          throw new RuntimeException("Opcode not implemented: " + opcode);
      }
      if (debug) {
        stackString = stackString();
        System.out.println(String.format("%-24s\t%s", dis, stackString));
      }
      opcode = code[ip++];
    }
  }

  private String stackString() {
    int[] usedStack = Arrays.copyOfRange(stack, 0, sp + 1);
    return Joiner.on(" ")
      .beginWith("stack=[")
      .endWith("]")
      .join(usedStack);
  }

  private String disassemble(int opcode) {
    Opcode opcodeData = OPCODE_MAP.get(opcode);
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%04d:\t%-11s", ip, opcodeData.getName()));
    int num = opcodeData.getNumArgs();
    if (num > 0) {
      Integer[] args = new Integer[num];
      for (int i = 0; i < num; i++) {
        args[i] = code[ip + i];
      }
      sb.append(" ");
      sb.append(Joiner.on(", ").join(args));
    }
    return sb.toString();
  }
}

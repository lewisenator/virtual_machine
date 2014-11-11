package com.lewisenator.vm;

import static com.lewisenator.vm.ByteCode.*;
/**
 * Created by kenneth on 11/9/14.
 */
public class MainClass {
  public static final int[] HELLO_WORLD = {
    ICONST, 1,
    ICONST, 2,
    IADD,
    PRINT,
    HALT
  };

  public static final int[] FACTORIAL = {
    // fact(n) {
    //  return 1 if n < 2
/* 0000 */ LOAD, -3,  // load first arg (n)
/* 0002 */ ICONST, 2,  // push 2
/* 0004 */ ILT,        // push n < 2
/* 0005 */ JUMPF, 10,  // if false, skip return
/* 0007 */ ICONST, 1,  // push 1 as result
/* 0009 */ RET,        // return
    //  return n * fact(n - 1)
/* 0010 */ LOAD, -3,  // push n
/* 0012 */ ICONST, 1,  // push 1
/* 0014 */ ISUB,       // n - 1
/* 0015 */ CALL, 0, 1, // func(n - 1)
/* 0018 */ LOAD, -3,  // push n
/* 0020 */ IMUL,       // func(n - 1) * n
/* 0021 */ RET,        // return
    // }
    // print fact(12)
/* 0022 */ ICONST, 12,
/* 0024 */ CALL, 0, 1,
/* 0027 */ PRINT,
/* 0028 */ HALT
  };



  public static void main(String[] args) {
//    VM vm = new VM(HELLO_WORLD, 0);
//    vm.execute(true);
    VM vm = new VM(FACTORIAL, 22);
    vm.execute(true);
  }
}

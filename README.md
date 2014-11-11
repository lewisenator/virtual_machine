# A simple stack based virtual machine

### About

This is a stack based virtual machine to serve as a target for my toy language projects. The goal is simplicity.

The virtual machine only supports integer and boolean data types for now. Booleans are just integers where 0 is false and anything else is true.

There is a list of currently supported operations in ByteCode.java.

All opcodes and arguments are 32 bit integers. The number of arguments, if any, is determined by the type of operation. The arguments are either integer or boolean literals, addresses in code memory for branch instructions, stack addresses relative to the frame pointer for function arguments and local variables, or addresses on the heap for global variables.

### Short Term Goals

1. Build a simple assembler with support for labels and functions so that assembly programs can be written without manually tracking memory addresses.
2. Add support for floating point, character, and string data types.
3. Load programs from code archives off of disk. Currently, programs have to be passed to the VM in java code.
4. Rework the bytecode so that opcodes are represented in 1 byte instead of a wasteful 32 bit int. The first 2 bits of the opcode could be a number spefying the number of arguments.

### License

This project is released under the [MIT License](http://www.opensource.org/licenses/MIT).


package org.openjdk.btrace.instr;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class BTraceMethodVisitor extends MethodVisitor {
  private final MethodInstrumentorHelper mHelper;

  public BTraceMethodVisitor(MethodVisitor mv, MethodInstrumentorHelper mHelper) {
    super(Opcodes.ASM9, mv);
    this.mHelper = mHelper;
  }

  public final int storeAsNew() {
    return mHelper.storeAsNew();
  }

  public final int storeNewLocal(Type t) {
    int index = mHelper.newVar(t);
    super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), index);
    return index;
  }

  public final void addTryCatchHandler(Label start, Label handler) {
    mHelper.addTryCatchHandler(start, handler);
  }

  public void insertFrameReplaceStack(Label l, Type... stack) {
    mHelper.insertFrameReplaceStack(l, stack);
  }

  public void insertFrameAppendStack(Label l, Type... stack) {
    mHelper.insertFrameAppendStack(l, stack);
  }

  public void insertFrameSameStack(Label l) {
    mHelper.insertFrameSameStack(l);
  }
}

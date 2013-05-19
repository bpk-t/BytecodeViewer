package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:32
 * To change this template use File | Settings | File Templates.
 */
case class IntegerInfo(_index : Int, value : Int) extends CpInfo(_index, CpInfo.kCONSTANT_Integer)
object IntegerInfo {
  val kValueLength = 4

  val kDataLength = CpInfo.kTagLength + kValueLength
}

/*
CONSTANT_Integer_info {
u1 tag;
u4 bytes;
}
*/
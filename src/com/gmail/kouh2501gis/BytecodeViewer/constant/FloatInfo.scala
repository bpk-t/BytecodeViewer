package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
case class FloatInfo(_index : Int, value : Float) extends CpInfo(_index, CpInfo.kCONSTANT_Float)
object FloatInfo {
  val kValueLength = 4
  val kDataLength = CpInfo.kTagLength + kValueLength
}
/*
CONSTANT_Float_info {
u1 tag;
u4 bytes;
}
*/
package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:40
 * To change this template use File | Settings | File Templates.
 */
case class DoubleInfo(_index : Int, value : Double) extends CpInfo(_index, CpInfo.kCONSTANT_Double)
object DoubleInfo {
  val kValueLength = 8

  val kDataLength = CpInfo.kTagLength + kValueLength
}
/*
Format
CONSTANT_Double_info {
u1 tag;
u4 high_bytes;
u4 low_bytes;
}
*/
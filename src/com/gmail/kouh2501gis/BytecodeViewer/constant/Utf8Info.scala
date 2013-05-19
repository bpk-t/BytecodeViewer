package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:46
 * To change this template use File | Settings | File Templates.
 */
case class Utf8Info(_index : Int, length : Int, value : String) extends CpInfo(_index, CpInfo.kCONSTANT_Utf8) {
  def kDataLength = {
    CpInfo.kTagLength + Utf8Info.kLengthLength + length
  }
}
object Utf8Info {
  val kLengthLength = 2
}
/*
Format
CONSTANT_Utf8_info {
u1 tag;
u2 length;
u1 bytes[length];
}
*/
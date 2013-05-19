package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:37
 * To change this template use File | Settings | File Templates.
 */
case class LongInfo(_index : Int, value : Long) extends CpInfo(_index, CpInfo.kCONSTANT_Long)
object LongInfo
{
  val kValueLength = 8

  val kDataLength = CpInfo.kTagLength + kValueLength
}
/*
CONSTANT_Long_info {
u1 tag;
u4 high_bytes;
u4 low_bytes;
}
*/
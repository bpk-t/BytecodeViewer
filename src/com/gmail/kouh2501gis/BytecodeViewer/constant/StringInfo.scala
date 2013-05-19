package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:27
 * To change this template use File | Settings | File Templates.
 */
case class StringInfo(_index : Int, stringIndex : Int) extends CpInfo(_index, CpInfo.kCONSTANT_String)
object StringInfo {
  val kStringIndexLength = 2

  val kDataLength = CpInfo.kTagLength + kStringIndexLength
}

/*
Format
CONSTANT_String_info {
u1 tag;
u2 string_index;
}
*/
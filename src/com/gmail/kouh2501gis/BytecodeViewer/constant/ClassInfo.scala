package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 0:57
 * To change this template use File | Settings | File Templates.
 */
case class ClassInfo(_index : Int, nameIndex : Int) extends CpInfo(_index, CpInfo.kCONSTANT_Class)
object ClassInfo {
  val kNameLength = 2

  val kDateLength = CpInfo.kTagLength + kNameLength
}
/*
 format
 CONSTANT_Class_info {
  u1 tag;
  u2 name_index;
 }
*/
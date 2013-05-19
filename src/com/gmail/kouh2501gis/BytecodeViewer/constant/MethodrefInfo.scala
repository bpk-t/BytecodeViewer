package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:20
 * To change this template use File | Settings | File Templates.
 */
case class MethodrefInfo(_index : Int, classIndex : Int, nameAndTypeIndex : Int)
  extends CpInfo(_index, CpInfo.kCONSTANT_Methodref)
object MethodrefInfo {
  val kClassIndexLength = 2
  val kNameAndTypeIndexLength = 2

  val kDataLength = CpInfo.kTagLength + kClassIndexLength + kNameAndTypeIndexLength
}

/*
CONSTANT_Methodref_info {
u1 tag;
u2 class_index;
u2 name_and_type_index;
}
*/
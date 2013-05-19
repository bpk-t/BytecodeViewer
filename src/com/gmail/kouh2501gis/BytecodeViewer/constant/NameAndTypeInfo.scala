package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 1:41
 * To change this template use File | Settings | File Templates.
 */
case class NameAndTypeInfo(_index : Int, nameIndex : Int, descriptorIndex : Int)
  extends CpInfo(_index, CpInfo.kCONSTANT_NameAndType)
object NameAndTypeInfo {
  val kNameIndexLength = 2
  val kDescriptorIndexLength = 2

  val kDataLength = CpInfo.kTagLength + kNameIndexLength + kDescriptorIndexLength
}
/*
Format
CONSTANT_NameAndType_info {
u1 tag;
u2 name_index;
u2 descriptor_index;
}
*/
package com.gmail.kouh2501gis.BytecodeViewer.constant

/**
 * Constant Pool Infomation
 *
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/19
 * Time: 0:43
 * To change this template use File | Settings | File Templates.
 */
class CpInfo(var index_ : Int, var tag_ : Int)
{
  def tag = tag_
  def index = index_
  def setIndex(value : Int) {
    index_ = value
  }
}
object CpInfo {

  val kTagLength = 1
  /**
   * VM Spec The class File Format / The Constant Poolから抜粋
   */
  val kCONSTANT_Class = 7

  val kCONSTANT_Fieldref = 9

  val kCONSTANT_Methodref = 10

  val kCONSTANT_InterfaceMethodref = 11

  val kCONSTANT_String = 8

  val kCONSTANT_Integer = 3

  val kCONSTANT_Float = 4

  val kCONSTANT_Long = 5

  val kCONSTANT_Double = 6

  val kCONSTANT_NameAndType = 12

  val kCONSTANT_Utf8 = 1

  val kUndefined = -1
}

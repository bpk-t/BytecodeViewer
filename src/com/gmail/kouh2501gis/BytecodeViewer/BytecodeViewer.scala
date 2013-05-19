package com.gmail.kouh2501gis.BytecodeViewer

import java.io.{FileInputStream, File}
import com.gmail.kouh2501gis.BytecodeViewer.constant._

/**
 * Created with IntelliJ IDEA.
 * User: kouhama
 * Date: 13/05/18
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
object BytecodeViewer {

  def main(args : Array[String]) : Unit = {

    if (args.length == 0) {
      println("Bad Argument")
      return ;
    }

    val classFile = new File(args(0))
    if (!classFile.exists()) {
      println("File Not Found")
      return ;
    }

    val in = new FileInputStream(classFile)
    val bytes = new Array[Byte](classFile.length().toInt)

    in.read(bytes)
    in.close()

    var cls = new ClassFile()
    cls.setData(bytes)

    println("magic = " + cls.magic)
    println("minorVersion = " + cls.minorVersion)
    println("majorVersion = " + cls.majorVersion)
    println("constant pool count = " + cls.constantPoolCount)

    cls.constantPool.map(e => {
      print(e.index + "  " + e.tag + ", ")
      e match {
        case x : Utf8Info => {
          println("value=" + x.value)
        }
        case x : StringInfo => {
          println("string Index=" + x.stringIndex)
        }
        case x : ClassInfo => {
          println("name Index=" + x.nameIndex)
        }
        case x : MethodrefInfo => {
          println("classNameIndex=" + x.classIndex + ", nameAndTypeIndex=" + x.nameAndTypeIndex)
        }
        case x : NameAndTypeInfo => {
          println("nameIndex=" + x.nameIndex + ", descriptorIndex=" + x.descriptorIndex)
        }
        case x : FieldrefInfo => {
          println("class index=" + x.classIndex + ", nameAndTypeIndex=" + x.nameAndTypeIndex)
        }
        case _ => {
          println()
        }
      }
    })

    printf("access flag=%X", cls.accessFlag.flag)
    println()
    println("access flag=" + cls.accessFlag)

    println("this class=" + cls.thisClass)
    println("super class=" + cls.superClass)

    println("interface count=" + cls.interfaceCount)
    println("fields count=" + cls.fieldsCount)

    println("method count=" + cls.methodsCount)
  }
}

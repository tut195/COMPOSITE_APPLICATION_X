package com.babenkovladimir.composite_application_x.andersen

//abstract class ConverterKt<I, O>{}
//
//
//constructor(private val reporter: ExceptionReporter? = null) {
//
//    abstract fun convert(input: I): O
//
//    fun convertList(list: MutableList<I>, propagateError: Boolean = false): MutableList<O> {
//        val out = arrayListOf<O>()
//        list.forEach {
//            try {
//                out.add(convert(it))
//            } catch (e: Exception) {
//                reporter?.reportThrowable(e)
//                if (propagateError) {
//                    throw e
//                }
//            }
//        }
//        return out
//    }
//
//
//    abstract fun convertBack(input: O): I
//
//    fun convertListBack(list: MutableList<O>, propagateError: Boolean = false): MutableList<I> {
//        val out = arrayListOf<I>()
//        list.forEach {
//            try {
//                out.add(convertBack(it))
//            } catch (e: Exception) {
//                reporter?.reportThrowable(e)
//                if (propagateError) {
//                    throw e
//                }
//            }
//        }
//        return out
//    }
//
//}
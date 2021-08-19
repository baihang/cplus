/******************************************************************************************
 * Data Structures in C++
 * ISBN: 7-302-33064-6 & 7-302-33065-3 & 7-302-29652-2 & 7-302-26883-3
 * Junhui DENG, deng@tsinghua.edu.cn
 * Computer Science & Technology, Tsinghua University
 * Copyright (c) 2003-2020. All rights reserved.
 ******************************************************************************************/

#pragma once

/******************************************************************************************
 * 将List各方法的实现部分，简洁地引入List.h
 * 效果等同于将这些实现直接汇入List.h
 * 在export尚未被编译器支持前，如此可将定义与实现分离，以便课程讲解
 ******************************************************************************************/
// #include "_share/release.h"
// #include "_share/util.h"

#include <stdlib.h>
#include <stdio.h>

#include "listNode.h"

#include "list_bracket.h"

#include "list_initialize.h"
#include "list_copyNodes.h"
#include "list_constructor_by_copying.h"
#include "list_destructor.h"

#include "List_find.h"
#include "List_search.h"

#include "List_insert.h"
#include "list_remove.h"
#include "list_clear.h"

#include "List_traverse.h"

#include "List_sort.h"
#include "List_insertionsort.h"
#include "List_selectMax.h"
#include "List_SelectionSort.h"
#include "List_merge.h"
#include "List_mergesort.h"
#include "List_RadixSort.h"

#include "List_deduplicate.h"
#include "List_uniquify.h"

#include "list_reverse1.h"
//#include "List_reverse2.h"
//#include "List_reverse3.h"

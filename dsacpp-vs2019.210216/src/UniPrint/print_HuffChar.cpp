/******************************************************************************************
 * Data Structures in C++
 * ISBN: 7-302-33064-6 & 7-302-33065-3 & 7-302-29652-2 & 7-302-26883-3
 * Junhui DENG, deng@tsinghua.edu.cn
 * Computer Science & Technology, Tsinghua University
 * Copyright (c) 2003-2020. All rights reserved.
 ******************************************************************************************/

#include "print.h"

/******************************************************************************************
 * Huffman�������ַ�
 ******************************************************************************************/
void UniPrint::p ( HuffChar& e ) { printf ( "[%c]:%-5d", e.ch, e.weight ); }
/******************************************************************************************
 * Data Structures in C++
 * ISBN: 7-302-33064-6 & 7-302-33065-3 & 7-302-29652-2 & 7-302-26883-3
 * Junhui DENG, deng@tsinghua.edu.cn
 * Computer Science & Technology, Tsinghua University
 * Copyright (c) 2003-2020. All rights reserved.
 ******************************************************************************************/

#pragma once

template <typename T> QListNodePosi<T> //将e作为当前节点的后继、b的上邻插入Quadlist
QuadlistNode<T>::insertAsSuccAbove ( T const& e, QListNodePosi<T> b ) {
   QListNodePosi<T> x = new QuadlistNode<T> ( e, this, succ, NULL, b ); //创建新节点
   succ->pred = x; succ = x; //设置水平逆向链接
   if ( b ) b->above = x; //设置垂直逆向链接
   return x; //返回新节点的位置
}
#include "BinNode.h"

template <typename T> class BinTree
{
protected:
    int _size; 
    BinNodePosi(T) _root;
    virtual int updateHeight(BinNodePosi(T) x);
    void updateHeightAbove(BinNodePosi(T) x);

private:
    /* data */
public:
    BinTree(): _size(0), _root(NULL){};
    ~BinTree(){
        if(_size <= 0) remove(_root);
    };

    int size(){return _size;}
    bool isEmpty(){return !_root;}

    BinNodePosi(T) root() const {return _root;}

};

template <typename T> int BinTree<T>::updateHeight(BinNodePosi(T) x){
    return x->height = 1 + max();
}

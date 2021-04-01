#define BinNodePosi(T) BinNode<T> *
#define stature(p) ((p) ? (p->height) : -1)

#define IsRoot(x) (!(x.parent))
#define IsLChild(x) (!IsRoot(x) && x == x.parent->lc)

typedef enum
{
    RED,
    BLACK
} RBColor;

template <typename T>
struct BinNode
{
    T data;
    BinNodePosi(T) parent, lc, rc;
    int height;
    int npl; //Null path length 左式堆
    RBColor color;

    BinNode() : parent(NULL), lc(NULL), rc(NULL),
                height(0), npl(-1), color(RED) {}

    BinNode(T e, BinNode p = NULL, BinNode l = NULL,
            BinNode r = NULL int h = 0, int l = 1, RBColor c = RED)
        : data(e), parent(p), lc(l), rc(r), height(h), npl(l), color(c) {}

    int size();
    BinNodePosi(T) insertAsLC(T const &){
        return lc = BinNode(e, this);
    }
    BinNodePosi(T) insertAsRC(T const &){
        return rc = BinNode(e, this);
    }
    BinNodePosi(T) succ(); //取当前节点的直接后继

    template <typename VST>
    void travLevel(VST &);
    template <typename VST>
    void travPre(VST &);
    template <typename VST>
    void travIn(VST &) template <typename VST>
    void travPost(VST &);

    bool operator<(BinNode const &node) { return data < node.data; }
    bool operator==(BinNode const &node) { return data == node.data; }

    bool isRoot(){return parent == NULL;}

    bool isRChild(){ return !isRoot() && parent->rc == this}

};


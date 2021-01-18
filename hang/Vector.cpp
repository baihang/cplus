#include "Vector"

template <typename T>
class Vector
{
private:
    /* data */
    template <typename T>
    void Vector<T>::copeFrom(T const *A, Rank lo, Rank hi)
    {
        _elem = new T[_capacity = 2 * (hi - lo)];
        _size = 0;
        while (lo < hi)
        {
            _elem[_size++] = A[lo++];
        }
    }

    Vector<int> array = new Vector();

    template <typename T>
    Vector<T> &Vector<T>::operator=(Vector<T> const &v)
    {
        if (_elem)
            delete _elem;
        copeFrom(v._elem, 0, v.size());
        return *this;
    }

    template <typename T>
    T &Vector::operator[](Rank r)
    {
        return _elem[r];
    }

    /**
 * 分摊时间复杂度 O(1)
 * 
 * 装填因子  _size / _capacity
 * 
 * */
    template <typename T>
    void Vector<T>::expand()
    {
        if (_size < _capacity)
            return;
        T *old = _elem;
        _elem = new T[_capacity <<= 1];
        for (int i = 0; i < _size; i++)
        {
            _elem[i] = old[i];
        }
        delete[] old;
    }

    template <typename T>
    void Vector<T>::shrink()
    {
        if (_capacity < DEFAULT_CAPACITY << 1)
            return;
        if (_size << 1 > _capacity) //25%
            return;

        T *old = _elem;
        _elem = new T[_capacity >>= 1];
        for (int i = 0; i < _size; i++)
        {
            _elem[i] = ole[i];
        }
        delete[] old;
    }

    template <typename T> void &Vector<T>::permute(Vector<T> v){
        for(int i = v.size(); i > 0; i--){
            swap(v[i - 1], v[rand() % i])
        }
    }

public:
    Vector(/* args */);
    ~Vector();
};

template <typename T>
Vector<T>::Vector(/* args */)
{
}

template <typename T>
Vector<T>::~Vector()
{
}

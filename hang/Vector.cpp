#include "Vector"

class Vector
{
private:
    /* data */
    template <typename T>
    void Vector::copeFrom(T const *A, Rank lo, Rank hi)
    {
        _elem = new T[_capacity = 2 * (hi - lo)];
        _size = 0;
        while(lo < hi){
            _elem[_size] = A[lo++];
        }
    }

public:
    Vector(/* args */);
    ~Vector();
};

Vector::Vector(/* args */)
{
}

Vector::~Vector()
{
}

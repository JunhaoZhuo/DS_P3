package utils.tuples;

public class Sextet<T, U, V, W, X, Y> {
    T element1;
    U element2;
    V element3;
    W element4;
    X element5;
    Y element6;

    public Sextet(T element1, U element2, V element3, W element4, X element5, Y element6) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
        this.element4 = element4;
        this.element5 = element5;
        this.element6 = element6;
    }

    public T getElement1() {
        return element1;
    }

    public U getElement2() {
        return element2;
    }

    public V getElement3() {
        return element3;
    }

    public W getElement4() {
        return element4;
    }

    public X getElement5() {
        return element5;
    }

    public Y getElement6() {
        return element6;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((element1 == null) ? 0 : element1.hashCode());
        result = prime * result + ((element2 == null) ? 0 : element2.hashCode());
        result = prime * result + ((element3 == null) ? 0 : element3.hashCode());
        result = prime * result + ((element4 == null) ? 0 : element4.hashCode());
        result = prime * result + ((element5 == null) ? 0 : element5.hashCode());
        result = prime * result + ((element6 == null) ? 0 : element6.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sextet<?, ?, ?, ?, ?, ?> other = (Sextet<?, ?, ?, ?, ?, ?>) obj;
        if (element1 == null) {
            if (other.element1 != null)
                return false;
        } else if (!element1.equals(other.element1))
            return false;
        if (element2 == null) {
            if (other.element2 != null)
                return false;
        } else if (!element2.equals(other.element2))
            return false;
        if (element3 == null) {
            if (other.element3 != null)
                return false;
        } else if (!element3.equals(other.element3))
            return false;
        if (element4 == null) {
            if (other.element4 != null)
                return false;
        } else if (!element4.equals(other.element4))
            return false;
        if (element5 == null) {
            if (other.element5 != null)
                return false;
        } else if (!element5.equals(other.element5))
            return false;
        if (element6 == null) {
            if (other.element6 != null)
                return false;
        } else if (!element6.equals(other.element6))
            return false;
        return true;
    }
}

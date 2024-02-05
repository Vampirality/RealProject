public class RealOperation {
    
    /* MAIN PURPOSED FUNCTIONS */
    public static Real add(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite || n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null && n.realInteger == null && n.realDecimal == null) {
            return new Real("zero");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            return n;
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return m;
        }
        if (isEquals(m, n) && !m.isNegative == n.isNegative) {
            return new Real("zero");
        }

        int commonLength, mLength, nLength, i, j, k, encode;
        boolean isNegative;
        int[] arrM, arrN;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }

        if (mLength >= nLength) {
            i = 0;
            j = mLength - nLength;
            k = mLength;
            commonLength = mLength;
        }
        else {
            i = nLength - mLength;
            j = 0;
            k = nLength;
            commonLength = nLength;
        }

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }

        encode = Math.max(mLength, nLength);
        commonLength += encode;
        arrM = new int[commonLength];
        arrN = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, arrM, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, arrM, k, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, arrN, j, n.realInteger.length);
        }
        if (n.realDecimal != null) {
            System.arraycopy(n.realDecimal, 0, arrN, k, n.realDecimal.length);
        }

        i = commonLength - 1;
        if (m.isNegative == n.isNegative) {
            while (true) {
                arrM[i] += arrN[i];
                if (i == 0) {
                    break;
                }
                if (arrM[i] >= 1000000) {
                    arrM[i - 1] += 1;
                    arrM[i] -= 1000000;
                }
                i--;
            }
            if (arrM[0] >= 1000000) {
                arrM = increaseFrontZero(arrM, 1);
                arrM[0] = 1;
                arrM[1] -= 1000000;
            }
            return new Real(arrM, encode, m.isNegative);
        }
        else {
            int[] arrX, arrY;
            isNegative = isMoreOrEquals(arrM, arrN);
            if (isNegative) {
                arrX = arrM;
                arrY = arrN;
                isNegative = m.isNegative;
            }
            else {
                arrX = arrN;
                arrY = arrM;
                isNegative = n.isNegative;
            }

            while (true) {
                arrX[i] -= arrY[i];
                if (i == 0) {
                    break;
                }
                if (arrX[i] < 0) {
                    arrX[i] += 1000000;
                    arrX[i - 1] -= 1;
                }
                i--;
            }
            return new Real(arrX, encode, isNegative);
        }
    }

    public static Real minus(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite || n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null && n.realInteger == null && n.realDecimal == null) {
            return new Real("zero");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            assert n.realInteger != null;
            return new Real(n.realInteger, n.realDecimal, !n.isNegative);
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return m;
        }
        if (isEquals(m, n) && m.isNegative == n.isNegative) {
            return new Real("zero");
        }
        int commonLength, mLength, nLength, i, j, k, encode;
        boolean isNegative;
        int[] arrM, arrN;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }

        if (mLength >= nLength) {
            i = 0;
            j = mLength - nLength;
            k = mLength;
            commonLength = mLength;
        }
        else {
            i = nLength - mLength;
            j = 0;
            k = nLength;
            commonLength = nLength;
        }

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }

        encode = Math.max(mLength, nLength);
        commonLength += encode;
        arrM = new int[commonLength];
        arrN = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, arrM, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, arrM, k, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, arrN, j, n.realInteger.length);
        }
        if (n.realDecimal != null) {
            System.arraycopy(n.realDecimal, 0, arrN, k, n.realDecimal.length);
        }

        i = commonLength - 1;
        if (m.isNegative != n.isNegative) {
            while (true) {
                arrM[i] += arrN[i];
                if (i == 0) {
                    break;
                }
                if (arrM[i] >= 1000000) {
                    arrM[i - 1] += 1;
                    arrM[i] -= 1000000;
                }
                i--;
            }
            if (arrM[0] >= 1000000) {
                arrM = increaseFrontZero(arrM, 1);
                arrM[0] = 1;
                arrM[1] -= 1000000;
            }
            return new Real(arrM, encode, m.isNegative);
        }
        else {
            int[] arrX, arrY;
            isNegative = isMoreOrEquals(arrM, arrN);
            if (isNegative) {
                arrX = arrM;
                arrY = arrN;
                isNegative = m.isNegative;
            } else {
                arrX = arrN;
                arrY = arrM;
                isNegative = n.isNegative;
            }

            while (true) {
                arrX[i] -= arrY[i];
                if (i == 0) {
                    break;
                }
                if (arrX[i] < 0) {
                    arrX[i] += 1000000;
                    arrX[i - 1] -= 1;
                }
                i--;
            }
            return new Real(arrX, encode, isNegative);
        }
    }

    public static Real multiply(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite || n.isInfinite) {
            return new Real("infinite");
        }
        if ((m.realInteger == null && m.realDecimal == null) || (n.realInteger == null && n.realDecimal == null)) {
            return new Real("zero");
        }
        if (m.realInteger != null) {
            if (m.realInteger.length == 1 && m.realInteger[0] == 1 && m.realDecimal == null) {
                return n;
            }
        }
        if (n.realInteger != null) {
            if (n.realInteger.length == 1 && n.realInteger[0] == 1 && n.realDecimal == null) {
                return m;
            }
        }

        int i, j, k, l, encode, x_front, x_back, y_front, y_back, saving;
        int[] arrM, arrN;

        if (m.realInteger == null) {
            i = 0;
        }
        else {
            i = m.realInteger.length;
        }

        if (m.realDecimal == null) {
            encode = 0;
            arrM = new int[i];
        }
        else {
            encode = m.realDecimal.length;
            arrM = new int[i + m.realDecimal.length];
        }

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, arrM, 0, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, arrM, i, m.realDecimal.length);
        }

        if (n.realInteger == null) {
            i = 0;
        }
        else {
            i = n.realInteger.length;
        }
        if (n.realDecimal == null) {
            arrN = new int[i];
        }
        else {
            encode += n.realDecimal.length;
            arrN = new int[i + n.realDecimal.length];
        }

        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, arrN, 0, n.realInteger.length);
        }
        if (n.realDecimal != null) {
            System.arraycopy(n.realDecimal, 0, arrN, i, n.realDecimal.length);
        }

        int[] arrX, arrY;
        if (arrM.length >= arrN.length) {
            arrX = arrN;
            arrY = arrM;
        }
        else {
            arrX = arrM;
            arrY = arrN;
        }

        int[] arrResult = new int[arrM.length + arrN.length];
        j = arrY.length - 1;
        l = arrX.length - 1;
        while (j >= 0) {
            y_front = arrY[j] / 1000;
            y_back = arrY[j] % 1000;
            i = l;
            k = i + j + 1;
            while (i >= 0) {
                x_front = arrX[i] / 1000;
                x_back = arrX[i] % 1000;
                saving = (x_front * y_back) + (x_back * y_front);
                arrResult[k] += x_back * y_back + (saving % 1000) * 1000;
                arrResult[k - 1] += (x_front * y_front) + (saving / 1000);
                arrResult[k - 1] += arrResult[k] / 1000000;
                arrResult[k] %= 1000000;
                i--;
                k--;
            }
            j--;
        }
        return new Real(arrResult, encode, m.isNegative != n.isNegative);
    }

    public static Real divide(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite && n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite) {
            return new Real("infinite");
        }
        if (n.isInfinite) {
            return new Real("zero");
        }
        if ((m.realInteger == null && m.realDecimal == null) && (n.realInteger == null && n.realDecimal == null)) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            return new Real("zero");
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return new Real("infinite");
        }
        if (n.realInteger != null) {
            if (n.realInteger.length == 1 && n.realInteger[0] == 1 && n.realDecimal == null) {
                return m;
            }
        }

        /* STEP 1 : scaling */
        int commonLength, mLength, nLength, i, j, k, exp;
        int[] dividend, subtract, resultInteger, resultDecimal;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }
        if (mLength > nLength) {
            i = 0;
            commonLength = mLength;
            resultInteger = new int[mLength - nLength + 1];
        }
        else {
            i = nLength - mLength;
            commonLength = nLength;
            resultInteger = new int[1];
        }
        j = commonLength;

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }
        commonLength += Math.max(mLength, nLength);
        dividend = new int[commonLength];
        subtract = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, dividend, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, dividend, j, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, subtract, 0, n.realInteger.length);
            if (n.realDecimal != null) {
                System.arraycopy(n.realDecimal, 0, subtract, n.realInteger.length, n.realDecimal.length);
            }
        } else {
            System.arraycopy(n.realDecimal, 0, subtract, 0, n.realDecimal.length);
        }

        /* STEP 2 : n-synchronization */
        exp = 1;
        k = dividend.length - 1;
        if (i == 0) {
            i = dividend[0];
            while (i > 0) {
                i /= 10;
                exp *= 10;
            }
            i = subtract[0];
            while (i > 0) {
                i /= 10;
                exp /= 10;
            }
            if (exp > 1) {
                i = 0;
                j = 1000000 / exp;
                while (i < k) {
                    subtract[i] = (subtract[i] * exp) + (subtract[i + 1] / j);
                    subtract[i + 1] %= j;
                    i++;
                }
                subtract[k] *= exp;
            } else {
                exp = 1;
            }
        }

        /* STEP 3 : division to find real integer */
        i = 0;
        while (true) {
            while (isMoreOrEquals(dividend, subtract)) {
                j = k;
                while (j > 0) {
                    dividend[j] -= subtract[j];
                    if (dividend[j] < 0) {
                        dividend[j] += 1000000;
                        dividend[j - 1] -= 1;
                    }
                    j--;
                }
                dividend[0] -= subtract[0];
                resultInteger[i] += exp;
            }
            if (exp == 1) {
                exp = 100000;
                i++;
            } else {
                exp /= 10;
            }
            if (i == resultInteger.length) {
                break;
            }
            j = k;
            while (j > 0) {
                subtract[j] = subtract[j] / 10 + (subtract[j - 1] % 10) * 100000;
                j--;
            }
            subtract[0] /= 10;
        }

        /* STEP 4 : transformation of m, n */
        i = 0;
        while (i < commonLength) {
            if (dividend[i] != 0 || subtract[i] != 0) {
                break;
            }
            i++;
        }

        int[] arrX = new int[commonLength - i + 1];
        int[] arrY = new int[commonLength - i + 1];

        System.arraycopy(dividend, i, arrX, 0, dividend.length - i);
        System.arraycopy(subtract, i, arrY, 0, subtract.length - i);

        k = arrX.length - 1;
        j = k;
        while (j > 0) {
            arrY[j] = arrY[j] / 10 + (arrY[j - 1] % 10) * 100000;
            j--;
        }
        arrY[0] /= 10;

        /* STEP 5 : division to find real decimal */
        resultDecimal = new int[5];
        i = 0;
        while (true) {
            while (isMoreOrEquals(arrX, arrY)) {
                j = k;
                while (j > 0) {
                    arrX[j] -= arrY[j];
                    if (arrX[j] < 0) {
                        arrX[j] += 1000000;
                        arrX[j - 1] -= 1;
                    }
                    j--;
                }
                arrX[0] -= arrY[0];
                resultDecimal[i] += exp;
            }
            if (exp == 1) {
                exp = 100000;
                i++;
                if (i == 5) {
                    break;
                }
                while (j < k) {
                    arrX[j] = arrX[j + 1];
                    arrY[j] = arrY[j + 1];
                    j++;
                }
                arrX[k] = 0;
                arrY[k] = 0;
            } else {
                exp /= 10;
            }
            j = k;
            while (j > 0) {
                arrY[j] = arrY[j] / 10 + (arrY[j - 1] % 10) * 100000;
                j--;
            }
            arrY[0] /= 10;
        }

        return new Real(resultInteger, resultDecimal, m.isNegative != n.isNegative);
    }

    public static Real divide(Real m, Real n, int point) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite && n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite) {
            return new Real("infinite");
        }
        if (n.isInfinite) {
            return new Real("zero");
        }
        if ((m.realInteger == null && m.realDecimal == null) && (n.realInteger == null && n.realDecimal == null)) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            return new Real("zero");
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return new Real("infinite");
        }
        if (n.realInteger != null) {
            if (n.realInteger.length == 1 && n.realInteger[0] == 1 && n.realDecimal == null) {
                return m;
            }
        }

        /* STEP 1 : scaling */
        int commonLength, mLength, nLength, i, j, k, l, exp;
        int[] dividend, subtract, resultInteger, resultDecimal;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }
        if (mLength > nLength) {
            i = 0;
            commonLength = mLength;
            resultInteger = new int[mLength - nLength + 1];
        }
        else {
            i = nLength - mLength;
            commonLength = nLength;
            resultInteger = new int[1];
        }
        j = commonLength;

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }
        commonLength += Math.max(mLength, nLength);
        dividend = new int[commonLength];
        subtract = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, dividend, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, dividend, j, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, subtract, 0, n.realInteger.length);
            if (n.realDecimal != null) {
                System.arraycopy(n.realDecimal, 0, subtract, n.realInteger.length, n.realDecimal.length);
            }
        } else {
            System.arraycopy(n.realDecimal, 0, subtract, 0, n.realDecimal.length);
        }

        /* STEP 2 : n-synchronization */
        exp = 1;
        k = dividend.length - 1;
        if (i == 0) {
            i = dividend[0];
            while (i > 0) {
                i /= 10;
                exp *= 10;
            }
            i = subtract[0];
            while (i > 0) {
                i /= 10;
                exp /= 10;
            }
            if (exp > 1) {
                i = 0;
                j = 1000000 / exp;
                while (i < k) {
                    subtract[i] = (subtract[i] * exp) + (subtract[i + 1] / j);
                    subtract[i + 1] %= j;
                    i++;
                }
                subtract[k] *= exp;
            } else {
                exp = 1;
            }
        }

        /* STEP 3 : division to find real integer */
        i = 0;
        while (true) {
            while (isMoreOrEquals(dividend, subtract)) {
                j = k;
                while (j > 0) {
                    dividend[j] -= subtract[j];
                    if (dividend[j] < 0) {
                        dividend[j] += 1000000;
                        dividend[j - 1] -= 1;
                    }
                    j--;
                }
                dividend[0] -= subtract[0];
                resultInteger[i] += exp;
            }
            if (exp == 1) {
                exp = 100000;
                i++;
            } else {
                exp /= 10;
            }
            if (i == resultInteger.length) {
                break;
            }
            j = k;
            while (j > 0) {
                subtract[j] = subtract[j] / 10 + (subtract[j - 1] % 10) * 100000;
                j--;
            }
            subtract[0] /= 10;
        }

        /* STEP 4 : transformation of m, n */
        if (point <= 0) {
            return new Real(resultInteger, 0, m.isNegative != n.isNegative);
        }

        i = 0;
        while (i < commonLength) {
            if (dividend[i] != 0 || subtract[i] != 0) {
                break;
            }
            i++;
        }

        int[] arrX = new int[commonLength - i + 1];
        int[] arrY = new int[commonLength - i + 1];

        System.arraycopy(dividend, i, arrX, 0, dividend.length - i);
        System.arraycopy(subtract, i, arrY, 0, subtract.length - i);

        k = arrX.length - 1;
        j = k;
        while (j > 0) {
            arrY[j] = arrY[j] / 10 + (arrY[j - 1] % 10) * 100000;
            j--;
        }
        arrY[0] /= 10;

        /* STEP 5 : division to find real decimal */
        resultDecimal = new int[(point - 1)/6 + 1];
        i = 0;
        l = 0;
        while (true) {
            while (isMoreOrEquals(arrX, arrY)) {
                j = k;
                while (j > 0) {
                    arrX[j] -= arrY[j];
                    if (arrX[j] < 0) {
                        arrX[j] += 1000000;
                        arrX[j - 1] -= 1;
                    }
                    j--;
                }
                arrX[0] -= arrY[0];
                resultDecimal[i] += exp;
            }
            l++;
            if (l == point) {
                break;
            }
            if (exp == 1) {
                exp = 100000;
                i++;
                while (j < k) {
                    arrX[j] = arrX[j + 1];
                    arrY[j] = arrY[j + 1];
                    j++;
                }
                arrX[k] = 0;
                arrY[k] = 0;
            } else {
                exp /= 10;
            }
            j = k;
            while (j > 0) {
                arrY[j] = arrY[j] / 10 + (arrY[j - 1] % 10) * 100000;
                j--;
            }
            arrY[0] /= 10;
        }

        return new Real(resultInteger, resultDecimal, m.isNegative != n.isNegative);
    }

    public static Real divideInteger(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite && n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite) {
            return new Real("infinite");
        }
        if (n.isInfinite) {
            return new Real("zero");
        }
        if ((m.realInteger == null && m.realDecimal == null) && (n.realInteger == null && n.realDecimal == null)) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            return new Real("zero");
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return new Real("infinite");
        }

        /* STEP 1 : scaling */
        int commonLength, mLength, nLength, i, j, k, exp;
        int[] dividend, subtract, result;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }
        if (nLength > mLength) {
            return new Real("zero");
        }
        result = new int[mLength];

        i = 0;
        j = mLength;
        commonLength = mLength;

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }
        commonLength += Math.max(mLength, nLength);
        dividend = new int[commonLength];
        subtract = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, dividend, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, dividend, j, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, subtract, 0, n.realInteger.length);
            if (n.realDecimal != null) {
                System.arraycopy(n.realDecimal, 0, subtract, n.realInteger.length, n.realDecimal.length);
            }
        } else {
            System.arraycopy(n.realDecimal, 0, subtract, 0, n.realDecimal.length);
        }

        /* STEP 2 : n-synchronization */
        exp = 1;
        i = dividend[0];
        while (i > 0) {
            i /= 10;
            exp *= 10;
        }
        i = subtract[0];
        while (i > 0) {
            i /= 10;
            exp /= 10;
        }
        k = dividend.length - 1;
        if (exp > 1) {
            i = 0;
            j = 1000000 / exp;
            while (i < k) {
                subtract[i] = (subtract[i] * exp) + (subtract[i + 1] / j);
                subtract[i + 1] %= j;
                i++;
            }
            subtract[k] *= exp;
        }
        else {
            exp = 1;
        }

        /* STEP 3 : division to find real integer */
        i = 0;
        while (true) {
            while (isMoreOrEquals(dividend, subtract)) {
                j = k;
                while (j > 0) {
                    dividend[j] -= subtract[j];
                    if (dividend[j] < 0) {
                        dividend[j] += 1000000;
                        dividend[j - 1] -= 1;
                    }
                    j--;
                }
                dividend[0] -= subtract[0];
                result[i] += exp;
            }
            if (exp == 1) {
                exp = 100000;
                i++;
            } else {
                exp /= 10;
            }
            if (i == result.length) {
                break;
            }
            j = k;
            while (j > 0) {
                subtract[j] = subtract[j] / 10 + (subtract[j - 1] % 10) * 100000;
                j--;
            }
            subtract[0] /= 10;
        }

        return new Real(result, 0, m.isNegative != n.isNegative);
    }

    public static Real modulo(Real m, Real n) {
        if (m.isIndefinite || n.isIndefinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite && n.isInfinite) {
            return new Real("indefinite");
        }
        if (m.isInfinite) {
            return new Real("infinite");
        }
        if (n.isInfinite) {
            return new Real("zero");
        }
        if ((m.realInteger == null && m.realDecimal == null) && (n.realInteger == null && n.realDecimal == null)) {
            return new Real("indefinite");
        }
        if (m.realInteger == null && m.realDecimal == null) {
            return new Real("zero");
        }
        if (n.realInteger == null && n.realDecimal == null) {
            return new Real("infinite");
        }

        /* STEP 1 : scaling */
        int commonLength, mLength, nLength, i, j, k, exp, resultLength, encode;
        int[] dividend, subtract;

        mLength = nLength = 0;
        if (m.realInteger != null) {
            mLength = m.realInteger.length;
        }
        if (n.realInteger != null) {
            nLength = n.realInteger.length;
        }
        if (nLength > mLength) {
            return m;
        }
        resultLength = mLength;

        i = 0;
        j = mLength;
        commonLength = mLength;

        mLength = nLength = 0;
        if (m.realDecimal != null) {
            mLength = m.realDecimal.length;
        }
        if (n.realDecimal != null) {
            nLength = n.realDecimal.length;
        }
        encode = Math.max(mLength, nLength);
        commonLength += encode;
        dividend = new int[commonLength];
        subtract = new int[commonLength];

        if (m.realInteger != null) {
            System.arraycopy(m.realInteger, 0, dividend, i, m.realInteger.length);
        }
        if (m.realDecimal != null) {
            System.arraycopy(m.realDecimal, 0, dividend, j, m.realDecimal.length);
        }
        if (n.realInteger != null) {
            System.arraycopy(n.realInteger, 0, subtract, 0, n.realInteger.length);
            if (n.realDecimal != null) {
                System.arraycopy(n.realDecimal, 0, subtract, n.realInteger.length, n.realDecimal.length);
            }
        } else {
            System.arraycopy(n.realDecimal, 0, subtract, 0, n.realDecimal.length);
        }

        /* STEP 2 : n-synchronization */
        exp = 1;
        i = dividend[0];
        while (i > 0) {
            i /= 10;
            exp *= 10;
        }
        i = subtract[0];
        while (i > 0) {
            i /= 10;
            exp /= 10;
        }
        k = dividend.length - 1;
        if (exp > 1) {
            i = 0;
            j = 1000000 / exp;
            while (i < k) {
                subtract[i] = (subtract[i] * exp) + (subtract[i + 1] / j);
                subtract[i + 1] %= j;
                i++;
            }
            subtract[k] *= exp;
        }
        else {
            exp = 1;
        }

        /* STEP 3 : division to find real integer */
        i = 0;
        while (true) {
            while (isMoreOrEquals(dividend, subtract)) {
                j = k;
                while (j > 0) {
                    dividend[j] -= subtract[j];
                    if (dividend[j] < 0) {
                        dividend[j] += 1000000;
                        dividend[j - 1] -= 1;
                    }
                    j--;
                }
                dividend[0] -= subtract[0];
            }
            if (exp == 1) {
                exp = 100000;
                i++;
            } else {
                exp /= 10;
            }
            if (i == resultLength) {
                break;
            }
            j = k;
            while (j > 0) {
                subtract[j] = subtract[j] / 10 + (subtract[j - 1] % 10) * 100000;
                j--;
            }
            subtract[0] /= 10;
        }

        return new Real(dividend, encode, m.isNegative);
    }

    /* HELPING PURPOSED FUNCTIONS */

    public static boolean isMoreOrEquals(int[] m, int[] n) {
        boolean isMore = m.length > n.length;
        boolean isEquals = m.length == n.length;
        int i = 0;
        while (isEquals) {
            isMore = m[i] > n[i];
            isEquals = m[i] == n[i];
            i++;
            if (i == m.length) {
                break;
            }
        }
        return isMore || isEquals;
    }

    public static boolean isEquals(Real m, Real n) {
        int i, thisLength, nLength;
        if (m.realInteger == null) {
            thisLength = 0;
        }
        else {
            thisLength = m.realInteger.length;
        }

        if (n.realInteger == null) {
            nLength = 0;
        }
        else {
            nLength = n.realInteger.length;
        }

        boolean isEquals = thisLength == nLength;
        i = 0;
        while (isEquals) {
            if (i == nLength) {
                break;
            }
            isEquals = m.realInteger[i] == n.realInteger[i];
            i++;
        }
        i = 0;

        if (isEquals) {
            if (m.realDecimal == null) {
                thisLength = 0;
            }
            else {
                thisLength = m.realDecimal.length;
            }

            if (n.realDecimal == null) {
                nLength = 0;
            }
            else {
                nLength = n.realDecimal.length;
            }

            isEquals = thisLength == nLength;
            while (isEquals) {
                if (i == nLength) {
                    break;
                }
                isEquals = m.realDecimal[i] == n.realDecimal[i];
                i++;
            }
        }

        return isEquals;
    }

    public static int[] increaseFrontZero(int[] n, int increase) {
        int[] arr;
        if (increase > 0) {
            arr = new int[n.length + increase];
            System.arraycopy(n, 0, arr, increase, n.length);
        } else {
            arr = new int[n.length];
            System.arraycopy(n, 0, arr, 0, n.length);
        }
        return arr;
    }

    public static int[] increaseBackZero(int[] n, int increase) {
        int[] arr;
        if (increase > 0) {
            arr = new int[n.length + increase];
        } else {
            arr = new int[n.length];
        }
        System.arraycopy(n, 0, arr, 0, n.length);
        return arr;
    }

    public static int[] clearFrontZero(int[] n) {
        int i = 0;
        int n_last = n.length - 1;
        while (i < n_last) {
            if (n[i] != 0) {
                break;
            }
            i++;
        }
        int[] arr_return = new int[n.length - i];
        int j = 0;
        while (i <= n_last) {
            arr_return[j] = n[i];
            i++;
            j++;
        }
        return arr_return;
    }
}

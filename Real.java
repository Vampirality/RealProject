public class Real {
    /*---NUMBER---*/
    public int[] realInteger;
    public int[] realDecimal;
    public String fullNumber;

    /*---NUMBER PROPERTIES---*/
    public boolean isIndefinite;
    public boolean isInfinite;
    public boolean isNegative;

    Real(String num) {
        setNewValue(num);
    }

    Real(int[] arrInteger, int[] arrDecimal, boolean isNegative) {
        this.isNegative = isNegative;
        this.isInfinite = false;

        int i = 0;
        int j = arrInteger.length - 1;
        while (i < j) {
            if (arrInteger[i] != 0) {
                break;
            }
            i++;
        }

        j = arrDecimal.length - 1;
        while (j > 0) {
            if (arrDecimal[j] != 0) {
                break;
            }
            j--;
        }

        j++;
        this.realInteger = new int[arrInteger.length - i];
        System.arraycopy(arrInteger, i, this.realInteger, 0, this.realInteger.length);
        this.realDecimal = new int[j];
        System.arraycopy(arrDecimal, 0, this.realDecimal, 0, j);
    }

    Real(int[] n, int encode, boolean isNegative) {
        this.isIndefinite = this.isInfinite = false;
        int i = 0;
        int j, zero_back;
        while (i < n.length) {
            if (n[i] != 0) {
                break;
            }
            i++;
        }
        if (i == n.length) {
            return;
        }
        else {
            j = n.length - 1;
            while (j >= 0) {
                if (n[j] != 0) {
                    break;
                }
                j--;
            }
        }
        zero_back = n.length - j - 1;
        if (encode <= zero_back) {
            this.realInteger = new int[n.length - i - encode];
            System.arraycopy(n, i, this.realInteger, 0, j - i + 1);
        }
        else if (encode < n.length - zero_back) {
            this.realInteger = new int[n.length - encode - i];
            this.realDecimal = new int[encode - zero_back];
            System.arraycopy(n,i,this.realInteger,0,this.realInteger.length);
            System.arraycopy(n,i + this.realInteger.length, this.realDecimal, 0, this.realDecimal.length);
        }
        else {
            this.realDecimal = new int[encode - zero_back];
            System.arraycopy(n, i, this.realDecimal, encode - i - n.length, j - i + 1);
        }
        this.isNegative = isNegative;
    }

    public void setNewValue(String num) {
        this.fullNumber = num;
        switch (num) {
            case "indefinite" -> {
                this.isIndefinite = true;
                return;
            }
            case "infinity" -> {
                this.isIndefinite = false;
                this.isInfinite = true;
                return;
            }
            case "zero" -> {
                this.isIndefinite = false;
                this.isInfinite = false;
                this.fullNumber = "0";
                return;
            }
        }

        this.isNegative = num.charAt(0) == '-';

        boolean isZero = true;
        int point;
        int first_num;
        if (this.isNegative) {
            point = first_num = 1;
        } else {
            point = first_num = 0;
        }

        while (point < num.length()) {
            if (num.charAt(point) == '.') {
                break;
            }
            point++;
        }

        int i = point - 1;
        int p = (i - first_num) / 6;
        int[] arr = new int[p + 1];

        int exp = 1;
        while (i >= first_num) {
            if (exp == 1000000) {
                isZero = isZero && arr[p] == 0;
                p--;
                arr[p] += (int) num.charAt(i) - 48;
                exp = 10;
            } else {
                arr[p] += ((int) num.charAt(i) - 48) * exp;
                exp *= 10;
            }
            i--;
        }
        isZero = isZero && arr[0] == 0;

        if (!isZero) {
            this.realInteger = arr;
            isZero = true;
        }

        int j = point + 1;
        p = 0;
        int[] dec = new int[(num.length() - j - 1) / 6 + 1];

        exp = 100000;
        while (j < num.length()) {
            if (exp == 0) {
                isZero = isZero && dec[p] == 0;
                p++;
                dec[p] += ((int) num.charAt(j) - 48) * 100000;
                exp = 10000;
            } else {
                dec[p] += ((int) num.charAt(j) - 48) * exp;
                exp /= 10;
            }
            j++;
        }
        isZero = isZero && dec[p] == 0;
        if (!isZero) {
            this.realDecimal = dec;
        }
    }

    public String getFullNumber() {
        if (this.fullNumber == null) {
            String num;
            if (this.realInteger == null) {
                num = "0";
            }
            else {
                num = (String.format("%d", this.realInteger[0]));
                for (int i = 1; i < this.realInteger.length; i++) {
                    num = String.format(num + "%06d", this.realInteger[i]);
                }
            }

            if (this.realDecimal != null) {
                num = num + '.';
                int x = this.realDecimal.length - 1;
                for (int i = 0; i < x; i++) {
                    num = String.format(num + "%06d", this.realDecimal[i]);
                }
                String num_last = String.format("%06d", this.realDecimal[x]);
                x = 5;
                while (x >= 0) {
                    if (num_last.charAt(x) == '0') {
                        x--;
                    } else {
                        break;
                    }
                }
                num = num + num_last.substring(0, x + 1);
            }

            if (this.isNegative) {
                num = '-' + num;
            }
            this.fullNumber = num;
        }
        return this.fullNumber;
    }

    public void printlnFullNumber() {
        if (this.fullNumber == null) {
            String num;
            num = (String.format("%d", this.realInteger[0]));

            for (int i = 1; i < this.realInteger.length; i++) {
                num = String.format(num + "%06d", this.realInteger[i]);
            }

            if (this.realDecimal.length != 1 || realDecimal[0] != 0) {
                num = num + '.';
                int x = this.realDecimal.length - 1;
                for (int i = 0; i < x; i++) {
                    num = String.format(num + "%06d", this.realDecimal[i]);
                }
                String num_last = String.format("%06d", this.realDecimal[x]);
                x = 5;
                while (true) {
                    if (num_last.charAt(x) == '0') {
                        x--;
                    } else {
                        break;
                    }
                }
                num = num + num_last.substring(0, x + 1);
            }
            if (this.isNegative) {
                num = '-' + num;
            }
            this.fullNumber = num;
            System.out.println(num);
        }
        else {
            System.out.println(this.fullNumber);
        }
    }

    public void multiplyByOneNegative() {
        this.isNegative = !this.isNegative;
    }

}

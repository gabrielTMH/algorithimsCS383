import edu.princeton.cs.algs4.StdOut;


import java.util.ArrayList;

public class BigNum {

    private ArrayList<Integer> digits;

    static public void main(String[] unused){ }

    public BigNum(String num) {
        digits = new ArrayList<>();
        if (num.length() == 0){
            digits.add(0);
            return;
        }
        for (int i = 0; i < num.length(); ++i) {
            digits.add(i, Integer.parseInt(String.valueOf(num.charAt(i))));
        }
    }

    private BigNum() {
        digits = new ArrayList<>();
    }

    public String toString(){
        ArrayList<String> num = new ArrayList<>();
        for (int i = 0; i < digits.size(); ++i) {
            num.add(i, String.valueOf(digits.get(i)));
        }
        String number = String.join("", num);
        return number;
    }

    public BigNum plus(BigNum b) {
        BigNum r;
        int length = Math.min(this.digits.size(), b.digits.size()) - 1;
        int diff = Math.abs(this.digits.size() - b.digits.size());
        int temp;
        int carry = 0;
        int bigger;

        if (this.digits.size() > b.digits.size()) bigger = 1;
        else bigger = 0;

        if (bigger == 1) r = new BigNum(this.toString());
        else r = new BigNum(b.toString());

        for (int i = length; i >= 0; i--){
            if (bigger == 1) temp = this.digits.get(i + diff) + b.digits.get(i) + carry;
            else temp = this.digits.get(i) + b.digits.get(i + diff) + carry;

            if (temp >= 10) {
                carry = 1;
                r.digits.set(i + diff, temp - 10);
            }
            else {
                carry = 0;
                r.digits.set(i + diff, temp);
            }
        }

        for (int i = diff - 1; i >= 0; --i) {
            if (bigger == 1) temp = this.digits.get(i) + carry;
            else temp = b.digits.get(i) + carry;

            if (temp >= 10) {
                carry = 1;
                r.digits.set(i, temp - 10);
            }
            else {
                carry = 0;
                r.digits.set(i, temp);
            }
        }
        if (carry == 1) r.digits.add(0, carry);

        return r;
    }

    public BigNum times(BigNum b) {
        /*
            determine smaller & larger
            loop through smaller(for each?)
                loop through larger
                    multiply value in smaller one by every value in the larger one
                    add i*j%10 to value in current position
                    add carry
                    set carry to mult val/10
                add leftover carry to carry_end
                set carry to 0

            add carry end to beginning of r
         */
        BigNum r = new BigNum("");
        int temp;
        int carry = 0;

        int p = 0;
        for (int i = b.digits.size() - 1; i >= 0; --i) {
            BigNum r1 = new BigNum();

            // multiply r1 by 10^p
            for (int j = 0; j < p; ++j) r1.digits.add(0, 0);
            ++p;

            carry = 0;
            for (int j = this.digits.size() - 1; j >= 0; --j) {
                temp = b.digits.get(i) * this.digits.get(j) + carry;
                r1.digits.add(0, temp % 10);
                carry = temp / 10;
            }
            if (carry != 0) r1.digits.add(0, carry);
            r = r.plus(r1);
        }

        return r;
    }
}

import java.util.BitSet;

/**
 * Created by aj on 10/14/15.
 */
public class TestThisOut {
    public static void main(String[] args) {
        BitSet[] multiBitSet = new BitSet[2];


        multiBitSet[0] = BitSet.valueOf("A".getBytes());

        System.out.println(Integer.toBinaryString(65));

        System.out.println(multiBitSet[0].toString());
        System.out.println(multiBitSet[0].cardinality());

        multiBitSet[1] = BitSet.valueOf("X".getBytes());

        System.out.println(Integer.toBinaryString(88));

        System.out.println(multiBitSet[1].toString());
        System.out.println(multiBitSet[1].cardinality());

    }


}

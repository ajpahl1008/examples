import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;

public class IsoTransactionFactory {
    public static void main(String[] args) {
        IsoMessage isoMessage = new IsoMessage();

        IsoValue isoValue2 = new IsoValue(IsoType.LLVAR, "0000000000000000000000000");
        IsoValue isoValue4 = new IsoValue(IsoType.LLVAR, "0000000000000000000000000");
        isoMessage.setIsoHeader("DUDENESS");
        isoMessage.setField(2,isoValue2);
        isoMessage.setField(4,isoValue4);

        System.out.println(isoMessage.getIsoHeader());
        System.out.println(isoMessage.getField(2));
        System.out.println(isoMessage.getField(4));

    }
}

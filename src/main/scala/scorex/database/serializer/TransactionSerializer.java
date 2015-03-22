package scorex.database.serializer;

import org.mapdb.Serializer;
import scorex.transaction.Transaction;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public class TransactionSerializer implements Serializer<Transaction>, Serializable {
    private static final long serialVersionUID = -6538913048331349777L;

    @Override
    public void serialize(DataOutput out, Transaction value) throws IOException {
        out.writeInt(value.dataLength());
        out.write(value.toBytes());
    }

    @Override
    public Transaction deserialize(DataInput in, int available) throws IOException {
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        try {
            return Transaction.fromBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int fixedSize() {
        return -1;
    }
}
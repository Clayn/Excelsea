package de.clayntech.excelsea.io;

import de.clayntech.excelsea.log.ExcelseaLoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.DoubleConsumer;

/**
 * This class represents a copy process from a source to a destination.
 * The source and destination can be virtually everything as long it provides an
 * {@code InputStream} and an {@code OutputStream}.
 */
public class Copy {
    @SuppressWarnings("")
    private static final Logger LOG= ExcelseaLoggerFactory.getLogger();
    private Source from;
    private Destination to;
    private long size=-1;

    public Copy() {
        this(null,null);
    }

    public Copy(Source from, Destination to) {
        this.from = from;
        this.to = to;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    public Source getFrom() {
        return from;
    }

    public void setFrom(Source from) {
        this.from = from;
    }

    public Destination getTo() {
        return to;
    }

    public void setTo(Destination to) {
        this.to = to;
    }

    private double calculateProgress(long done) {
        if(size<=0) {
            return -1;
        }
        return done*1.0/size*1.0;
    }

    /**
     * Performs the copy process by reading all bytes from the source and writes it to
     * the destination. When a size for the source data is given the progress will be calculated,
     * otherwise it will always be {@code -1}.
     * @param progressConsumer the consumer that will get the calculated progress during the copy process.
     * @throws IOException if an IOException occurs while reading or writing.
     */
    public void perform(DoubleConsumer progressConsumer) throws IOException {
        if(from==null||to==null) {
            return;
        }
        if(from==to||from.equals(to)) {
            return;
        }
        byte[] buffer=new byte[256];
        long done=0;
        try(InputStream in=from.openRead(); OutputStream out=to.openWrite()) {
            int read;
            while((read=in.read(buffer))!=-1) {
                done+=read;
                out.write(buffer,0,read);
                if(progressConsumer!=null) {
                    progressConsumer.accept(calculateProgress(done));
                }
            }
            out.flush();
        }
    }

    public void perform() throws IOException {
        perform(null);
    }
}

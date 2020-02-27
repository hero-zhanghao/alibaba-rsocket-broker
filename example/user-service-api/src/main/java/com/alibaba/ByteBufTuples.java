package com.alibaba;

import io.netty.buffer.ByteBuf;
import reactor.util.function.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * ByteBuf Tuples
 *
 * @author leijuan
 */
@SuppressWarnings("unchecked")
public class ByteBufTuples {
    public static Map<Class<?>, ByteBufValueReader<?>> READERS = new HashMap<>();

    static {
        READERS.put(Byte.class, ByteBuf::readByte);
        READERS.put(Integer.class, ByteBuf::readInt);
        READERS.put(Long.class, ByteBuf::readLong);
        READERS.put(Double.class, ByteBuf::readDouble);
        READERS.put(Boolean.class, buf -> buf.readByte() == 1);
        READERS.put(String.class, buf -> {
            int len = buf.readInt();
            if (len == 0) {
                return "";
            } else {
                return buf.readCharSequence(len, StandardCharsets.UTF_8).toString();
            }
        });
    }

    public static <T1, T2> Tuple2<T1, T2> of(ByteBuf buf, Class<T1> t1, Class<T2> t2) {
        return (Tuple2<T1, T2>) Tuples.of(read(buf, t1), read(buf, t2));
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(ByteBuf buf, Class<T1> t1, Class<T2> t2, Class<T3> t3) {
        return (Tuple3<T1, T2, T3>) Tuples.of(read(buf, t1), read(buf, t2), read(buf, t3));
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(ByteBuf buf, Class<T1> t1, Class<T2> t2, Class<T3> t3, Class<T4> t4) {
        return (Tuple4<T1, T2, T3, T4>) Tuples.of(read(buf, t1), read(buf, t2), read(buf, t3), read(buf, t4));
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> of(
            ByteBuf buf,
            Class<T1> t1,
            Class<T2> t2,
            Class<T3> t3,
            Class<T4> t4,
            Class<T5> t5) {
        return (Tuple5<T1, T2, T3, T4, T5>) Tuples.of(read(buf, t1), read(buf, t2), read(buf, t3), read(buf, t4), read(buf, t5));
    }


    public static <T extends Class<?>> Object read(ByteBuf buf, T type) {
        return READERS.get(type).read(buf);
    }

    @FunctionalInterface
    public interface ByteBufValueReader<T> {
        T read(ByteBuf buf);
    }
}
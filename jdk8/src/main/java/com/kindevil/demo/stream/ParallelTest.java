package com.kindevil.demo.stream;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import com.google.common.collect.Lists;

/**
 * @author zhangxin.zhang created on 16-5-10.
 */
public class ParallelTest {
    public static final List<Integer> resutl = Collections.synchronizedList(Lists.newArrayList());

    public static void main(String[] args) {
        System.out.println(resutl);
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream().collect(new Collector<Integer, List<Integer>, List<Integer>>() {

                    @Override
                    public Supplier<List<Integer>> supplier() {
                        System.out.println("supplier");
                        return (Supplier<List<Integer>>) new Supplier<List<Integer>>() {
                            @Override
                            public List<Integer> get() {
                                return resutl;
                            }
                        };
                    }

                    @Override
                    public BiConsumer<List<Integer>, Integer> accumulator() {
                        return new BiConsumer<List<Integer>, Integer>() {
                            @Override
                            public void accept(List<Integer> integers, Integer e) {
                                resutl.add(e);
                            }
                        };
                    }

                    @Override
                    public BinaryOperator<List<Integer>> combiner() {
                        System.out.println("combiner");
                        return (left, right) -> resutl;
                    }

                    @Override
                    public Function<List<Integer>, List<Integer>> finisher() {
                        System.out.println("finisher");
                        return list -> list;
                    }

                    @Override
                    public Set<Characteristics> characteristics() {
                        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.CONCURRENT,
                                Collector.Characteristics.UNORDERED,
                                Collector.Characteristics.IDENTITY_FINISH));
                    }
                });
        System.out.println(resutl);
    }
}

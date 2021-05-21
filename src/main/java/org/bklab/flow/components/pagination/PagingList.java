/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-05-17 14:40:57
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.components.pagination.PagingList
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.components.pagination;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PagingList<T> implements Function<Integer, List<T>> {

    private List<T> instance = new ArrayList<>();
    private int singlePageSize = 1024;

    private int lastPageNo = 1;
    private int lastSinglePageSize = 1024;

    public PagingList(Collection<T> instance, int singlePageSize) {
        this.instance = instance == null ? new ArrayList<>(this.instance) : new ArrayList<>(instance);
        this.singlePageSize = singlePageSize == 0 ? this.singlePageSize : singlePageSize;
    }

    @SafeVarargs
    public PagingList(int singlePageSize, T... entities) {
        this.instance = entities == null ? this.instance : Arrays.asList(entities);
        this.singlePageSize = singlePageSize == 0 ? this.singlePageSize : singlePageSize;
    }

    public PagingList<T> reverse() {
        instance = instance.stream().collect(ArrayList::new, (a, b) -> a.add(0, b), (a, b) -> a.addAll(0, b));
        return this;
    }

    public PagingList<T> sorted(Comparator<? super T> comparator) {
        instance = instance.stream().sorted(comparator).collect(Collectors.toList());
        return this;
    }

    public int inPage(T t) {
        int n = instance.indexOf(t);
        return n < 0 ? n : n / singlePageSize + 1;
    }

    public int length() {
        return Double.valueOf(Math.ceil(1.0 * instance.size() / singlePageSize)).intValue();
    }

    public int dataLength() {
        return instance.size();
    }

    public PagingList<T> setSinglePageSize(int singlePageSize) {
        this.singlePageSize = singlePageSize > 0 ? singlePageSize : this.singlePageSize;
        return this;
    }

    @Override
    public List<T> apply(Integer pageNo) {
        this.lastPageNo = pageNo;
        return instance.stream()
                .skip((Math.max(Math.min(pageNo == null ? 1 : pageNo, length()), 1) - 1) * singlePageSize)
                .limit(singlePageSize).collect(Collectors.toList());
    }

    public List<T> apply(Integer pageNo, Integer singlePageSize) {
        singlePageSize = singlePageSize == 0 ? this.singlePageSize : singlePageSize;
        this.lastSinglePageSize = singlePageSize;
        return instance.stream()
                .skip((Math.max(Math.min(pageNo == null ? 1 : pageNo, length()), 1) - 1) * singlePageSize)
                .limit(singlePageSize).collect(Collectors.toList());
    }

    public List<T> apply() {
        return apply(lastPageNo, lastSinglePageSize);
    }

    public PagingList<T> update(Collection<T> instance) {
        this.instance = instance == null ? this.instance : new ArrayList<>(instance);
        return this;
    }

    public PagingList<T> update(Collection<T> instance, Integer singlePageSize) {
        this.instance = instance == null ? this.instance : new ArrayList<>(instance);
        this.singlePageSize = singlePageSize == 0 ? this.singlePageSize : singlePageSize;
        return this;
    }

    public List<List<T>> split() {
        return IntStream.range(1, length() + 1).mapToObj(this::apply).collect(Collectors.toList());
    }

    public PagingList<T> forEach(Consumer<List<T>> consumer) {
        split().forEach(consumer);
        return this;
    }

    public List<T> getInstance() {
        return instance;
    }
}

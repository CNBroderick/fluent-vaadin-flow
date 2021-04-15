/*
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 * Author: Broderick Johansson
 * E-mail: z@bkLab.org
 * Modify date：2021-04-13 15:36:55
 * _____________________________
 * Project name: fluent-vaadin-flow
 * Class name：org.bklab.flow.util.dialog.ErrorMessageDialogBuilder
 * Copyright (c) 2008 - 2021. - Broderick Labs.
 */

package org.bklab.flow.util.dialog;

import org.bklab.flow.dialog.ErrorDialog;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ErrorMessageDialogBuilder implements Collector<String, ErrorMessageDialogBuilder, ErrorMessageDialogBuilder> {

    private final List<String> messages = new ArrayList<>();
    private final List<NoErrorListener> noErrorListeners = new ArrayList<>();
    private final List<HasErrorListener> hasErrorListeners = new ArrayList<>();

    public ErrorMessageDialogBuilder add(String message) {
        this.messages.add(message);
        List<String> collect = Stream.of("").collect(Collectors.toList());
        return this;
    }

    public ErrorMessageDialogBuilder add(boolean add, String message) {
        return add ? add(message) : this;
    }

    public ErrorMessageDialogBuilder add(String... messages) {
        this.messages.addAll(Arrays.asList(messages));
        return this;
    }

    public ErrorMessageDialogBuilder add(Collection<String> messages) {
        this.messages.addAll(messages);
        return this;
    }

    public ErrorMessageDialogBuilder add(boolean add, String... messages) {
        return add ? add(messages) : this;
    }

    public <E> ErrorMessageDialogBuilder add(Predicate<E> predicate, E test, String... messages) {
        return predicate.test(test) ? add(messages) : this;
    }

    public boolean isValid() {
        return messages.isEmpty();
    }

    public ErrorMessageDialogBuilder whenNoError(NoErrorListener noErrorListener) {
        if (noErrorListener != null) this.noErrorListeners.add(noErrorListener);
        return this;
    }

    public ErrorMessageDialogBuilder whenHasError(HasErrorListener hasErrorListener) {
        if (hasErrorListener != null) this.hasErrorListeners.add(hasErrorListener);
        return this;
    }

    public boolean computeAndOpen() {
        return computeAndOpen(null);
    }

    public boolean computeAndOpen(String header) {
        if (messages.isEmpty()) {
            noErrorListeners.forEach(NoErrorListener::onNoErrors);
            return true;
        }
        hasErrorListeners.forEach(a -> a.onHasErrors(this));
        ErrorDialog errorDialog = new ErrorDialog(messages.size() <= 1 ? messages : IntStream.range(0, messages.size())
                .mapToObj(i -> (i + 1) + ". " + messages.get(i)).collect(Collectors.toList()));
        if (header != null && !header.isBlank()) {
            errorDialog.header(header);
        }
        errorDialog.build().open();
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }

    @Override
    public Supplier<ErrorMessageDialogBuilder> supplier() {
        return () -> this;
    }

    @Override
    public BiConsumer<ErrorMessageDialogBuilder, String> accumulator() {
        return ErrorMessageDialogBuilder::add;
    }

    @Override
    public BinaryOperator<ErrorMessageDialogBuilder> combiner() {
        return (left, right) -> left.add(right.messages);
    }

    @Override
    public Function<ErrorMessageDialogBuilder, ErrorMessageDialogBuilder> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }

    public interface NoErrorListener {
        void onNoErrors();
    }

    public interface HasErrorListener {
        void onHasErrors(ErrorMessageDialogBuilder builder);
    }
}

package org.bklab.flow.util.dialog;

import org.bklab.flow.dialog.ErrorDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ErrorMessageDialogBuilder {

    private final List<String> messages = new ArrayList<>();

    public ErrorMessageDialogBuilder add(String message) {
        this.messages.add(message);
        return this;
    }

    public ErrorMessageDialogBuilder add(boolean add, String message) {
        return add ? add(message) : this;
    }

    public ErrorMessageDialogBuilder add(String... messages) {
        this.messages.addAll(Arrays.asList(messages));
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

    public boolean computeAndOpen() {
        if (messages.isEmpty()) return true;
        new ErrorDialog().message(IntStream.range(0, messages.size())
                .mapToObj(i -> (i + 1) + ". " + messages.get(i))
                .collect(Collectors.toList())).build().open();
        return false;
    }

    public List<String> getMessages() {
        return messages;
    }
}
